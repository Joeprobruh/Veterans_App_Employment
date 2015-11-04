package com.Senior_Proj_Fall_2015.Veterans_App_Employment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class NetClient {
    private static final String directory = "http://elvis.rowan.edu/~romanol8/android/";
    private static final String addVetSkill = "addSkill.php";
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
    private HttpClient httpClient = null;
    DataKeeper dataKeeper;
    private String userID;
    private String jobID;
    private String role;
    private boolean isSignedUp;

    public NetClient(DataKeeper dk) {
        dataKeeper = dk;
        httpClient = new DefaultHttpClient();
        jobID = null;
        userID = null;
        isSignedUp = false;
    }

    public void addVetSkill(final String skill, final String months) {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                // Creating HTTP Post

                HttpPost httpPost = new HttpPost(directory + addVetSkill);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("id", userID));
                nameValuePair.add(new BasicNameValuePair("skill", skill));
                nameValuePair.add(new BasicNameValuePair("months", months));

                // Url Encoding the POST parameters
                try{
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {}
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
                                }
                                catch(Throwable T){}

                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();

    }

    public void login (final String username, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(directory + login);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("username", username));
                nameValuePair.add(new BasicNameValuePair("password", password));
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                } catch (Throwable t) {
                                }

                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }


    public void signUp(final String username, final String password, final String roleParam) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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
                                    role   = obj.getString("role");
                                    isSignedUp = true;
                                } catch (Throwable t) {
                                }
                            } else {
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }

    public void addVetProfile(final String name, final String age, final String description, final String address, final String sex, final String branch, final String rank) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(directory + addVetProfile);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("id", userID));
                nameValuePair.add(new BasicNameValuePair("name", name));
                nameValuePair.add(new BasicNameValuePair("age", age));
                nameValuePair.add(new BasicNameValuePair("address", address));
                nameValuePair.add(new BasicNameValuePair("sex", sex));
                nameValuePair.add(new BasicNameValuePair("branch", branch));
                nameValuePair.add(new BasicNameValuePair("rank", rank));
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                } catch (Throwable t) {
                                }
                            } else {
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();

    }

    public void addEmployerProfile(final String name, final String title, final String company, final String description,  final String address, final String phone, final String email) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                    String id = obj.getString("id");
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }

    public void addJob(final String  title, final String company, final String  description, final String contact, final String address, final String phone, final String email, final String deadline){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(directory + addJob);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("id", userID));
                nameValuePair.add(new BasicNameValuePair("title", title));
                nameValuePair.add(new BasicNameValuePair("company", company));
                nameValuePair.add(new BasicNameValuePair("description", description));
                nameValuePair.add(new BasicNameValuePair("contact", contact));
                nameValuePair.add(new BasicNameValuePair("address", address));
                nameValuePair.add(new BasicNameValuePair("phone", phone));
                nameValuePair.add(new BasicNameValuePair("email", email));
                nameValuePair.add(new BasicNameValuePair("deadline", deadline));
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }

    public void addJobSkill(final String jobid, final String skill, final String months){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(directory + addJobSkill);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("id", jobID));
                nameValuePair.add(new BasicNameValuePair("skill", skill));
                nameValuePair.add(new BasicNameValuePair("months", months));
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                    String id = obj.getString("id");
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }

    public void loadVetProfile(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(directory + loadVetProfile);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("id", userID));
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                    dataKeeper.setVetProfile(obj);
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();

    }

    public void loadEmployerProfile(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(directory + loadEmployerProfile);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("id", userID));
                // Url Encoding the POST parameters
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
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
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();

    }

    public void loadVets(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }

    public void loadJobs(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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
                                } catch (Throwable t) {
                                }
                            }
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                } catch (IOException e) {
                    // writing exception to log
                }
            }
        });
        thread.start();
    }
    public String getUserID(){
        return userID;
    }
    public boolean getSignUp(){
        return isSignedUp;
    }
    public String getRole() { return role; }
}