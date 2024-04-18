package com.alibaba.android.arouter.facade.service;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Provide degrade service for router, you can do something when route has lost.
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 2016/9/22 14:51
 */
public interface DegradeService extends IProvider {

    /**
     * Router has lost.
     *
     * @param postcard meta
     */
    boolean onLost(Context context, Postcard postcard);

    /**
     * 路由解析失败
     *
     * @param route 解析失败的路由
     * @return 处理后的新路由, null 为不继续执行解析
     */
    @Nullable
    Postcard onRouteParseFailed(@NonNull String route);

    /**
     * 路由解析失败
     *
     * @param route 解析失败的路由
     * @return 处理后的新路由, null 为不继续执行解析
     */
    @Nullable
    Postcard onRouteParseFailed(@NonNull Uri route);
}
