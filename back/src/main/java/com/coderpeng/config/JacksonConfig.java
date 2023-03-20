package com.coderpeng.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * author: CoderPeng
 * Date: 2023/3/20 16:16
 * desc:
 */
@Configuration
public class JacksonConfig {
    /**
     * Jackson全局转化long类型为String，解决jackson序列化时传入前端Long类型缺失精度问题
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer cunstomizer = new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                //变成字符串
                jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            }
        };
        return cunstomizer;
    }
}
