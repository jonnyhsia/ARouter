package com.alibaba.android.arouter.facade.template;

import com.alibaba.android.arouter.facade.model.RouteMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Potato
 * @date 10/19/24
 */
public class RouteGroups implements IRouteGroup {

    private final List<Class<? extends IRouteGroup>> groups = new ArrayList<>();

    @Override
    public void loadInto(Map<String, RouteMeta> atlas) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Class<? extends IRouteGroup> group : groups) {
            group.getConstructor()
                    .newInstance()
                    .loadInto(atlas);
        }
        groups.clear();
    }

    public void addPartialGroup(Class<? extends IRouteGroup> group) {
        groups.add(group);
    }
}