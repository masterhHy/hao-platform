package com.hao.common.annotation;

import com.hao.common.annotation.configure.OAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用该注解标注Spring Boot启动类后，就可以使用Feign访问受资源服务器保护的资源了
 *
 * 注入自定义request拦截 在head 注入资源服务器的密钥
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OAuth2FeignConfigure.class)
public @interface EnableOauth2FeignClient {
}
