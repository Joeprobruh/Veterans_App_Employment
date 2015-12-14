package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 10/31/2015.
 */
public class MenuPage extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String user = getCurrentLoggedInUser();
        super.onCreate(savedInstanceState);
        if (user.equals("veteran")) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StartPage.client.setIsTaskDone(false);
                    StartPage.client.loadJobs();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(50);
                    }
                    StartPage.client.setIsTaskDone(false);
                    StartPage.client.loadVetProfile();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(50);
                    }
                    StartPage.client.setIsTaskDone(false);
                    StartPage.client.loadEmployers();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(50);
                    }
                }
            });
            thread.start();
            setContentView(R.layout.activity_menu_user_page);
            Button button_search_jobs =
                (Button) findViewById(R.id.button_search_jobs);
            button_search_jobs.setOnClickListener(this);
            Button button_search_employer_list =
                (Button) findViewById(R.id.button_search_employer_list);
            button_search_employer_list.setOnClickListener(this);
            Button button_edit_profile =
                (Button) findViewById(R.id.button_edit_profile);
            button_edit_profile.setOnClickListener(this);
            Button button_view_profile =
                (Button) findViewById(R.id.button_view_profile);
            button_view_profile.setOnClickListener(this);
            Button button_resources_page =
                (Button) findViewById(R.id.button_resources_page);
            button_resources_page.setOnClickListener(this);
        }
        else {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StartPage.client.setIsTaskDone(false);
                    StartPage.client.loadJobsByEmployer();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(50);
                    }
                    StartPage.client.setIsTaskDone(false);
                    StartPage.client.loadVets();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(50);
                    }
                    StartPage.client.setIsTaskDone(false);
                    StartPage.client.loadEmployerProfile();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(50);
                    }
                }
            });
            thread.start();
            setContentView(R.layout.activity_menu_employer_page);
            Button button_create_new_job_posting =
                (Button) findViewById(R.id.button_create_new_job_posting);
            button_create_new_job_posting.setOnClickListener(this);
            Button button_current_job_postings =
                (Button) findViewById(R.id.button_current_job_postings);
            button_current_job_postings.setOnClickListener(this);
            Button button_edit_employer_profile =
                (Button) findViewById(R.id.button_edit_employer_profile);
            button_edit_employer_profile.setOnClickListener(this);
            Button button_search_users =
                (Button) findViewById(R.id.button_search_users);
            button_search_users.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_search_jobs:
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadJobs();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread.start();
                if (StartPage.dk.getJobList() == null) {
                    AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
                    helpBuilder.setTitle("No jobs available.");
                    helpBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.show();
                }
                else {
                    Intent i = new Intent(
                        MenuPage.this, JobOpportunityListPage.class);
                    startActivity(i);
                }
                break;

            case R.id.button_search_employer_list:
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadEmployers();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread1.start();
                if (StartPage.dk.getEmployerList() == null) {
                    AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
                    helpBuilder.setTitle("No employers available.");
                    helpBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.show();
                }
                else {
                    Intent j = new Intent(
                        MenuPage.this, EmployerListPage.class);
                    startActivity(j);
                }
                break;

            case R.id.button_view_profile:
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadVetProfile();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread2.start();
                if (StartPage.dk.getVetProfile() == null) {
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
                else {
                    Intent k = new Intent(
                        MenuPage.this, UserProfilePage.class);
                    startActivity(k);
                }
                break;

            case R.id.button_edit_profile:
                Thread thread3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadVetProfile();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread3.start();
                Intent r = new Intent(
                    MenuPage.this, UserProfileCreationPage.class);
                startActivity(r);
                break;

            case R.id.button_edit_employer_profile:
                Thread thread4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadEmployerProfile();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread4.start();
                Intent s = new Intent(
                    MenuPage.this, EmployerProfileCreationPage.class);
                startActivity(s);
                break;

            case R.id.button_resources_page:
                Intent l = new Intent(
                    MenuPage.this, ResourcesPage.class);
                startActivity(l);
                break;

            case R.id.button_create_new_job_posting:
                StartPage.dk.clearJob();
                Intent m = new Intent(
                    MenuPage.this, JobOpportunityProfileCreationPage.class);
                startActivity(m);
                break;

            case R.id.button_current_job_postings:
                Thread thread5 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadJobsByEmployer();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread5.start();
                if (StartPage.dk.getJobList() == null) {
                    AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
                    helpBuilder.setTitle("Error: No job postings entered.");
                    helpBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.show();
                }
                else {
                    Intent n = new Intent(
                        MenuPage.this, JobOpportunityListByEmployerPage.class);
                    startActivity(n);
                }
                break;

            case R.id.button_search_users:
                Thread thread6 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartPage.client.setIsTaskDone(false);
                        StartPage.client.loadVets();
                        while (!StartPage.client.getIsTaskDone()) {
                            SystemClock.sleep(50);
                        }
                    }
                });
                thread6.start();
                if (StartPage.dk.getVetList() == null) {
                    AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
                    helpBuilder.setTitle("Error: No user profiles entered.");
                    helpBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.show();
                }
                else {
                    Intent q = new Intent(
                        MenuPage.this, UserListPage.class);
                    startActivity(q);
                }
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

    public String getCurrentLoggedInUser () {
        return StartPage.client.getRole();
    }

}
