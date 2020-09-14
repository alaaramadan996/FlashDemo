
package com.alaaramadan.flashdemo.data.model.GetWinners;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWinners {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<DataWinner> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataWinner> getData() {
        return data;
    }

    public void setData(List<DataWinner> data) {
        this.data = data;
    }

}
