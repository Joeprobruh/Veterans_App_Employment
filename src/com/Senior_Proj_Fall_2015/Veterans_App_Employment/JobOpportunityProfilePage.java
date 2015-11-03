package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityProfilePage extends Activity {

    protected Job selection;

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
        selection = JobOpportunityListPage.selection;
        jobTitle.setText(selection.getTitle());
        jobDescription.setText(selection.getDescription());
        jobSubDate.setText(selection.getSubmissionDate());
    }

}
