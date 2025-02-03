package com.liveweather.forms;

import cn.nukkit.form.element.*;
import com.liveweather.PublicValues;

public class TrackingAgreementForm extends Form implements LWForm<TrackingAgreementFormResponse> {
    private FormResponse<TrackingAgreementFormResponse> response;
    private final TrackingAgreementFormResponse internalResponse;

    public TrackingAgreementForm() {
        super("LiveWeather"); // ToDo: Translate
        addElement(new ElementLabel("This server has LiveWeather installed")); // ToDo: Translate
        addElement(new ElementLabel("This server reflects the weather in your location")); // ToDo: Translate
        addElement(new ElementLabel("This server wants to get your location for that reason")); // ToDo: Translate
        if(PublicValues.trackingProvider.needsLicenseAttribution()) {
            addElement(new ElementLabel("Tracking provided by " + PublicValues.trackingProvider.friendlyName())); // ToDo: Translate
            addElement(new ElementLabel(PublicValues.trackingProvider.getAttributionText()));
        }
        addElement(new ElementLabel(""));
        addElement(new ElementLabel("By clicking the toggle you grant the server the right to get your location and save it unencrypted solely for this purpose")); // ToDo: Translate
        addElement(new ElementToggle("Accept", false)); // ToDo: Translate
        internalResponse = new TrackingAgreementFormResponse();
        setOnFormResponse((formResponse, i) -> {
            internalResponse.setAccepted(formResponse.getToggleResponse(5));
            response.onResponseReady(internalResponse);
        });
    }

    @Override
    public void setOnResponse(FormResponse<TrackingAgreementFormResponse> formResponse) {
        this.response = formResponse;
    }
}
