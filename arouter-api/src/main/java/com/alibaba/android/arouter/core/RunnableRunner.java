package com.alibaba.android.arouter.core;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Potato
 * @date 8/6/25
 */
public class RunnableRunner {

    @Nullable
    public static Object callRunnable(Postcard postcard, NavigationCallback callback) {
        Class<?> targetClass = postcard.getDestination();
        Method method;
        try {
            method = targetClass.getDeclaredMethod("onNavigate", Postcard.class);
            method.setAccessible(true);
        } catch (Exception e) {
            callback.onLost(postcard);
            ARouter.logger.error("RunnableRunner", e.getMessage(), e);
            return null;
        }

        try {
            if (Modifier.isStatic(method.getModifiers())) {
                // 调用静态 onNavigate 方法
                return method.invoke(null, postcard);
            } else {
                // 如果 onNavigate 不是静态方法, 则调用这个类的无参构造方法, 在调用 onNavigate
                // Object instance = targetClass.getDeclaredConstructor().newInstance();

                // 暂不支持非静态方法
                return null;
            }
        } catch (Exception e) {
            ARouter.logger.error("RunnableRunner", e.getMessage(), e);
            return null;
        }
    }
}
