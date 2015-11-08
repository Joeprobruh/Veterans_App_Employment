package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityProfilePage extends Activity implements View.OnClickListener {

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
        // jobSkills = (TextView) findViewById(R.id.textView_job_skills);
        jobDescription = (TextView) findViewById(R.id.textView_job_description);
        jobDeadline = (TextView) findViewById(R.id.textView_job_deadline);

        updateProfile();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textView_job_email_address:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + jobEmailAddress.getText().toString()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My email's subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "My email's body");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(JobOpportunityProfilePage.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textView_job_phone_number:
                String phoneNumber = jobPhoneNumber.getText().toString().trim();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
                break;
        }
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
            /*
            String skills = "";
            String[] skillArray = StartPage.dk.getJobSkills();
            for (int i = 0; i < skillArray.length - 1; i++) {
                skills += skillArray[i] + " , ";
            }
            skills += skillArray[skillArray.length - 1];
            jobSkills.setText(skills);
            */
            jobDescription.setText(selection.getString("description"));
            jobDeadline.setText(selection.getString("deadline"));
        } catch (JSONException e) {
            // No exceptions
        }
    }

}
