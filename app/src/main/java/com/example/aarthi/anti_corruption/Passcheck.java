package com.example.aarthi.anti_corruption;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Passcheck extends Activity implements OnClickListener{

	private static final String MYPREFERENCES = "UserDetails";
	private static final String Pass = "Pass";

	SharedPreferences store;
	String storedpass;
	EditText pass;
	Button enter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passcheck);

		pass = (EditText) findViewById(R.id.etPass);
		enter = (Button) findViewById(R.id.bEnter);
		store = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);

		enter.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		storedpass = store.getString(Pass, "");
		String typedpass = pass.getText().toString();
		
		if(typedpass.equals(storedpass))
		{
			Intent menu = new Intent(getApplicationContext(),mainscreen.class);
			startActivity(menu);
		}
		else
			Toast.makeText(this, "Incorrect Password!!!", Toast.LENGTH_LONG)
			.show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
