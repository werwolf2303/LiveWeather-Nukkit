package com.liveweather.forms;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DefaultFormResponse {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName().split("\\.")[getClass().getName().split("\\.").length-1]).append("(");
        for(Method method : getClass().getDeclaredMethods()) {
            if(method.getParameterCount() != 0) continue;
            try {
                builder.append(method.getName()).append("=").append(method.invoke(this).toString()).append(",");
            } catch (IllegalAccessException | InvocationTargetException e) {
                builder.append(method.getName()).append("=").append("N/A").append(",");
            }
        }
        builder.setCharAt(builder.length()-1, ')');
        return builder.toString();
    }
}
