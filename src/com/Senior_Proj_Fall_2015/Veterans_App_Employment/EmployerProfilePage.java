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
 * <p/>
 * Generates the profile page for the Employer chosen in the EmployerListPage.
 */
public class EmployerProfilePage extends Activity implements View.OnClickListener {

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

    /**
     * When the e-mail address or phone number are clicked, activates the corresponding response to either send an
     * email or make a phone call.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

    /**
     * Populates the fields of the page with the Employer profile's information. If data is missing, fills in the space
     * with the corresponding message.
     */
    private void updateProfile() {
        JSONObject selection = StartPage.dk.getEmployerProfile();
        try {
            if (!selection.getString("title").equals("null")) {
                employerTitle.setText(selection.getString("title"));
            } else {
                employerTitle.setText("No title entered.");
            }
            if (!selection.getString("company").equals("null")) {
                employerCompany.setText(selection.getString("company"));
            } else {
                employerCompany.setText("No company name entered.");
            }
            if (!selection.getString("name").equals("null")) {
                employerName.setText(selection.getString("name"));
            } else {
                employerName.setText("No name entered.");
            }
            if (!selection.getString("address").equals("null")) {
                employerAddress.setText(selection.getString("address"));
            } else {
                employerAddress.setText("No address entered.");
            }
            if (!selection.getString("phone").equals("null")) {
                employerPhoneNumber.setText(selection.getString("phone"));
            } else {
                employerPhoneNumber.setText("No phone number entered.");
            }
            if (!selection.getString("email").equals("null")) {
                employerEmailAddress.setText(selection.getString("email"));
            } else {
                employerEmailAddress.setText("No e-mail address entered.");
            }
            if (!selection.getString("description").equals("null")) {
                employerDescription.setText(selection.getString("description"));
            } else {
                employerDescription.setText("No description entered.");
            }
        } catch (JSONException e) {
            // No exceptions
        } catch (NullPointerException e) {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
            helpBuilder.setTitle("Error: Profile has not been created.");
            helpBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();
        }
    }
}
