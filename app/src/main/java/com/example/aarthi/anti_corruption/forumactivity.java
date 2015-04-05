package com.example.aarthi.anti_corruption;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

infolist=new ArrayList<>(10);
            Log.d("Response: ", "> " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray event = jsonObj.getJSONArray("results");

                        count=event.length();
                        for (int i = 0; i < event.length(); i++) {
                            info=new HashMap<>(10);

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
            adder(s2,s3);
            Log.d("data", s2);
            }
            }
        public int idno;
       public TextView tv1;
        public    CardView cv;
        public int id;
        public void adder(String s1,String s2)
        {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
           cv=new CardView(getApplicationContext());

            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);

            tv1 = new TextView(getApplicationContext());
            tv1.setText(s1);
            Log.d("text", s1);
            CardView.LayoutParams params1=new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,CardView.LayoutParams.WRAP_CONTENT);
            tv1.setLayoutParams(params1);
            tv1.setTextSize(22);
            tv1.setTextColor(Color.parseColor("#000000"));
            tv1.setPadding(10,10,10,10);
            TextView tv2=new TextView(getApplicationContext());
            tv1.setId(id);
            id++;
            RelativeLayout rl=new RelativeLayout(getApplicationContext());
            RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(CardView.LayoutParams.MATCH_PARENT,CardView.LayoutParams.WRAP_CONTENT);
            tv2.setLayoutParams(params2);
            tv2.setTextSize(18);
            tv2.setText(s2);
            tv2.setPadding(10,10,10,10);
            tv2.setTextColor(Color.parseColor("#bcbcbc"));
            cv.setId(idno);
            cv.setCardElevation(10);
            cv.setPadding(10,10,10,10);
            params2.addRule(RelativeLayout.BELOW,tv1.getId());
            params.addRule(RelativeLayout.BELOW, cv.getId() - 1);
            idno++;
            rl.addView(tv1);
            rl.addView(tv2,params2);
            cv.addView(rl);
            relativeLayout.addView(cv,params);

        }

        }


    }



