package com.example.sopcode.testretrofit;

import com.google.gson.annotations.*;

import java.io.Serializable;

public class Bean implements Serializable {
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
