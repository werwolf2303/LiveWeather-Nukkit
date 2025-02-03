package com.liveweather.forms;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import com.liveweather.PublicValues;

import java.util.ArrayList;

public class LocationSelectorForm extends Form implements LWForm<LocationSelectorFormResponse> {
    private com.liveweather.forms.FormResponse<LocationSelectorFormResponse> response;
    private final LocationSelectorFormResponse internalResponse;

    public LocationSelectorForm() {
        super("Enter Location", new ArrayList<Element>() {{ // ToDo: Translate
            add(new ElementLabel("Leave empty or close window if you don't want to provide your location")); // ToDo: Translate
            add(new ElementInput("Country")); // ToDo: Translate
            add(new ElementInput("State")); // ToDo: Translate
            add(new ElementInput("City")); // ToDo: Translate
            if(PublicValues.geocodingProvider.needsLicenseAttribution()) {
                add(new ElementLabel(PublicValues.geocodingProvider.getAttributionText())); // ToDo: Translate
            }
        }});
        internalResponse = new LocationSelectorFormResponse();
        setOnFormResponse((formResponse, i) -> {
            for(int res = 0; res < formResponse.getResponses().size(); res ++) {
                switch (res) {
                    case 0:
                        internalResponse.setCountry(formResponse.getInputResponse(0));
                        break;
                    case 1:
                        internalResponse.setState(formResponse.getInputResponse(1));
                        break;
                    case 2:
                        internalResponse.setCity(formResponse.getInputResponse(2));
                        response.onResponseReady(internalResponse);
                }
            }
        });
    }

    @Override
    public void setOnResponse(com.liveweather.forms.FormResponse<LocationSelectorFormResponse> response) {
        this.response = response;
    }
}

