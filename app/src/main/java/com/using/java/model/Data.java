package com.using.java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Data {

    @SerializedName("data")
    @Expose
    private ArrayList<Android> data = null;

    public ArrayList<Android> getData() {
        return data;
    }

    public void setData(ArrayList<Android> data) {
        this.data = data;
    }
}
