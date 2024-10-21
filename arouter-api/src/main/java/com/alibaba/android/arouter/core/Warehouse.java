package com.alibaba.android.arouter.core;

import com.alibaba.android.arouter.base.RouteGroupHashMap;
import com.alibaba.android.arouter.base.UniqueKeyTreeMap;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.RouteGroups;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Storage of route meta and other data.
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/2/23 下午1:39
 */
class Warehouse {
    // Cache route and metas
    static RouteGroupHashMap groupsIndex = new RouteGroupHashMap();
    static Map<String, RouteMeta> routes = new HashMap<>();

    // Cache provider
    static Map<Class, IProvider> providers = new HashMap<>();
    static Map<String, RouteMeta> providersIndex = new HashMap<>();

    // Cache interceptor
    static Map<Integer, Class<? extends IInterceptor>> interceptorsIndex = new UniqueKeyTreeMap<>("More than one interceptors use same priority [%s]");
    static List<IInterceptor> interceptors = new ArrayList<>();

    static void clear() {
        routes.clear();
        groupsIndex.clear();
        providers.clear();
        providersIndex.clear();
        interceptors.clear();
        interceptorsIndex.clear();
    }

    static void appendRouteRoot(HashMap<String, Class<? extends IRouteGroup>> map) {
        for (String group : map.keySet()) {
            groupsIndex.putGroup(group, map.get(group));
        }
    }

    static void loadGroupToRoutes(Object groupObject) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (groupObject instanceof Class) {
            ((Class<? extends IRouteGroup>) groupObject).getConstructor()
                    .newInstance()
                    .loadInto(Warehouse.routes);
        } else if (groupObject instanceof RouteGroups){
            ((RouteGroups) groupObject).loadInto(Warehouse.routes);
        }

    }
}
