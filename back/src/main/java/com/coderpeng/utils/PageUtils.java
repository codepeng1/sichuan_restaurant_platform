package com.coderpeng.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderpeng.entity.PageInfo;
import com.coderpeng.entity.PageResult;
import org.springframework.stereotype.Component;

/**
 * author: CoderPeng
 * Date: 2023/3/6 11:05
 * desc: 分页工具类
 */
@Component
public class PageUtils {

    /**
     * 获取分页后的结果集
     *
     * @param pageInfo   分页参数
     * @param baseMapper 查询接口
     * @param wrapper    查询条件
     * @param <T>        类型
     */
    public <T> PageResult getPageResult(PageInfo pageInfo, BaseMapper<T> baseMapper, Wrapper<T> wrapper) {
        Page<T> page = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        baseMapper.selectPage(page, wrapper);
        return new PageResult() {{
            setCurrent(page.getCurrent());
            setSize(page.getSize());
            setTotal(page.getTotal());
            setList(page.getRecords());
        }};
    }
}
