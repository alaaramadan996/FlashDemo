
package com.alaaramadan.flashdemo.data.model.ExternalAds;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalAds {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private DataExternal data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataExternal getData() {
        return data;
    }

    public void setData(DataExternal data) {
        this.data = data;
    }

}
