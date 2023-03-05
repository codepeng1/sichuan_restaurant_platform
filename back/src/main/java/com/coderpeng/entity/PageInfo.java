package com.coderpeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: CoderPeng
 * Date: 2023/3/5 22:21
 * desc:
 */
@Data
@AllArgsConstructor
public class PageInfo {
    private Long current;
    private Long size;
}
