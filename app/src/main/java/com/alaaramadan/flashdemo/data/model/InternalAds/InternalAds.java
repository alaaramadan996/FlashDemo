
package com.alaaramadan.flashdemo.data.model.InternalAds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternalAds {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataInternalAds data;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataInternalAds getData() {
        return data;
    }

    public void setData(DataInternalAds data) {
        this.data = data;
    }

}
