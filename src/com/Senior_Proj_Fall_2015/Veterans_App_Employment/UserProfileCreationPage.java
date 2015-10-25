package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 10/24/2015.
 */
public class UserProfileCreationPage extends Activity implements View.OnClickListener {

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
                showRadioboxMilitaryBranch();
                break;

            case R.id.button_rank:
                showRadioboxMilitaryRank();
                break;

            case R.id.button_title:
                showRadioboxMilitaryTitle();
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

    private void showRadioboxMilitaryBranch() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Branch");

        LayoutInflater inflater = getLayoutInflater();
        View checkboxLayout = inflater.inflate(R.layout.layout_radiobox_military_branch, null);
        helpBuilder.setView(checkboxLayout);

        helpBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                }
            });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }


    private void showRadioboxMilitaryRank() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Rank");

        LayoutInflater inflater = getLayoutInflater();
        View checkboxLayout = inflater.inflate(R.layout.layout_radiobox_military_rank, null);
        helpBuilder.setView(checkboxLayout);

        helpBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                }
            });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void showRadioboxMilitaryTitle() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Military Title");

        LayoutInflater inflater = getLayoutInflater();
        View checkboxLayout = inflater.inflate(R.layout.layout_radiobox_military_title, null);
        helpBuilder.setView(checkboxLayout);

        helpBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                }
            });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

}
