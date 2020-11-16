package com.baixing.hc.redpacket.utils;

/**
 * @author hucong
 * @date 2020/11/8 2:29 下午
 */
public class IdentityUtil {

    public static Long getIdentity() {
        return System.currentTimeMillis() * 1000000L + System.nanoTime() % 1000000L;
    }
}

