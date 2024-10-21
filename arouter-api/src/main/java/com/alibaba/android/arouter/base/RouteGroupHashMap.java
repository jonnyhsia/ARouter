package com.alibaba.android.arouter.base;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.RouteGroups;

import java.util.HashMap;

/**
 * Value: Class<? extends IRouteGroup> or RouteGroups
 *
 * @author Potato
 * @date 10/19/24
 */
public class RouteGroupHashMap extends HashMap<String, Object> {

    @Nullable
    public Object putGroup(String key, Class<? extends IRouteGroup> value) {
        if (containsKey(key)) {
            return replaceWithRouteGroups(key, value);
        }
        return put(key, value);
    }

    @Nullable
    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }

    private Object replaceWithRouteGroups(String key, Class<? extends IRouteGroup> value) {
        Object oldValue = get(key);
        RouteGroups groups;
        if (oldValue instanceof Class) {
            groups = new RouteGroups();
            // 把原先的 group class 保存到 groups 中, 并替换进 map 中
            groups.addPartialGroup((Class<? extends IRouteGroup>) oldValue);
            put(key, groups);
        } else if (oldValue instanceof RouteGroups) {
            groups = (RouteGroups) oldValue;
        } else {
            groups = null;
        }
        if (groups != null) {
            groups.addPartialGroup(value);
        }
        return groups;
    }
}
