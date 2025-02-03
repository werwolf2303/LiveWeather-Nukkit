package com.liveweather.forms;

public interface LWForm<T> {
    void setOnResponse(FormResponse<T> formResponse);
}
