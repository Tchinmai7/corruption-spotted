package com.example.aarthi.anti_corruption;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aarthi on 20/3/15.
 */
public class forumactivity extends Activity {
public RelativeLayout relativeLayout;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);
        relativeLayout= (RelativeLayout) findViewById(R.id.realtive);
    getter g=new getter();
        g.execute();
    }
    protected class getter extends AsyncTask<Void,Void,Void>
    {
        public HashMap<String, String> info;
        public ArrayList<HashMap<String, String>> infolist;
        public String s;
        public int count;
        ProgressDialog mProgress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            mProgress = new ProgressDialog(forumactivity.this);
            mProgress.setMessage("Please wait...");
            mProgress.setCancelable(false);
            mProgress.show();
        }
        protected Void doInBackground(Void... params) {

                // Creating service handler class instance
                ServiceHandler sh = new ServiceHandler();
                String url="http://smallbang.hol.es/corruption/getpost.php";

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
                    info=new HashMap<>(10);

infolist=new ArrayList<>(10);
            Log.d("Response: ", "> " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray event = jsonObj.getJSONArray("results");
                        count=event.length();
                        for (int i = 0; i < event.length(); i++) {
                            JSONObject c = event.getJSONObject(i);

                          //  String img = c.getString("Image");
                            String loc = c.getString("Location");
                            String comp = c.getString("Complaint");
                            //String to = c.getString(TagTo);
                            //String venue = c.getString(TagVenue);
                            Log.d("data",loc+comp);
                           // info.put("Image", img);
                            info.put("Location", loc);
                            info.put("Complaint", comp);
                            //info.put(TagTo, to);
                            //info.put(TagVenue, venue);
                            infolist.add(info);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("ServiceHandler", "Couldn't get any data from the url");
                }

                return null;
            }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (mProgress.isShowing())
                mProgress.dismiss();
            String s1,s2,s3;
             //  s1 = infolist.get(j).get("Image");
            for(int i=0;i<count-1;i++){
                s2 = infolist.get(i).get("Location");
                s3 = infolist.get(i).get("Complaint");
            adder(s2+"\n"+s3);
            Log.d("data",s2);
            }
            }
        public int idno;
        public void adder(String s1)
        {
            Log.d("addercall",":dsfsdfsdfsd");
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            TextView tv1 = new TextView(getApplicationContext());
            tv1.setText(s1);
            tv1.setId(idno);
            params.addRule(RelativeLayout.BELOW,tv1.getId()-1);
            idno++;

            relativeLayout.addView(tv1,params   );

        }

        }


    }



