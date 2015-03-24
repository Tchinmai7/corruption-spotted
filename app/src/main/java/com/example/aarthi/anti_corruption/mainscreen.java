package com.example.aarthi.anti_corruption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by aarthi on 20/3/15.
 */
public class mainscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button complaint = (Button) findViewById(R.id.button1);
        final Button forum = (Button) findViewById(R.id.button2);
        final Button reputation = (Button) findViewById(R.id.button3);

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(mainscreen.this, complaint.class);
                startActivity(i1);
            }});

            forum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2=new Intent(mainscreen.this,forumactivity.class);
                    startActivity(i2);
                }});

                reputation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i3=new Intent(mainscreen.this,GroundOverlayDemoActivity.class);
                        startActivity(i3);
                    }
        });

    }
}