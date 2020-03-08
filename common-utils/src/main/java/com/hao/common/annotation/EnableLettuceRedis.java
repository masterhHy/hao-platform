package com.hao.common.annotation;

import com.hao.common.annotation.configure.LettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * 注入 redis 需要的Template 模板实例
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LettuceRedisConfigure.class)
public @interface EnableLettuceRedis {
}
