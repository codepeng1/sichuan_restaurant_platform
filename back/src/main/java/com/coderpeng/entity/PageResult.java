package com.coderpeng.entity;

import lombok.Data;

import java.util.List;

/**
 * author: CoderPeng
 * Date: 2023/3/5 19:49
 * desc:
 */
@Data
public class PageResult {
    /**
     * 当前页码
     */
    private Long current;
    /**
     * 条数
     */
    private Long size;
    /**
     * 总数
     */
    private Long total;
    /**
     * 列表数据
     */
    private List<?> list;
}
