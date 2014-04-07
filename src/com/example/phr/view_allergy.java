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
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("userData", 0);
		userID = settings.getString("userID", "string");
		System.out.println(userID);
         if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	} 
        // setting default screen to login.xml
        
        setContentView(R.layout.view_allergy);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("allergy_arrays","string" , getPackageName()));
        
        TextView addNewAllergy= (TextView) findViewById(R.id.add_new_allergy);
        TextView backAllergy= (TextView) findViewById(R.id.back_view_allergy);
        
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
		
    

          final ListView listview = (ListView) findViewById(R.id.view_allergy_list);
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
          final StableArrayAdapter adapter = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list);
          listview.setAdapter((ListAdapter) adapter);

          
        }

        private class StableArrayAdapter extends ArrayAdapter<String> {

          HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

          public StableArrayAdapter(Context context, int textViewResourceId,
              List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
              mIdMap.put(objects.get(i), i);
            }
          }

          public void notifyDataSetChanged() {
			// TODO Auto-generated method stub
			
		}

		@Override
          public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
          }

          @Override
          public boolean hasStableIds() {
            return true;
          }

        }

       
        
	}
