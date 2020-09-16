
package com.alaaramadan.flashdemo.data.model.ExternalAds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalAds {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataExternal data;

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

    public DataExternal getData() {
        return data;
    }

    public void setData(DataExternal data) {
        this.data = data;
    }

}
