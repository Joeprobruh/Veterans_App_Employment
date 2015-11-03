package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Joe on 11/1/2015.
 */
public class JobListDatabaseHelper {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_SUBDATE = "submissiondate";

    private static final String TAG = "JobListDatabaseHelper";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "ListOfJobs";
    private static final String SQLITE_TABLE = "JobListings";
    private static final int DATABASE_VERSION = 1;

    private final Context context;

    private static final String DATABASE_CREATE =
        "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
            KEY_ROWID + " integer PRIMARY KEY autoincrement," +
            KEY_TITLE + "," +
            KEY_DESCRIPTION + "," +
            KEY_SUBDATE + ",";

    public JobListDatabaseHelper(Context context) {
        this.context = context;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public JobListDatabaseHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    public long createJobListing(String code, String title,
                              String description, String subDate) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DESCRIPTION, description);
        initialValues.put(KEY_SUBDATE, subDate);

        return database.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllJobListings() {

        int doneDelete = 0;
        doneDelete = database.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchJobListingByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = database.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                    KEY_TITLE, KEY_DESCRIPTION, KEY_SUBDATE},
                null, null, null, null, null);

        }
        else {
            mCursor = database.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
                    KEY_TITLE, KEY_DESCRIPTION, KEY_SUBDATE},
                KEY_TITLE + " like '%" + inputText + "%'", null,
                null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllJobListings() {

        Cursor mCursor = database.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                KEY_TITLE, KEY_DESCRIPTION, KEY_SUBDATE},
            null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void generateJobPostings() {

        createJobListing("AFG", "Afghanistan", "Asia", "Southern and Central Asia");
        createJobListing("ALB", "Albania", "Europe", "Southern Europe");
        createJobListing("DZA", "Algeria", "Africa", "Northern Africa");
        createJobListing("ASM", "American Samoa", "Oceania", "Polynesia");
        createJobListing("AND", "Andorra", "Europe", "Southern Europe");
        createJobListing("AGO", "Angola", "Africa", "Central Africa");
        createJobListing("AIA","Anguilla","North America","Caribbean");

    }

}
