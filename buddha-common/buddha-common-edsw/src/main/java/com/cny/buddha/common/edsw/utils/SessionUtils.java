package com.cny.buddha.common.edsw.utils;


import com.cny.buddha.common.entity.persistence.User;

public class SessionUtils {
    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static User getUser() {
        User user=null;
        //user=Jedis.get("User"); TODO
        if (user != null) {
            return user;
        }
        return new User();// 如果没有登录，则返回实例化空的User对象。

    }
}
