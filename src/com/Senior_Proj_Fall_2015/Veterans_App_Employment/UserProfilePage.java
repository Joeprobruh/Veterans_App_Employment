package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 11/7/2015.
 */
public class UserProfilePage extends Activity implements View.OnClickListener{

    protected TextView userName;
    protected TextView userAge;
    protected TextView userAddress;
    protected TextView userPhoneNumber;
    protected TextView userEmailAddress;
    protected TextView userBranch;
    protected TextView userRank;
    protected TextView userSkills;
    protected TextView userDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        userName = (TextView) findViewById(R.id.textView_user_name);
        userAge = (TextView) findViewById(R.id.textView_user_age);
        userAddress = (TextView) findViewById(R.id.textView_user_address);
        userPhoneNumber = (TextView) findViewById(R.id.textView_user_phone_number);
        userEmailAddress = (TextView) findViewById(R.id.textView_user_email_address);
        userBranch = (TextView) findViewById(R.id.textView_user_branch);
        userRank = (TextView) findViewById(R.id.textView_user_rank);
        userSkills = (TextView) findViewById(R.id.textView_user_skills);
        userDescription = (TextView) findViewById(R.id.textView_user_description);

        updateProfile();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textView_user_email_address:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + userEmailAddress.getText().toString()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My email's subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "My email's body");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(UserProfilePage.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textView_user_phone_number:
                String phoneNumber = userPhoneNumber.getText().toString().trim();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
                break;
        }
    }

    private void updateProfile() {
        JSONObject selection = StartPage.dk.getVetProfile();
        try {
            if (!selection.getString("name").equals("null")) {
                userName.setText(selection.getString("name"));
            }
            else {
                userName.setText("No name entered.");
            }
            if (!selection.getString("age").equals("null")) {
                userAge.setText(selection.getString("age"));
            }
            else {
                userAge.setText("No age entered.");
            }
            if (!selection.getString("address").equals("null")) {
                userAddress.setText(selection.getString("address"));
            }
            else {
                userAddress.setText("No address entered.");
            }
            if (!selection.getString("phone").equals("null")) {
                userPhoneNumber.setText(selection.getString("phone"));
            }
            else {
                userPhoneNumber.setText("No phone number entered.");
            }
            if (!selection.getString("email").equals("null")) {
                userEmailAddress.setText(selection.getString("email"));
            }
            else {
                userEmailAddress.setText("No e-mail address entered.");
            }
            try {
                String skills = "";
                String[] skillArray = StartPage.dk.getVetSkills();
                if (skillArray != null) {
                    for (int i = 0; i < skillArray.length - 1; i++) {
                        skills += skillArray[i] + "\r\n";
                    }
                    skills += skillArray[skillArray.length - 1];
                    userSkills.setText(skills);
                } else {
                    userSkills.setText("No skills chosen, or failed to load list of skills.");
                }
            }
            catch (NullPointerException e) {
                userSkills.setText("No skills chosen, or failed to load list of skills.");
            }
            if (!selection.getString("branch").equals("null")) {
                userBranch.setText(selection.getString("branch"));
            }
            else {
                userBranch.setText("No branch selected.");
            }
            if (!selection.getString("rank").equals("null")) {
                userRank.setText(selection.getString("rank"));
            }
            else {
                userRank.setText("No rank selected.");
            }
            if (!selection.getString("description").equals("null")) {
                userDescription.setText(selection.getString("description"));
            }
            else {
                userDescription.setText("No description entered.");
            }
        } catch (JSONException e) {
            // No exceptions
        }
    }

}
