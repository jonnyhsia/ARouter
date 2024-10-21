package com.alibaba.android.arouter.facade.template;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;

public interface INavigator {

    default boolean isRunOnMainThread() {
        return false;
    }

    void onNavigate(@NonNull Postcard postcard, @Nullable NavigationCallback callback);
}