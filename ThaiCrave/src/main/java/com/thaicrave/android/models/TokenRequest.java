package com.thaicrave.android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bicxman on 1/9/14.
 */
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
