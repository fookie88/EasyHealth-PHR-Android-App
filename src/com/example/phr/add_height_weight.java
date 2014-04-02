package com.example.phr;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

public class add_height_weight extends Activity{
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
        
        setContentView(R.layout.add_height_weight);
	}
}
