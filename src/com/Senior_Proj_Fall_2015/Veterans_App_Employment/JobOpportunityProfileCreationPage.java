package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityProfileCreationPage extends Activity implements View.OnClickListener {

    private static final String[] CONTACT_METHODS = new String[] {"Phone Number", "E-mail Address", "Website URL",
        "Snail Mail"};
    private static CheckBox[] SKILL_LIST;
    private static Boolean[] CURRENT_SELECTED_SKILLS = new Boolean[] {false, false, false, false, false, false};

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

        SKILL_LIST = new CheckBox[] {(CheckBox) findViewById(R.id.job_skill_first),
            (CheckBox) findViewById(R.id.job_skill_second),
            (CheckBox) findViewById(R.id.job_skill_third),
            (CheckBox) findViewById(R.id.job_skill_fourth),
            (CheckBox) findViewById(R.id.job_skill_fifth),
            (CheckBox) findViewById(R.id.job_skill_sixth)};

        if (StartPage.dk.getJob() != null) {
            populateFields();
        }
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

    public void onCheckboxClicked(View view) {
        boolean isChecked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.user_skill_first:
                if (isChecked) {
                    CURRENT_SELECTED_SKILLS[0] = true;
                }
                else {
                    CURRENT_SELECTED_SKILLS[0] = false;
                }
                break;
            case R.id.user_skill_second:
                if (isChecked) {
                    CURRENT_SELECTED_SKILLS[1] = true;
                }
                else {
                    CURRENT_SELECTED_SKILLS[1] = false;
                }
                break;
            case R.id.user_skill_third:
                if (isChecked) {
                    CURRENT_SELECTED_SKILLS[2] = true;
                }
                else {
                    CURRENT_SELECTED_SKILLS[2] = false;
                }
                break;
            case R.id.user_skill_fourth:
                if (isChecked) {
                    CURRENT_SELECTED_SKILLS[3] = true;
                }
                else {
                    CURRENT_SELECTED_SKILLS[3] = false;
                }
                break;
            case R.id.user_skill_fifth:
                if (isChecked) {
                    CURRENT_SELECTED_SKILLS[4] = true;
                }
                else {
                    CURRENT_SELECTED_SKILLS[4] = false;
                }
                break;
            case R.id.user_skill_sixth:
                if (isChecked) {
                    CURRENT_SELECTED_SKILLS[5] = true;
                }
                else {
                    CURRENT_SELECTED_SKILLS[5] = false;
                }
                break;
        }
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
        SystemClock.sleep(250);
        String title = StartPage.dk.getJobDetail("title");
        String company = StartPage.dk.getJobDetail("company");
        String description = StartPage.dk.getJobDetail("description");
        String[] skills;
        try {
            skills = StartPage.dk.getVetSkills();
        }
        catch (NullPointerException e) {
            skills = null;
        }
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
        if (skills != null) {
            for (int i = 0; i < skills.length; i++) {
                for (int j = 0; j < SKILL_LIST.length; j++) {
                    if (skills[i].equals(SKILL_LIST[j].getText().toString())) {
                        CURRENT_SELECTED_SKILLS[j] = true;
                        SKILL_LIST[j].setChecked(true);
                        break;
                    }
                }
            }
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


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StartPage.client.addJob(
                    (StartPage.dk.getJob() != null) ?
                        StartPage.dk.getJobDetail("jobid") :
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
                String[] skillArray = new String[SKILL_LIST.length];
                int pointer = 0;
                for(int i = 0; i < SKILL_LIST.length; i++) {
                    if (SKILL_LIST[i].isChecked()) {
                        skillArray[pointer] = SKILL_LIST[i].getText().toString();
                        pointer++;
                    }
                }
                while (!StartPage.client.getIsTaskDone()){
                    SystemClock.sleep(50);
                }
                StartPage.client.addJobSkill(skillArray);int i = 0;

                //   h.sendEmptyMessage(0);

            }
        });
        thread.start();
    }

}
