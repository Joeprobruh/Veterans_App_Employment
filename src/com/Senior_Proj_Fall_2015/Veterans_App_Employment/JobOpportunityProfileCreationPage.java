package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Joe on 10/31/2015.
 * <p/>
 * Creates the Job profile Creation/Editing page.
 */
public class JobOpportunityProfileCreationPage extends Activity implements View.OnClickListener {

    private static final String[] CONTACT_METHODS = new String[]{"Phone Number", "E-mail Address", "Website URL",
        "Snail Mail"};
    private static CharSequence[] listOfSkills;
    private static boolean[] checkedSkills = null;
    ArrayAdapter<String> adapter;

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

        Button button_skills =
            (Button) findViewById(R.id.button_skills);
        button_skills.setOnClickListener(this);

        final Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                listOfSkills = StartPage.dk.getSkills();
                checkedSkills = new boolean[listOfSkills.length];
                for (int i = 0; i < checkedSkills.length; i++) {
                    checkedSkills[i] = false;
                }
                populateFields();
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StartPage.client.loadSkills();
                while (!StartPage.client.getIsTaskDone()) {
                    SystemClock.sleep(50);
                }
                h.sendEmptyMessage(0);
            }
        });
        thread.start();

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

            case R.id.button_skills:
                showListSkills(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * Generates a pop-up window with the possible choices for the Preferred Contact Method.
     *
     * @param view
     */
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

    /**
     * Generates a pop-up window with the list of possible skill choices, with a search bar to narrow down the skills by
     * name.
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showListSkills(final View view) {
        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View popupLayout = inflater.inflate(R.layout.activity_skill_search_dialog, null);
        helpBuilder.setView(popupLayout);
        EditText searchBar = (EditText) popupLayout.findViewById(R.id.editText_job_skill_search);
        searchBar.addTextChangedListener(filterTextWatcher);
        ListView skillList = (ListView) popupLayout.findViewById(R.id.listView_skill_list);
        skillList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        skillList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                boolean test = ((CheckedTextView) view).isChecked();
                checkedSkills[position] = test;
            }
        });
        adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice,
            (String[]) listOfSkills);
        skillList.setAdapter(adapter);
        if (checkedSkills != null) {
            for (int i = 0; i < checkedSkills.length; i++) {
                if (checkedSkills[i]) {
                    skillList.setItemChecked(i, true);
                }
            }
        }
        helpBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                int count = 0;
                for (int i = 0; i < checkedSkills.length; i++) {
                    if (checkedSkills[i]) {
                        count++;
                    }
                }
                if (count == 1) {
                    ((Button) view).setText(count + " Skill Chosen");
                } else {
                    ((Button) view).setText(count + " Skills Chosen");
                }
            }
        });
        AlertDialog dialog = helpBuilder.create();
        dialog.show();
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            adapter.getFilter().filter(s);
        }
    };

    /**
     * If the Job profile is to be edited, populates the fields with the existing information in the database.
     */
    public void populateFields() {
        SystemClock.sleep(250);
        String title = StartPage.dk.getJobDetail("title");
        String company = StartPage.dk.getJobDetail("company");
        String description = StartPage.dk.getJobDetail("description");
        String[] skills;
        try {
            skills = StartPage.dk.getJobSkills();
        } catch (NullPointerException e) {
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
                for (int j = 0; j < listOfSkills.length; j++) {
                    if (skills[i].equals(listOfSkills[j])) {
                        checkedSkills[j] = true;
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

    /**
     * Submits data from the page's fields to the database, then submits the skills as a separately linked set.
     */
    public void validateAndSubmitInputs() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StartPage.client.addJob(
                    (StartPage.dk.getJob() != null) ?
                        StartPage.dk.getJobDetail("jobid") : "0",
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
                while (!StartPage.client.getIsTaskDone()) {
                    SystemClock.sleep(50);
                }
                final String[] skillArray = new String[listOfSkills.length];
                int pointer = 0;
                for (int i = 0; i < checkedSkills.length; i++) {
                    if (checkedSkills[i]) {
                        skillArray[pointer++] = listOfSkills[i].toString();
                    }
                }
                StartPage.client.addJobSkill(skillArray);
                while (!StartPage.client.getIsTaskDone()) {
                    SystemClock.sleep(50);
                }
            }
        });
        thread.start();
    }
}
