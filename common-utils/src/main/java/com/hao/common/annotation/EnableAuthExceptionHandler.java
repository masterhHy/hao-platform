package com.hao.common.annotation;

import com.hao.common.annotation.configure.AuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 注入资源服务器 -->自定义异常拦截实例
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthExceptionConfigure.class)
public @interface EnableAuthExceptionHandler {
}
