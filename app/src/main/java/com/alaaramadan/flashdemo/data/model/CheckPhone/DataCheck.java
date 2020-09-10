
package com.alaaramadan.flashdemo.data.model.CheckPhone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCheck {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}
