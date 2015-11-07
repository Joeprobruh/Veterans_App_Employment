package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityProfileCreationPage extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_opportunity_profile_creation_page);

        Button button_submit =
            (Button) findViewById(R.id.button_submit);
        button_submit.setOnClickListener(this);

        Button button_cancel =
            (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

        // populateFields();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_submit:
                validateAndSubmitInputs();
                finish();
                break;

            case R.id.button_cancel:
                Intent j = new Intent(
                    JobOpportunityProfileCreationPage.this, MenuPage.class);
                startActivity(j);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /*
    public void populateFields() {
        StartPage.client.loadEmployerProfile();
        SystemClock.sleep(250);
        String name = StartPage.dk.getEmployerDetail("name");
        String title = StartPage.dk.getEmployerDetail("title");
        String company = StartPage.dk.getEmployerDetail("company");
        String description = StartPage.dk.getEmployerDetail("description");
        String address = StartPage.dk.getEmployerDetail("address");
        String phoneNumber = StartPage.dk.getEmployerDetail("phone");
        String emailAddress = StartPage.dk.getEmployerDetail("email");

        if (name != null) {
            ((EditText) findViewById(R.id.employer_editText_name)).setText(name);
        }
        if (title != null) {
            ((EditText) findViewById(R.id.employer_editText_title)).setText(title);
        }
        if (company != null) {
            ((EditText) findViewById(R.id.employer_editText_company)).setText(company);
        }
        if (description != null) {
            ((EditText) findViewById(R.id.employer_editText_description)).setText(description);
        }
        if (address != null) {
            ((EditText) findViewById(R.id.employer_editText_address)).setText(address);
        }
        if (phoneNumber != null) {
            ((EditText) findViewById(R.id.employer_editText_phone_number)).setText(phoneNumber);
        }
        if (emailAddress != null) {
            ((EditText) findViewById(R.id.employer_editText_email_address)).setText(emailAddress);
        }
    }
    */

    public void validateAndSubmitInputs() {
        StartPage.client.addJob(
            "0",
            ((EditText) findViewById(R.id.job_editText_title)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_company)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_description)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_contact)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_address)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_phone_number)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_email_address)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_submission_date)).getText().toString().trim());
    }

}
