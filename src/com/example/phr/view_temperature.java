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

public class view_temperature extends Activity{
String userID,query_response;
ArrayList<NameValuePair> postParameters_date, postParameters_temperature;

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
        
        setContentView(R.layout.view_temperature);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("temperature_arrays","string" , getPackageName()));
        
        TextView addNewtemperature= (TextView) findViewById(R.id.add_new_temperature);
        TextView backtemperature= (TextView) findViewById(R.id.back_temperature);
        //Spinner temperatureViewBy= (Spinner) findViewById(R.id.temperature_view_by);
        
        //String view_by = temperatureViewBy.getSelectedItem().toString();
        
        
        addNewtemperature.setOnClickListener(new View.OnClickListener() {
         
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_temperature.class);
                startActivity(i);

            }
});
        backtemperature.setOnClickListener(new View.OnClickListener() {
        
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i);

            }
});
        
        postParameters_date = new ArrayList<NameValuePair>();
        postParameters_date.add(new BasicNameValuePair("userID",userID));
        postParameters_date.add(new BasicNameValuePair("view_by","date"));
        
        postParameters_temperature = new ArrayList<NameValuePair>();
        postParameters_temperature.add(new BasicNameValuePair("userID",userID));
        postParameters_temperature.add(new BasicNameValuePair("view_by","temperature"));
        
        

        String[] values_date = new String[5];
        String[] values_temperature = new String[5];
        String[] values_weight = new String[5];

        try {
values_date = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_temperature.php", postParameters_date);
values_temperature = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_temperature.php", postParameters_temperature);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        
        JSONArray arr_date = null;
        JSONArray arr_temperature = null;
        JSONArray arr_weight = null;
        JSONArray arr_pulse = null;
//try {
try {
	arr_date = new JSONArray(values_date[0]);
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	arr_temperature = new JSONArray(values_temperature[0]);
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
        
        List<String> list_temperature = new ArrayList<String>();
        for(int i = 0; i < arr_temperature.length(); i++){
            try {
            	list_temperature.add(arr_temperature.getJSONObject(i).getString("temperature"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }
        

        


          final ListView listview_date = (ListView) findViewById(R.id.view_temperature_date);

          final StableArrayAdapter adapter_date = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_date);
          listview_date.setAdapter((ListAdapter) adapter_date);
          
          
          final ListView listview_temperature = (ListView) findViewById(R.id.view_temperature_temperature);

          final StableArrayAdapter adapter_temperature = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_temperature);
          listview_temperature.setAdapter((ListAdapter) adapter_temperature);
          
         
          
          

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