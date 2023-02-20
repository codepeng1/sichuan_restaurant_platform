package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.UserDao;
import com.coderpeng.entity.User;
import com.coderpeng.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:44
 * desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
}
