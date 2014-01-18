package com.thaicrave.android;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.thaicrave.android.app.TcActivity;
import com.thaicrave.android.app.Volleyball;
import com.thaicrave.android.models.Token;
import com.thaicrave.android.models.TokenRequest;
import com.thaicrave.android.toolbox.GsonRequest;
import com.thaicrave.android.toolbox.MultiListener;
import com.thaicrave.android.toolbox.Utils;

import org.json.JSONObject;

public class LoginActivity extends TcActivity {

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

                TokenRequest tokenReq = new TokenRequest();
                tokenReq.setEmail(usernameET.getText().toString());
                tokenReq.setPassword(passwordET.getText().toString());

                GsonRequest<Token> req = new GsonRequest<Token>(
                        Request.Method.POST,
                        Volleyball.getUrl("tokens"),
                        tokenReq,
                        Token.class,
                        getTokenListener()
                );

                Volleyball.addReq(req);

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        };
    }

    private View.OnClickListener getSignUpClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.start(CONTEXT);
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
                    Utils.showCenterToast(token.getToken(), CONTEXT);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = Volleyball.getErrorMessage(error);

                if (message != null) {
                    Log.d("LOGIN", "Data: " + message);
                    Utils.showCenterToast(message, CONTEXT);
                } else {
                    Utils.showCenterToast(getString(R.string.login_error_unable_to_log_in), CONTEXT);
                }
            }
        });
    }
    
}
