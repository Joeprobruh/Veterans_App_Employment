package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joe on 10/17/2015.
 * <p/>
 * Where a new User or Employer signs up for a new account. When correct username/password submitted, moves to
 * either UserProfileCreationPage or EmployerProfileCreationPage.
 */
public class SignUpPage extends Activity implements View.OnClickListener {

    private static final String regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
    private Pattern pattern;
    private String role;

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
        switch (v.getId()) {
            case R.id.submit_OK:
                sendUserInfo(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * The main method for signing up a user. Verifies the username is available, and verifies the password fits the
     * regular expression chosen.
     *
     * @param view
     */
    public void sendUserInfo(View view) {
        //collect user entered name and password values
        final TextView status = (TextView) findViewById(R.id.attempt_status);
        EditText userName = (EditText) findViewById(R.id.username_text_field);
        EditText password = (EditText) findViewById(R.id.password_text_field);
        EditText passwordConfirm = (EditText) findViewById(R.id.confirm_password_text_field);
        final String user = userName.getText().toString();
        final String pass = password.getText().toString();
        String passConf = passwordConfirm.getText().toString();

        if (!pass.equals(passConf)) {
            // Passwords do not match
            status.setText("Passwords do not match, try again.");
            ((EditText) findViewById(R.id.password_text_field)).getText().clear();
            ((EditText) findViewById(R.id.confirm_password_text_field)).getText().clear();
        } else if (!pattern.matcher(pass).matches()) {
            // Password contains an illegal character
            status.setText("Password does not contain necessary characters. Try again.");
            ((EditText) findViewById(R.id.password_text_field)).getText().clear();
            ((EditText) findViewById(R.id.confirm_password_text_field)).getText().clear();
        } else if (verify(user, pass)) {
            /**
             * Idea: Store user/pass combo in local memory to be used in future login attempts??? POSSIBLE?????
             */
            status.setText("Set up correctly. Continue...");
            if (role.equals("veteran")) {
                Intent intent = new Intent(SignUpPage.this, UserProfileCreationPage.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SignUpPage.this, EmployerProfileCreationPage.class);
                startActivity(intent);
            }
        } else {
            status.setText("Set up correctly. Continue...");
            final Handler h = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (StartPage.client.getSignUp()) {
                        TextView status = (TextView) findViewById(R.id.attempt_status);

                        if (role.equals("veteran")) {
                            Intent intent = new Intent(SignUpPage.this, UserProfileCreationPage.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(SignUpPage.this, EmployerProfileCreationPage.class);
                            startActivity(intent);
                        }
                    } else {
                        // Username is taken, must
                        status.setText("That username is already taken. Try again.");
                        ((EditText) findViewById(R.id.username_text_field)).getText().clear();
                    }
                }
            };
            if (((RadioButton) findViewById(R.id.radio_user)).isChecked()) {
                role = "veteran";
            } else {
                role = "employer";
            }
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StartPage.client.signUp(user, pass, role);
                    while (!StartPage.client.getIsTaskDone()) {
                        SystemClock.sleep(50);
                    }
                    h.sendEmptyMessage(0);
                }
            });
            thread.start();
        }
    }

    /**
     * Attempts to sign up the user.
     * <p/>
     * *****This method is most likely useless at this point, but I am unsure of what implications removing it might
     * have. I didn't create it. JJP
     *
     * @param username
     * @param password
     * @return
     */
    private boolean verify(final String username, final String password) {
        TextView status = (TextView) findViewById(R.id.attempt_status);
        if (((RadioButton) findViewById(R.id.radio_user)).isChecked()) {
            role = "veteran";
        } else {
            role = "employer";
        }
        status.setText("About to attempt signUp()");
        StartPage.client.signUp(username, password, role);
        SystemClock.sleep(250);
        status.setText("signUp finished...");
        return (StartPage.client.getSignUp());
    }
}
