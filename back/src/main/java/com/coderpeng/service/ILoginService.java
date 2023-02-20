package com.coderpeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderpeng.entity.User;

import java.util.Map;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:43
 * desc:
 */
public interface ILoginService extends IService<User> {
    Map<String, Object> userLogin(User user);
}
