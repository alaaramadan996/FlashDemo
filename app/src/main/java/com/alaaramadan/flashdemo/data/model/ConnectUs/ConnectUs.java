
package com.alaaramadan.flashdemo.data.model.ConnectUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectUs {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataConnectUs data;

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

    public DataConnectUs getData() {
        return data;
    }

    public void setData(DataConnectUs data) {
        this.data = data;
    }

}
