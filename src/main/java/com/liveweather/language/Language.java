package com.liveweather.language;

import com.liveweather.Cache;
import com.liveweather.instances.InstanceManager;
import com.liveweather.storage.LWConfig;
import com.liveweather.utils.FileUtils;
import com.liveweather.utils.Resources;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Language {
    String lang = new LWConfig().read("language");
    public String get(String obj) {
        if(Cache.languageCache.equals("")) {
            Cache.languageCache = new Resources().read("/lang/" + lang + ".json");
            Cache.lastLanguage = new LWConfig().read("language");
        }
        if(!Objects.equals(Cache.lastLanguage, new LWConfig().read("language"))) {
            Cache.languageCache = new Resources().read("/lang/" + lang + ".json");
            Cache.lastLanguage = new LWConfig().read("language");
        }
        JSONObject object = new JSONObject(Cache.languageCache);
        try {
            return object.getString(obj);
        }catch (JSONException ex) {
            return obj;
        }
    }
}
