package com.water.quality.context;

import com.water.quality.utils.JwtUtil;

/**
 * 用户上下文
 * @author zby
 * @date 2022-02-14 23:08
 */
public class UserContext {

    public static ThreadLocal<String> userIdContext = new ThreadLocal<>();

    public static Long getUserId() {
        return JwtUtil.getUserId(userIdContext.get());
    }

    public static void setToken(String token) {
        userIdContext.set(token);
    }
}
