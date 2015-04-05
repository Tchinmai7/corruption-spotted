package com.example.aarthi.anti_corruption;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by aarthi on 2/4/15.
 */
public class Sms extends Activity {
    private SmsManager smsManager;
public Button b1,b2,b3;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.smssend);

        Intent intent=getIntent();

      final  String number=intent.getStringExtra("number");
        this.smsManager = SmsManager.getDefault();
        final PendingIntent sentIntent = PendingIntent.getActivity(this, 0, new Intent(this, Sms.class), 0);
         b1=(Button)findViewById(R.id.buttonsend);

        final EditText editText=(EditText)findViewById(R.id.editText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (PhoneNumberUtils.isWellFormedSmsAddress(number)) {try{
                     smsManager.sendTextMessage(number, null, msg, null, null);
                    Toast.makeText(Sms.this,"Sent",Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(Sms.this,mainscreen.class);
                    startActivity(intent1);
                    finish();
                }catch (Exception e){e.printStackTrace();}}
                else Toast.makeText(Sms.this,"not sent",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
