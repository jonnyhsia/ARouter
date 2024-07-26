package com.alibaba.android.arouter.core;

import androidx.annotation.RestrictTo;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.template.INavigator;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class NavigatorRunner {

    public static void run(INavigator navigator, Postcard postcard) {
        if (navigator.isRunOnMainThread()) {
            navigator.onNavigate(postcard);
        } else {
            LogisticsCenter.executor.execute(() -> {
                navigator.onNavigate(postcard);
            });
        }
    }
}