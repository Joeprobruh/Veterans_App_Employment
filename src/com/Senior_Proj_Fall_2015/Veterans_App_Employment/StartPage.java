package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 10/17/2015.
 */
public class StartPage extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        Button login_OK =
            (Button) findViewById(R.id.login_OK);
        login_OK.setOnClickListener(this);

        Button sign_up_OK =
            (Button) findViewById(R.id.sign_up_OK);
        sign_up_OK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.login_OK:
                Intent i = new Intent(
                    StartPage.this, Login.class);
                startActivity(i);
                finish();
                break;

            case R.id.sign_up_OK:
                Intent j = new Intent(
                    StartPage.this, SignUpPage.class);
                startActivity(j);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

}
