
package com.alaaramadan.flashdemo.data.model.ConnectUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataConnectUs {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("whats_app")
    @Expose
    private String whatsApp;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
