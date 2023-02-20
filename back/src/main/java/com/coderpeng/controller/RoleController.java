package com.coderpeng.controller;

import com.coderpeng.api.Code;
import com.coderpeng.api.Result;
import com.coderpeng.entity.Role;
import com.coderpeng.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: CoderPeng
 * Date: 2023/2/4 21:38
 * desc:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    /**
     * 获取全部角色列表
     */
    @GetMapping
    public Result getAll() {
        List<Role> list = iRoleService.list();
        return new Result(list != null ? Code.GET_OK : Code.GET_ERR, list, list != null ? "获取角色列表成功~" : "获取角色列表失败!");
    }

    /**
     * 获取用户角色列表
     *
     * @param id 用户id
     */
    @GetMapping("/user/{id}")
    public Result getRoleListByUserId(@PathVariable Long id) {
        List<Role> list = iRoleService.getRoleListByUserId(id);
        return new Result(list != null ? Code.GET_OK : Code.GET_ERR, list, list != null ? "获取角色列表成功~" : "获取角色列表失败!");
    }
}
