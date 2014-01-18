package com.thaicrave.android.models;

import com.google.gson.annotations.SerializedName;

public class TokenRequest {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
