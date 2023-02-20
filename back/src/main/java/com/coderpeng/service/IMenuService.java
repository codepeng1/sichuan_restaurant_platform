package com.coderpeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderpeng.entity.Menu;

import java.util.List;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:07
 * desc:
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenuListByUserId(Long id);
}
