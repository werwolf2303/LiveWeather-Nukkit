package com.liveweather.translate;

import com.gargoylesoftware.htmlunit.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import java.util.logging.Level;

@SuppressWarnings("unused")
public class Translate {
    /**
     * @param text To translate
     * @param from From language
     * @param to To language
     * @return translated text
     */
    public static String translate(String text, String from, String to) {
        try {
            String toreturn = "";
            java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
            try (final WebClient webClient = new WebClient()) {
                webClient.getOptions().setJavaScriptEnabled(true);
                webClient.getOptions().setCssEnabled(false);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                URL url = new URL("https://libretranslate.com/translate");
                WebRequest requestSettings = new WebRequest(url, HttpMethod.POST);
                requestSettings.setAdditionalHeader("Accept", "*/*");
                requestSettings.setAdditionalHeader("Content-Type", "application/json");
                requestSettings.setAdditionalHeader("Accept-Language", "en-US,en;q=0.8");
                requestSettings.setAdditionalHeader("Accept-Encoding", "gzip,deflate,sdch");
                requestSettings.setAdditionalHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
                requestSettings.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
                requestSettings.setAdditionalHeader("Cache-Control", "no-cache");
                requestSettings.setAdditionalHeader("Pragma", "no-cache");
                requestSettings.setAdditionalHeader("Origin", "https://libretranslate.com");
                com.nimbusds.jose.shaded.json.JSONObject object = new com.nimbusds.jose.shaded.json.JSONObject();
                object.appendField("q", text);
                object.appendField("source", from);
                object.appendField("target", to);
                object.appendField("format", "text");
                requestSettings.setRequestBody(object.toString());
                Page redirectPage = webClient.getPage(requestSettings);
                if (!(redirectPage.getWebResponse().getStatusCode() == 200)) {
                    return text;
                }
                JSONObject json = new JSONObject(redirectPage.getWebResponse().getContentAsString());
                toreturn = json.getString("translatedText");
            } catch (IOException ignored) {
            }
            return toreturn;
        }catch (FailingHttpStatusCodeException df) {
            return text;
        }
    }
}
