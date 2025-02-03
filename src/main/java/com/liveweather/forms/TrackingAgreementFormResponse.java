package com.liveweather.forms;

public class TrackingAgreementFormResponse extends DefaultFormResponse {
    private boolean accepted;

    void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean getAccepted() {
        return accepted;
    }
}
