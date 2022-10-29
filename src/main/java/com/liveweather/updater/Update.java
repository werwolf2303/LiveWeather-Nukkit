package com.liveweather.updater;

import com.exampleextension.com.extension.Init;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.liveweather.GlobalValues;
import com.liveweather.Initiator;
import com.liveweather.api.GHKey;
import com.liveweather.client.Client;
import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;
import com.liveweather.storage.Zippie;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import static org.eclipse.jetty.client.GZIPContentDecoder.DEFAULT_BUFFER_SIZE;

public class Update {
    public void downloadNightly() {
        String get = new Client().get("https://api.github.com/repos/Werwolf2303/LiveWeather-Nukkit/actions/artifacts");
        JSONObject json = new JSONObject(get);
        JSONArray artifacts = json.getJSONArray("artifacts");
        JSONObject newest = artifacts.getJSONObject(0);
        String downloadurl = newest.getString("archive_download_url");
        String token = new GHKey().get();
        try {
            WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
            webClient.addRequestHeader("Authorization", "token " + token);
            WebResponse response = webClient.getPage(downloadurl).getWebResponse();
            copyInputStreamToFile(response.getContentAsStream(), new File("updatecache"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Zippie.extractZIP("updatecache", "update");
        new File("updatecache").delete();
        try {
            FileUtils.copyFile(new File("update/LiveWeather-Nukkit.jar"), new File(InstanceManager.getServer().getPluginPath().replace("\\", "/") + "/LiveWeather-Nukkit.jar"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(File f : new File("update").listFiles()) {
            f.delete();
        }
        new File("update").delete();
    }
    public Update() {
        if(!new File(UpdateConfig.config).exists()) {
            new UpdateConfig();
            new UpdateConfig().writeComment("Possible Candidate Types: nightly, stable");
            new UpdateConfig().write("candidate", "stable");
        }
        if(checkUpdate()) {
            new LWLogging().normal(new Language().get("liveweather.updater.available"));
            if(new UpdateConfig().read("candidate").equals("stable")) {
                downloadStable();
            }else{
                downloadNightly();
            }
            InstanceManager.getServer().reload();
            new LWLogging().normal(new Language().get("liveweather.updater.done"));
        }
    }
    public boolean checkUpdate() {
        String get = new Client().get("https://raw.githubusercontent.com/werwolf2303/LiveWeather-Nukkit/main/src/main/resources/plugin.yml");
        String version = "";
        for(String s : get.split("\n")) {
            if(s.contains("version")) {
                version = s.replace("version: ", "");
                break;
            }
        }
        if(GlobalValues.debug) {
            String pluginversion = "";
            String f = new com.liveweather.utils.FileUtils().getContent("src/main/resources/plugin.yml");
            for(String s : f.split("\n")) {
                if(s.contains("version: ")) {
                    pluginversion = s.replace("version: ", "").replace("\"", "");
                    break;
                }
            }
            if (Float.parseFloat(pluginversion) > Float.parseFloat(version.replace("\"", ""))) {
                //Development version.. SKIP
                return false;
            }
            if (!pluginversion.equals(version)) {
                return true;
            } else {
                return false;
            }
        }else {
            if (Float.parseFloat(Initiator.plugin.getDescription().getVersion().replace("\"", "")) > Float.parseFloat(version.replace("\"", ""))) {
                //Development version.. SKIP
                return false;
            }
            if (!Initiator.plugin.getDescription().getVersion().replace("\"", "").equals(version.replace("\"", ""))) {
                return true;
            } else {
                return false;
            }
        }
    }
    public void downloadStable() {
        try {
            WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
            WebResponse response = webClient.getPage("https://github.com/Werwolf2303/LiveWeather-Nukkit/releases/latest/download/LiveWeather-Nukkit.jar").getWebResponse();
            copyInputStreamToFile(response.getContentAsStream(), new File(InstanceManager.getServer().getPluginPath().replace("\\", "/") + "/LiveWeather-Nukkit.jar"));
        } catch (MalformedURLException e) {
            new LWLogging().throwable(e);
        } catch (IOException e) {
            new LWLogging().throwable(e);
        }
    }
    static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }
}
