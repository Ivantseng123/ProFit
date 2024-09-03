package com.ProFit.util;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class CountProperty {
    public static String getPropertyCount(Class<?> beanClass) {
        Method[] methods = beanClass.getMethods();
        Set<String> propertyNames = new HashSet<>();

        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && name.length() > 3 && method.getParameterCount() == 0) {
                propertyNames.add(name.substring(3).toLowerCase());
            } else if (name.startsWith("is") && name.length() > 2 && method.getParameterCount() == 0) {
                propertyNames.add(name.substring(2).toLowerCase());
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < propertyNames.size() - 1; i++) {
            if (i == propertyNames.size() - 2){
                stringBuilder.append("?");
            } else {
                stringBuilder.append("?, ");
            }
        }
        return stringBuilder.toString();
    }
}

