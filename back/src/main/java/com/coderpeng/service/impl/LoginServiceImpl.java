package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.UserDao;
import com.coderpeng.entity.User;
import com.coderpeng.service.ILoginService;
import com.coderpeng.utils.JwtUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:44
 * desc:
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserDao, User> implements ILoginService {

    @Autowired
    public JwtUitls jwtUitls;

    /**
     * 用户登录
     */
    public Map<String, Object> userLogin(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        User user1 = baseMapper.selectOne(userLambdaQueryWrapper);

        // 没有找到该用户
        if (user1 == null) return null;

        // 登录成功
        String token = jwtUitls.createToken(user1.getId().toString(), user1.getUsername());

        return new HashMap<>() {{
            put("id", user1.getId());
            put("name", user1.getUsername());
            put("token", token);
        }};
    }
}
