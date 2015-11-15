package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
    protected TextView jobWebsiteURL;
    protected TextView jobPreferredContactMethod;
    protected TextView jobDescription;
    protected TextView jobDeadline;
    protected TextView jobSkills;

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
        jobPreferredContactMethod = (TextView) findViewById(R.id.textView_job_contact_method);
        jobWebsiteURL = (TextView) findViewById(R.id.textView_job_website_url);
        jobDescription = (TextView) findViewById(R.id.textView_job_description);
        jobDeadline = (TextView) findViewById(R.id.textView_job_deadline);
        jobSkills = (TextView) findViewById(R.id.textView_job_skills);

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateProfile() {
        JSONObject selection = StartPage.dk.getJob();
        try {
            if (!selection.getString("title").equals("null")) {
                jobTitle.setText(selection.getString("title"));
            }
            else {
                jobTitle.setText("No title entered.");
            }
            if (!selection.getString("company").equals("null")) {
                jobCompany.setText(selection.getString("company"));
            }
            else {
                jobCompany.setText("No company name entered.");
            }
            if (!selection.getString("contact").equals("null")) {
                jobContact.setText(selection.getString("contact"));
            }
            else {
                jobContact.setText("No contact name entered.");
            }
            if (!selection.getString("address").equals("null")) {
                jobAddress.setText(selection.getString("address"));
            }
            else {
                jobAddress.setText("No address entered.");
            }
            if (!selection.getString("phone").equals("null")) {
                jobPhoneNumber.setText(selection.getString("phone"));
            }
            else {
                jobPhoneNumber.setText("No phone number entered.");
            }
            if (!selection.getString("email").equals("null")) {
                jobEmailAddress.setText(selection.getString("email"));
            }
            else {
                jobEmailAddress.setText("No e-mail address entered.");
            }
            try {
                String skills = "";
                String[] skillArray = StartPage.dk.getJobSkills();
                if (skillArray != null) {
                    for (int i = 0; i < skillArray.length - 1; i++) {
                        skills += skillArray[i] + "\r\n";
                    }
                    skills += skillArray[skillArray.length - 1];
                    jobSkills.setText(skills);
                } else {
                    jobSkills.setText("No skills chosen, or failed to load list of skills.");
                }
            }
            catch (NullPointerException e) {
                jobSkills.setText("No skills chosen, or failed to load list of skills.");
            }
            if (!selection.getString("url").equals("null")) {
                jobWebsiteURL.setText(selection.getString("url"));
            }
            else {
                jobWebsiteURL.setText("No website URL entered.");
            }
            if (!selection.getString("applymethod").equals("null")) {
                jobPreferredContactMethod.setText(selection.getString("applymethod"));
            }
            else {
                jobPreferredContactMethod.setText("No preferred contact method selected.");
            }
            if (!selection.getString("description").equals("null")) {
                jobDescription.setText(selection.getString("description"));
            }
            else {
                jobDescription.setText("No description entered.");
            }
            if (!selection.getString("deadline").equals("null")) {
                jobDeadline.setText(selection.getString("deadline"));
            }
            else {
                jobDeadline.setText("No deadline entered.");
            }
        }
        catch (JSONException e) {
            // No exceptions
        }
        catch (NullPointerException e) {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
            helpBuilder.setTitle("Error: Profile has not been created.");
            helpBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            helpBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    finish();
                }
            });
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();
        }
    }

}
