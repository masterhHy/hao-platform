package com.hao.common.annotation.configure;

import com.google.common.net.HttpHeaders;
import com.hao.common.constant.SystemConstant;
import com.hao.common.utils.WebUtils;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Base64Utils;

/**
 * OAuth2 Feign配置
 *
 */
public class OAuth2FeignConfigure {

	@Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 请求头中添加 Gateway Token
            String zuulToken = new String(Base64Utils.encode(SystemConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(SystemConstant.GATEWAY_TOKEN_HEADER, zuulToken);
            // 请求头中添加原请求头中的 Token
            String authorizationToken = WebUtils.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, SystemConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }
}
