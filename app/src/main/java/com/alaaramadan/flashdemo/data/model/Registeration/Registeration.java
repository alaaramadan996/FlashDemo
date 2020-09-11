
package com.alaaramadan.flashdemo.data.model.Registeration;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registeration {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private DataRegsteration data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataRegsteration getData() {
        return data;
    }

    public void setData(DataRegsteration data) {
        this.data = data;
    }

}
