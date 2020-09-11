
package com.alaaramadan.flashdemo.data.model.ListPrivacyPolicy;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrivacyPolicy {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataPrivacyPolicy> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataPrivacyPolicy> getData() {
        return data;
    }

    public void setData(List<DataPrivacyPolicy> data) {
        this.data = data;
    }

}
