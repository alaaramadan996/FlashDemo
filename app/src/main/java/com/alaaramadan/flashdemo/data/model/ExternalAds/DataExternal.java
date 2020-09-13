
package com.alaaramadan.flashdemo.data.model.ExternalAds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataExternal {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("placehold")
    @Expose
    private String placehold;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlacehold() {
        return placehold;
    }

    public void setPlacehold(String placehold) {
        this.placehold = placehold;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
