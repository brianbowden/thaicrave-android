package com.thaicrave.android.models;

import com.google.gson.annotations.SerializedName;

public class AuthenticatedUser extends User {
    @SerializedName("authentication_token")
    private String mAuthToken;

    public String getAuthToken() {
        return mAuthToken;
    }
}
