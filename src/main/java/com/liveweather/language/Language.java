package com.liveweather.language;

import com.liveweather.Cache;
import com.liveweather.instances.InstanceManager;
import com.liveweather.report.Report;
import com.liveweather.storage.LWConfig;
import com.liveweather.utils.Resources;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;

public class Language {
    public String lang = new LWConfig().read("language");
    public String get(String obj) {
        if(Cache.languageCache.equals("")) {
            Cache.languageCache = new Resources().read("/lang/" + lang + ".json");
            Cache.lastLanguage = new LWConfig().read("language");
            JSONObject object = new JSONObject(Cache.languageCache);
            JSONObject skeleton = new JSONObject(new Resources().read("/lang/" + "skeleton" + ".json"));
            int count = 0;
            for(String s : JSONObject.getNames(skeleton)) {
                if(!object.has(s)) {
                    count++;
                }
            }
            if(count!=0) {
                InstanceManager.getLogger().warning("Your language: '" + lang + "' is not fully supported: " + count + " are not translated");
            }
        }
        if(!Objects.equals(Cache.lastLanguage, new LWConfig().read("language"))) {
            Cache.languageCache = new Resources().read("/lang/" + lang + ".json");
            Cache.lastLanguage = new LWConfig().read("language");
            JSONObject object = new JSONObject(Cache.languageCache);
            JSONObject skeleton = new JSONObject(new Resources().read("/lang/" + "skeleton" + ".json"));
            int count = 0;
            for(String s : JSONObject.getNames(skeleton)) {
                if(!object.has(s)) {
                    count++;
                }
            }
            if(count!=0) {
                InstanceManager.getLogger().warning("Your language: '" + lang + "' is not fully supported: " + count + " are not translated");
            }
        }
        JSONObject object = new JSONObject(Cache.languageCache);
        JSONObject skeleton = new JSONObject(new Resources().read("/lang/" + "skeleton" + ".json"));
        int count = 0;
        for(String s : JSONObject.getNames(skeleton)) {
            if(!object.has(s)) {
                count++;
            }
        }
        if(count!=0) {
            InstanceManager.getLogger().warning("Your language: '" + lang + "' is not fully supported: " + count + " are not translated");
        }
        try {
            return object.getString(obj);
        }catch (JSONException ex) {
            InstanceManager.getLogger().warning("Language not translated : '" + obj + "' fallback to english");
            try {
                JSONObject fallback = new JSONObject(new Resources().read("/lang/en.json"));
                return fallback.getString(obj);
            }catch (JSONException exs) {
                new Report().create("English Translation Failure", "English not fully translated\nStacktrace\n\n" + exs.toString());
                return obj;
            }
        }
    }
}
