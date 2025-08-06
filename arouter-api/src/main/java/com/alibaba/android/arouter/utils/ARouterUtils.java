package com.alibaba.android.arouter.utils;

import com.alibaba.android.arouter.exception.HandlerException;

/**
 * @author Potato
 * @date 7/28/25
 */
public class ARouterUtils {

    /**
     * Extract the default group from path.
     */
    public static String extractGroup(String path) {
        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
            return null;
        }

        try {
            String defaultGroup = path.substring(1, path.indexOf("/", 1));
            if (TextUtils.isEmpty(defaultGroup)) {
                throw new HandlerException(Consts.TAG + "Extract the default group failed! There's nothing between 2 '/'!");
            } else {
                return defaultGroup;
            }
        } catch (Exception e) {
            return null;
        }
    }

}