package com.liveweather.storage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liveweather.PublicValues;
import com.liveweather.logging.LWLogging;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class JSONStorage implements Storage {
    private JsonObject jsonObject;
    private final String supportedStorageVersion = "1.0.0";
    private int modifyTimes = 0;
    private final int modifyThreshold = 4; // How many times the values have to change before the contents are saved

    @Override
    public boolean hasEntered(UUID playerUuid) {
        return jsonObject.has(playerUuid.toString());
    }

    @Override
    public boolean changePlayerCity(UUID playerUuid, float newLat, float newLon, boolean didAccept) {
        try {
            jsonObject.remove(playerUuid.toString());
            JsonObject location = new JsonObject();
            location.addProperty("lat", newLat);
            location.addProperty("lon", newLon);
            location.addProperty("didAccept", didAccept);
            jsonObject.add(playerUuid.toString(), location);
        }catch (Exception e) {
            LWLogging.error("Failed executing changePlayerCity");
            LWLogging.throwable(e);
            return false;
        }
        if(modifyTimes > modifyThreshold) {
            modifyTimes = 0;
            try {
                save();
            } catch (IOException e) {
                LWLogging.error("Failed saving storage");
                LWLogging.throwable(e);
            }
        } else modifyTimes++;
        return true;
    }

    private void save() throws IOException {
        Files.write(Paths.get(PublicValues.configPath.getParent(), "storage.json"), jsonObject.toString().getBytes());
    }

    @Override
    public boolean createPlayer(UUID playerUuid, float lat, float lon, boolean didAccept) {
        try {
            JsonObject location = new JsonObject();
            location.addProperty("lat", lat);
            location.addProperty("lon", lon);
            location.addProperty("didAccept", didAccept);
            jsonObject.add(playerUuid.toString(), location);
        }catch (Exception e) {
            LWLogging.error("Failed executing changePlayerCity");
            LWLogging.throwable(e);
            return false;
        }
        if(modifyTimes > modifyThreshold) {
            modifyTimes = 0;
            try {
                save();
            } catch (IOException e) {
                LWLogging.error("Failed saving storage");
                LWLogging.throwable(e);
            }
        } else modifyTimes++;
        return true;
    }

    @Override
    public boolean deletePlayer(UUID playerUuid) {
        try {
            jsonObject.remove(playerUuid.toString());
        }catch (Exception e) {
            LWLogging.error("Failed executing deletePlayer");
            LWLogging.throwable(e);
            return false;
        }
        if(modifyTimes > modifyThreshold) {
            modifyTimes = 0;
            try {
                save();
            } catch (IOException e) {
                LWLogging.error("Failed saving storage");
                LWLogging.throwable(e);
            }
        } else modifyTimes++;
        return true;
    }

    @Override
    public Float[] getSettingOfPlayer(UUID playerUuid) {
        JsonObject location = jsonObject.getAsJsonObject(playerUuid.toString());
        return new Float[] {
                location.get("lat").getAsFloat(),
                location.get("lon").getAsFloat()
        };
    }

    @Override
    public void onUnload() throws Exception {
        save();
    }

    @Override
    public void init() throws Exception {
        jsonObject = new JsonObject();
        checkAndCreateConfig();
        if(!jsonObject.get("supportedStorageVersion").getAsString().equals(supportedStorageVersion))  {
            LWLogging.error("Compatible storage version is other than the supported storage version! Clearing...");
            if(!new File(PublicValues.configPath.getParent(), "storage.json").delete()) {
                throw new IOException("Failed to delete incompatible storage file");
            }
            checkAndCreateConfig();
        }
    }

    private void checkAndCreateConfig() throws Exception {
        jsonObject.addProperty("supportedStorageVersion", supportedStorageVersion);
        if(!new File(PublicValues.configPath.getParent(), "storage.json").exists()) {
            if(!new File(PublicValues.configPath.getParent(), "storage.json").createNewFile()) {
                throw new IOException("Failed creating storage file");
            }
            Files.write(Paths.get(PublicValues.configPath.getParent(), "storage.json"), jsonObject.toString().getBytes());
        }else {
            jsonObject = new Gson().fromJson(new FileReader(new File(PublicValues.configPath.getParent(), "storage.json")), JsonObject.class);
        }
    }

    @Override
    public String name() {
        return "JSON";
    }

    @Override
    public boolean didAccept(UUID playerUuid) {
        return jsonObject.get(playerUuid.toString()).getAsJsonObject().get("didAccept").getAsBoolean();
    }
}
