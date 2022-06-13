package com.liveweather.translate;

import com.liveweather.test.Test;

import java.util.HashMap;

public class Languages {
    public String GERMAN = "de";
    public String ENGLISH = "en";
    public String ARABIC = "ar";
    public String AZERBAIJANI = "az";
    public String CHINESE = "zh";
    public String CZECH = "cs";
    public String DUTCH = "nl";
    public String FINNISH = "fi";
    public String FRENCH = "fr";
    public String HINDI = "hi";
    public String HUNGARIAN = "hu";
    public String INDONESIAN = "id";
    public String IRISH = "ga";
    public String ITALIAN = "it";
    public String JAPANESE = "ja";
    public String KOREAN = "ko";
    public String POLISH = "pl";
    public String PORTUGUESE = "pt";
    public String RUSSIAN = "ru";
    public String SPANISH = "es";
    public String SWEDISH  = "sv";
    public String TURKISH = "tr";
    public String UKRANIAN = "uk";
    public String VIETNAMESE = "vi";
    private static Languages language;
    public synchronized static Languages getInstance() {
        if (language == null) {
            language = new Languages();
        }
        return language;
    }
}
