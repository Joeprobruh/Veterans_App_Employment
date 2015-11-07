package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
            Button button_resources_page =
                (Button) findViewById(R.id.button_resources_page);
            button_resources_page.setOnClickListener(this);
        }
        else {
            setContentView(R.layout.activity_menu_employer_page);
            Button button_create_new_job_posting =
                (Button) findViewById(R.id.button_create_new_job_posting);
            button_create_new_job_posting.setOnClickListener(this);
            Button button_current_job_postings =
                (Button) findViewById(R.id.button_current_job_postings);
            button_current_job_postings.setOnClickListener(this);
            Button button_review_applicants =
                (Button) findViewById(R.id.button_review_applicants);
            button_review_applicants.setOnClickListener(this);
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
                    MenuPage.this, SearchEmployerDatabase.class);
                startActivity(j);
                break;
            case R.id.button_edit_profile:
                Intent k = new Intent(
                    //Moves to the UserProfileCreationPage, but we have to figure out how to populate the fields that
                    //  have already been filled in by the user
                    MenuPage.this, UserProfileCreationPage.class);
                startActivity(k);
                break;
            case R.id.button_resources_page:
                Intent l = new Intent(
                    MenuPage.this, ResourcesPage.class);
                startActivity(l);
                break;
            case R.id.button_create_new_job_posting:
                Intent m = new Intent(
                    MenuPage.this, JobOpportunityProfileCreationLayout.class);
                startActivity(m);
                break;
            case R.id.button_current_job_postings:
                Intent n = new Intent(
                    MenuPage.this, JobOpportunityListByEmployer.class);
                startActivity(n);
                break;
            case R.id.button_review_applicants:
                Intent p = new Intent(
                    MenuPage.this, JobApplicantsByEmployer.class);
                startActivity(p);
                break;
            case R.id.button_search_users:
                Intent q = new Intent(
                    MenuPage.this, SearchUserDatabase.class);
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
