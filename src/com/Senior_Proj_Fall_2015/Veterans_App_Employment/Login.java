package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Joe on 10/17/2015.
 */
public class Login extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button submit_OK =
            (Button) findViewById(R.id.submit_OK);
        submit_OK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.submit_OK:

                StartPage.client.login(
                    ((EditText) findViewById(R.id.login_username)).getText().toString(),
                    ((EditText) findViewById(R.id.login_password)).getText().toString());

                SystemClock.sleep(500);

                if (StartPage.client.getUserID() == null) {
                    ((TextView) findViewById(R.id.login_status)).setText("Username or Password incorrect, try again.");
                    ((EditText) findViewById(R.id.login_username)).getText().clear();
                    ((EditText) findViewById(R.id.login_password)).getText().clear();
                }

                else {
                    Intent i = new Intent(
                        Login.this, MenuPage.class);
                    startActivity(i);
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();

    }

}
