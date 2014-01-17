package com.thaicrave.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity {

    public static void start(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, SignUpActivity.class));
        }
    }

    private EditText username_field;
    private EditText password_field;
    private EditText confirm_password_field;
    private Button submit_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username_field = (EditText) findViewById(R.id.sign_up_username_et);
        password_field = (EditText) findViewById(R.id.sign_up_password_et);
        confirm_password_field = (EditText) findViewById(R.id.sign_up_password_confirmation_et);
        submit_button = (Button) findViewById(R.id.sign_up_submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSignUpData();
            }
        });
    }

    private void processSignUpData() {
        boolean successful = true;

        if (username_field.getText().toString().trim().equals("")) {
            username_field.setError("Required");

            successful = false;
        }

        if (password_field.getText().toString().trim().equals("")) {
            password_field.setError("Required");

            successful = false;
        }

        if (!confirm_password_field.getText().toString().equals(password_field.getText().toString())) {
            confirm_password_field.setError("Does not match password");

            successful = false;
        }

        if (successful) {
            // send to API
        }
    }
}
