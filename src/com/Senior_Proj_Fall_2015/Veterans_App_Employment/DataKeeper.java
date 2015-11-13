package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Larry on 11/2/2015.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Larry on 11/2/2015.
 */
public class DataKeeper {
    private JSONObject vetProfile;
    private JSONArray veteranList;
    private JSONObject employerProfile;
    private JSONArray employerList;
    private JSONObject job;
    private int jobIndex;
    private JSONArray jobList;
    private JSONArray allSkills;

    public DataKeeper() {
        vetProfile = null;
        veteranList = null;
        employerProfile = null;
        jobList = null;
        job = null;
        employerList = null;
        allSkills = null;
    }

    public void setVetProfile(JSONObject tmp) {
        vetProfile = tmp;
    }

    public void setVetList(JSONArray tmp) {
        veteranList = tmp;
    }

    public void setEmployerProfile(JSONObject tmp) {
        employerProfile = tmp;
    }

    public void setEmployerList(JSONArray tmp) {
        employerList = tmp;
    }

    public void setJobList(JSONArray tmp) {
        jobList = tmp;
    }

    public void setSkills(JSONArray tmp) {
        allSkills = tmp;
    }

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
           if (length == 0) {
                return null;
            }
            JSONArray skillJSON = vetProfile.getJSONArray("skills");
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) skillJSON.get(i);
            }
            return skills;
        } catch (Throwable t) {
            return null;

        }
    }

    public String[] getVetSkills(int index) {
        String[] skills;
        try {

            JSONObject obj = veteranList.getJSONObject(index);
            int length = obj.getInt("index");
            if (length == 0)
                return null;
            JSONArray skillJSON = obj.getJSONArray("skills");
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) skillJSON.get(i);
            }
            return skills;
        } catch (Throwable t) {
            return null;

        }
    }

    public String[] getJobSkills() {
        String[] skills;
        try {

            JSONObject obj = jobList.getJSONObject(jobIndex);
            int length = obj.getInt("index");
            if (length == 0)
                return null;
            JSONArray skillJSON = obj.getJSONArray("skills");
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) skillJSON.get(i);
            }
            return skills;
        } catch (Throwable t) {
            return null;

        }
    }

    public String[] getSkills() {
        String[] skills;
        try {
            int length = allSkills.length();
            if (length == 0)
                return null;
            skills = new String[length];
            for (int i = 0; i < length; i++) {
                skills[i] = (String) allSkills.get(i);
            }
            return skills;
        } catch (Throwable t) {
            return null;

        }
    }

    public String getVetDetail(String detail, int index) {
        try {
            return veteranList.getJSONObject(index).getString(detail);
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

    public void setJob(int index) {
        try {
            job = jobList.getJSONObject(index);
            jobIndex = index;
        } catch (JSONException e) {
            job = null;
        }
    }

    public void setVetProfile(int index) {
        try {
            vetProfile = veteranList.getJSONObject(index);
        } catch (JSONException e) {
            vetProfile = null;
        }
    }

    public void setEmployerProfile(int index) {
        try {
            employerProfile = employerList.getJSONObject(index);
        } catch (JSONException e) {
            employerProfile = null;
        }
    }

    public JSONObject getVetProfile() {
        return vetProfile;
    }

    public JSONObject getJob() {
        return job;
    }

    public String getJobDetail(String detail) {
        try {
            return job.getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public String getJobDetail(String detail, int index) {
        try {
            return jobList.getJSONObject(index).getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public String getEmployerDetail(String detail, int index) {
        try {
            return employerList.getJSONObject(index).getString(detail);
        } catch (Throwable t) {
            return null;
        }
    }

    public void clearJob() {
        job = null;
    }

    public JSONArray getJobList() {
        return jobList;
    }

    public JSONArray getVetList() {
        return veteranList;
    }

    public JSONArray getEmployerList() {
        return employerList;
    }

    public JSONObject getEmployerProfile() {
        return employerProfile;
    }
}




