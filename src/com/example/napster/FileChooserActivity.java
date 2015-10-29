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
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class FileChooserActivity extends ListActivity {
//	static String fIP="192.0.0.5";
//    /** Called when the activity is first created. */
//	private FileArrayAdapter adapter;
//	int statusCode;
//	   private File currentDir;
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//		
//        super.onCreate(savedInstanceState);
//        currentDir = new File("/sdcard/");
//        fill(currentDir);
//        
//    }
//	private void fill(File f)
//    {
//        File[]dirs = f.listFiles();
//         this.setTitle("Current Dir: "+f.getName());
//         List<Option>dir = new ArrayList<Option>();
//         List<Option>fls = new ArrayList<Option>();
//         try{
//             for(File ff: dirs)
//             {
//            	 
//                if(ff.isDirectory())
//                    dir.add(new Option(ff.getName(),"Folder",ff.getAbsolutePath()));
//                else
//                {
//                    fls.add(new Option(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath()));
//                }
//                
//             }
//             
//         }catch(Exception e)
//         {
//             
//         }
//         Collections.sort(dir);
//         Collections.sort(fls);
//         fls.add(new Option("GO_HOME",null,"GO_HOME"));
//         dir.addAll(fls);
//         if(!f.getName().equalsIgnoreCase("sdcard"))
//             dir.add(0,new Option("..","Parent Directory",f.getParent()));
//         adapter = new FileArrayAdapter(FileChooserActivity.this,R.layout.file_view,dir);
//		 this.setListAdapter(adapter);
//
//
//    }
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
//		Option o = adapter.getItem(position);
//		if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory")){
//				currentDir = new File(o.getPath());
//				fill(currentDir);
//		}
//		else
//		{
//			
//			
//			String x=onFileClick(o);
//			  
//			if(!o.getPath().equalsIgnoreCase("go_home"))
//			{
//			  FTPClient f = new FTPClient();
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
//		     String filename = x;
//		    
//		     
//		     FTPFile[] listing = f.listFiles(getIntent().getSerializableExtra("Uname").toString());
//		     
//		  // enumerate thru listing
//int i=listing.length,j=0,counter=0;
//		  while(listing!=null&&j<i) {
//
//		  if(o.getName().equalsIgnoreCase(listing[j].getName()))
//		  {
//			  Toast.makeText(this, "File exist on the server change name to upload", Toast.LENGTH_SHORT).show();
//			      
//				counter=-1;
//		  }
//		  
//          j++;
//		 
//
//		  }
//		  if(counter==0)
//		  {
//		   FileInputStream bis=new FileInputStream(filename);
//			f.setFileType(FTP.BINARY_FILE_TYPE);
//			boolean Store= f.storeFile(getIntent().getSerializableExtra("Uname").toString()+"/"+o.getName(), bis);
//		      bis.close();
//		  }  
//		      f.logout(); 
//		        f.disconnect(); 
//		      }
//			 catch(Exception e){
//	        	 
//		        	e.printStackTrace();
//		        	e.getCause();
//		            
//		             
//		        }
//			}else
//			{
//				  Toast.makeText(this, "File exist on the server change name to upload", Toast.LENGTH_SHORT).show();
//						
//			 /*Intent i = new Intent(this, LOGINCloudActivity.class);
//				i.putExtra("iname",getIntent().getSerializableExtra("Uname").toString() );
//			    i.putExtra("fname",getIntent().getSerializableExtra("mainid").toString() );
//			    i.putExtra("bool", "true");
//			startActivity(i);
//			 android.os.Process.killProcess(android.os.Process.myPid());
//			*/}
//		}
//
//
//	}
//	public  String getExtension(Option o) {
//		String ext = null;
//		String s = o.getName();
//		int i = s.lastIndexOf('.');
//
//		if (i > 0 &&  i < s.length() - 1) {
//		    ext = s.substring(i+1).toLowerCase();
//		}
//		return ext;
//		}
//	private String onFileClick(Option o)
//    { //***********************uploadcode
//		
//		
//		return o.getPath();
//		}
//	 public void onBackPressed() {
//		    // check if page 2 is open
//		        android.os.Process.killProcess(android.os.Process.myPid());
//
//		    }
//	 @Override
//	    public boolean onKeyDown(int keyCode, KeyEvent event) {
//	        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//	        onBackPressed();
//	            //android.os.Process.killProcess(android.os.Process.myPid());
//	        }
//	        return super.onKeyDown(keyCode, event);
//	    }
//
//
//}
