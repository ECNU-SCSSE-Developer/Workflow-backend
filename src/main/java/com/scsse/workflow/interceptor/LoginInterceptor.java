package com.scsse.workflow.interceptor;

import com.scsse.workflow.util.mvc.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.scsse.workflow.constant.ErrorMessage.OPENID_NOT_CARRY;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-14 19:34
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 方便起见，从请求头中获取openid
     * 若不存在,则返回false
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String openid = request.getHeader("openid");
        if (openid == null) {
            response.sendError(SC_FORBIDDEN, OPENID_NOT_CARRY);
            return false;
        } else {
            // 添加至RequestContextHolder
            RequestUtil.setOpenId(openid);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
