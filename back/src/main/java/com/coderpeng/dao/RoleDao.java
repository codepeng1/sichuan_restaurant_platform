package com.coderpeng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderpeng.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:43
 * desc:
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {
}
