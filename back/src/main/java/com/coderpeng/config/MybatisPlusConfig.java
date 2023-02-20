package com.coderpeng.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: CoderPeng
 * Date: 2023/1/29 12:41
 * desc: Mp拦截器
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mpInterceptor() {
        // 1.创建一个Mp拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 2.添加分页拦截
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 3.添加乐观锁拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
