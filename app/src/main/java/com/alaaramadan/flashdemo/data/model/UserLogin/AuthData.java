
package com.alaaramadan.flashdemo.data.model.UserLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("gov")
    @Expose
    private String gov;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("static")
    @Expose
    private String _static;
    @SerializedName("internal_ads")
    @Expose
    private Integer internalAds;
    @SerializedName("activation_remaining")
    @Expose
    private Integer activationRemaining;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGov() {
        return gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatic() {
        return _static;
    }

    public void setStatic(String _static) {
        this._static = _static;
    }

    public Integer getInternalAds() {
        return internalAds;
    }

    public void setInternalAds(Integer internalAds) {
        this.internalAds = internalAds;
    }

    public Integer getActivationRemaining() {
        return activationRemaining;
    }

    public void setActivationRemaining(Integer activationRemaining) {
        this.activationRemaining = activationRemaining;
    }

}
