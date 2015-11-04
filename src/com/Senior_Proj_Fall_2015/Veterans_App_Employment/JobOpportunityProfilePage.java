package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityProfilePage extends Activity {

    protected TextView jobTitle;
    protected TextView jobDescription;
    protected TextView jobSubDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_opportunity_profile);

        jobTitle = (TextView) findViewById(R.id.textView_job_title);
        jobDescription = (TextView) findViewById(R.id.textView_job_description);
        jobSubDate = (TextView) findViewById(R.id.textView_job_submission_date);

        updateProfile();
    }

    private void updateProfile() {
        JSONObject selection = StartPage.dk.getJob();
        try {
        jobTitle.setText(selection.getString("title"));
        jobDescription.setText(selection.getString("description"));
        jobSubDate.setText(selection.getString("deadline"));
        }
        catch (JSONException e) {
            ;
        }
    }

}
