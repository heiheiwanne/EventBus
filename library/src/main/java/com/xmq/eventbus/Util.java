
package com.xmq.eventbus;

import java.util.ArrayList;


/**
 * Created by xmq on 2016/7/23.
 */
class Util {

    @SuppressWarnings("unchecked")
    static ArrayList<Class<? extends IEvent>> getInterfaces(IEvent event) {
        Class<?>[] interfaces = event.getClass().getInterfaces();
        ArrayList<Class<? extends IEvent>> eventClass = new ArrayList<>();
        for (Class<?> in : interfaces) {
            if (isExtendsInterface(in, IEvent.class)) {
                eventClass.add((Class<? extends IEvent>) in);
            }
        }
        return eventClass;
    }


    static boolean isExtendsInterface(Class<?> in, Class<?> superClass) {
        Class<?>[] subIns = in.getInterfaces();
        for (Class<?> subIn : subIns) {
            if (IEvent.class.equals(subIn)) {
                return true;
            }
        }
        return false;
    }


    static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException(
                    "API declarations must be interfaces.");
        }
        if (!isExtendsInterface(service, IEvent.class)) {
            throw new IllegalArgumentException(
                    "API declarations must be extends IEvent.");
        }

    }
}
