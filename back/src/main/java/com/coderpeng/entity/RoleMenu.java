package com.coderpeng.entity;

import lombok.Data;

import java.util.Date;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:00
 * desc: 角色-菜单关系类
 */
@Data
public class RoleMenu {
    private Long roleId;
    private Long menuId;
    private Date createTime;
}
