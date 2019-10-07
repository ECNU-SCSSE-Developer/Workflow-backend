package com.scsse.workflow.util.MVCUtil;

import com.scsse.workflow.constant.MyRequestScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

/**
 * @author Alfred Fu
 * Created on 2019/9/18 10:41 上午
 */
public class RequestUtil {

    private static final String OPENID_ATTRIBUTE_KEY = "openid";


    private static boolean setAttribute(String key, String value, int scope) {
        Optional attribute = Optional.ofNullable(RequestContextHolder.getRequestAttributes());
        if (attribute.isPresent()) {
            ((RequestAttributes) attribute.get()).setAttribute(key, value, scope);
            return true;
        } else {
            return false;
        }
    }

    private static String getAttribute(String key, int scope) {
        Optional attribute = Optional.ofNullable(RequestContextHolder.getRequestAttributes());
        if (attribute.isPresent()) {
            return (String) ((RequestAttributes) attribute.get()).getAttribute(key, scope);
        } else {
            return "";
        }
    }

    public static String getOpenId() {
        return getAttribute(OPENID_ATTRIBUTE_KEY, MyRequestScope.DEFAULT_SCOPE);
    }

    public static void setOpenId(String openId) {
        setAttribute(OPENID_ATTRIBUTE_KEY, openId, MyRequestScope.DEFAULT_SCOPE);
    }
}
