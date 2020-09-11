
package com.alaaramadan.flashdemo.data.model.ListUsageAgreement;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUsageAgreement {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataUsageAgreement> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataUsageAgreement> getData() {
        return data;
    }

    public void setData(List<DataUsageAgreement> data) {
        this.data = data;
    }

}
