package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.UserRoleDao;
import com.coderpeng.entity.UserRole;
import com.coderpeng.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:09
 * desc:
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements IUserRoleService {
}
