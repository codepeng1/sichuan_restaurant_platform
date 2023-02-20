package com.coderpeng.controller;

import com.coderpeng.api.Code;
import com.coderpeng.api.Result;
import com.coderpeng.entity.User;
import com.coderpeng.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * author: CoderPeng
 * Date: 2023/2/4 21:38
 * desc:
 */
@RestController
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Map<String, Object> map = iLoginService.userLogin(user);
        String msg = map != null ? "登录成功~" : "用户名或密码错误!";
        return new Result(map != null ? Code.SAVE_OK : Code.SAVE_ERR, map, msg);
    }
}
