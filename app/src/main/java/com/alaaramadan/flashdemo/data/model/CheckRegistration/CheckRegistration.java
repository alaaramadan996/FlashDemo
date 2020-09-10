
package com.alaaramadan.flashdemo.data.model.CheckRegistration;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckRegistration {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataRegistration> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataRegistration> getData() {
        return data;
    }

    public void setData(List<DataRegistration> data) {
        this.data = data;
    }

}
