//package com.nit.storage;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.Random;
//
//import org.apache.http.Header;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class HTTPTest extends Activity {
//	static String hIP="10.0.2.2:8080";
//static int i=0;
//static int ts=0;
//Header[] im;
//Header it;
//Header[] tf;
//	ImageView image;
//	TextView mytext=null;
//	int z=0;
//	int tl=0;
//	 String [] fi;
//	int statusCode=0;
//	String mainid="";
//	String othernam="";
//String imageUrl="http://"+hIP+"/one/";
//String textUrl="http://"+hIP+"/one/WebContent/";
//	//String imageUrl="/sdcard/";
//	Random r;
//
///** Called when the activity is first created. */
//@Override
//public void onCreate(Bundle icicle) {
//	
//super.onCreate(icicle);
//othernam=getIntent().getSerializableExtra("Uname").toString();
//String otherid=getIntent().getSerializableExtra("Uid").toString();
//mainid=getIntent().getSerializableExtra("mainid").toString();
//
//
//HttpClient client = new DefaultHttpClient(); 
//String getURL ="http://10.0.2.2:8080/one/getfiletype.jsp?uid="+othernam;
//HttpGet get = new HttpGet(getURL);
//
//try {	             
//     HttpResponse responseGet = client.execute(get);           
//     StatusLine statusLine = responseGet.getStatusLine();
//			 statusCode= statusLine.getStatusCode();
//			if (statusCode == 200) {
//                             //do something with the response
//             //Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
//				tl=responseGet.getHeaders("text").length;
//				 tf=responseGet.getHeaders("text");
//									z=responseGet.getHeaders("image").length;
//									 im=responseGet.getHeaders("image");
//									
//														
//			}
//} 
//catch (Exception e) {
//					TextView tv=new TextView(this);
//					tv.setText(e.getLocalizedMessage());
//					setContentView(tv);
//}	
//
//
//imageUrl=imageUrl+othernam+"/";
//setContentView(R.layout.download);
//TextView t = (TextView)findViewById(R.id.txt2);
//t.setText("you are viewing "+othernam+"'s profile ");
//r= new Random();
//
//Button bt3= (Button)findViewById(R.id.get_imagebt);
//
//bt3.setOnClickListener(getImgListener);
//
//image = (ImageView)findViewById(R.id.imview);
//
//}
//
//View.OnClickListener getImgListener = new View.OnClickListener()
//{
//@Override
//public void onClick(View view) {
//// TODO Auto-generated method stub
//
//
//Bitmap x=downloadFile(imageUrl+im[i].getValue());
////FileWriter ss=new FileWriter(null);
//image.setImageBitmap(x);
//Log.i("im url",imageUrl+im[i]);
//i++;
//if(i==z)
//	i=0;
//}
//};
//public void imagetinker(View v)
//{
//	setContentView(R.layout.download);
//	TextView t = (TextView)findViewById(R.id.txt2);
//	t.setText("you are viewing "+othernam+"'s profile ");
//	Bitmap x=downloadFile(imageUrl+im[i].getValue());
//	//FileWriter ss=new FileWriter(null);
//	image.setImageBitmap(x);
//	image = (ImageView)findViewById(R.id.imview);
//	Log.i("im url",imageUrl+im[i]);
//	i++;
//	if(i==z)
//		i=0;	
//}
//
//
//
//public void textfunc(View v)
//{
//
//	
//
//	setContentView(R.layout.downloadima);
//	TextView t = (TextView)findViewById(R.id.txt2);
//	t.setText("you are viewing "+othernam+"'s profile ");
//	
//	
//	HttpClient client = new DefaultHttpClient(); 
//	String getURL ="http://10.0.2.2:8080/one/rtext.jsp?ufol="+othernam+"&uid="+tf[ts].getValue();
//	HttpGet get = new HttpGet(getURL);
//
//	try {	             
//	     HttpResponse responseGet = client.execute(get);           
//	     StatusLine statusLine = responseGet.getStatusLine();
//				 statusCode= statusLine.getStatusCode();
//				if (statusCode == 200) {
//	                             //do something with the response
//	             //Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
//										 it=responseGet.getFirstHeader("textValue");
//										
//															
//				}
//	} 
//	catch (Exception e) {
//						TextView tv=new TextView(this);
//						tv.setText(e.getLocalizedMessage());
//						setContentView(tv);
//	}	
//
//
//TextView mytext1= (TextView) findViewById(R.id.tv);
//mytext1.setText(it.getValue());
//ts++;
//if(ts==tl)
//{ts=0;
//	}
//
//}
//
//
//private Bitmap downloadFile(String fileUrl){
//	Bitmap bmImg = null;
//	URL myFileUrl =null;
//	try {
//	myFileUrl= new URL(fileUrl);
//	HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
//	conn.setDoInput(true);
//	conn.connect();
//	InputStream is = conn.getInputStream();
//	bmImg = BitmapFactory.decodeStream(is);
//	
//	
//	
//	} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//	}
//	return bmImg;
//	}
///*public void goHome(View v)
//{	
//	Intent i2 = new Intent(this, LOGINCloudActivity.class);
//i2.putExtra("fname",mainid);
//i2.putExtra("bool", "true");
//startActivity(i2);
//android.os.Process.killProcess(android.os.Process.myPid());
//	}*/ public void onBackPressed() {
//	    // check if page 2 is open
//        android.os.Process.killProcess(android.os.Process.myPid());
//
//    }
//@Override
//public boolean onKeyDown(int keyCode, KeyEvent event) {
//    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//    onBackPressed();
//        //android.os.Process.killProcess(android.os.Process.myPid());
//    }
//    return super.onKeyDown(keyCode, event);
//}
//public void media(View v){
//	
//	setContentView(R.layout.downloadima);
//	TextView t = (TextView)findViewById(R.id.txt2);
//	t.setText("you are viewing "+othernam+"'s profile ");
//Toast.makeText(getApplicationContext(), "underconstruction", 2).show();
//	
//	
//}
//}
