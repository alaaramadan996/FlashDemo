
package com.alaaramadan.flashdemo.data.model.GetActivationValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetActivationValues {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataActivationValue data;

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

    public DataActivationValue getData() {
        return data;
    }

    public void setData(DataActivationValue data) {
        this.data = data;
    }

}
