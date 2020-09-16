
package com.alaaramadan.flashdemo.data.model.ListCity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCity {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataCity> data = null;

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

    public List<DataCity> getData() {
        return data;
    }

    public void setData(List<DataCity> data) {
        this.data = data;
    }

}
