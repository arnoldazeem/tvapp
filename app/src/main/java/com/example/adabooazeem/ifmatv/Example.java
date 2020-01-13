package com.example.adabooazeem.ifmatv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("result")
    @Expose
    private Result result;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Example withError(Error error) {
        this.error = error;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Example withResult(Result result) {
        this.result = result;
        return this;
    }
}