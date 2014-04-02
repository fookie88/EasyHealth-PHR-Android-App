package com.example.phr;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

public class add_allergy extends Activity{
	TextView submitAllergy,allergen,allergic_reaction,age_of_onset,severity,onset_of_reaction,query_response;
	ArrayList<NameValuePair> postParameters;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	} 
        // setting default screen to login.xml
        
        setContentView(R.layout.add_allergy);
        postParameters = new ArrayList<NameValuePair>();
        submitAllergy= (TextView) findViewById(R.id.submit_allergy);
        allergen= (TextView) findViewById(R.id.allergy_allergen);
        allergic_reaction= (TextView) findViewById(R.id.allergy_allergic_reaction);
        age_of_onset= (TextView) findViewById(R.id.allergy_age_of_onset);
        severity= (TextView) findViewById(R.id.allergy_severity);
        onset_of_reaction= (TextView) findViewById(R.id.allergy_onset_of_reaction);
        query_response= (TextView) findViewById(R.id.response);
        
        submitAllergy.setOnClickListener(new View.OnClickListener() {
            String response;
            String userID = getIntent().getExtras().getString("userID");
            public void onClick(View v) {
            	
            	postParameters.add(new BasicNameValuePair("userID",userID));
                postParameters.add(new BasicNameValuePair("allergen",allergen.getText().toString()));
                postParameters.add(new BasicNameValuePair("allergic_reaction",allergic_reaction.getText().toString()));
                postParameters.add(new BasicNameValuePair("age_of_onset",age_of_onset.getText().toString()));
                postParameters.add(new BasicNameValuePair("severity",severity.getText().toString()));
                postParameters.add(new BasicNameValuePair("onset_of_reaction",onset_of_reaction.getText().toString()));
                System.out.println(allergen.getText().toString()+""+userID);
                try {
					response = CustomHttpClient.executeHttpPost("https://phr-ripudamanflora.rhcloud.com/mobile/add_allergy.php", postParameters);
				} catch (Exception e) {
					query_response.setText(response);
				}
                System.out.println(response);
                query_response.setText(response);
                //Intent i = new Intent(getApplicationContext(), view_allergy.class);
                //i.putExtra("userID", userID);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//startActivity(i); 
				
            }});
	}
}
