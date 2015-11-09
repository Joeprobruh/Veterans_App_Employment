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
import android.widget.Switch;

import java.util.ArrayList;

/**
 * Created by Joe on 10/24/2015.
 */
public class UserProfileCreationPage extends Activity implements View.OnClickListener {

    private static String militaryBranch = null;
    private static final CharSequence[] MILITARY_BRANCH = {"Air Force", "Army", "Coast Guard", "Marines", "Navy"};
    private static final CharSequence[] MILITARY_RANK_ARMY = {"E-1: Private", "E-2: Private 2",
        "E-3: Private First Class", "E-4: Specialist", "E-4: Corporal", "E-5: Sergeant", "E-6: Staff Sergeant",
        "E-7: Sergeant First Class", "E-8: Master Sergeant", "E-8: First Sergeant", "E-9: Sergeant Major",
        "E-9: Sergeant Major of the Army", "W-1: Warrant Officer", "W-2: Chief Warrant Officer 2",
        "W-3: Chief Warrant Officer 3", "W-4: Chief Warrant Officer 4", "W-5: Chief Warrant Officer 5",
        "O-1: Second Lieutenant", "O-2: First Lieutenant", "O-3: Captain", "O-4: Major", "O-5: Lieutenant Colonel",
        "O-6: Colonel", "O-7: Brigadier General", "O-8: Major General", "0-9: Lieutenant General", "O-10: General",
        "Special: General of the Army"};
    private static final CharSequence[] MILITARY_RANK_AIR_FORCE = {"E-1: Airman Basic", "E-2: Airman",
        "E-3: Airman First Class", "E-4: Senior Airman", "E-5: Staff Sergeant", "E-6: Technical Sergeant",
        "E-7: Master Sergeant", "E-7: Master Sergeant (Diamond)", "E-8: Senior Master Sergeant",
        "E-8: Senior Master Sergeant (Diamond)", "E-9: Chief Master Sergeant", "E-9: Chief Master Sergeant (Diamond)",
        "E-9: Command Chief Master Sergeant", "E-9 Spec: Chief Master of the Air Force", "O-1: Second Lieutenant",
        "O-2: First Lieutenant", "O-3: Captain", "O-4: Major", "O-5: Lieutenant Colonel", "O-6: Colonel",
        "O-7: Brigadier General", "O-8: Major General", "0-9: Lieutenant General",
        "O-10: General Air Force Chief of Staff", "Special: General of the Air Force"};
    private static final CharSequence[] MILITARY_RANK_NAVY = {"E-1: Seaman Recruit", "E-2: Seaman Apprentice",
        "E-3: Seaman", "E-4: Petty Officer 3rd Class", "E-5: Petty Officer 2nd Class", "E-6: Petty Officer 1st Class",
        "E-7: Chief Petty Officer", "E-8: Senior Chief Petty Officer", "E-9: Master Chief Petty Officer",
        "E-9: Fleet/Commander Master Chief Petty Officer", "E-9 Spec: Master Chief Petty Officer of the Navy",
        "W-2: Chief Warrant Officer 2", "W-3: Chief Warrant Officer 3", "W-4: Chief Warrant Officer 4",
        "W-5: Chief Warrant Officer 5", "O-1: Ensign", "O-2: Lieutenant Junior Grade", "O-3: Lieutenant",
        "O-4: Lieutenant Commander", "O-5: Commander", "O-6: Captain", "O-7: Rear Admiral (Lower Half)",
        "O-8: Rear Admiral (Upper Half)", "O-9: Vice Admiral", "O-10: Admiral Chief of Naval Ops/ Commandant of the CG",
        "O-11: Fleet Admiral"};
    private static final CharSequence[] MILITARY_RANK_MARINES = {"E-1: Private", "E-2: Private First Class",
        "E-3: Lance Corporal", "E-4: Corporal", "E-5: Sergeant", "E-6: Staff Sergeant", "E-7: Gunnery Sergeant",
        "E-8: Master Sergeant", "E-8: First Sergeant", "E-9: Master Gunnery Sergeant", "E-9: Sergeant Major",
        "E-9 Spec: Sergeant Major of the Marine Corps", "W-1: Warrant Officer", "W-2: Chief Warrant Officer 2",
        "W-3: Chief Warrant Officer 3", "W-4: Chief Warrant Officer 4", "W-5: Chief Warrant Officer 5",
        "O-1: Second Lieutenant", "O-2: First Lieutenant", "O-3: Captain", "O-4: Major", "O-5: Lieutenant Colonel",
        "O-6: Colonel", "O-7: Brigadier General", "O-8: Major General", "0-9: Lieutenant General", "O-10: General"};
    private static final CharSequence[] MILITARY_RANK_COAST_GUARD = {
        "E-1: Seaman Recruit", "E-2: Seaman Apprentice", "E-2: Fireman Apprentice", "E-2: Airman Apprentice",
        "E-3: Seaman", "E-3: Fireman", "E-3: Airman", "E-4: Petty Officer 3rd Class", "E-5: Petty Officer 2nd Class",
        "E-6: Petty Officer 1st Class", "E-7: Chief Petty Officer", "E-8: Senior Chief Petty Officer",
        "E-9: Master Chief Petty Officer", "E-9: Commander Master Chief Petty Officer",
        "E-9: Master Chief Petty Officer of the Coast Guard", "W-2: Chief Warrant Officer 2",
        "W-3: Chief Warrant Officer 3", "W-4: Chief Warrant Officer 4", "O-1: Ensign", "O-2: Lieutenant Junior Grade",
        "O-3: Lieutenant", "O-4: Lieutenant Commander", "O-5: Commander", "O-6: Captain",
        "O-7: Rear Admiral (Lower Half)", "O-8: Rear Admiral (Upper Half)", "O-9: Vice Admiral",
        "O-10: Admiral Chief of Naval Ops/ Commandant of the CG"};
    private static CheckBox[] SKILL_LIST;
    private static Boolean[] CURRENT_SELECTED_SKILLS = new Boolean[] {false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_creation_page);

        Button button_branch =
            (Button) findViewById(R.id.button_branch);
        button_branch.setOnClickListener(this);

        Button button_rank =
            (Button) findViewById(R.id.button_rank);
        button_rank.setOnClickListener(this);

        Button button_submit =
            (Button) findViewById(R.id.button_submit);
        button_submit.setOnClickListener(this);

        Button button_cancel =
            (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

        SKILL_LIST = new CheckBox[] {(CheckBox) findViewById(R.id.user_skill_first),
            (CheckBox) findViewById(R.id.user_skill_second),
            (CheckBox) findViewById(R.id.user_skill_third),
            (CheckBox) findViewById(R.id.user_skill_fourth),
            (CheckBox) findViewById(R.id.user_skill_fifth),
            (CheckBox) findViewById(R.id.user_skill_sixth)};

        populateFields();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_branch:
                showListMilitaryBranch(v);
                break;

            case R.id.button_rank:
                showListMilitaryRank(v);
                break;

            case R.id.button_submit:
                validateAndSubmitInputs();
                finish();
                break;

            case R.id.button_cancel:
                Intent j = new Intent(
                    UserProfileCreationPage.this, MenuPage.class);
                startActivity(j);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    private void showListMilitaryBranch(final View view) {
        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Branch");
        helpBuilder.setItems(MILITARY_BRANCH, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ((Button) view).setText(MILITARY_BRANCH[which]);
                militaryBranch = (String) MILITARY_BRANCH[which];
            }
        });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showListMilitaryRank(final View view) {
        if (militaryBranch == null) {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
            helpBuilder.setTitle("Choose Branch First!");
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();
        } else {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
            helpBuilder.setTitle("Choose " + militaryBranch + " Rank");
            final CharSequence[] branch;
            switch (militaryBranch) {
                case "Air Force":
                    branch = MILITARY_RANK_AIR_FORCE;
                    break;
                case "Army":
                    branch = MILITARY_RANK_ARMY;
                    break;
                case "Coast Guard":
                    branch = MILITARY_RANK_COAST_GUARD;
                    break;
                case "Marines":
                    branch = MILITARY_RANK_MARINES;
                    break;
                case "Navy":
                    branch = MILITARY_RANK_NAVY;
                    break;
                default:
                    branch = null;
                    break;
            }
            helpBuilder.setItems(branch, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (branch != null) {
                        ((Button) view).setText(branch[which]);
                    }
                }
            });
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();
        }
    }

    public void onUserCheckboxClicked(View view) {
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

    public void populateFields() {
        StartPage.client.loadVetProfile();
        SystemClock.sleep(250);
        String name = StartPage.dk.getVetDetail("name");
        String age = StartPage.dk.getVetDetail("age");
        String address = StartPage.dk.getVetDetail("address");
        String phone = StartPage.dk.getVetDetail("phone");
        String email = StartPage.dk.getVetDetail("email");
        String sex = StartPage.dk.getVetDetail("sex");
        String branch = StartPage.dk.getVetDetail("branch");
        String rank = StartPage.dk.getVetDetail("rank");
        String[] skills;
        try {
            skills = StartPage.dk.getVetSkills();
        }
        catch (NullPointerException e) {
            skills = null;
        }
        String description = StartPage.dk.getVetDetail("description");

        if (name != null) {
            ((EditText) findViewById(R.id.user_editText_name)).setText(name);
        }
        if (age != null) {
            ((EditText) findViewById(R.id.user_editText_age)).setText(age);
        }
        if (address != null) {
            ((EditText) findViewById(R.id.user_editText_address)).setText(address);
        }
        if (phone != null) {
            ((EditText) findViewById(R.id.user_editText_phone_number)).setText(phone);
        }
        if (email != null) {
            ((EditText) findViewById(R.id.user_editText_email_address)).setText(email);
        }
        if (sex != null) {
            if (sex.equals("Female")) {
                ((Switch) findViewById(R.id.user_switch_sex)).setChecked(true);
            }
            else {
                ((Switch) findViewById(R.id.user_switch_sex)).setChecked(false);
            }
        }
        if (branch != null) {
            ((Button) findViewById(R.id.button_branch)).setText(branch);
        }
        if (rank != null) {
            ((Button) findViewById(R.id.button_rank)).setText(rank);
        }
        for (int i = 0; i < SKILL_LIST.length; i++) {
            SKILL_LIST[i].setChecked(false);
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
        else {
            if (description != null) {
                ((EditText) findViewById(R.id.user_editText_description)).setText(description);
            }
        }
    }

    public void validateAndSubmitInputs() {
        StartPage.client.addVetProfile(((EditText) findViewById(R.id.user_editText_name)).getText().toString().trim(),
            ((EditText) findViewById(R.id.user_editText_age)).getText().toString().trim(),
            ((EditText) findViewById(R.id.user_editText_description)).getText().toString().trim(),
            ((EditText) findViewById(R.id.user_editText_address)).getText().toString().trim(),
            ((EditText) findViewById(R.id.user_editText_phone_number)).getText().toString().trim(),
            ((EditText) findViewById(R.id.user_editText_email_address)).getText().toString().trim(),
            ((Switch) findViewById(R.id.user_switch_sex)).isChecked() ?
                ((Switch) findViewById(R.id.user_switch_sex)).getTextOn().toString().trim() :
                ((Switch) findViewById(R.id.user_switch_sex)).getTextOff().toString().trim(),
            ((Button) findViewById(R.id.button_branch)).getText().toString().trim(),
            ((Button) findViewById(R.id.button_rank)).getText().toString().trim());
        String[] skillArray = new String[SKILL_LIST.length];
        int pointer = 0;
        for(int i = 0; i < SKILL_LIST.length; i++) {
            if (SKILL_LIST[i].isChecked()) {
                skillArray[pointer] = SKILL_LIST[i].getText().toString();
                pointer++;
            }
        }
        SystemClock.sleep(500);
        StartPage.client.addVetSkill(skillArray);
        SystemClock.sleep(500);
    }

}
