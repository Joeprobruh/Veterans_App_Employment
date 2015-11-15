package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import android.os.StrictMode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class NetClient {
    private static final String directory = "http://elvis.rowan.edu/~romanol8/android/";
    private static final String addVetSkill = "addskill.php";
    private static final String addEmployerProfile = "addemployerdetails.php";
    private static final String addVetProfile = "addvetdetails";
    private static final String addJobSkill = "addjobskill.php";
    private static final String login = "login.php";
    private static final String signUp = "adduser.php";
    private static final String addJob = "addjob.php";
    private static final String loadVetProfile = "getavet.php";
    private static final String loadVets = "getvets.php";
    private static final String loadJobs = "getjobs.php";
    private static final String loadEmployerProfile = "getaboss.php";
    private static final String loadJobsByEmployer = "getjobsbyemployer.php";
    private static final String loadEmployers = "getbosses.php";
    private static final String loadSkills = "getskills.php";

    private HttpClient httpClient = null;
    DataKeeper dataKeeper;
    private String userID;
    private String jobID;
    private String role;
    private boolean isSignedUp;
    private boolean isTaskDone;

    public NetClient(DataKeeper dk) {
        dataKeeper = dk;
        httpClient = getThreadSafeClient();
        jobID = null;
        userID = null;
        isSignedUp = false;
        isTaskDone = false;
    }

    public void addVetSkill(final String[] skill) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        Integer numSkills = skill.length;

        HttpPost httpPost = new HttpPost(directory + addVetSkill);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", userID));
        String key;
        nameValuePair.add(new BasicNameValuePair("numskills", numSkills.toString()));
        for (int i = 0; i < numSkills; i++) {
            key = "skill" + i;
            nameValuePair.add(new BasicNameValuePair(key, skill[i]));
        }
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
        }
        try{
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        try {
                            String responseBody = EntityUtils.toString(entity);
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            isTaskDone = true;
                        }
                        catch(Throwable T){
                            isTaskDone = true;
                        }

                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;

    }
    public void login (final String username, final String password) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + login);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username", username));
        nameValuePair.add(new BasicNameValuePair("password", password));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
            // writing error to Log
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            userID = obj.getString("id");
                            role = obj.getString("role");
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            // writing exception to log
            isTaskDone = true;
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }



    public void signUp(final String username, final String password, final String roleParam) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + signUp);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username", username));
        nameValuePair.add(new BasicNameValuePair("password", password));
        nameValuePair.add(new BasicNameValuePair("role", roleParam));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
            // writing error to Log
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            isTaskDone = true;
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            userID = obj.getString("id");
                            role   = obj.getString("role");
                            isSignedUp = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    } else {
                        isTaskDone = true;
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;

    }

    public void addVetProfile(final String name, final String age, final String description, final String address,
                              final String phone, final String email, final String sex, final String branch,
                              final String rank) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + addVetProfile);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", userID));
        nameValuePair.add(new BasicNameValuePair("name", name));
        nameValuePair.add(new BasicNameValuePair("age", age));
        nameValuePair.add(new BasicNameValuePair("description", description));
        nameValuePair.add(new BasicNameValuePair("address", address));
        nameValuePair.add(new BasicNameValuePair("sex", sex));
        nameValuePair.add(new BasicNameValuePair("branch", branch));
        nameValuePair.add(new BasicNameValuePair("rank", rank));
        nameValuePair.add(new BasicNameValuePair("description", description));
        nameValuePair.add(new BasicNameValuePair("phone", phone));
        nameValuePair.add(new BasicNameValuePair("email", email));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            isTaskDone = true;
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            userID = obj.getString("id");
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    } else {
                        isTaskDone = true;
                    }
            }
        } catch (ClientProtocolException e) {
            // writing exception to log
            isTaskDone = true;
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }

    public void addEmployerProfile(final String name, final String title, final String company,
                                   final String description,  final String address, final String phone,
                                   final String email) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + addEmployerProfile);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", userID));
        nameValuePair.add(new BasicNameValuePair("name", name));
        nameValuePair.add(new BasicNameValuePair("title", title));
        nameValuePair.add(new BasicNameValuePair("company", company));
        nameValuePair.add(new BasicNameValuePair("address", address));
        nameValuePair.add(new BasicNameValuePair("phone", phone));
        nameValuePair.add(new BasicNameValuePair("email", email));
        nameValuePair.add(new BasicNameValuePair("description", description));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
            // writing error to Log
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            userID = obj.getString("id");
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }

    public void addJob(final String id, final String title, final String company, final String description,
                       final String contact, final String address, final String phone, final String email,
                       final String url, final String applyMethod, final String deadline){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + addJob);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("jobid", id));
        nameValuePair.add(new BasicNameValuePair("id", userID));
        nameValuePair.add(new BasicNameValuePair("title", title));
        nameValuePair.add(new BasicNameValuePair("company", company));
        nameValuePair.add(new BasicNameValuePair("description", description));
        nameValuePair.add(new BasicNameValuePair("contact", contact));
        nameValuePair.add(new BasicNameValuePair("address", address));
        nameValuePair.add(new BasicNameValuePair("phone", phone));
        nameValuePair.add(new BasicNameValuePair("email", email));
        nameValuePair.add(new BasicNameValuePair("url", url));
        nameValuePair.add(new BasicNameValuePair("deadline", deadline));
        nameValuePair.add(new BasicNameValuePair("applymethod", applyMethod));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
            // writing error to Log
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            jobID = obj.getString("id");
                            isTaskDone = true;

                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
    }

    public void addJobSkill(final String[] skill) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        Integer numSkills = skill.length;

        HttpPost httpPost = new HttpPost(directory + addJobSkill);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", jobID));
        String key;
        nameValuePair.add(new BasicNameValuePair("numskills", numSkills.toString()));
        for (int i = 0; i < numSkills; i ++) {
            key = "skill" + i;
            nameValuePair.add(new BasicNameValuePair(key, skill[i]));
        }

        // Url Encoding the POST parameters
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
        }
        // Making HTTP Request
        try{
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        try {
                            String responseBody = EntityUtils.toString(entity);
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            isTaskDone = true;
                        }
                        catch(Throwable T){
                            isTaskDone = true;
                        }

                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;

    }


    public void loadVetProfile(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadVetProfile);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", userID));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            isTaskDone = true;
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            dataKeeper.setVetProfile(obj);
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;

    }

    public void loadEmployerProfile(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadEmployerProfile);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", userID));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
            // writing error to Log
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            JSONObject obj = array.getJSONObject(0);
                            dataKeeper.setEmployerProfile(obj);
                            isTaskDone = true;

                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;

    }

    public void loadVets(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadVets);
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            dataKeeper.setVetList(array);
                            isTaskDone = true;

                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;

    }

    public void loadJobs(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadJobs);
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            dataKeeper.setJobList(array);
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }
    public void loadJobsByEmployer(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadJobsByEmployer);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("id", userID));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            isTaskDone = true;
            // writing error to Log
        }
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {
                            JSONArray array = new JSONArray(responseBody);
                            dataKeeper.setJobList(array);
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }
    public void loadEmployers(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadEmployers);
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {

                            JSONArray array = new JSONArray(responseBody);
                            dataKeeper.setEmployerList(array);
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }

    public void loadSkills(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        isTaskDone = false;
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(directory + loadSkills);
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int responseCode = response.getStatusLine().getStatusCode();
            switch (responseCode) {
                case 200:
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        try {

                            JSONArray array = new JSONArray(responseBody);
                            dataKeeper.setSkills(array);
                            isTaskDone = true;
                        } catch (Throwable t) {
                            isTaskDone = true;
                        }
                    }
            }
        } catch (ClientProtocolException e) {
            isTaskDone = true;
            // writing exception to log
        } catch (IOException e) {
            isTaskDone = true;
            // writing exception to log
        }
        isTaskDone = true;
    }

    public static DefaultHttpClient getThreadSafeClient() {
        DefaultHttpClient client = new DefaultHttpClient();
        ClientConnectionManager mgr = client.getConnectionManager();
        HttpParams params = client.getParams();
        client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,
            mgr.getSchemeRegistry()), params);
        return client;

    }

    public String getUserID(){return userID;}
    public boolean getSignUp(){return isSignedUp;}
    public String getRole() { return role; }
    public boolean getIsTaskDone(){return isTaskDone;}
    public void setIsTaskDone (boolean bool) {
        isTaskDone = bool;
    }
}