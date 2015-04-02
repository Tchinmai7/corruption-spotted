package com.example.aarthi.anti_corruption;
import java.sql.SQLException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Newuser extends Activity implements OnClickListener {

	private static final String MYPREFERENCES = "UserDetails";
	private static final String Name = "Name";
	private static final String Phone = "Phone";
	private static final String Email = "Email";
	private static final String Location = "Location";
	private static final String Pass = "Pass";
	EditText name, location, phno, pass, cpass, email;
	Button register;
	SharedPreferences store;
	//Emergencydb db;
	//Pharmacydb db1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newuser);

		name = (EditText) findViewById(R.id.etName);
		location = (EditText) findViewById(R.id.etAddress);
		phno = (EditText) findViewById(R.id.etPhNo);
		pass = (EditText) findViewById(R.id.etPass);
		cpass = (EditText) findViewById(R.id.etCPass);
		email = (EditText) findViewById(R.id.etEmail);
		register = (Button) findViewById(R.id.bRegister);

		store = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);

		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if(name.getText().equals("") || location.getText().equals("") || pass.getText().equals("") ||
				cpass.getText().equals("") || email.getText().equals("") || phno.getText().equals(""))
		{
		    	
			Toast.makeText(this, "Please enter all fields!!!", Toast.LENGTH_LONG).show();
		}
		
	else if (pass.getText().toString().equals(cpass.getText().toString())) {
			Editor editor = store.edit();
			editor.putString(Name, name.getText().toString());
			editor.putString(Phone, phno.getText().toString());
			editor.putString(Email, email.getText().toString());
			editor.putString(Location, location.getText().toString());
			editor.putString(Pass, pass.getText().toString());

			editor.commit();
			
			/*
			db = new Emergencydb(this);
			try {
				db.open();
				db.createEntry("A.C. Ambulance Service", "West Mambalam", "4424355942");
				db.createEntry("Accident Relief Force	", "Adayar", "4424355942");
				db.createEntry("Apollo Hospital", "Teynampet", "4424355942");
				db.createEntry("Balaji Hospital", "Chrompet", "4424355942");
				db.createEntry("Jayadev Ambulance Service", "Kodambakam", "4424355942");
				db.createEntry("Malar Hospitals Ltd.", "Adayar", "4424355942");
				db.createEntry("Miot Hospital", "Nandambakkam", "4424355942");
				db.createEntry("Sri Kumaran Ambulance Service", "Nungambakkam", "4424355942");
				db.createEntry("Govt. Royapettah Hospital", "Royapettah", "4424355942");
				db.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			db1 = new Pharmacydb(this);
			try {
				db1.open();
				db1.createEntry("Sri Muthu Medicals", "Omr Road, Sholinganallur", "4466757958");
				db1.createEntry("Annai Pharmacy", "1st Main Road, Perungudi", "4466209436");
				db1.createEntry("Sri Kumaran Pharmacy", "Railway Station Road, Avadi", "4466591578");
				db1.createEntry("Sayar Pharmacy	", "Jenis Street, Saidapet", "4466421539");
				db1.createEntry("Sree Ambaal Medicals", "Omr Road, Sholinganallur", "4466757958");
				db1.createEntry("Apollo Pharmacy", "Omr Road, Sholinganallur", "4496757958");
				db1.createEntry("Apollo Pharmacy", "18 B-Blk Kilpauk Garden Rd Kilpauk", "4424343075");
				db1.createEntry("Sri Muthu Medicals", "No 24 Thanikachalam Rd", "44246461224");
				db1.createEntry("Ashok Pharmacy", "454 R K Shanmugam Salai", "4420412893");
				db1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			Intent i = new Intent(Newuser.this,Passcheck.class);
			startActivity(i);
			
		} else
			Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
