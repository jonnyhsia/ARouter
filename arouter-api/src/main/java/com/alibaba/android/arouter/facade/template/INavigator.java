package com.alibaba.android.arouter.facade.template;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.Postcard;

public interface INavigator {

    default boolean isRunOnMainThread() {
        return false;
    }

    void onNavigate(@NonNull Postcard postcard);
}