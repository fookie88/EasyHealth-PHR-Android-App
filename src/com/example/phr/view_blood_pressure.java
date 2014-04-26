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

public class view_blood_pressure extends Activity{
String userID,query_response;
ArrayList<NameValuePair> postParameters_date, postParameters_systolic, postParameters_diastolic, postParameters_pulse;

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
        
        setContentView(R.layout.view_blood_pressure);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("blood_pressure_arrays","string" , getPackageName()));
        
        TextView addNewblood_pressure= (TextView) findViewById(R.id.add_new_blood_pressure);
        TextView backblood_pressure= (TextView) findViewById(R.id.back_blood_pressure);
        //Spinner blood_pressureViewBy= (Spinner) findViewById(R.id.blood_pressure_view_by);
        
        //String view_by = blood_pressureViewBy.getSelectedItem().toString();
        
        
        addNewblood_pressure.setOnClickListener(new View.OnClickListener() {
         
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_blood_pressure.class);
                startActivity(i);

            }
});
        backblood_pressure.setOnClickListener(new View.OnClickListener() {
        
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i);

            }
});
        
        postParameters_date = new ArrayList<NameValuePair>();
        postParameters_date.add(new BasicNameValuePair("userID",userID));
        postParameters_date.add(new BasicNameValuePair("view_by","date"));
        
        postParameters_systolic = new ArrayList<NameValuePair>();
        postParameters_systolic.add(new BasicNameValuePair("userID",userID));
        postParameters_systolic.add(new BasicNameValuePair("view_by","systolic"));
        
        postParameters_diastolic = new ArrayList<NameValuePair>();
        postParameters_diastolic.add(new BasicNameValuePair("userID",userID));
        postParameters_diastolic.add(new BasicNameValuePair("view_by","diastolic"));
        
        postParameters_pulse = new ArrayList<NameValuePair>();
        postParameters_pulse.add(new BasicNameValuePair("userID",userID));
        postParameters_pulse.add(new BasicNameValuePair("view_by","pulse"));

        String[] values_date = new String[5];
        String[] values_systolic = new String[5];
        String[] values_diastolic = new String[5];
        String[] values_pulse = new String[5];
        try {
values_date = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters_date);
values_systolic = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters_systolic);
values_diastolic = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters_diastolic);
values_pulse = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters_pulse);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        
        JSONArray arr_date = null;
        JSONArray arr_systolic = null;
        JSONArray arr_diastolic = null;
        JSONArray arr_pulse = null;
//try {
try {
	arr_date = new JSONArray(values_date[0]);
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	arr_systolic = new JSONArray(values_systolic[0]);
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	arr_diastolic = new JSONArray(values_diastolic[0]);
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	arr_pulse = new JSONArray(values_pulse[0]);
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
//} catch (JSONException e1) {
// TODO Auto-generated catch block
//e1.printStackTrace();
//}
        List<String> list_date = new ArrayList<String>();
        for(int i = 0; i < arr_date.length(); i++){
            try {
            	list_date.add(arr_date.getJSONObject(i).getString("date"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }   
        
        List<String> list_systolic = new ArrayList<String>();
        for(int i = 0; i < arr_systolic.length(); i++){
            try {
            	list_systolic.add(arr_systolic.getJSONObject(i).getString("systolic"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }
        
        List<String> list_diastolic = new ArrayList<String>();
        for(int i = 0; i < arr_diastolic.length(); i++){
            try {
            	list_diastolic.add(arr_diastolic.getJSONObject(i).getString("diastolic"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }
        
        List<String> list_pulse = new ArrayList<String>();
        for(int i = 0; i < arr_pulse.length(); i++){
            try {
            	list_pulse.add(arr_pulse.getJSONObject(i).getString("pulse"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }

          final ListView listview_date = (ListView) findViewById(R.id.view_blood_pressure_date);

          final StableArrayAdapter adapter_date = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_date);
          listview_date.setAdapter((ListAdapter) adapter_date);
          
          
          final ListView listview_systolic = (ListView) findViewById(R.id.view_blood_pressure_systolic);

          final StableArrayAdapter adapter_systolic = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_systolic);
          listview_systolic.setAdapter((ListAdapter) adapter_systolic);
          
          
          final ListView listview_diastolic = (ListView) findViewById(R.id.view_blood_pressure_diastolic);

          final StableArrayAdapter adapter_diastolic = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_diastolic);
          listview_diastolic.setAdapter((ListAdapter) adapter_diastolic);
          
          
          final ListView listview_pulse = (ListView) findViewById(R.id.view_blood_pressure_pulse);

          final StableArrayAdapter adapter_pulse = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_pulse);
          listview_pulse.setAdapter((ListAdapter) adapter_pulse);
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