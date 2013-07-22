package com.thaicrave.android;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {

    TextView signUpTV;
    Button logInButton;

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
        logInButton = (Button) this.findViewById(R.id.login_log_in_button);
        signUpTV = (TextView) this.findViewById(R.id.login_sign_up_tv);
    }

    private View.OnClickListener getLogInClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
