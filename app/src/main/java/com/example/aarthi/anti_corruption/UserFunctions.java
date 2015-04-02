package com.example.aarthi.anti_corruption;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserFunctions {
    JSONObject json;

    private JSONParser jsonParser;
    private static String loginURL = "http://smallbang.hol.es/corruption/httppost.php";
    private static String update_tag = "update";
    public JSONObject postUpdate(String picture, String Location, String Complaint){
        // Building Parameters
        Log.d("gg", "user func");
        jsonParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Tag", update_tag));
        params.add(new BasicNameValuePair("Image",picture));
    params.add(new BasicNameValuePair("Location", Location));
        params.add(new BasicNameValuePair("Complaint", Complaint));
        json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        return json;
    }
}


