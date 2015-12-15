package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by Joe on 11/7/2015.
 * <p/>
 * Generates the profile page creator/editor. If profile is to be edited, populates fields which have been previously
 * filled and stored in the database for that particular profile.
 */
public class EmployerProfileCreationPage extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile_creation_page);

        Button button_submit =
            (Button) findViewById(R.id.button_submit);
        button_submit.setOnClickListener(this);

        Button button_cancel =
            (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

        populateFields();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_submit:
                validateAndSubmitInputs();
                if (StartPage.isLogIn) {
                    finish();
                } else {
                    Intent j = new Intent(
                        EmployerProfileCreationPage.this, MenuPage.class);
                    startActivity(j);
                }
                break;

            case R.id.button_cancel:
                if (StartPage.isLogIn) {
                    finish();
                } else {
                    Intent j = new Intent(
                        EmployerProfileCreationPage.this, MenuPage.class);
                    startActivity(j);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * Populates previously filled fields.
     */
    public void populateFields() {

        final Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
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
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StartPage.client.loadEmployerProfile();
                while (!StartPage.client.getIsTaskDone()) {
                    SystemClock.sleep(50);
                }
                h.sendEmptyMessage(0);
            }
        });
        thread.start();
    }

    /**
     * When the user presses submit, submits the new values to the database.
     */
    public void validateAndSubmitInputs() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StartPage.client.addEmployerProfile(
                    ((EditText) findViewById(R.id.employer_editText_name)).getText().toString().trim(),
                    ((EditText) findViewById(R.id.employer_editText_title)).getText().toString().trim(),
                    ((EditText) findViewById(R.id.employer_editText_company)).getText().toString().trim(),
                    ((EditText) findViewById(R.id.employer_editText_description)).getText().toString().trim(),
                    ((EditText) findViewById(R.id.employer_editText_address)).getText().toString().trim(),
                    ((EditText) findViewById(R.id.employer_editText_phone_number)).getText().toString().trim(),
                    ((EditText) findViewById(R.id.employer_editText_email_address)).getText().toString().trim());
            }
        });
        thread.start();
    }
}
