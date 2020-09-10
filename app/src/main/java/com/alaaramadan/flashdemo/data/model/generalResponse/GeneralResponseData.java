
package com.alaaramadan.flashdemo.data.model.generalResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralResponseData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city_id")
    @Expose
    private int cityId;
    @SerializedName("governorate_id")
    @Expose
    private int governorateId;
    @SerializedName("city")
    @Expose
    private GeneralResponseData city;
    @SerializedName("governorate")
    @Expose
    private GeneralResponseData governorate;

    public GeneralResponseData(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(int governorateId) {
        this.governorateId = governorateId;
    }

    public GeneralResponseData getCity() {
        return city;
    }

    public void setCity(GeneralResponseData city) {
        this.city = city;
    }

    public GeneralResponseData getGovernorate() {
        return governorate;
    }

    public void setGovernorate(GeneralResponseData governorate) {
        this.governorate = governorate;
    }
}
