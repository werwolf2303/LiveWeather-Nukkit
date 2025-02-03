package com.liveweather.storage;

import java.util.UUID;

public class DummyStorage implements Storage{
    @Override
    public boolean hasEntered(UUID playerUuid) {
        return false;
    }

    @Override
    public boolean createPlayer(UUID playerUuid, float lat, float lon, boolean didAccept) {
        return false;
    }

    @Override
    public boolean changePlayerCity(UUID playerUuid, float newLat, float newLon, boolean didAccept) {
        return false;
    }

    @Override
    public boolean deletePlayer(UUID playerUuid) {
        return false;
    }

    @Override
    public Float[] getSettingOfPlayer(UUID playerUuid) {
        return new Float[] {};
    }

    @Override
    public void onUnload() {

    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public String name() {
        return "DUMMY";
    }

    @Override
    public boolean didAccept(UUID playerUuid) {
        return false;
    }
}
