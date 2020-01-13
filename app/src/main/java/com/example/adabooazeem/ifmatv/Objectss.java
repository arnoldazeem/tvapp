package com.example.adabooazeem.ifmatv;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Objectss {

    @SerializedName("attrs")
    @Expose
    private Attrs attrs;
    @SerializedName("showId")
    @Expose
    private String showId;

    public Attrs getAttrs() {
        return attrs;
    }

    public void setAttrs(Attrs attrs) {
        this.attrs = attrs;
    }

    public Object withAttrs(Attrs attrs) {
        this.attrs = attrs;
        return this;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Object withShowId(String showId) {
        this.showId = showId;
        return this;
    }

}