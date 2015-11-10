package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
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
            StartPage.client.loadJobsByEmployer();
            SystemClock.sleep(500);
            return new String("");
        }

        @Override
        protected void onPostExecute (String string) {
            for (int i = 0; i < StartPage.dk.getJobList().length(); i++) {
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
    }

}
