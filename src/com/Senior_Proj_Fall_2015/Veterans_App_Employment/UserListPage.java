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
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joe on 11/7/2015.
 * <p/>
 * Generates the list of Users currently in the database.
 */
public class UserListPage extends Activity {

    ArrayList<HashMap<String, String>> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        new UserListCreation().execute();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class UserListCreation extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... p) {
            StartPage.client.loadVets();
            SystemClock.sleep(50);
            return new String("");
        }

        /**
         * Generates the lsit of Users, and populates the list item fields.
         *
         * @param string
         */
        @Override
        protected void onPostExecute(String string) {
            JSONArray vetList = StartPage.dk.getVetList();
            for (int i = 0; i < vetList.length(); i++) {
                String name = StartPage.dk.getVetDetail("name", i);
                String age = StartPage.dk.getVetDetail("age", i);
                String branch = StartPage.dk.getVetDetail("branch", i);
                String rank = StartPage.dk.getVetDetail("rank", i);

                HashMap<String, String> map = new HashMap<>();

                if (!name.equals("null")) {
                    map.put("name", name);
                } else {
                    map.put("name", "No name entered.");
                }
                if (!age.equals("null")) {
                    map.put("age", age);
                } else {
                    map.put("age", "No age entered.");
                }
                if (!branch.equals("null") && !branch.equals("Branch...")) {
                    map.put("branch", branch);
                } else {
                    map.put("branch", "No branch entered.");
                }
                if (!rank.equals("null") && !rank.equals("Rank...")) {
                    map.put("rank", rank);
                } else {
                    map.put("rank", "No rank entered.");
                }

                userList.add(map);
                ListView list = (ListView) findViewById(R.id.list_users);
                ListAdapter adapter = new SimpleAdapter(UserListPage.this,
                    userList,
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
                        Intent j = new Intent(UserListPage.this, UserProfilePage.class);
                        startActivity(j);
                    }
                });
            }
        }
    }
}
