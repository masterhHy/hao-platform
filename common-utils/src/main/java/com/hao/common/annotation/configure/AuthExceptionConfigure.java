package com.hao.common.annotation.configure;

import com.hao.common.annotation.handler.AuthAccessDeniedHandler;
import com.hao.common.annotation.handler.AuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 授权异常配置类
 */
public class AuthExceptionConfigure {

	@Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public AccessDeniedHandler accessDeniedHandler() {
        return new AuthAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public AuthExceptionEntryPoint authenticationEntryPoint() {
        return new AuthExceptionEntryPoint();
    }
}
