
package com.alaaramadan.flashdemo.data.model.ListCity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

}
