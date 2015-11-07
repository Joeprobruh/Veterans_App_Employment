package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Larry on 11/2/2015.
 */
public class DataKeeper {
    private JSONObject vetProfile;
    private JSONArray vetList;
    private JSONObject employerProfile;
    private JSONObject job;
    private JSONArray jobList;
    private JSONArray employerList;

    public DataKeeper() {
        vetProfile = null;
        vetList = null;
        employerProfile = null;
        jobList = null;
        employerList = null;
    }

    public void setVetProfile(JSONObject tmp) {
        vetProfile = tmp;
    }

    public void setVetList(JSONArray tmp) {
        vetList = tmp;
    }

    public void setEmployerProfile(JSONObject tmp) {
        employerProfile = tmp;
    }

    public void setJobList(JSONArray tmp) {
        jobList = tmp;
    }

    public void setEmployerList(JSONArray tmp) {
        employerList = tmp;
    }

    ;


    public String getVetDetail(String detail) {
        try {
            return vetProfile.getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public String[] getVetSkills() {
        String[] skills;
        try {
            int length = vetProfile.getInt("index");
            if (length == 0)
                return null;
            JSONArray skillJSON = vetProfile.getJSONArray("skills");
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) skillJSON.get(i);
            }
            return skills;
        } catch (JSONException e) {

        }
        return null;
    }

    public String[] getVetSkills(int index) {
        String[] skills;
        try {

            JSONObject obj = vetList.getJSONObject(index);
            int length = obj.getInt("index");
            if (length == 0)
                return null;
            JSONArray skillJSON = obj.getJSONArray("skills");
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) skillJSON.get(i);
            }
            return skills;
        } catch (JSONException e) {

        }
        return null;
    }

    public String[] getJobSkills(int index) {
        String[] skills;
        try {

            JSONObject obj = jobList.getJSONObject(index);
            int length = obj.getInt("index");
            if (length == 0)
                return null;
            JSONArray skillJSON = obj.getJSONArray("skills");
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) skillJSON.get(i);
            }
            return skills;
        } catch (JSONException e) {

        }
        return null;
    }

    public String getVetDetail(String detail, int index) {
        try {
            return vetList.getJSONObject(index).getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public String getEmployerDetail(String detail) {
        try {
            return employerProfile.getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public void setJobByIndex(int index) {
        try {
            job = jobList.getJSONObject(index);
        } catch (JSONException e) {
            job = null;
        }
    }

    public void setEmployerProfileByIndex(int index) {
        try {
            employerProfile = employerList.getJSONObject(index);
        } catch (JSONException e) {
            employerProfile = null;
        }
    }

    public void setVetProfileByIndex(int index) {
        try {
            vetProfile = vetList.getJSONObject(index);
        } catch (JSONException e) {
            vetProfile = null;
        }
    }

    public JSONObject getJob() {
        return job;
    }

    public String getJobDetail(String detail, int index) {
        try {
            return jobList.getJSONObject(index).getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public JSONArray getJobList() {
        return jobList;
    }

    public JSONArray getEmployerList() {
        return employerList;
    }

    public JSONArray getVetList() {
        return vetList;
    }
}

