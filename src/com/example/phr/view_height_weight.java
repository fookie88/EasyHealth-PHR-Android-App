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

public class view_height_weight extends Activity{
String userID,query_response;
ArrayList<NameValuePair> postParameters_date, postParameters_height, postParameters_weight;

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
        
        setContentView(R.layout.view_height_weight);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("height_weight_arrays","string" , getPackageName()));
        
        TextView addNewheight_weight= (TextView) findViewById(R.id.add_new_height_weight);
        TextView backheight_weight= (TextView) findViewById(R.id.back_height_weight);
        //Spinner height_weightViewBy= (Spinner) findViewById(R.id.height_weight_view_by);
        
        //String view_by = height_weightViewBy.getSelectedItem().toString();
        
        
        addNewheight_weight.setOnClickListener(new View.OnClickListener() {
         
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_height_weight.class);
                startActivity(i);

            }
});
        backheight_weight.setOnClickListener(new View.OnClickListener() {
        
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i);

            }
});
        
        postParameters_date = new ArrayList<NameValuePair>();
        postParameters_date.add(new BasicNameValuePair("userID",userID));
        postParameters_date.add(new BasicNameValuePair("view_by","date"));
        
        postParameters_height = new ArrayList<NameValuePair>();
        postParameters_height.add(new BasicNameValuePair("userID",userID));
        postParameters_height.add(new BasicNameValuePair("view_by","height"));
        
        postParameters_weight = new ArrayList<NameValuePair>();
        postParameters_weight.add(new BasicNameValuePair("userID",userID));
        postParameters_weight.add(new BasicNameValuePair("view_by","weight"));
        

        String[] values_date = new String[5];
        String[] values_height = new String[5];
        String[] values_weight = new String[5];
        String[] values_pulse = new String[5];
        try {
values_date = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_height_weight.php", postParameters_date);
values_height = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_height_weight.php", postParameters_height);
values_weight = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_height_weight.php", postParameters_weight);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        
        JSONArray arr_date = null;
        JSONArray arr_height = null;
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
	arr_height = new JSONArray(values_height[0]);
} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	arr_weight = new JSONArray(values_weight[0]);
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
        
        List<String> list_height = new ArrayList<String>();
        for(int i = 0; i < arr_height.length(); i++){
            try {
            	list_height.add(arr_height.getJSONObject(i).getString("height"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }
        
        List<String> list_weight = new ArrayList<String>();
        for(int i = 0; i < arr_weight.length(); i++){
            try {
            	list_weight.add(arr_weight.getJSONObject(i).getString("weight"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }
        


          final ListView listview_date = (ListView) findViewById(R.id.view_temperature_date);

          final StableArrayAdapter adapter_date = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_date);
          listview_date.setAdapter((ListAdapter) adapter_date);
          
          
          final ListView listview_height = (ListView) findViewById(R.id.view_temperature_temperature);

          final StableArrayAdapter adapter_height = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_height);
          listview_height.setAdapter((ListAdapter) adapter_height);
          
          
          final ListView listview_weight = (ListView) findViewById(R.id.view_height_weight_weight);

          final StableArrayAdapter adapter_weight = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_weight);
          listview_weight.setAdapter((ListAdapter) adapter_weight);
          
          

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