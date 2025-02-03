package com.liveweather.storage;

import java.util.UUID;

public interface Storage {
    boolean hasEntered(UUID playerUuid);
    boolean createPlayer(UUID playerUuid, float lat, float lon, boolean didAccept);
    boolean changePlayerCity(UUID playerUuid, float newLat, float newLon, boolean didAccept);
    boolean deletePlayer(UUID playerUuid);
    Float[] getSettingOfPlayer(UUID playerUuid);
    void onUnload() throws Exception;
    void init() throws Exception;
    String name();
    boolean didAccept(UUID playerUuid);
}
