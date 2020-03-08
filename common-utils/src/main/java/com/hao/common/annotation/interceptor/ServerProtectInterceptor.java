package com.hao.common.annotation.interceptor;

import com.hao.common.constant.SystemConstant;
import com.hao.common.utils.WebUtils;
import com.hao.common.utils.vo.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
    	// 从请求头中获取 Gateway Token
        String token = request.getHeader(SystemConstant.GATEWAY_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(SystemConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验 Gateway Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {

        	WebUtils.makeResponse(response,MediaType.APPLICATION_JSON_VALUE,
                    HttpServletResponse.SC_FORBIDDEN, ResponseData.fail().message("请通过网关获取资源"));
            return false;
        }
    }
}
