package com.example.phr;
import java.math.*;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.security.*;

public class LoginActivity extends Activity {
    //@Override
    
 
        // Importing all assets like buttons, text fields
  
    TextView inputEmail;
    TextView inputPassword;
    TextView loginErrorMsg;
    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    int userID;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	} 
        // setting default screen to login.xml
        
        setContentView(R.layout.login);
        inputEmail = (TextView) findViewById(R.id.LoginID);
        inputPassword = (TextView) findViewById(R.id.LoginPass);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        TextView login= (TextView) findViewById(R.id.regError);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);
        // Listening to register new account link

        

        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View v) {
            	String response;

            	try {
                	byte[] bytesOfMessage = inputPassword.getText().toString().getBytes("UTF-8");
                	MessageDigest md = MessageDigest.getInstance("MD5");
                	byte[] thedigest = md.digest(bytesOfMessage);
                	BigInteger bigInt = new BigInteger(1,thedigest);
                	String hashtext = bigInt.toString(16);
                    postParameters.add(new BasicNameValuePair("username",inputEmail.getText().toString()));
                    postParameters.add(new BasicNameValuePair("password",hashtext));
                    response = CustomHttpClient.executeHttpPost("https://phr-ripudamanflora.rhcloud.com/mobile/JSON.php", postParameters);
					response = response.substring(1,response.length()-2);
                    loginErrorMsg.setText(response);
                    try{
                    userID =Integer.parseInt(response);
					
                    
                    Intent i = new Intent(getApplicationContext(), profile_page.class);
                    i.putExtra("userID", userID+"");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i); 
					// Close Login Screen
					finish();
                    }catch(Exception e){
                    	loginErrorMsg.setText(response);                    	
                    }   
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            	           }
                // Switching to Welcome screen
                
            
        });
    }
}