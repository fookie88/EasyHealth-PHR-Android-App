package com.example.phr;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

public class add_blood_pressure extends Activity {
	ArrayList<NameValuePair> postParameters;
	TextView submitBloodPressure,date,systolic,diastolic,pulse,query_response;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	} 
        // setting default screen to login.xml
        
        setContentView(R.layout.add_blood_pressure);
        

        postParameters = new ArrayList<NameValuePair>();
        submitBloodPressure= (TextView) findViewById(R.id.submit_blood_pressure);
        date= (TextView) findViewById(R.id.blood_pressure_date);
        systolic= (TextView) findViewById(R.id.blood_pressure_systolic);
        diastolic= (TextView) findViewById(R.id.blood_pressure_diastolic);
        pulse= (TextView) findViewById(R.id.blood_pressure_pulse);
        query_response= (TextView) findViewById(R.id.blood_pressure_response);
        
        submitBloodPressure.setOnClickListener(new View.OnClickListener() {
            String response;
            SharedPreferences settings = getSharedPreferences("userData", 0);
    		String userID = settings.getString("userID", "string");
             public void onClick(View v) {
            	
            	postParameters.add(new BasicNameValuePair("userID",userID));
                postParameters.add(new BasicNameValuePair("date",date.getText().toString()));
                postParameters.add(new BasicNameValuePair("systolic",systolic.getText().toString()));
                postParameters.add(new BasicNameValuePair("diastolic",diastolic.getText().toString()));
                postParameters.add(new BasicNameValuePair("pulse",pulse.getText().toString()));
                System.out.println(date.getText().toString()+""+userID);
                try {
					response = CustomHttpClient.executeHttpPost("https://phr-ripudamanflora.rhcloud.com/mobile/add_blood_pressure.php", postParameters);
				} catch (Exception e) {
					query_response.setText(response);
				}
                System.out.println(response);
                query_response.setText(response);
                //Intent i = new Intent(getApplicationContext(), view_blood_pressure.class);
                //i.putExtra("userID", userID);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//startActivity(i); 
				
            }});
        
        TextView cancelBloodPressure= (TextView) findViewById(R.id.cancel_blood_pressure);
               
        cancelBloodPressure.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i); 

            }
	});
	}
}
