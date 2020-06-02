package com.example.shop.Pojo;

import com.example.shop.Data.BrandPart;
import com.example.shop.Data.Part;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrandsPojo {
    @SerializedName("result")
    @Expose
    private List<BrandPojo> result = null;

    public List <BrandPojo> getParts() {
        return result;
    }

    public void setParts(List<BrandPojo> result) {
        this.result = result;
    }
}
