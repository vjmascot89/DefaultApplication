package com.example.napster;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.defaultapplication.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NITCloudActivity extends Activity {
    /** Called when the activity is first created. */
	static String hIP="10.0.2.2:8080";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.cloudlogin);  
       
        
    } 
    public void call(View v)
    {    String name1=null;
	 String name2=null;
    EditText name=(EditText)findViewById(R.id.username);
     name1= name.getText().toString();
    	 EditText pass=(EditText)findViewById(R.id.password);
    	 name2= pass.getText().toString();
        HttpClient client = new DefaultHttpClient();
        
        if(name1.length()!=0&&name2.length()!=0)
        {
        String getURL ="http://"+hIP+"/one/try.jsp?userName="+name1+"&password="+name2;
        HttpGet get = new HttpGet(getURL);  
        try {
        	
            
            HttpResponse responseGet = client.execute(get);  
          
            StatusLine statusLine = responseGet.getStatusLine();
    		int statusCode = statusLine.getStatusCode();
    		if (statusCode == 200) {
                                    //do something with the response
                   //Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
    			int x=responseGet.getHeaders("name").length;
    			Header[] head=responseGet.getHeaders("name");
    			int y=responseGet.getHeaders("boo").length;
    			Header[] boolhead=responseGet.getHeaders("boo");
    			int z=responseGet.getHeaders("uname").length;
    			Header[] uhead=responseGet.getHeaders("uname");
    			int bol=responseGet.getHeaders("bool").length;
    			Header[] ubooli=responseGet.getHeaders("bool");
 int s=0;
 String zz="";
 String yy="";
 String xx="";
 String bool="";
    			while(s<x) 
    			{
    			
    			zz=head[s].getValue();
    		   	 
    		   	  
    		   	  s++;
                 
    			}
    			s=0;
    			while(s<y) 
    			{
    			
    			yy=boolhead[s].getValue();
    		   	 
    		   	  
    		   	  s++;
                 
    			}
    			s=0;
    			while(s<z) 
    			{
    			
    			xx=uhead[s].getValue();
    		   	 
    		   	  
    		   	  s++;
                 
    			}
    			s=0;
    			while(s<bol) 
    			{
    			
    			bool=ubooli[s].getValue();
    		   	 
    		   	  
    		   	  s++;
                 
    			}
    			if(bool.equalsIgnoreCase("true")){
    				
    			Intent i = new Intent(this, LOGINCloudActivity.class);
    			i.putExtra("iname",xx );
    		    i.putExtra("fname",zz );
    		    i.putExtra("bool", yy);
    		    i.putExtra("booli", bool);
    		startActivity(i);  
    		android.os.Process.killProcess(android.os.Process.myPid());}
    			else
    			{
    		        
    	        Toast.makeText(this, "Wrong ID || Pass", 5).show();
    	        setContentView(R.layout.cloudlogin);
    	        }
    			}
    } catch (Exception e) {
    	  TextView tv=new TextView(this);
    	  tv.setText(e.getLocalizedMessage());
    	  setContentView(tv);
    
    }	
        }
        else
        {
        
        	Toast.makeText(this, "One or two fields are blank", 5).show();
           setContentView(R.layout.cloudlogin);
        }
    }
    public void ending(View v)
    {

    	android.os.Process.killProcess(android.os.Process.myPid());

    	
    }
	
}