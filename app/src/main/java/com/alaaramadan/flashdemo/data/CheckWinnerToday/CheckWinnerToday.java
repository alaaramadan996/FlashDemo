
package com.alaaramadan.flashdemo.data.CheckWinnerToday;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckWinnerToday {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataCheck> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataCheck> getData() {
        return data;
    }

    public void setData(List<DataCheck> data) {
        this.data = data;
    }

}
