package com.liveweather.forms;

import cn.nukkit.form.response.FormResponseCustom;

@FunctionalInterface
public interface FormResponseHandler {
    void onResponse(FormResponseCustom formResponse, int i);
}
