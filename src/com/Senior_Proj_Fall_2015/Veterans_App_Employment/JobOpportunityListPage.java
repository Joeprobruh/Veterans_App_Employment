package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by Joe on 10/31/2015.
 */
public class JobOpportunityListPage extends Activity {

    public static Job selection;
    private SimpleCursorAdapter adapter;
    private JobListDatabaseHelper jobListDBHelper;

    //private DatabaseEntryList databaseList = getDatabaseList(jobOpportunityList);
    private ArrayList<Job> databaseList = new ArrayList<>();//stub list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_opportunity_list);

        jobListDBHelper = new JobListDatabaseHelper(this);
        jobListDBHelper.open();
        displayListView();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private void displayListView() {

        String[] columns = new String[]{"Title",
            "Description",
            "Submission Date"};
        int[] spots = new int[] {R.id.textView_job_title,
            R.id.textView_job_description,
            R.id.textView_job_submission_date};

        Cursor cursor = jobListDBHelper.fetchAllJobListings();

        adapter = new SimpleCursorAdapter(this, R.layout.activity_job_list_item, cursor, columns, spots, 0);

        final ListView list = (ListView) findViewById(R.id.listView_job_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selection = databaseList.get(position);
                Intent i = new Intent(list.getContext(), JobOpportunityProfilePage.class);
                startActivity(i);
            }
        });
    }

}
