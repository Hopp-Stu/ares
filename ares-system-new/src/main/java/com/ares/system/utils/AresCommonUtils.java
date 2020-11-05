package com.ares.system.utils;

import com.alibaba.fastjson.JSON;
import com.ares.redis.utils.RedisUtil;
import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * @description:
 * @author: yy 2020/05/15
 **/
public class AresCommonUtils {

    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Map) {
            return null != obj && ((Map) obj).size() > 0;
        } else if (obj instanceof Collection) {
            return null != obj && ((Collection) obj).size() > 0;
        } else if (obj instanceof String) {
            return null != obj && !"".equals(((String) obj).trim());
        } else {
            return null != obj;
        }
    }

    public static boolean isEmpty(Object obj) {
        return !isNotEmpty(obj);
    }


    public static String getCode(HttpServletRequest request, String key) {
        try {
            String code = request.getParameter(key);
            if (null != code) {
                code = code.trim();
            }
            if ("".equals(code)) {
                code = null;
            }
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkVerifyCode(String code, String uuid) {
        Object verifyCodeActual = RedisUtil.get(Constants.KAPTCHA_SESSION_KEY + uuid);
        if (null == verifyCodeActual || !verifyCodeActual.equals(code)) {
            return false;
        }
        return true;
    }

    public static void writeResponse(HttpServletResponse response, Object result) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(result));
    }

}
