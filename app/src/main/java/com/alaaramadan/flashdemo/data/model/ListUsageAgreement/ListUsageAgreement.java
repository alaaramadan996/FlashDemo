
package com.alaaramadan.flashdemo.data.model.ListUsageAgreement;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUsageAgreement {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataUsageAgreement> data = null;

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

    public List<DataUsageAgreement> getData() {
        return data;
    }

    public void setData(List<DataUsageAgreement> data) {
        this.data = data;
    }

}
