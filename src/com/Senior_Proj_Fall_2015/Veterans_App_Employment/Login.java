package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 10/17/2015.
 */
public class Login extends Activity implements View.OnClickListener {

    /*
    All users must login to the app before use, either as a user or as an employer

    Two fields, and two buttons
        -Username field
        -Password field
        -Back button
        -Submit button

    Accesses off-device database for verification
    Incorrect login fails right there, no activity change
    Correct login changes activity to Menu
     */

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

                /*
                Must verify login credentials here.
                - To make this simple for vets to use, try to find out how to store the user credentials
                    for future use, so login is only REQUIRED once, but then the vet can access their
                    account on other devices if they choose.

                Once verified:
                    Intent i = new Intent(
                        Login.this, JobOpportunityList.class);
                    startActivity(i);
                    break;
                */
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
