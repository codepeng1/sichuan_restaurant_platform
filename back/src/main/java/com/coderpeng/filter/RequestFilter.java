package com.coderpeng.filter;

import com.alibaba.fastjson.JSONObject;
import com.coderpeng.utils.JwtUitls;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * author: CoderPeng
 * Date: 2023/2/14 9:28
 * desc:
 */
@WebFilter
public class RequestFilter implements Filter {

    @Autowired
    public JwtUitls jwtUitls;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if (url != null) {
            //登录请求直接放行
            if ("/login".equals(url)) {
                filterChain.doFilter(servletRequest, resp);
                return;
            } else {
                //其他请求验证token
                String token = null;
                String authorization = ((HttpServletRequest) servletRequest).getHeader("Authorization");
                if (authorization != null) {
                    token = authorization.split("Bearer ")[1];
                }
                if (StringUtils.isNotBlank(token)) {
                    //token验证结果
                    int verify = jwtUitls.verify(token);
                    if (verify == 1) {
                        //验证成功，放行
                        filterChain.doFilter(servletRequest, resp);
                        return;
                    } else {
                        //验证失败
                        if (verify == 2) {
                            map.put("errorMsg", "登录已过期，请重新登录!");
                            resp.setStatus(401);
                        } else if (verify == 0) {
                            map.put("errorMsg", "用户信息验证失败!");
                            resp.setStatus(401);
                        }
                    }
                } else {
                    //token为空的返回
                    map.put("errorMsg", "身份验证失败!");
                    resp.setStatus(401);
                }
            }
            JSONObject jsonObject = new JSONObject(map);
            resp.setContentType("application/json");
            //设置响应的编码
            resp.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw = resp.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        }
    }
}
