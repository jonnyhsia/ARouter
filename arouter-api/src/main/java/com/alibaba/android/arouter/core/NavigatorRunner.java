package com.alibaba.android.arouter.core;

import androidx.annotation.RestrictTo;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.template.INavigator;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class NavigatorRunner {

    public static void run(INavigator navigator, Postcard postcard, NavigationCallback callback) {
        if (navigator.isRunOnMainThread()) {
            navigator.onNavigate(postcard, callback);
        } else {
            LogisticsCenter.executor.execute(() -> {
                navigator.onNavigate(postcard, callback);
            });
        }
    }
}