package com.liveweather.forms;

@FunctionalInterface
public interface FormResponse<T> {
    void onResponseReady(T response);
}
