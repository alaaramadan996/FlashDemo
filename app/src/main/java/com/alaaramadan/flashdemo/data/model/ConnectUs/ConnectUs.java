
package com.alaaramadan.flashdemo.data.model.ConnectUs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectUs {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private DataConnectUs data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataConnectUs getData() {
        return data;
    }

    public void setData(DataConnectUs data) {
        this.data = data;
    }

}
