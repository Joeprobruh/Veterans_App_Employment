package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityListByEmployerPage extends Activity {

    ArrayList<HashMap<String, String>> jobList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_by_employer_list);
        new JobListByEmployerCreation().execute();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class JobListByEmployerCreation extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... p) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StartPage.client.loadJobsByEmployer();
                    while (!StartPage.client.getIsTaskDone()) {
                        SystemClock.sleep(50);
                    }
                }
            });
            thread.start();
            return new String("");
        }

        @Override
        protected void onPostExecute(String string) {
            for (int i = 0; i < StartPage.dk.getJobList().length(); i++) {
                String title = StartPage.dk.getJobDetail("title", i);
                String description = StartPage.dk.getJobDetail("description", i);
                String deadline = StartPage.dk.getJobDetail("deadline", i);

                HashMap<String, String> map = new HashMap<>();

                if (!title.equals("null")) {
                    map.put("title", title);
                }
                else {
                    map.put("title", "No title entered.");
                }
                if (!description.equals("null")) {
                    map.put("description", description);
                }
                else {
                    map.put("description", "No description entered.");
                }
                if (!deadline.equals("null")) {
                    map.put("deadline", deadline);
                }
                else {
                    map.put("deadline", "No deadline entered.");
                }

                jobList.add(map);

                ListView list = (ListView) findViewById(R.id.list_jobs_by_employer);

                ListAdapter adapter = new SimpleAdapter(JobOpportunityListByEmployerPage.this,
                    jobList,
                    R.layout.activity_job_list_item,
                    new String[]{"title", "description", "deadline"},
                    new int[]{R.id.textView_job_list_title,
                        R.id.textView_job_list_description,
                        R.id.textView_job_list_submission_date});

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        StartPage.dk.setJob(position);
                        Intent j = new Intent(JobOpportunityListByEmployerPage.this, JobOpportunityProfilePage.class);
                        startActivity(j);
                    }
                });
            }
        }
    }

}
