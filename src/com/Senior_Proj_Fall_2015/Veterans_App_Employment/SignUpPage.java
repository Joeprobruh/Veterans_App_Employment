package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joe on 10/17/2015.
 */
public class SignUpPage extends Activity implements View.OnClickListener {

    private static final String regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
    private Pattern pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        Button submit_OK =
            (Button) findViewById(R.id.submit_OK);
        submit_OK.setOnClickListener(this);

        pattern = Pattern.compile(regExp);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.submit_OK:
                sendUserInfo(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void sendUserInfo(View view) {

        //collect user entered name and password values
        TextView status = (TextView) findViewById(R.id.attempt_status);
        EditText userName = (EditText) findViewById(R.id.username_text_field);
        EditText password = (EditText) findViewById(R.id.password_text_field);
        EditText passwordConfirm = (EditText) findViewById(R.id.confirm_password_text_field);
        String user = userName.getText().toString();
        String pass = password.getText().toString();
        String passConf = passwordConfirm.getText().toString();

        if (!pass.equals(passConf)) {

            // Passwords do not match
            status.setText("Passwords do not match, try again.");
            ((EditText) findViewById(R.id.password_text_field)).getText().clear();
            ((EditText) findViewById(R.id.confirm_password_text_field)).getText().clear();
        }
        else if (!pattern.matcher(pass).matches()) {

            // Password contains an illegal character
            status.setText("Password does not contain necessary characters. Try again.");
            ((EditText) findViewById(R.id.password_text_field)).getText().clear();
            ((EditText) findViewById(R.id.confirm_password_text_field)).getText().clear();
        }
        else if (verify(user, pass)) {
            /**
             * Idea: Store user/pass combo in local memory to be used in future login attempts??? POSSIBLE?????
             */
            status.setText("Set up correctly. Continue...");
            Intent intent = new Intent(SignUpPage.this, MenuPage.class);
            startActivity(intent);
        }
        else {
            // Username is taken, must
            status.setText("That username is already taken. Try again.");
            ((EditText) findViewById(R.id.username_text_field)).getText().clear();
        }
    }

    private boolean verify(final String username, final String password) {
        TextView status = (TextView) findViewById(R.id.attempt_status);
        final String role;
        if (((RadioButton) findViewById(R.id.radio_user)).isChecked()) {
            role = "veteran";
        }
        else {
            role = "employer";
        }
        status.setText("About to attempt signUp()");
        StartPage.client.signUp(username, password, role);
        SystemClock.sleep(250);
        status.setText("signUp finished...");
        return (StartPage.client.getSignUp());
    }
}
