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
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.*;
public class view_emergency_contact extends Activity {
    String userID, query_response;
    ArrayList < NameValuePair > postParameters,postParametersInit;
    Spinner emergency_contactViewBy;
    StableArrayAdapter adapter;
    int iCurrentSelection;
    ListView listview;
    List < String > list,listInit;
    String baseElement;
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

        setContentView(R.layout.view_emergency_contact);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("emergency_contact_arrays","string" , getPackageName()));
        list = new ArrayList < String > ();
        listInit = new ArrayList < String > ();

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView addNewemergency_contact = (TextView) findViewById(R.id.add_new_emergency_contact);
        TextView backemergency_contact = (TextView) findViewById(R.id.back_view_emergency_contact);
        emergency_contactViewBy = (Spinner) findViewById(R.id.emergency_contact_view_by);
        listview = (ListView) findViewById(R.id.view_emergency_contact_list);
        baseElement=emergency_contactViewBy.getItemAtPosition(0).toString() ;
        textView2.setText(baseElement);
        postParametersInit = new ArrayList < NameValuePair > ();
        postParametersInit.add(new BasicNameValuePair("userID", userID));
        postParametersInit.add(new BasicNameValuePair("view_by",baseElement));
        String[] valuesInit = new String[5];
        
        /*
         * Code Ends Here for removing first item..
         */
        
        
        try {
            valuesInit = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_emergency_contact.php", postParametersInit);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray arrInit = null;
        try {
            arrInit = new JSONArray(valuesInit[0]);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < arrInit.length(); i++) {
            try {
                listInit.add(arrInit.getJSONObject(i).getString(baseElement));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		
        }

        addNewemergency_contact.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_emergency_contact.class);
                startActivity(i);

            }
        });
        backemergency_contact.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i);

            }
        });

        //Spinner magic
//        iCurrentSelection = emergency_contactViewBy.getSelectedItemPosition();

        emergency_contactViewBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {@
            Override
            public void onItemSelected(AdapterView <? > parent, View view,
                int position, long id) {
                // TODO Auto-generated method stub
//        		listview.getChildAt(5-listview.getFirstVisiblePosition());
        		//if(adapter.getCount()>0){
        		//}
        		try{
                if(adapter.getCount()>=5){
                    adapter.remove(adapter.getItem(4));
                    adapter.remove(adapter.getItem(3));
                    adapter.remove(adapter.getItem(2));
                    adapter.remove(adapter.getItem(1));
                    adapter.remove(adapter.getItem(0));
                }                
        		}catch(Exception e){}
        		String view_by = emergency_contactViewBy.getSelectedItem().toString();
                postParameters = new ArrayList < NameValuePair > ();
                postParameters.add(new BasicNameValuePair("userID", userID));
                postParameters.add(new BasicNameValuePair("view_by", view_by));

                String[] values = new String[5];
                try {
                    values = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_emergency_contact.php", postParameters);
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

                for (int i = 0; i < arr.length(); i++) {
                    try {
                    	if(emergency_contactViewBy.getSelectedItemPosition()==0)
                            list.add(arr.getJSONObject(i).getString(view_by));
                    	else
                        list.add(listInit.get(i).toString()+"\t\t--\t\t"+arr.getJSONObject(i).getString(view_by));
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            		
                }
                adapter = new StableArrayAdapter(parent.getContext(),
                		android.R.layout.simple_list_item_1, list);


                listview.setAdapter((ListAdapter) adapter);
                adapter.notifyDataSetChanged();
                //	    iCurrentSelection = i;
            	
        }
            @Override
            public void onNothingSelected(AdapterView <? > parent) {
                // TODO Auto-generated method stub

            }


        });
    }

}