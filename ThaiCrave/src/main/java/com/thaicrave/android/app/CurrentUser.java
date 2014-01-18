package com.thaicrave.android.app;

import com.thaicrave.android.models.AuthenticatedUser;

public class CurrentUser extends AuthenticatedUser {

    private static AuthenticatedUser mCurrentUser;

    public static AuthenticatedUser get() {
        return mCurrentUser;
    }

    public static void set(AuthenticatedUser user) {
        mCurrentUser = user;
    }
}
