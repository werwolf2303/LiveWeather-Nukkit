package com.liveweather.language;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liveweather.logging.LWLogging;
import com.liveweather.utils.Resources;

import java.util.Locale;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public class libLanguage {
    /*
    How to use:

    1. Define if library auto-finds the language or not (when not define the language code yourself look at: https://www.science.co.il/language/Locale-codes.php line 'Language Code' in the table)
    2. Set Language Files folder
    3. Enter translation key z.b hello.world

    INFO: All files must be located in the resources folder


    Language skeleton (Name the file as the language code it has z.b de.json)


    {
       "hello.world" : "Hello World",
       "hello.world2" : "Hello World 2"
     }
     */
    String languageCode = System.getProperty("user.language");
    String lf = "";

    public void setNoAutoFindLanguage(String code) {
        languageCode = code;
    }

    public void setAutoFindLanguage() {
        languageCode = Locale.getDefault().getLanguage();
    }

    public void setLanguageFolder(String path) {
        lf = path;
    }
    public String removeComment(String json) {
        StringBuilder builder = new StringBuilder();
        for(String s : json.split("\n")) {
            if(s.replaceAll(" ", "").startsWith("//")) {
                continue;
            }
            builder.append(s);
        }
        return builder.toString();
    }
    String jsoncache = "";
    public String translate(String key) {
        final String[] ret = {key};
        if(jsoncache.isEmpty()) {
            try {
                JsonObject object = new Gson().fromJson(removeComment(new Resources().readToString(lf + "/" + languageCode + ".json")), JsonObject.class);
                object.asMap().forEach((BiConsumer<String, Object>) (s, o) -> {
                    if(s.equals(key)) {
                        ret[0] = o.toString();
                    }
                });
                jsoncache = object.toString();
            } catch (Exception e) {
                LWLogging.error("Failed to get translation for: " + languageCode);
                LWLogging.throwable(e);
            }
        }
        try {
            JsonObject object = new Gson().fromJson(jsoncache, JsonObject.class);
            object.asMap().forEach((BiConsumer<String, Object>) (s, o) -> {
                if(s.equals(key)) {
                    ret[0] = o.toString();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ret[0];
    }
}
