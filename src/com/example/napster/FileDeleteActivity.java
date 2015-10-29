//package com.nit.storage;
//
//
//
//import com.nit.storage.Option;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.jibble.simpleftp.SimpleFTP;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.List;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import android.app.ListActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
//
//public class FileDeleteActivity extends ListActivity {
//    /** Called when the activity is first created. */
//	static String fIP="192.0.0.5";
//	   
//	private FileArrayAdapter adapter;
//	String[] zz=null;
//	int statusCode;
//	   private File currentDir;
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//		
//        super.onCreate(savedInstanceState);
//        initial();
//       
//	}
//	public void initial()
//	{
//		 FTPClient f = new FTPClient();
//		   
//			try{
//			
//			
//		    f.connect(fIP,21);
//		    f.enterLocalPassiveMode();
//		   
//		       
//		   f.login("napser","1821389Vijay");
//			   
//		   
//		    
//		     
//		     FTPFile[] listing = f.listFiles("/"+getIntent().getSerializableExtra("Uname").toString());
//		     
//		     f.logout(); 
//	         f.disconnect(); 
//		     zz=new String[listing.length];
//		     int s=0;
//		     while(s<listing.length){
//		    	 zz[s]=listing[s].getName();
//		    	 s++;
//		     }
//		     setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, zz));
//
//		     ListView lv = getListView();
//		     lv.setTextFilterEnabled(true);
//
//		     lv.setOnItemClickListener(new OnItemClickListener() {
//		       public void onItemClick(AdapterView<?> parent, View view,
//		           int position, long id) {
//		         // When clicked, show a toast with the TextView text
//		         downloading(zz[position]);
//		    	 //  Toast.makeText(getApplicationContext(), zz[position],
//		           //  Toast.LENGTH_SHORT).show();
//		       }
//		     });
//		    
//			}catch(Exception e)
//			{
//				
//			}
//	}
//		public void downloading(String filen)
//		{
//			FTPClient f1 = new FTPClient();
//		    
//		 
//		       try{
//		        	
//		    f1.connect(fIP,21);
//		    f1.enterLocalPassiveMode();
//		       
//		   f1.login("napser","1821389Vijay");
//		   
//		 f1.deleteFile("/"+getIntent().getSerializableExtra("Uname").toString()+"/"+filen);
//	      
//		  
//
//		    
//		         f1.logout(); 
//		         f1.disconnect(); 
//		       }
//		       catch(Exception e)
//		       {}
//		       initial();
//		       }
//		 public void onBackPressed() {
//			    // check if page 2 is open
//			        android.os.Process.killProcess(android.os.Process.myPid());
//
//			    }
//		 @Override
//		    public boolean onKeyDown(int keyCode, KeyEvent event) {
//		        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//		        onBackPressed();
//		            //android.os.Process.killProcess(android.os.Process.myPid());
//		        }
//		        return super.onKeyDown(keyCode, event);
//		    }
//}