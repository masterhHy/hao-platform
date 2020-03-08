package com.hao.common.annotation;

import com.hao.common.annotation.configure.ServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用该注解标注Spring Boot启动类后，客户端请求将无法直接访问微服务，只能通过微服务网关访问
 *
 * 注入一个拦截器 拦截所有request请求，没有head 密钥则当作外界请求 非内部
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerProtectConfigure.class)
public @interface EnableServerProtect {
}
