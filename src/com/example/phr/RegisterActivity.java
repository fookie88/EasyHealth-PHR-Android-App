package com.example.phr;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
 
public class RegisterActivity extends Activity {
    
    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    TextView register;
    TextView loginScreen;
    TextView regEmail;
    TextView regPassword;
    TextView regError;
    TextView regQue;
    TextView regAns;
 
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        // Set View to register.xml
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	        new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	} 
 	setContentView(R.layout.register);
    	register= (TextView) findViewById(R.id.btnRegister);
        loginScreen = (TextView) findViewById(R.id.link_to_login);
        regEmail = (TextView) findViewById(R.id.reg_email);
        regPassword = (TextView) findViewById(R.id.reg_password);
        regError= (TextView) findViewById(R.id.regError);
        regQue= (TextView) findViewById(R.id.reg_secretQ);
        regAns= (TextView) findViewById(R.id.reg_secretAns);
    	     
        register.setOnClickListener(new View.OnClickListener() {
        	String response;
            				 
            public void onClick(View arg0) {
            	 
                try{
                	byte[] bytesOfMessage = regPassword.getText().toString().getBytes("UTF-8");
                	MessageDigest md = MessageDigest.getInstance("MD5");
                	byte[] thedigest = md.digest(bytesOfMessage);
                	BigInteger bigInt = new BigInteger(1,thedigest);
                	String hashtext = bigInt.toString(16);
                // Listening to Login Screen link
                postParameters.add(new BasicNameValuePair("username",regEmail.getText().toString()));
                postParameters.add(new BasicNameValuePair("password",hashtext));
                postParameters.add(new BasicNameValuePair("secQue",regQue.getText().toString()));
                postParameters.add(new BasicNameValuePair("secAns",regAns.getText().toString()));
                response = CustomHttpClient.executeHttpPost("https://phr-ripudamanflora.rhcloud.com/mobile/register_android.php", postParameters);
        		regError.setText(response);
                }catch(Exception e){
                	regError.setText(e.getMessage());
                	
                }                                // Closing registration screen
                // Switching to Login Screen/closing register screen
                //finish();
            }
        });
		loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
    }
}