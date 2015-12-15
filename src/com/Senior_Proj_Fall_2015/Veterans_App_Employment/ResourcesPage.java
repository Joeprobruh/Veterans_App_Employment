package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Joe on 10/31/2015.
 * <p/>
 * Home to the Resources page. Each button represents a different website the user can go to for information.
 */
public class ResourcesPage extends Activity implements View.OnClickListener {

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
                goToUrl("http://www.va.com");
                break;

            case R.id.resource_2:
                goToUrl("http://www.heroestoheroes.org");
                break;

            case R.id.resource_3:
                goToUrl("http://www.vets4warriors.com");
                break;
        }
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
