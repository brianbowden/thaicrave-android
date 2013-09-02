package com.thaicrave.android;

import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.thaicrave.android.app.Volleyball;
import com.thaicrave.android.models.Token;
import com.thaicrave.android.toolbox.GsonRequest;
import com.thaicrave.android.toolbox.MultiListener;
import com.thaicrave.android.toolbox.Utils;

import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends Activity {

    private EditText usernameET;
    private EditText passwordET;

    private TextView signUpTV;
    private Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        logInButton.setOnClickListener(getLogInClickListener());

        signUpTV.setPaintFlags(signUpTV.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        signUpTV.setOnClickListener(getSignUpClickListener());
    }

    private void initViews() {
        usernameET = (EditText) this.findViewById(R.id.login_username_et);
        passwordET = (EditText) this.findViewById(R.id.login_password_et);

        logInButton = (Button) this.findViewById(R.id.login_log_in_button);
        signUpTV = (TextView) this.findViewById(R.id.login_sign_up_tv);
    }

    private View.OnClickListener getLogInClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GsonRequest<Token> req = new GsonRequest<Token>(
                        Request.Method.POST,
                        "http://192.168.56.1:3000/api/tokens.json",
                        Token.class,
                        getTokenListener()
                );

                req.addParam("email", usernameET.getText().toString());
                req.addParam("password", passwordET.getText().toString());

                Volleyball.addReq(req);

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        };
    }

    private View.OnClickListener getSignUpClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stuff
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    private MultiListener<Token> getTokenListener() {
        return MultiListener.create(new MultiListener.TcListener<Token>() {
            @Override
            public void onResponse(Token token) {
                if (token != null) {
                    Utils.showCenterToast(token.getToken(), LoginActivity.this);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    Log.e("LOGIN", "Error: " + error.getMessage());

                    try {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            JSONObject resObj = new JSONObject(new String(error.networkResponse.data, "utf-8"));
                            String message = resObj.getString("message");

                            Utils.showCenterToast(message, LoginActivity.this);
                        }
                        else {
                            Utils.showCenterToast("Unable to log in! Please try again.", LoginActivity.this);
                        }
                    } catch (Exception e) {
                        Log.e("LOGIN", "Error: " + e.getMessage());
                        Utils.showCenterToast("Unable to log in! Please try again.", LoginActivity.this);
                    }
                }
            }
        });
    }
    
}
