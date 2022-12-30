package com.liveweather.test;

import com.liveweather.GlobalValues;
import com.liveweather.language.Language;

public class Test {
    public static void main(String[] args) {
        GlobalValues.debug = true;
        Language language = new Language();
        language.lang = "chs";
        System.out.println(language.get("liveweather.forms.title"));
    }
}