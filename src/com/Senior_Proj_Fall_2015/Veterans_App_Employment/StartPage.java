package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 10/17/2015.
 * <p/>
 * The first page to be seen after LoadScreen. Allows the user to choose to either log in or create an account.
 */
public class StartPage extends Activity implements View.OnClickListener {

    protected static DataKeeper dk;
    protected static NetClient client;
    public static boolean isLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        dk = new DataKeeper();
        client = new NetClient(dk);

        Button login_OK =
            (Button) findViewById(R.id.login_OK);
        login_OK.setOnClickListener(this);

        Button sign_up_OK =
            (Button) findViewById(R.id.sign_up_OK);
        sign_up_OK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_OK:
                isLogIn = true;
                Intent i = new Intent(
                    StartPage.this, Login.class);
                startActivity(i);
                break;

            case R.id.sign_up_OK:
                isLogIn = false;
                Intent j = new Intent(
                    StartPage.this, SignUpPage.class);
                startActivity(j);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
