package com.coderpeng.controller;

import com.coderpeng.api.Code;
import com.coderpeng.api.Result;
import com.coderpeng.entity.PageInfo;
import com.coderpeng.entity.PageResult;
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
        boolean b = iUserService.saveUser(user);
        return new Result(b ? Code.SAVE_OK : Code.SAVE_ERR, b, b ? "用户新增成功~" : "用户新增失败!");
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        boolean b = iUserService.removeById(id);
        return new Result(b ? Code.DELETE_OK : Code.DELETE_ERR, b, b ? "用户删除成功~" : "用户删除失败!");
    }

    /**
     * 修改用户
     */
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        boolean b = iUserService.updateUserById(user);
        return new Result(b ? Code.UPDATE_OK : Code.UPDATE_ERR, b, b ? "用户修改成功~" : "用户修改失败!");
    }

    /**
     * 获取所有用户列表
     */
    @PostMapping("/list")
    public Result findAll(Long current, Long size, @RequestBody User user) {
        PageResult pageResult = iUserService.findAll(new PageInfo(current, size), user);
        boolean b = pageResult.getList() != null;
        return new Result(b ? Code.GET_OK : Code.GET_ERR, pageResult, b ? "获取用户列表成功~" : "获取用户列表失败!");
    }

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        User user = iUserService.getById(id);
        return new Result(user != null ? Code.GET_OK : Code.GET_ERR, user, user != null ? "获取用户信息成功~" : "获取用户信息失败!");
    }

}
