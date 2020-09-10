
package com.alaaramadan.flashdemo.data.model.ListGovernorate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListGovernorate {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataGovernorate> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataGovernorate> getData() {
        return data;
    }

    public void setData(List<DataGovernorate> data) {
        this.data = data;
    }

}
