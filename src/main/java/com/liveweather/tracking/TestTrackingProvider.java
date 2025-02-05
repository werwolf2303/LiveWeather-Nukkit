package com.liveweather.tracking;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.response.FormResponseCustom;
import com.liveweather.exceptions.InvalidLocationException;
import com.liveweather.forms.Form;
import com.liveweather.forms.FormResponseHandler;

public class TestTrackingProvider implements TrackingProvider {
    private float lat;
    private float lon;

    @Override
    public boolean isNonCommercial() {
        return true;
    }

    @Override
    public float[] getLatLonForIP(String ip) throws InvalidLocationException {
        return new float[] {
                lat,
                lon
        };
    }

    @Override
    public String name() {
        return "TEST";
    }

    @Override
    public boolean needsLicenseAttribution() {
        return false;
    }

    @Override
    public String getAttributionText() {
        return "";
    }

    @Override
    public String friendlyName() {
        return "Test";
    }

    @Override
    public void init() {
        lat = 43;
        lon = -75;
        Server.getInstance().getCommandMap().register("help", new Command("lwsettestw") {
            @Override
            public boolean execute(CommandSender commandSender, String s, String[] strings) {
                if(commandSender instanceof Player) {
                    Form form = new Form("Select lat lon") {
                        @Override
                        public void presentToPlayer(Player player) {
                            setOnFormResponse(new FormResponseHandler() {
                                @Override
                                public void onResponse(FormResponseCustom formResponse, int i) {
                                    if(formResponse.getInputResponse(0).isEmpty() || formResponse.getInputResponse(1).isEmpty()) {
                                        return;
                                    }
                                    lat = Float.parseFloat(formResponse.getInputResponse(0));
                                    lon = Float.parseFloat(formResponse.getInputResponse(1));
                                }
                            });
                            super.presentToPlayer(player);
                        }
                    };
                    form.addElement(new ElementInput("Latitude"));
                    form.addElement(new ElementInput("Longitude"));
                    form.presentToPlayer((Player) commandSender);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void unload() {

    }
}
