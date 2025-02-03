package com.liveweather.utils;

import cn.nukkit.Player;
import com.liveweather.PublicValues;
import com.liveweather.exceptions.InvalidLocationException;
import com.liveweather.forms.*;
import com.liveweather.logging.LWLogging;
import com.liveweather.tracking.TrackingProvider;

import java.io.IOException;

public class FormUtils {
    public static void openTrackingAgreementForm(Player player) {
        TrackingAgreementForm form = new TrackingAgreementForm();
        form.setOnResponse(response -> {
            try {
                if(!response.getAccepted()) {
                    // Didn't accept
                    PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
                } else {
                    TrackingProvider provider = PublicValues.trackingProvider;
                    float[] latLon = provider.getLatLonForIP(player.getAddress());
                    PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), latLon[0], latLon[1], false);
                }
            } catch (com.liveweather.exceptions.InvalidLocationException e) {
                throw new RuntimeException(e);
            }
        });
        form.setOnClose(() -> {
            // Didn't accept
            PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
        });
        form.presentToPlayer(player);
    }

    public static void openLocationSelectForm(Player player) {
        LocationSelectorForm form = new LocationSelectorForm();
        form.setOnResponse(response -> {
            if(response.getCountry().isEmpty() || response.getState().isEmpty() || response.getCity().isEmpty()) {
                PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
            } else {
                try {
                    float[] lonLat = PublicValues.geocodingProvider.getLonLat(response.getCountry(), response.getState(), response.getCity());
                    PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), lonLat[1], lonLat[0], false);
                }catch (IOException e) {
                    player.sendMessage("Failed setting location");
                    LWLogging.throwable(e);
                }catch (InvalidLocationException e) {
                    player.sendMessage("Invalid or unknown location");
                    LWLogging.error("Invalid or unknown location for player: " + player.getName());
                }
            }
        });
        form.setOnClose(new Runnable() {
            @Override
            public void run() {
                PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
            }
        });
        form.presentToPlayer(player);
    }
}
