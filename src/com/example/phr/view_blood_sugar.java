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

public class view_blood_sugar extends Activity{
String userID,query_response;
ArrayList<NameValuePair> postParameters_date, postParameters_blood_sugar;

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
        
        setContentView(R.layout.view_blood_sugar);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("blood_sugar_arrays","string" , getPackageName()));
        
        TextView addNewblood_sugar= (TextView) findViewById(R.id.add_new_blood_sugar);
        TextView backblood_sugar= (TextView) findViewById(R.id.back_blood_sugar);
        //Spinner blood_sugarViewBy= (Spinner) findViewById(R.id.blood_sugar_view_by);
        
        //String view_by = blood_sugarViewBy.getSelectedItem().toString();
        
        
        addNewblood_sugar.setOnClickListener(new View.OnClickListener() {
         
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_blood_sugar.class);
                startActivity(i);

            }
});
        backblood_sugar.setOnClickListener(new View.OnClickListener() {
        
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile_page.class);
                startActivity(i);

            }
});
        
        postParameters_date = new ArrayList<NameValuePair>();
        postParameters_date.add(new BasicNameValuePair("userID",userID));
        postParameters_date.add(new BasicNameValuePair("view_by","date"));
        
        postParameters_blood_sugar = new ArrayList<NameValuePair>();
        postParameters_blood_sugar.add(new BasicNameValuePair("userID",userID));
        postParameters_blood_sugar.add(new BasicNameValuePair("view_by","blood_sugar"));
        
        

        String[] values_date = new String[5];
        String[] values_blood_sugar = new String[5];
        String[] values_weight = new String[5];

        try {
values_date = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_sugar.php", postParameters_date);
values_blood_sugar = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_sugar.php", postParameters_blood_sugar);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        
        JSONArray arr_date = null;
        JSONArray arr_blood_sugar = null;
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
	arr_blood_sugar = new JSONArray(values_blood_sugar[0]);
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
        
        List<String> list_blood_sugar = new ArrayList<String>();
        for(int i = 0; i < arr_blood_sugar.length(); i++){
            try {
            	list_blood_sugar.add(arr_blood_sugar.getJSONObject(i).getString("blood_sugar"));
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        }
        

        


          final ListView listview_date = (ListView) findViewById(R.id.view_blood_sugar_date);

          final StableArrayAdapter adapter_date = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_date);
          listview_date.setAdapter((ListAdapter) adapter_date);
          
          
          final ListView listview_blood_sugar = (ListView) findViewById(R.id.view_blood_sugar_blood_sugar);

          final StableArrayAdapter adapter_blood_sugar = new StableArrayAdapter(this,
              android.R.layout.simple_list_item_1, list_blood_sugar);
          listview_blood_sugar.setAdapter((ListAdapter) adapter_blood_sugar);
          
         
          
          

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