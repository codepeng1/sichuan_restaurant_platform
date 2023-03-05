package com.coderpeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderpeng.entity.PageInfo;
import com.coderpeng.entity.PageResult;
import com.coderpeng.entity.User;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:43
 * desc:
 */
public interface IUserService extends IService<User> {
    PageResult findAll(PageInfo pageInfo, User user);
}
