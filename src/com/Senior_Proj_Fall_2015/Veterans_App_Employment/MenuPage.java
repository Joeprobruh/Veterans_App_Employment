package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
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
                    StartPage.client.loadJobs();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(500);
                    }
                    StartPage.client.loadVetProfile();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(500);
                    }
                    StartPage.client.loadEmployers();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(500);
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
                    StartPage.client.loadJobsByEmployer();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(500);
                    }
                    StartPage.client.loadVets();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(500);
                    }
                    StartPage.client.loadEmployerProfile();
                    while (!StartPage.client.getIsTaskDone()){
                        SystemClock.sleep(500);
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
            Button button_search_users =
                (Button) findViewById(R.id.button_search_users);
            button_search_users.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_search_jobs:
                Intent i = new Intent(
                    MenuPage.this, JobOpportunityListPage.class);
                startActivity(i);
                break;
            case R.id.button_search_employer_list:
                Intent j = new Intent(
                    MenuPage.this, EmployerListPage.class);
                startActivity(j);
                break;
            case R.id.button_view_profile:
                Intent k = new Intent(
                    MenuPage.this, UserProfilePage.class);
                startActivity(k);
                break;
            case R.id.button_edit_profile:
                Intent r = new Intent(
                    MenuPage.this, UserProfileCreationPage.class);
                startActivity(r);
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
                Intent n = new Intent(
                    MenuPage.this, JobOpportunityListByEmployerPage.class);
                startActivity(n);
                break;
            case R.id.button_search_users:
                Intent q = new Intent(
                    MenuPage.this, UserListPage.class);
                startActivity(q);
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
