
package com.alaaramadan.flashdemo.data.model.Registeration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registeration {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataRegsteration data;

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

    public DataRegsteration getData() {
        return data;
    }

    public void setData(DataRegsteration data) {
        this.data = data;
    }

}
