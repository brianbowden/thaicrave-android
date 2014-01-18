package com.thaicrave.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.thaicrave.android.app.CurrentUser;
import com.thaicrave.android.app.TcActivity;
import com.thaicrave.android.app.Volleyball;
import com.thaicrave.android.models.AuthenticatedUser;
import com.thaicrave.android.models.User;
import com.thaicrave.android.toolbox.GsonRequest;
import com.thaicrave.android.toolbox.MultiListener;
import com.thaicrave.android.toolbox.Utils;

public class SignUpActivity extends TcActivity {

    public static void start(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, SignUpActivity.class));
        }
    }

    private EditText first_name_field;
    private EditText last_name_field;
    private EditText email_field;
    private EditText password_field;
    private EditText confirm_password_field;
    private Button submit_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        first_name_field = (EditText) findViewById(R.id.sign_up_first_name_et);
        last_name_field = (EditText) findViewById(R.id.sign_up_last_name_et);
        email_field = (EditText) findViewById(R.id.sign_up_username_et);
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

        EditText[] requiredFields = new EditText[] {
                first_name_field,
                last_name_field,
                email_field,
                password_field
        };

        for (EditText field : requiredFields) {
            if (field.getText().toString().trim().equals("")) {
                field.setError(getString(R.string.generic_required));
                successful = false;
            }
        }

        if (!confirm_password_field.getText().toString().equals(password_field.getText().toString())) {
            confirm_password_field.setError(getString(R.string.sign_up_error_pass_match));
            successful = false;
        }

        if (successful) {
            User newUser = new User();
            newUser.setFirstName(first_name_field.getText().toString());
            newUser.setLastName(last_name_field.getText().toString());
            newUser.setEmail(email_field.getText().toString());
            newUser.setPassword(password_field.getText().toString());
            newUser.setPasswordConfirm(confirm_password_field.getText().toString());

            GsonRequest<AuthenticatedUser> req = new GsonRequest<AuthenticatedUser>(
                    Request.Method.POST,
                    Volleyball.getUrl("users"),
                    newUser,
                    AuthenticatedUser.class,
                    getUserListener()
            );

            Volleyball.addReq(req);
        }
    }

    private MultiListener<AuthenticatedUser> getUserListener() {
        return MultiListener.create(new MultiListener.TcListener<AuthenticatedUser>() {
            @Override
            public void onResponse(AuthenticatedUser user) {
                if (user != null) {
                    CurrentUser.set(user);
                } else {
                    Utils.showCenterToast("User is null!", CONTEXT);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = Volleyball.getErrorMessage(error);
                String toastMessage = message != null ? message : "Error signing up! Please try again.";
                Utils.showCenterToast(toastMessage, CONTEXT);
            }
        });
    }
}
