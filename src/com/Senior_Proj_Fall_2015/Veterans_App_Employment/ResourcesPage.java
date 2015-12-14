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

    NOT FINISHED, no actual resources added.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page);

        Button resource_1 =
            (Button) findViewById(R.id.resource_1);
        resource_1.setOnClickListener(this);
        Button resource_2 =
            (Button) findViewById(R.id.resource_2);
        resource_2.setOnClickListener(this);
        Button resource_3 =
            (Button) findViewById(R.id.resource_3);
        resource_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resource_1:

                break;

            case R.id.resource_2:

                break;

            case R.id.resource_3:

                break;
        }
    }

}
