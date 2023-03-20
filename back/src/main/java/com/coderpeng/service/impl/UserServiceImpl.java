package com.coderpeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpeng.dao.UserDao;
import com.coderpeng.entity.PageInfo;
import com.coderpeng.entity.PageResult;
import com.coderpeng.entity.User;
import com.coderpeng.service.IUserService;
import com.coderpeng.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:44
 * desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Autowired
    private PageUtils pageUtils;

    @Override
    public PageResult findAll(PageInfo pageInfo, User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(user.getUsername() != null, User::getUsername, user.getUsername())
                .like(user.getMobile() != null, User::getMobile, user.getMobile())
                .ge(user.getStartTime() != null, User::getCreateTime, user.getStartTime())
                .le(user.getEndTime() != null, User::getCreateTime, user.getEndTime());

        return pageUtils.getPageResult(pageInfo, baseMapper, wrapper);
    }

    @Override
    public Boolean saveUser(User user) {
        user.setCreateTime(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int insert = baseMapper.insert(user);
        return insert > 0;
    }

    @Override
    public Boolean updateUserById(User user) {
        String password = user.getPassword();
        if (password != null) user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int i = baseMapper.updateById(user);
        return i > 0;
    }
}
