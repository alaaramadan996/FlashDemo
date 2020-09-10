
package com.alaaramadan.flashdemo.data.model.ListCity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCity {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataCity> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataCity> getData() {
        return data;
    }

    public void setData(List<DataCity> data) {
        this.data = data;
    }

}