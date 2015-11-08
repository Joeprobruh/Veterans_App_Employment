package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private static final String[] CONTACT_METHODS = new String[] {"Phone Number", "E-mail Address", "Website URL",
                                                                    "Snail Mail"};

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

        Button button_preferred_contact =
            (Button) findViewById(R.id.button_preferred_contact);
        button_preferred_contact.setOnClickListener(this);

        populateFields();
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

            case R.id.button_preferred_contact:
                showListPreferredContactMethod(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void showListPreferredContactMethod(final View view) {
        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Select Preferred Contact Method");
        helpBuilder.setItems(CONTACT_METHODS, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ((Button) view).setText(CONTACT_METHODS[which]);
            }
        });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    public void populateFields() {
        StartPage.dk.getJob();
        SystemClock.sleep(250);
        String title = StartPage.dk.getJobDetail("title");
        String company = StartPage.dk.getJobDetail("company");
        String description = StartPage.dk.getJobDetail("description");
        String contact = StartPage.dk.getJobDetail("contact");
        String address = StartPage.dk.getJobDetail("address");
        String phoneNumber = StartPage.dk.getJobDetail("phone");
        String emailAddress = StartPage.dk.getJobDetail("email");
        String websiteURL = StartPage.dk.getJobDetail("url");
        String submissionDeadline = StartPage.dk.getJobDetail("deadline");
        String preferredContactMethod = StartPage.dk.getJobDetail("applymethod");

        if (title != null) {
            ((EditText) findViewById(R.id.job_editText_title)).setText(title);
        }
        if (company != null) {
            ((EditText) findViewById(R.id.job_editText_company)).setText(company);
        }
        if (description != null) {
            ((EditText) findViewById(R.id.job_editText_description)).setText(description);
        }
        if (contact != null) {
            ((EditText) findViewById(R.id.job_editText_contact)).setText(contact);
        }
        if (address != null) {
            ((EditText) findViewById(R.id.job_editText_address)).setText(address);
        }
        if (phoneNumber != null) {
            ((EditText) findViewById(R.id.job_editText_phone_number)).setText(phoneNumber);
        }
        if (emailAddress != null) {
            ((EditText) findViewById(R.id.job_editText_email_address)).setText(emailAddress);
        }
        if (websiteURL != null) {
            ((EditText) findViewById(R.id.job_editText_website_url)).setText(websiteURL);
        }
        if (preferredContactMethod != null) {
            ((Button) findViewById(R.id.button_preferred_contact)).setText(preferredContactMethod);
        }
        if (submissionDeadline != null) {
            ((EditText) findViewById(R.id.job_editText_submission_deadline)).setText(submissionDeadline);
        }
    }

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
            ((EditText) findViewById(R.id.job_editText_website_url)).getText().toString().trim(),
            ((Button) findViewById(R.id.button_preferred_contact)).getText().toString().trim(),
            ((EditText) findViewById(R.id.job_editText_submission_deadline)).getText().toString().trim());
    }

}
