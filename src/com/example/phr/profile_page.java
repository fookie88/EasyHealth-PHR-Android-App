package com.example.phr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

public class profile_page extends Activity{
    
	String userID;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userID = getIntent().getExtras().getString("userID");
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	} 
        // setting default screen to login.xml
        
        setContentView(R.layout.profile_page);
        
        TextView viewCaretakers= (TextView) findViewById(R.id.view_caretakers);
        TextView viewAllergy = (TextView) findViewById(R.id.get_allergy);
        TextView viewImmunization= (TextView) findViewById(R.id.get_immunization);
        TextView viewBloodPressure= (TextView) findViewById(R.id.get_blood_pressure);
        TextView viewBloodSugar= (TextView) findViewById(R.id.get_blood_sugar);
        TextView viewEmergencyContact= (TextView) findViewById(R.id.get_emergency_contact);
        TextView viewHeightWeight= (TextView) findViewById(R.id.get_height_weight);
        TextView viewMedication= (TextView) findViewById(R.id.get_medication);
        TextView viewTemperature= (TextView) findViewById(R.id.get_temperature);

        TextView addAllergy = (TextView) findViewById(R.id.add_allergy);
        TextView addImmunization= (TextView) findViewById(R.id.add_immunization);
        TextView addBloodPressure= (TextView) findViewById(R.id.add_blood_pressure);
        TextView addBloodSugar= (TextView) findViewById(R.id.add_blood_sugar);
        TextView addEmergencyContact= (TextView) findViewById(R.id.add_emergency_contact);
        TextView addHeightWeight= (TextView) findViewById(R.id.add_height_weight);
        TextView addMedication= (TextView) findViewById(R.id.add_medication);
        TextView addTemperature= (TextView) findViewById(R.id.add_temperature_1);

        viewCaretakers.setOnClickListener(new View.OnClickListener() {
          	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), caretakers.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewAllergy.setOnClickListener(new View.OnClickListener() {
         	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_allergy.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewImmunization.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_immunization.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewBloodPressure.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_blood_pressure.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewBloodSugar.setOnClickListener(new View.OnClickListener() {
          	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_blood_sugar.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewEmergencyContact.setOnClickListener(new View.OnClickListener() {
         	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_emergency_contact.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewHeightWeight.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_height_weight.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewMedication.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_medication.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        viewTemperature.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_temperature.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});

	
	
	// Add buttons now
        
        
        
        addAllergy.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_allergy.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addImmunization.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_immunization.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addBloodPressure.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_blood_pressure.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addBloodSugar.setOnClickListener(new View.OnClickListener() {
          	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_blood_sugar.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addEmergencyContact.setOnClickListener(new View.OnClickListener() {
         	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_emergency_contact.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addHeightWeight.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_height_weight.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addMedication.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_medication.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});
        addTemperature.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_temperature.class);
                i.putExtra("userID", userID);
                startActivity(i); 

            }
	});

	
	
	
	
	
	}
}