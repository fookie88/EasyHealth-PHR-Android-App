package com.example.phr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

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
import android.widget.Spinner;
import android.widget.TextView;

public class view_allergy extends Activity{
	String userID,query_response;
	ArrayList<NameValuePair> postParameters;
	Spinner allergyViewBy;
	StableArrayAdapter adapter;
	int iCurrentSelection;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("userData", 0);
		userID = settings.getString("userID", "string");
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
        allergyViewBy= (Spinner) findViewById(R.id.allergy_view_by);
        
        String view_by = allergyViewBy.getSelectedItem().toString();
        
        
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
        postParameters.add(new BasicNameValuePair("view_by",view_by));
		
        String[] values = new String[5];
        try {
			values = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_allergy.php", postParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JSONArray arr = null;
		try {
			arr = new JSONArray(values[0]);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < arr.length(); i++){
            try {
				list.add(arr.getJSONObject(i).getString(view_by));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
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

          //final ArrayList<String> list = new ArrayList<String>();
          //for (int i = 0; i < values.length; ++i) {
           // list.add(values[i]);
          //}
          adapter = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list);
          listview.setAdapter((ListAdapter) adapter);
          
          //Spinner magic
          iCurrentSelection = allergyViewBy.getSelectedItemPosition();

		allergyViewBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { 
		    if (iCurrentSelection != i){
		    	String view_by = allergyViewBy.getSelectedItem().toString();
		    	 postParameters = new ArrayList<NameValuePair>();        
		         postParameters.add(new BasicNameValuePair("userID",userID));
		         postParameters.add(new BasicNameValuePair("view_by",view_by));
		 		
		         String[] values1 = new String[5];
		         try {
		 			values1 = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_allergy.php", postParameters);
		 		} catch (Exception e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		         
		         JSONArray arr1 = null;
		 		try {
		 			arr1 = new JSONArray(values1[0]);
		 		} catch (JSONException e1) {
		 			// TODO Auto-generated catch block
		 			e1.printStackTrace();
		 		}
		         List<String> list1 = new ArrayList<String>();
		         for(int i1 = 0; i1 < arr1.length(); i1++){
		             try {
		 				list1.add(arr1.getJSONObject(i).getString(view_by));
		 			} catch (JSONException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}
		         }
		 	
		         final ListView listview = (ListView) findViewById(R.id.view_allergy_list);
		         listview.setAdapter((ListAdapter) adapter);
		             
		    }
		    iCurrentSelection = i;
		    } 
		
		    public void onNothingSelected(AdapterView<?> adapterView) {
		        return;
		    } 
		}); 



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

