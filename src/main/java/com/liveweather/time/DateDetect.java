package com.liveweather.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateDetect {
    public String date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
