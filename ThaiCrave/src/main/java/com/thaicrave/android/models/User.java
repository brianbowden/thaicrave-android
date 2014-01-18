package com.thaicrave.android.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("password_confirmation")
    private String mPasswordConfirm;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getPasswordConfirm() {
        return mPasswordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.mPasswordConfirm = passwordConfirm;
    }
}
