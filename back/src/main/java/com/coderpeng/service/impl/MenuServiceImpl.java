package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.MenuDao;
import com.coderpeng.entity.Menu;
import com.coderpeng.entity.Role;
import com.coderpeng.entity.RoleMenu;
import com.coderpeng.service.IMenuService;
import com.coderpeng.service.IRoleMenuService;
import com.coderpeng.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:09
 * desc:
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {

    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IRoleMenuService iRoleMenuService;

    /**
     * 通过userId获取用户菜单列表
     */
    public List<Menu> getMenuListByUserId(Long id) {
        // 1.获取该用户的角色列表
        List<Role> roleList = iRoleService.getRoleListByUserId(id);
        // 2.遍历角色列表获取去重后的角色菜单关系列表
        Set<RoleMenu> roleMenus = new HashSet<>();
        for (Role role : roleList) {
            LambdaQueryWrapper<RoleMenu> roleMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            roleMenuLambdaQueryWrapper.eq(RoleMenu::getRoleId, role.getId());
            List<RoleMenu> menuList = iRoleMenuService.list(roleMenuLambdaQueryWrapper);
            roleMenus.addAll(menuList);
        }

        // 没有菜单
        if (roleMenus.isEmpty()) return new ArrayList<>();

        // 3.通过id集合获取菜单头结点列表
        List<Long> menuIdList = roleMenus.stream().map(RoleMenu::getMenuId).toList();
        List<Menu> menuList = baseMapper.selectBatchIds(menuIdList);
        return headListToTree(menuList, baseMapper.selectList(null));
    }

    /**
     * 获取子节点
     */
    public List<Menu> getChildren(Menu menu, List<Menu> allList) {
        return allList.stream().filter(m -> menu.getId().equals(m.getParentId())).toList();
    }

    /**
     * 头结点列表转树表
     */
    public List<Menu> headListToTree(List<Menu> list, List<Menu> allList) {
        for (Menu menu : list) {
            List<Menu> children = getChildren(menu, allList);
            // 末子节点
            if(children.isEmpty()) {
                menu.setIsLast(true);
            } else {
                menu.setChildren(children);
                headListToTree(children, allList);
            }
        }
        return list;
    }
}
