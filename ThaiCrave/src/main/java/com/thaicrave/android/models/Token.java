package com.thaicrave.android.models;

import com.google.gson.annotations.SerializedName;

public class Token extends BaseModel {
    public Token() {}

    @SerializedName("token")
    private String token;

    public String getToken() {
        return this.token;
    }
}
