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
            int length = 0;
            try {
                length = StartPage.dk.getJobList().length();
            } catch (NullPointerException e) {
                length = 0;
                AlertDialog.Builder helpBuilder = new AlertDialog.Builder(JobOpportunityListByEmployerPage.this);
                helpBuilder.setTitle("No jobs currently posted.");
                helpBuilder.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        finish();
                    }
                });
                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();
            }
            if (length > 0) {
                setContentView(R.layout.activity_jobs_by_employer_list);
                for (int i = 0; i < length; i++) {
                    String title = StartPage.dk.getJobDetail("title", i);
                    String description = StartPage.dk.getJobDetail("description", i);
                    String deadline = StartPage.dk.getJobDetail("deadline", i);

                    HashMap<String, String> map = new HashMap<>();

                    map.put("title", title);
                    map.put("description", description);
                    map.put("deadline", deadline);

                    jobList.add(map);

                    ListView list = (ListView) findViewById(R.id.list_jobs_by_employer);

                    ListAdapter adapter = new SimpleAdapter(JobOpportunityListByEmployerPage.this,
                        jobList,
                        R.layout.activity_job_list_item,
                        new String[]{"title", "description", "submission date"},
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
            else {
                ;
            }
        }
    }

}
