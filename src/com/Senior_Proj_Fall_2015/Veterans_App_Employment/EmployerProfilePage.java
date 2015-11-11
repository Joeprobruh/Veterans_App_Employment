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
 * Created by Joe on 11/7/2015.
 */
public class EmployerProfilePage extends Activity implements View.OnClickListener{

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
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textView_employer_email_address:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + employerEmailAddress.getText().toString()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My email's subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "My email's body");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(EmployerProfilePage.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textView_employer_phone_number:
                String phoneNumber = employerPhoneNumber.getText().toString().trim();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void updateProfile() {
        JSONObject selection = StartPage.dk.getEmployerProfile();
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
