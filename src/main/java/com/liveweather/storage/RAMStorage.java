package com.liveweather.storage;

import com.liveweather.logging.LWLogging;

import java.util.HashMap;
import java.util.UUID;

public class RAMStorage implements Storage {
    private static class UserEntry {
        public float lat;
        public float lon;
        public boolean didAccept;

        public UserEntry(float lat, float lon, boolean didAccept) {
            this.lat = lat;
            this.lon = lon;
            this.didAccept = didAccept;
        }
    }

    private HashMap<String, UserEntry> hashMap;

    @Override
    public boolean hasEntered(UUID playerUuid) {
        return hashMap.containsKey(playerUuid.toString());
    }

    @Override
    public boolean createPlayer(UUID playerUuid, float lat, float lon, boolean didAccept) {
        hashMap.put(playerUuid.toString(), new UserEntry(lat, lon, didAccept));
        return hasEntered(playerUuid);
    }

    @Override
    public boolean changePlayerCity(UUID playerUuid, float newLat, float newLon, boolean didAccept) {
        hashMap.remove(playerUuid.toString());
        return createPlayer(playerUuid, newLat, newLon, didAccept);
    }

    @Override
    public boolean deletePlayer(UUID playerUuid) {
        hashMap.remove(playerUuid.toString());
        return !hasEntered(playerUuid);
    }

    @Override
    public Float[] getSettingOfPlayer(UUID playerUuid) {
        UserEntry userEntry = hashMap.get(playerUuid.toString());
        return new Float[] {
                userEntry.lat,
                userEntry.lon,
        };
    }

    @Override
    public void onUnload() throws Exception {

    }

    @Override
    public void init() throws Exception {
        hashMap = new HashMap<>();
    }

    @Override
    public String name() {
        return "RAM";
    }

    @Override
    public boolean didAccept(UUID playerUuid) {
        if(hasEntered(playerUuid)) {
            return hashMap.get(playerUuid.toString()).didAccept;
        }
        LWLogging.warning("[RAMStorage] Called didAccept when the player wasn't created");
        return false;
    }
}
