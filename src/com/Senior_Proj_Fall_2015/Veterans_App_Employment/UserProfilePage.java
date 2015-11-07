package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 11/7/2015.
 */
public class UserProfilePage extends Activity {

    protected TextView userName;
    protected TextView userAge;
    protected TextView userAddress;
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

    private void updateProfile() {
        JSONObject selection = StartPage.dk.getJob();
        try {
            userName.setText(selection.getString("name"));
            userAge.setText(selection.getString("age"));
            userAddress.setText(selection.getString("address"));
            userBranch.setText(selection.getString("branch"));
            userRank.setText(selection.getString("rank"));
            String skills = "";
            String[] skillArray = StartPage.dk.getVetSkills();
            for (int i = 0; i < skillArray.length - 1; i++) {
                skills += skillArray[i] + " , ";
            }
            skills += skillArray[skillArray.length - 1];
            userSkills.setText(skills);
            userDescription.setText(selection.getString("description"));
        } catch (JSONException e) {
            // No exceptions
        }
    }

}
