
package com.alaaramadan.flashdemo.data.model.UserRestore;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRestore {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private DataRestore data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataRestore getData() {
        return data;
    }

    public void setData(DataRestore data) {
        this.data = data;
    }

}
