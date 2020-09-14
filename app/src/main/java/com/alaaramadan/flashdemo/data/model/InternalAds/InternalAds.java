
package com.alaaramadan.flashdemo.data.model.InternalAds;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternalAds {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private DataInternalAds data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataInternalAds getData() {
        return data;
    }

    public void setData(DataInternalAds data) {
        this.data = data;
    }

}
