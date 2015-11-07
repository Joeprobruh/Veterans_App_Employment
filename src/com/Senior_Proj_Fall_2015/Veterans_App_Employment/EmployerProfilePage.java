package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 11/7/2015.
 */
public class EmployerProfilePage extends Activity {

    protected TextView employerCompany;
    protected TextView employerName;
    protected TextView employerTitle;
    protected TextView employerAddress;
    protected TextView employerPhoneNumber;
    protected TextView employerEmailAddress;
    protected TextView employerDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile_page);

        employerCompany = (TextView) findViewById(R.id.textView_employer_company);
        employerName = (TextView) findViewById(R.id.textView_employer_name);
        employerTitle = (TextView) findViewById(R.id.textView_employer_title);
        employerAddress = (TextView) findViewById(R.id.textView_employer_address);
        employerPhoneNumber = (TextView) findViewById(R.id.textView_employer_phone_number);
        employerEmailAddress = (TextView) findViewById(R.id.textView_employer_email_address);
        employerDescription = (TextView) findViewById(R.id.textView_employer_description);

        updateProfile();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void updateProfile() {
        JSONObject selection = StartPage.dk.getJob();
        try {
            employerCompany.setText(selection.getString("company"));
            employerName.setText(selection.getString("name"));
            employerTitle.setText(selection.getString("title"));
            employerAddress.setText(selection.getString("address"));
            employerPhoneNumber.setText(selection.getString("phone"));
            employerEmailAddress.setText(selection.getString("email"));
            employerDescription.setText(selection.getString("description"));
        } catch (JSONException e) {
            // No exceptions
        }
    }

}
