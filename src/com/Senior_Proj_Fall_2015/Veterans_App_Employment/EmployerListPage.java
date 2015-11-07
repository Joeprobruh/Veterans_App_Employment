package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joe on 11/7/2015.
 */
public class EmployerListPage extends Activity{

    ArrayList<HashMap<String, String>> employerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_list);
        new EmployerListCreation().execute();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class EmployerListCreation extends AsyncTask<String, String, String> {

        @Override

        protected String doInBackground(String... p) {
            StartPage.client.loadEmployers();
            SystemClock.sleep(250);
            return new String("");
        }

        @Override
        protected void onPostExecute (String string) {
            for (int i = 0; i < StartPage.dk.getEmployerList().length(); i++) {
                String company = StartPage.dk.getEmployerDetail("company");
                String address = StartPage.dk.getEmployerDetail("address");
                String phone = StartPage.dk.getEmployerDetail("phone");
                String email = StartPage.dk.getEmployerDetail("email");

                HashMap<String, String> map = new HashMap<>();

                map.put("company", company);
                map.put("address", address);
                map.put("phone", phone);
                map.put("email", email);

                employerList.add(map);

                ListView list = (ListView) findViewById(R.id.list_employers);

                ListAdapter adapter = new SimpleAdapter(EmployerListPage.this,
                    employerList,
                    R.layout.activity_employer_list_item,
                    new String[]{"company", "address", "phone", "email"},
                    new int[]{R.id.textView_employer_list_company,
                        R.id.textView_employer_list_address,
                        R.id.textView_employer_list_phone_number,
                        R.id.textView_employer_list_email_address});

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        StartPage.dk.setEmployerProfileByIndex(position);
                        Intent j = new Intent(EmployerListPage.this, EmployerProfilePage.class);
                        startActivity(j);
                    }
                });
            }
        }
    }

}
