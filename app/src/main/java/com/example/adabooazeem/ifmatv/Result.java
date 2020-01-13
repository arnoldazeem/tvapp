package com.example.adabooazeem.ifmatv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("objects")
    @Expose
    private List<Objectss> objects = null;

    public List<Objectss> getObjects() {
        return objects;
    }

    public void setObjects(List<Objectss> objects) {
        this.objects = objects;
    }

    public Result withObjects(List<Objectss> objects) {
        this.objects = objects;
        return this;
    }

}
