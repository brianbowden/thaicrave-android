package com.thaicrave.android.toolbox;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

    public static void showCenterToast(String text, Context context) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
