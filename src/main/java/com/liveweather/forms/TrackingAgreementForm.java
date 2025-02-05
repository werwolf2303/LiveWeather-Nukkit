package com.liveweather.forms;

import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementToggle;
import com.liveweather.PublicValues;

public class TrackingAgreementForm extends Form implements LWForm<TrackingAgreementFormResponse> {
    private FormResponse<TrackingAgreementFormResponse> response;
    private final TrackingAgreementFormResponse internalResponse;

    public TrackingAgreementForm() {
        super(PublicValues.language.translate("liveweather.forms.trackingagreement.title"));
        addElement(new ElementLabel(PublicValues.language.translate("liveweather.forms.trackingagreement.info1")));
        addElement(new ElementLabel(PublicValues.language.translate("liveweather.forms.trackingagreement.info2")));
        addElement(new ElementLabel(PublicValues.language.translate("liveweather.forms.trackingagreement.info3")));
        if(PublicValues.trackingProvider.needsLicenseAttribution()) {
            addElement(new ElementLabel("Tracking provided by " + PublicValues.trackingProvider.friendlyName()));
            addElement(new ElementLabel(PublicValues.trackingProvider.getAttributionText()));
        }
        addElement(new ElementLabel(""));
        addElement(new ElementLabel(PublicValues.language.translate("liveweather.forms.trackingagreement.toggleInfo")));
        addElement(new ElementToggle(PublicValues.language.translate("liveweather.forms.trackingagreement.toggleText"), false));
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
