package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.UserDao;
import com.coderpeng.entity.PageInfo;
import com.coderpeng.entity.PageResult;
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
    @Override
    public PageResult findAll(PageInfo pageInfo, User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(user.getUsername() != null, User::getUsername, user.getUsername())
                .like(user.getMobile() != null, User::getMobile, user.getMobile())
                .ge(user.getStartTime() != null, User::getCreateTime, user.getStartTime())
                .le(user.getEndTime() != null, User::getCreateTime, user.getEndTime());

        Page<User> userPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        baseMapper.selectPage(userPage, wrapper);
        return new PageResult() {{
            setCurrent(userPage.getCurrent());
            setSize(userPage.getSize());
            setTotal(userPage.getTotal());
            setList(userPage.getRecords());
        }};
    }
}
