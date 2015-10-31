package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 10/31/2015.
 */
public class ResourcesPage extends Activity implements View.OnClickListener {

    /*
    Where we put all of our resource links
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
        switch (v.getId()) {
            case R.id.submit_OK:
                Intent i = new Intent(
                    ResourcesPage.this, UserProfileCreationPage.class);
                startActivity(i);
                break;
        }
    }

}
