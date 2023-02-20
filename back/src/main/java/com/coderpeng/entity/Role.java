package com.coderpeng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * author: CoderPeng
 * Date: 2023/2/17 14:00
 * desc: 角色类
 */
@Data
public class Role {
    private Long id;
    private String roleName;
    private String description;
    private Date createTime;
    private Integer status;
}
