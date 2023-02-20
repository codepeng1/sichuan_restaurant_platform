package com.coderpeng.controller;

import com.coderpeng.api.Code;
import com.coderpeng.api.Result;
import com.coderpeng.entity.User;
import com.coderpeng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:55
 * desc:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 新增用户
     */
    @PostMapping
    public Result saveUser(@RequestBody User user) {
        boolean b = iUserService.save(user);
        return new Result(b ? Code.SAVE_OK : Code.SAVE_ERR, b, b ? "用户新增成功~" : "用户新增失败!");
    }

    /**
     * 通过id获取用户信息
     * @param id 用户id
     * @return User
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        User user = iUserService.getById(id);
        return new Result(user != null ? Code.GET_OK : Code.GET_ERR, user, user != null ? "获取用户信息成功~" : "获取用户信息失败!");
    }

}
