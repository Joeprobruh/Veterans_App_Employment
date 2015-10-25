package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

/**
 * Created by Joe on 10/24/2015.
 */
public class UserProfileCreationPage extends Activity implements View.OnClickListener {

    private static volatile String militaryBranch = "Branch...";
    private static String militaryRank = "Rank...";
    private static String militaryTitle = "Title...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_creation);

        Button button_branch =
            (Button) findViewById(R.id.button_branch);
        button_branch.setOnClickListener(this);

        Button button_title =
            (Button) findViewById(R.id.button_title);
        button_title.setOnClickListener(this);

        Button button_rank =
            (Button) findViewById(R.id.button_rank);
        button_rank.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_branch:
                showRadioboxMilitaryBranch(v);
                break;

            case R.id.button_rank:
                showRadioboxMilitaryRank(v);
                break;

            case R.id.button_title:
                showRadioboxMilitaryTitle(v);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    private void showRadioboxMilitaryBranch(final View view) {

        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Branch");

        LayoutInflater inflater = getLayoutInflater();
        final View checkboxLayout = inflater.inflate(R.layout.layout_radiobox_military_branch, null);
        helpBuilder.setView(checkboxLayout);

        helpBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    ((Button) view).setText(militaryBranch);
                }
            });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showRadioboxMilitaryRank(final View view) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Rank");

        LayoutInflater inflater = getLayoutInflater();
        View checkboxLayout = inflater.inflate(R.layout.layout_radiobox_military_rank, null);
        helpBuilder.setView(checkboxLayout);

        helpBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    ((Button) view).setText(militaryRank);
                }
            });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showRadioboxMilitaryTitle(final View view) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Title");

        LayoutInflater inflater = getLayoutInflater();
        View checkboxLayout = inflater.inflate(R.layout.layout_radiobox_military_title, null);
        helpBuilder.setView(checkboxLayout);

        helpBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    ((Button) view).setText(militaryRank);
                }
            });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_air_force:
                if (checked)
                    militaryBranch = "Air Force";
                break;
            case R.id.radio_army:
                if (checked)
                    militaryBranch = "Army";
                break;
            case R.id.radio_coast_guard:
                if (checked)
                    militaryBranch = "Coast Guard";
                break;
            case R.id.radio_marines:
                if (checked)
                    militaryBranch = "Marines";
                break;
            case R.id.radio_navy:
                if (checked)
                    militaryBranch = "Navy";
                break;
        }
    }

}
