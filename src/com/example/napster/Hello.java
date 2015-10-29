//package com.nit.storage;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class Hello extends Activity {
//	// Called the activity is first created.
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		TextView tv = new TextView(this);
//		tv.setText(getIntent().getSerializableExtra("bool").toString());
//		setContentView(tv);
//	}
//}
//
///*
// * public class Hello { static int i; Hello() { i=0; } }
// */
///*
// * import java.io.File; import java.io.FileInputStream; import
// * java.util.ArrayList; import java.util.Collections; import java.util.List;
// * 
// * import org.apache.http.HttpResponse; import org.apache.http.StatusLine;
// * import org.apache.http.client.HttpClient; import
// * org.apache.http.client.methods.HttpGet; import
// * org.apache.http.client.methods.HttpPost; import
// * org.apache.http.entity.InputStreamEntity; import
// * org.apache.http.impl.client.DefaultHttpClient;
// * 
// * import co.file.FileArrayAdapter;
// * 
// * import co.file.Option;
// * 
// * import android.app.ListActivity; import android.os.Bundle; import
// * android.os.Environment; import android.view.View; import
// * android.widget.ListView; import android.widget.TextView; import
// * android.widget.Toast;
// * 
// * public class FileChooserActivity extends ListActivity { private
// * FileArrayAdapter adapter; private File currentDir;
// * 
// * @Override public void onCreate(Bundle savedInstanceState) {
// * super.onCreate(savedInstanceState); currentDir = new File("/sdcard/");
// * fill(currentDir);
// * 
// * } private void fill(File f) { File[]dirs = f.listFiles();
// * this.setTitle("Current Dir: "+f.getName()); List<Option>dir = new
// * ArrayList<Option>(); List<Option>fls = new ArrayList<Option>(); try{ for(File
// * ff: dirs) { if(ff.isDirectory()) dir.add(new
// * Option(ff.getName(),"Folder",ff.getAbsolutePath())); else { fls.add(new
// * Option(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath())); } }
// * }catch(Exception e) {
// * 
// * } Collections.sort(dir); Collections.sort(fls); dir.addAll(fls);
// * if(!f.getName().equalsIgnoreCase("sdcard")) dir.add(0,new
// * Option("..","Parent Directory",f.getParent())); adapter = new
// * FileArrayAdapter(FileChooserActivity.this,R.layout.file_view,dir);
// * this.setListAdapter(adapter);
// * 
// * 
// * }
// * 
// * @Override protected void onListItemClick(ListView l, View v, int position,
// * long id) { // TODO Auto-generated method stub super.onListItemClick(l, v,
// * position, id); Option o = adapter.getItem(position);
// * if(o.getData().equalsIgnoreCase
// * ("folder")||o.getData().equalsIgnoreCase("parent directory")){ currentDir =
// * new File(o.getPath()); fill(currentDir); } else {
// * 
// * 
// * String x=onFileClick(o); //TextView tv=new TextView(this); //tv.setText(x);
// * //setContentView(tv); Toast.makeText(this,
// * "File Clicked: "+x//o.getData().length(), Toast.LENGTH_SHORT).show();
// * 
// * }
// * 
// * 
// * }
// * 
// * private String onFileClick(Option o) {
// * 
// * String strLine=""; try{ // Open the file that is the first // command line
// * parameter
// * 
// * FileInputStream fstream = new FileInputStream(o.getPath()+"/"); // Get the
// * object of DataInputStream DataInputStream in = new DataInputStream(fstream);
// * BufferedReader br = new BufferedReader(new InputStreamReader(in)); String
// * strLin; //Read File Line By Line while ((strLin = br.readLine()) != null) {
// * // Print the content on the console
// * 
// * strLine+=strLin+"\n";%><br><% } //Close the input stream in.close(); }catch
// * (Exception e){//Catch exception if any out.println( e.getMessage()); }
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * 
// * String ss=null; String url = "http://10.0.2.2:8080/one/uploading.jsp"; File
// * file = new File(Environment.getExternalStorageDirectory(),o.getName());
// * HttpPost http = new HttpPost(url); HttpClient httpclient = new
// * DefaultHttpClient(); try {
// * 
// * 
// * 
// * InputStreamEntity reqEntity = new InputStreamEntity(new
// * FileInputStream(file), -1); reqEntity.setContentType("multipart/form-data");
// * reqEntity.setChunked(true); // Send in multiple parts if needed
// * http.setEntity(reqEntity); HttpResponse responseGet =
// * httpclient.execute(http);
// * 
// * StatusLine statusLine = responseGet.getStatusLine(); int statusCode =
// * statusLine.getStatusCode(); ss=ss+"   "+statusCode+""; if (statusCode == 200)
// * {
// * 
// * //HttpResponse response = httpclient.execute(httppost); //Do something with
// * response... ss=responseGet.getFirstHeader("name").getValue(); } else
// * ss=""+statusCode+""; } catch (Exception e) { // show error } return ss;
// * 
// * }
// * 
// * 
// * }
// */
