
package com.alaaramadan.flashdemo.data.model.GetActivationValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataActivationValue {

    @SerializedName("activation_value")
    @Expose
    private String activationValue;
    @SerializedName("activation_period")
    @Expose
    private String activationPeriod;
    @SerializedName("card")
    @Expose
    private String card;

    public String getActivationValue() {
        return activationValue;
    }

    public void setActivationValue(String activationValue) {
        this.activationValue = activationValue;
    }

    public String getActivationPeriod() {
        return activationPeriod;
    }

    public void setActivationPeriod(String activationPeriod) {
        this.activationPeriod = activationPeriod;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

}
