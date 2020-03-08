package com.hao.common.annotation.handler;

import com.hao.common.utils.WebUtils;
import com.hao.common.utils.vo.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.access.AccessDeniedException e) throws IOException, ServletException {
        WebUtils.makeResponse(
                httpServletResponse, MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_FORBIDDEN, ResponseData.fail().message("没有权限访问该资源"));
    }
}
