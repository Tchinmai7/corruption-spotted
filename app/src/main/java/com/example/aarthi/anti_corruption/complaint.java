package com.example.aarthi.anti_corruption;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by aarthi on 20/3/15.
 */
public class complaint extends Activity {
public   EditText t1;
    public SharedPreferences sp;
    public SharedPreferences.Editor ed;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_choice);
       t1 = (EditText) findViewById(R.id.editText);
     sp=getSharedPreferences("myapp",0);
        ed=sp.edit();
        Button next = (Button) findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=t1.getText().toString();
                ed.putString("complaint",text);
                ed.apply();
                Intent i=new Intent(t1.getContext(),GroundOverlayDemoActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
