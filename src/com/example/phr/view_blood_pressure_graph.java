package com.example.phr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.*;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphViewSeries.*;

import android.content.res.Resources;
public class view_blood_pressure_graph extends Activity {
    String userID, query_response;
    ArrayList < NameValuePair > postParameters1,postParameters2,postParameters3,postParameters4;
    Spinner blood_pressureViewBy;
    StableArrayAdapter adapter;
    int iCurrentSelection;
    ListView listview;
    List < String > list1,list2,list3,list4;
    String []baseElement;
    LinearLayout layout;
    
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

        setContentView(R.layout.view_blood_pressure_graph);
        //String [] arrayOfElements= getResources().getStringArray(getResources().getIdentifier("blood_pressure_arrays","string" , getPackageName()));
        list1 = new ArrayList < String > ();
        list2 = new ArrayList < String > ();
        list3 = new ArrayList < String > ();
        list4 = new ArrayList < String > ();

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView addNewblood_pressure = (TextView) findViewById(R.id.add_new_blood_pressure);
        TextView backblood_pressure = (TextView) findViewById(R.id.back_blood_pressure);

        baseElement = getResources().getStringArray(R.array.blood_pressure_arrays);

        //Acquire data 1
        postParameters1= new ArrayList < NameValuePair > ();
        postParameters1.add(new BasicNameValuePair("userID", userID));
        postParameters1.add(new BasicNameValuePair("view_by",baseElement[0]));
        String[] values1 = new String[5];

        try {
            values1 = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray arr1 = null;
        try {
            arr1 = new JSONArray(values1[0]);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < arr1.length(); i++) {
            try {
                list1.add(arr1.getJSONObject(i).getString(baseElement[0]));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		
        }

        //Acquire data2
        postParameters2 = new ArrayList < NameValuePair > ();
        postParameters2.add(new BasicNameValuePair("userID", userID));
        postParameters2.add(new BasicNameValuePair("view_by",baseElement[1]));
        String[] values2 = new String[5];

        try {
            values2 = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray arr2 = null;
        try {
            arr2 = new JSONArray(values2[0]);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < arr2.length(); i++) {
            try {
                list2.add(arr2.getJSONObject(i).getString(baseElement[1]));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		
        }

        //Acquire data 3
        postParameters3= new ArrayList < NameValuePair > ();
        postParameters3.add(new BasicNameValuePair("userID", userID));
        postParameters3.add(new BasicNameValuePair("view_by",baseElement[2]));
        String[] values3 = new String[5];

        try {
            values3 = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters3);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray arr3 = null;
        try {
            arr3 = new JSONArray(values3[0]);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < arr3.length(); i++) {
            try {
                list3.add(arr3.getJSONObject(i).getString(baseElement[2]));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		
        }


        //Acquire data 4
        postParameters4= new ArrayList < NameValuePair > ();
        postParameters4.add(new BasicNameValuePair("userID", userID));
        postParameters4.add(new BasicNameValuePair("view_by",baseElement[3]));
        String[] values4 = new String[5];

        try {
            values4 = CustomHttpClient.executeHttpPostArray("https://phr-ripudamanflora.rhcloud.com/mobile/view_blood_pressure.php", postParameters4);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray arr4 = null;
        try {
            arr4 = new JSONArray(values4[0]);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < arr4.length(); i++) {
            try {
                list4.add(arr4.getJSONObject(i).getString(baseElement[3]));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		
        }

        
     // Graph shit.   
     // first init data
     // sin curve
     int num = 5;
     GraphViewData[] data = new GraphViewData[num];
     try{
     for (int i=0; i<5; i++) {
       data[i] = new GraphViewData(i, Integer.parseInt(list2.get(i)));
     }
     }catch(Exception e){}
     GraphViewSeries systolic = new GraphViewSeries("Systolic     ", new GraphViewSeriesStyle(Color.rgb(19, 123, 123), 2)  , data);
      
     // cos curve
     data = new GraphViewData[num];
     for (int i=0; i<5; i++) {
       data[i] = new GraphViewData(i, Integer.parseInt(list3.get(i)));
     }
     GraphViewSeries diastolic = new GraphViewSeries("Diastolic      ", new GraphViewSeriesStyle(Color.rgb(191, 23, 23), 2) , data);
      
     // random curve
     num = 5;
     data = new GraphViewData[num];
     for (int i=0; i<5; i++) {
       data[i] = new GraphViewData(i, Integer.parseInt(list4.get(i)));
     }
     GraphViewSeries pulse = new GraphViewSeries("Pulse        ", new GraphViewSeriesStyle(Color.rgb(11, 223, 23), 2) , data);
      
     /*
      * create graph
      */
     
     GraphView graphView = new LineGraphView(
         this
         , "Blood Pressure"
     );
     // add data
     graphView.addSeries(systolic);
     graphView.addSeries(diastolic);
     graphView.addSeries(pulse);
     // optional - set view port, start=2, size=10
     graphView.setViewPort(0, 5);
     // optional - legend
     graphView.setScrollable(true);
     graphView.setScalable(true);
     graphView.setShowLegend(true);
     graphView.setLegendAlign(LegendAlign.MIDDLE);
     graphView.setLegendWidth(200);
     graphView.getGraphViewStyle().setNumHorizontalLabels(5);

  /*
     int sum=0;
    int max=0;
    int min=99999;
    for(int i1=0;i1<5;i1++){
    	 if(max<Integer.parseInt(list2.get(i1)))
    		 max = Integer.parseInt(list2.get(i1));
    	 if(max<Integer.parseInt(list3.get(i1)))
    		 max = Integer.parseInt(list2.get(i1));
    	 if(max<Integer.parseInt(list4.get(i1)))
    		 max = Integer.parseInt(list2.get(i1));
    	 if(min>Integer.parseInt(list2.get(i1)))
    		 min = Integer.parseInt(list2.get(i1));
    	 if(min>Integer.parseInt(list3.get(i1)))
    		 min = Integer.parseInt(list2.get(i1));
    	 if(min>Integer.parseInt(list4.get(i1)))
    		 min = Integer.parseInt(list2.get(i1));

     }
 */ //   int interval= (max-min)/5;
//     graphView.setVerticalLabels(new String[] {max+"", max-interval+"",max-interval*2+"",max-interval*3+"",max-interval*4+"",min+""});
     
 
    
     graphView.setHorizontalLabels(new String[] {list1.get(0).substring(5), list1.get(1).substring(5), list1.get(2).substring(5), list1.get(3).substring(5),list1.get(4).substring(5)});

     layout = (LinearLayout) findViewById(R.id.view_blood_pressure);
     layout.addView(graphView);
        /*
         * Code Ends Here for removing first item..
         */
        
        
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

        //Spinner magic
//        iCurrentSelection = blood_pressureViewBy.getSelectedItemPosition();

    }
}