package com.coderpeng.controller;

import com.coderpeng.api.Code;
import com.coderpeng.api.Result;
import com.coderpeng.entity.Menu;
import com.coderpeng.service.IMenuService;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService iMenuService;

    /**
     * 获取用户菜单列表
     *
     * @param id 用户id
     */
    @GetMapping("/user/{id}")
    public Result getMenuListByUserId(@PathVariable Long id) {
        List<Menu> list = iMenuService.getMenuListByUserId(id);
        return new Result(list != null ? Code.GET_OK : Code.GET_ERR, list, list != null ? "获取菜单列表成功~" : "获取菜单列表失败!");
    }
}
