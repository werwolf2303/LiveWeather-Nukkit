package com.liveweather.utils;

import cn.nukkit.Player;
import com.liveweather.MessageConstructor;
import com.liveweather.PublicValues;
import com.liveweather.exceptions.InvalidLocationException;
import com.liveweather.forms.LocationSelectorForm;
import com.liveweather.forms.TrackingAgreementForm;
import com.liveweather.logging.LWLogging;
import com.liveweather.tracking.TrackingProvider;

import java.io.IOException;

@SuppressWarnings("Duplicates")
public class FormUtils {
    public static void openTrackingAgreementForm(Player player) {
        TrackingAgreementForm form = new TrackingAgreementForm();
        form.setOnResponse(response -> {
            try {
                if (!response.getAccepted()) {
                    // Didn't accept
                    PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
                } else {
                    TrackingProvider provider = PublicValues.trackingProvider;
                    float[] latLon = provider.getLatLonForIP(player.getAddress());
                    if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                        PublicValues.playerStorageProvider.changePlayerCity(player.getUniqueId(), latLon[0], latLon[1], true);
                        return;
                    }
                    PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), latLon[0], latLon[1], true);
                }
            } catch (com.liveweather.exceptions.InvalidLocationException e) {
                player.sendMessage(MessageConstructor.fromTranslation("liveweather.forms.general.invalid"));
            }
        });
        form.setOnClose(() -> {
            // Didn't accept
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                PublicValues.playerStorageProvider.changePlayerCity(player.getUniqueId(), 0, 0, false);
                return;
            }
            PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
        });
        form.presentToPlayer(player);
    }

    public static void openLocationSelectForm(Player player) {
        LocationSelectorForm form = new LocationSelectorForm();
        form.setOnResponse(response -> {
            if (response.getCountry().isEmpty() || response.getState().isEmpty() || response.getCity().isEmpty()) {
                PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
            } else {
                try {
                    if (PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                        float[] lonLat = PublicValues.geocodingProvider.getLonLat(response.getCountry(), response.getState(), response.getCity());
                        PublicValues.playerStorageProvider.changePlayerCity(player.getUniqueId(), lonLat[1], lonLat[0], true);
                    } else {
                        float[] lonLat = PublicValues.geocodingProvider.getLonLat(response.getCountry(), response.getState(), response.getCity());
                        PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), lonLat[1], lonLat[0], true);
                    }
                } catch (IOException e) {
                    player.sendMessage(MessageConstructor.constructMessage("liveweather.forms.failed"));
                    LWLogging.throwable(e);
                } catch (InvalidLocationException e) {
                    player.sendMessage(MessageConstructor.fromTranslation("liveweather.forms.general.invalid"));
                    LWLogging.error("Invalid or unknown location for player: " + player.getName());
                }
            }
        });
        form.setOnClose(() -> {
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                PublicValues.playerStorageProvider.changePlayerCity(player.getUniqueId(), 0, 0, false);
                return;
            }
            PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), 0, 0, false);
        });
        form.presentToPlayer(player);
    }
}
