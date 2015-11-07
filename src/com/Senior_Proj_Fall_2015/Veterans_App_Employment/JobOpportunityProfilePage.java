package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityProfilePage extends Activity {

    protected TextView jobTitle;
    protected TextView jobCompany;
    protected TextView jobAddress;
    protected TextView jobContact;
    protected TextView jobPhoneNumber;
    protected TextView jobEmailAddress;
    protected TextView jobDescription;
    protected TextView jobDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_opportunity_profile_page);

        jobTitle = (TextView) findViewById(R.id.textView_job_title);
        jobCompany = (TextView) findViewById(R.id.textView_job_company);
        jobAddress = (TextView) findViewById(R.id.textView_job_address);
        jobContact = (TextView) findViewById(R.id.textView_job_contact);
        jobPhoneNumber = (TextView) findViewById(R.id.textView_job_phone_number);
        jobEmailAddress = (TextView) findViewById(R.id.textView_job_email_address);
        jobDescription = (TextView) findViewById(R.id.textView_job_description);
        jobDeadline = (TextView) findViewById(R.id.textView_job_deadline);

        updateProfile();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void updateProfile() {
        JSONObject selection = StartPage.dk.getJob();
        try {
            jobTitle.setText(selection.getString("title"));
            jobCompany.setText(selection.getString("company"));
            jobAddress.setText(selection.getString("address"));
            jobContact.setText(selection.getString("contact"));
            jobPhoneNumber.setText(selection.getString("phone"));
            jobEmailAddress.setText(selection.getString("email"));
            jobDescription.setText(selection.getString("description"));
            jobDeadline.setText(selection.getString("deadline"));
        } catch (JSONException e) {
            // No exceptions
        }
    }

}
