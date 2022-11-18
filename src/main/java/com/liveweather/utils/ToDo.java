package com.liveweather.utils;

import com.liveweather.commandline.LWLogging;

public class ToDo {
    public ToDo(String todo) {
        // Prints
        // Todo
        // Messages
        new LWLogging().normal("ToDo: " + todo);
    }
}
