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
        super(PublicValues.language.translate("liveweather.forms.locationselector.title"), new ArrayList<Element>() {{
            add(new ElementLabel(PublicValues.language.translate("liveweather.forms.locationselector.info")));
            add(new ElementInput(PublicValues.language.translate("liveweather.forms.locationselector.country")));
            add(new ElementInput(PublicValues.language.translate("liveweather.forms.locationselector.state")));
            add(new ElementInput(PublicValues.language.translate("liveweather.forms.locationselector.city")));
            if(PublicValues.geocodingProvider.needsLicenseAttribution()) {
                add(new ElementLabel(PublicValues.geocodingProvider.getAttributionText()));
            }
        }});
        internalResponse = new LocationSelectorFormResponse();
        setOnFormResponse((formResponse, i) -> {
            for(int res = 0; res < formResponse.getResponses().size(); res ++) {
                switch (res) {
                    case 1:
                        internalResponse.setCountry(formResponse.getInputResponse(1));
                        break;
                    case 2:
                        internalResponse.setState(formResponse.getInputResponse(2));
                        break;
                    case 3:
                        internalResponse.setCity(formResponse.getInputResponse(3));
                        response.onResponseReady(internalResponse);
                        break;
                }
            }
        });
    }

    @Override
    public void setOnResponse(com.liveweather.forms.FormResponse<LocationSelectorFormResponse> response) {
        this.response = response;
    }
}

