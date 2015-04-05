package com.example.aarthi.anti_corruption;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aarthi on 2/4/15.
 */
public class callmsg extends Activity {

    public RelativeLayout relativeLayout;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callsms);
        Button b1=(Button)findViewById(R.id.call);
        Button b3=(Button)findViewById(R.id.call2);
        Button b5=(Button)findViewById(R.id.call3);
        Button b7=(Button)findViewById(R.id.fblink);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9840385881"));
                startActivity(intent);

            }
        });
        Button b2=(Button)findViewById(R.id.smssend);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(callmsg.this,Sms.class);
                intent.putExtra("number","9840385881");
                startActivity(intent);
            }
        });

    b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9442159301"));
            startActivity(intent);

        }
    });
    Button b4=(Button)findViewById(R.id.smssend2);
    b4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(callmsg.this,Sms.class);
            intent.putExtra("number","9442159301");
            startActivity(intent);
        }
    });
b5.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9941467666"));
        startActivity(intent);

        }
        });
        Button b6=(Button)findViewById(R.id.smssend3);
        b6.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent(callmsg.this,Sms.class);
        intent.putExtra("number","9941467666");
        startActivity(intent);
        }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.fb);
                WebView browser = (WebView) findViewById(R.id.fb1);
                browser.loadUrl("https://www.facebook.com/pages/Corruption-Spotted/1054456457902085");

            }
        });
        }
}