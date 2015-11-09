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
public class JobApplicantsByEmployerListPage extends Activity {

    ArrayList<HashMap<String, String>> applicantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_by_employer_list);
        new ApplicantListCreation().execute();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class ApplicantListCreation extends AsyncTask<String, String, String> {

        @Override

        protected String doInBackground(String... p) {
            StartPage.client.loadJobsByEmployer();
            SystemClock.sleep(500);
            return new String("");
        }

        @Override
        protected void onPostExecute (String string) {
            for (int i = 0; i < StartPage.dk.getVetList().length(); i++) {
                String name = StartPage.dk.getVetDetail("name");
                String age = StartPage.dk.getVetDetail("age");
                String branch = StartPage.dk.getVetDetail("branch");
                String rank = StartPage.dk.getVetDetail("rank");

                HashMap<String, String> map = new HashMap<>();

                map.put("name", name);
                map.put("age", age);
                map.put("branch", branch);
                map.put("rank", rank);

                applicantList.add(map);

                ListView list = (ListView) findViewById(R.id.list_users);

                ListAdapter adapter = new SimpleAdapter(JobApplicantsByEmployerListPage.this,
                    applicantList,
                    R.layout.activity_user_list_item,
                    new String[]{"name", "age", "branch", "rank"},
                    new int[]{R.id.textView_user_list_name,
                        R.id.textView_user_list_age,
                        R.id.textView_user_list_branch,
                        R.id.textView_user_list_rank});

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        StartPage.dk.setVetProfile(position);
                        Intent j = new Intent(JobApplicantsByEmployerListPage.this, UserProfilePage.class);
                        startActivity(j);
                    }
                });
            }
        }
    }
}
