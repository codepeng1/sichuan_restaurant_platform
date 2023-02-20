package com.coderpeng.entity;

import lombok.Data;

import java.util.Date;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:00
 * desc: 用户-角色关系类
 */
@Data
public class UserRole {
    private Long userId;
    private Long roleId;
    private Date createTime;
}
