package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityListPage extends Activity {

    ArrayList<HashMap<String, String>> jobList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_opportunity_list);
        new JobListCreation().execute();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class JobListCreation extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... p) {
            StartPage.client.loadJobs();
            SystemClock.sleep(250);
            return new String("");
        }

        @Override
        protected void onPostExecute (String string) {
            for (int i = 0; i < StartPage.dk.getJobList().length(); i++) {
                String title = StartPage.dk.getJobDetail("title", i);
                String description = StartPage.dk.getJobDetail("description", i);
                String submissionDate = StartPage.dk.getJobDetail("submission date", i);

                HashMap<String, String> map = new HashMap<>();

                map.put("title", title);
                map.put("description", description);
                map.put("submission date", submissionDate);

                jobList.add(map);

                ListView list = (ListView) findViewById(R.id.list_jobs);

                ListAdapter adapter = new SimpleAdapter(JobOpportunityListPage.this,
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
                        Intent j = new Intent(JobOpportunityListPage.this, JobOpportunityProfilePage.class);
                        startActivity(j);
                    }
                });
            }
        }
    }

}
