package com.liveweather.test;

import com.liveweather.GlobalValues;
import com.liveweather.language.Language;
import com.liveweather.utils.Resources;

public class Test {
    public static void main(String[] args) {
        GlobalValues.debug = true;
        Language v2 = new Language();
        System.out.println(v2.get("liveweather.commands.whatsmyweather.noautofind.current"));
    }
}