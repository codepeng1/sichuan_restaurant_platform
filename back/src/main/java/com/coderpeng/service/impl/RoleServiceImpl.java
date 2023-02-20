package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.RoleDao;
import com.coderpeng.entity.Role;
import com.coderpeng.entity.UserRole;
import com.coderpeng.service.IRoleService;
import com.coderpeng.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:09
 * desc:
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

    @Autowired
    private IUserRoleService iUserRoleService;

    /**
     * 通过id获取用户角色列表
     */
    public List<Role> getRoleListByUserId(Long id) {
        // 1.获取该用户的角色列表id集合
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(UserRole::getUserId, id);
        List<UserRole> userRoleList = iUserRoleService.list(userRoleLambdaQueryWrapper);

        // 没有角色
        if (userRoleList.isEmpty()) return new ArrayList<>();

        // 2.通过id集合返回角色列表信息
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).toList();
        return baseMapper.selectBatchIds(roleIdList);
    }
}
