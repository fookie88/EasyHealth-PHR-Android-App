package com.example.phr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class view_allergy extends Activity{
	String userID,query_response;
	String [] values;
	ArrayList<NameValuePair> postParameters;
	ArrayList<String> list;
	StableArrayAdapter adapter ;
	ListView listView2; 
	TextView addNewAllergy, backAllergy;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
       	StrictMode.ThreadPolicy policy = 
       	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
       	StrictMode.setThreadPolicy(policy);
       	} 

        //Retrieving the User ID
        SharedPreferences settings = getSharedPreferences("userData", 0);
		userID = settings.getString("userID", "string");
		System.out.println(userID);
        // setting default screen to view_allergy.xml
        setContentView(R.layout.view_allergy);

        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("allergy_arrays","string" , getPackageName()));
        
        addNewAllergy= (TextView) findViewById(R.id.add_new_allergy);
        backAllergy= (TextView) findViewById(R.id.back_view_allergy);
        
        addNewAllergy.setOnClickListener(new View.OnClickListener() {
         	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_allergy.class);
                startActivity(i); 

            }
	});
        backAllergy.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i); 

            }
	});
        
        postParameters = new ArrayList<NameValuePair>();        
        postParameters.add(new BasicNameValuePair("userID",userID));
		
        
        try {
			values = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_allergy.php", postParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problem getting the values");
			e.printStackTrace();
		}
		System.out.println(values[0]);
		System.out.println(values[1]);
		System.out.println(values[2]);
		System.out.println(values[3]);
		System.out.println(values[4]);
        //System.out.println(values);
        //query_response.setText(values);
        //Intent i = new Intent(getApplicationContext(), view_allergy.class);
        //i.putExtra("userID", userID);
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//startActivity(i); 
		
    

        listView2= (ListView) findViewById(R.id.view_allergy_list);
          //String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
           //   "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
           //   "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
           //   "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
           //   "Android", "iPhone", "WindowsMobile" };

          list = new ArrayList<String>();
          try{
          for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
          }
          }catch(Exception e){System.out.println("No values were retrieved");}
          adapter = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list);
          listView2.setAdapter((ListAdapter) adapter);

          
        }
}