
package com.alaaramadan.flashdemo.data.model.ListGovernorate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListGovernorate {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataGovernorate> data = null;

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

    public List<DataGovernorate> getData() {
        return data;
    }

    public void setData(List<DataGovernorate> data) {
        this.data = data;
    }

}
