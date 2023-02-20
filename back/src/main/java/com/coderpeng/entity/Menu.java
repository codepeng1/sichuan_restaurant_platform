package com.coderpeng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * author: CoderPeng
 * Date: 2023/2/17 16:23
 * desc: 菜单类
 */
@Data
public class Menu {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private String code;
    private Integer level;
    private Long parentId;
    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<Menu> children;
    /**
     * 是否末子节点
     */
    @TableField(exist = false)
    private Boolean isLast;
}
