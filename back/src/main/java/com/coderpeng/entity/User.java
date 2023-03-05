package com.coderpeng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * author: CoderPeng
 * Date: 2023/1/31 16:43
 * desc: 用户类
 */
@Data
public class User {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @TableField(select = false)
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信号
     */
    private String wx;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除标记
     */
    @TableField(select = false)
    private Integer deleted;

    /**
     * 开始时间
     */
    @TableField(exist = false, select = false)
    private Date startTime;
    /**
     * 截止时间
     */
    @TableField(exist = false, select = false)
    private Date endTime;
}
