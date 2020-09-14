
package com.alaaramadan.flashdemo.data.model.Login;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private CheckData data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CheckData getData() {
        return data;
    }

    public void setData(CheckData data) {
        this.data = data;
    }

}
