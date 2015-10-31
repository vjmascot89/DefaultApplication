package com.example.napster;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.defaultapplication.R;
import com.example.napster.concurrencypackage.SignInAsyncTask;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.model.UserDataModel;
import com.example.napster.utils.ConnectionDetailsUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LOGINCloudActivity extends Activity {
	// Called the activity is first created.
	static String hIP = "10.0.2.2:8080";
	String othername = null;
	String otherid = null;
	String ss = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialuser(null);

	}

	public void initialuser(View v) {
		Object userDataModel = getIntent().getSerializableExtra("UserModelValue");
					
//		String zz = userdata(ss);
		setContentView(R.layout.fbinfo);
		TextView t = (TextView) findViewById(R.id.tx);
		// TextView tv =new TextView(this);
		t.setText(((UserDataModel)userDataModel).getUserName());

	}

	public void setdata(View v) {
		String ss = getIntent().getSerializableExtra("fname").toString();
		EditText name = (EditText) findViewById(R.id.username);
		String uname = "";
		try {
			uname = URLEncoder.encode(name.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		EditText age = (EditText) findViewById(R.id.age);
		String ua = "";
		try {
			ua = URLEncoder.encode(age.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		EditText sex1 = (EditText) findViewById(R.id.sex);
		String us = "";
		try {
			us = URLEncoder.encode(sex1.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		EditText add1 = (EditText) findViewById(R.id.add);
		String uad = "";
		try {
			uad = URLEncoder.encode(add1.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		EditText city1 = (EditText) findViewById(R.id.city);
		String uc = "";
		try {
			uc = URLEncoder.encode(city1.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		EditText state1 = (EditText) findViewById(R.id.state);
		String ust = "";
		try {
			ust = URLEncoder.encode(state1.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		EditText count = (EditText) findViewById(R.id.country);
		String uct = "";
		try {
			uct = URLEncoder.encode(count.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EditText status1 = (EditText) findViewById(R.id.status);
		String usts = "";
		try {
			usts = URLEncoder.encode(status1.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EditText mob1 = (EditText) findViewById(R.id.mob);
		String um = "";
		try {
			um = URLEncoder.encode(mob1.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpClient client = new DefaultHttpClient();
		if (uname.length() == 0 || usts.length() == 0 || ua.length() == 0
				|| us.length() == 0 || um.length() != 10) {
			Toast.makeText(this,
					"Wrong value entered or madatory value missing", 5).show();
			setContentView(R.layout.cloudinfo);

		} else {
			String getURL = "http://" + hIP + "/one/index.jsp?uid=" + ss
					+ "&name=" + uname + "&age=" + ua + "&sex=" + us + "&add="
					+ uad + "&city=" + uc + "&state=" + ust + "&country=" + uct
					+ "&status=" + usts + "&mob=" + um;
			HttpGet get = new HttpGet(getURL);
			try {

				HttpResponse responseGet = client.execute(get);

				StatusLine statusLine = responseGet.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					// do something with the response
					// Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
					int x = responseGet.getHeaders("name").length;
					Header[] head = responseGet.getHeaders("name");
					int s = 0;
					String zz = "";
					while (s < x) {
						zz = zz + head[s].getValue() + "\n";
						s++;
					}
					// test

					setContentView(R.layout.fbinfo);
					TextView t = (TextView) findViewById(R.id.tx);
					t.setText(zz);
				}
			} catch (Exception e) {
				TextView tv = new TextView(this);
				tv.setText(e.getLocalizedMessage());
				setContentView(tv);

			}

		}

	}

	public void uptodate(View v) {
		setContentView(R.layout.cloudinfo);

	}

	public String userdata(int x) {

		String zz = "";
		HttpClient client = new DefaultHttpClient();
		String getURL = "http://" + hIP + "/one/getdata.jsp?uid=" + x + "";
		HttpGet get = new HttpGet(getURL);
		try {
			HttpResponse responseGet = client.execute(get);
			StatusLine statusLine = responseGet.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				// do something with the response
				// Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
				int z = responseGet.getHeaders("name").length;
				Header[] head = responseGet.getHeaders("name");
				int s = 0;
				while (s < z) {
					zz = zz + head[s].getValue() + "\n";
					s++;
				}

			}
		} catch (Exception e) {
			TextView tv = new TextView(this);
			tv.setText(e.getLocalizedMessage());
			setContentView(tv);
		}
		return zz;
	}

	// method userslist
	public void users1(final View v) {
		UserDataModel userDataModel =(UserDataModel) getIntent().getSerializableExtra("UserModelValue");
		final List<NameValuePair> urlParameters = new ArrayList<>();
		HttpPost post = new HttpPost(ConnectionDetailsUtility.connectionUrlUsersGet);
		try {
			urlParameters.add(new BasicNameValuePair(
					ModelConstantStrings.userId,userDataModel.getUserId().toString()));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UserDataModel> list = new ArrayList<>(); 
		SignInAsyncTask signInAsyncTask = new SignInAsyncTask();
		try {
			HttpResponse response = signInAsyncTask.execute(post).get();
			Header[] hr = response.getAllHeaders();
			
			Type listType = new TypeToken<ArrayList<UserDataModel>>() {
            }.getType();
			for (Header h : hr) {

				if (h.getName().equals(
						"UsersList")) {
					 Gson gson = new Gson();
					   list = gson.fromJson(h.getValue(),listType);
					   Toast.makeText(getBaseContext(), list.toString(),Toast.LENGTH_LONG).show();
				} 
			}
			Log.d("Vijay", list.toString());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(R.layout.list_item);
		final ListView listview = (ListView) findViewById(R.id.listview);
				  final StableArrayAdapter adapter = new StableArrayAdapter(this,
					        android.R.layout.simple_list_item_1, list);
					    listview.setAdapter(adapter);

					    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					      @Override
					      public void onItemClick(AdapterView<?> parent, final View view,
					          int position, long id) {
					        final String item = (String) parent.getItemAtPosition(position);
					        
					      }

					    });
	}

	public void otheruser(String uname, String id) {
		HttpClient client = new DefaultHttpClient();
		String getURL = "http://" + hIP + "/one/getdata.jsp?uid=" + id;
		HttpGet get = new HttpGet(getURL);
		String zz = "";
		try {
			HttpResponse responseGet = client.execute(get);
			StatusLine statusLine = responseGet.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				// do something with the response
				// Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
				int z = responseGet.getHeaders("name").length;
				Header[] head = responseGet.getHeaders("name");
				int s = 0;
				while (s < z) {
					zz = zz + head[s].getValue() + "\n";
					s++;
				}

			}
		} catch (Exception e) {
			TextView tv = new TextView(this);
			tv.setText(e.getLocalizedMessage());
			setContentView(tv);
		}

		setContentView(R.layout.otherinfo);
		TextView t = (TextView) findViewById(R.id.tx);
		// TextView tv =new TextView(this);
		t.setText(zz);
	}

	public void switching(View v) {
		// Intent i2=new Intent(this,HTTPTest.class);
		// i2.putExtra("Uname", othername);
		// i2.putExtra("Uid", otherid);
		// i2.putExtra("mainid",getIntent().getSerializableExtra("fname").toString());
		// startActivity(i2);

	}

	public void downfile(View v) {
		// Intent i2=new Intent(this,FileDownloadActivity.class);
		// i2.putExtra("Uname", othername);
		// i2.putExtra("Uid", otherid);
		// i2.putExtra("mainid",getIntent().getSerializableExtra("fname").toString());
		// startActivity(i2);
		//

	}

	public void delFile(View v) {
		// Intent i6=new Intent(this,FileDeleteActivity.class);
		// i6.putExtra("Uname",
		// getIntent().getSerializableExtra("iname").toString());
		// i6.putExtra("Uid",
		// getIntent().getSerializableExtra("fname").toString());
		// i6.putExtra("mainid",getIntent().getSerializableExtra("fname").toString());
		// startActivity(i6);

	}

	public void mainpage(View v) {
		String bo = getIntent().getSerializableExtra("bool").toString();
		if (bo.equalsIgnoreCase("false"))
			this.finish();
		else {

			int ss = Integer.parseInt(getIntent().getSerializableExtra("fname")
					.toString());
			String zz = userdata(ss);
			setContentView(R.layout.fbinfo);
			TextView t = (TextView) findViewById(R.id.tx);
			// TextView tv =new TextView(this);
			t.setText(zz);
		}

	}

	public void mainpage2(View v) {
		Intent i2 = new Intent(this, NITCloudActivity.class);

		startActivity(i2);

		android.os.Process.killProcess(android.os.Process.myPid());

	}

	public void uplcloud(View v) {
		// Intent i4=new Intent(this,FileChooserActivity.class);
		// i4.putExtra("Uname",
		// getIntent().getSerializableExtra("iname").toString());
		// i4.putExtra("Uid",
		// getIntent().getSerializableExtra("fname").toString());
		// i4.putExtra("mainid",getIntent().getSerializableExtra("fname").toString());
		// startActivity(i4);

	}

	public void downLoad(View v) {

		//
		// Intent i5=new Intent(this,FileDownloadActivity.class);
		// i5.putExtra("Uname",
		// getIntent().getSerializableExtra("iname").toString());
		// i5.putExtra("Uid",
		// getIntent().getSerializableExtra("fname").toString());
		// i5.putExtra("mainid",getIntent().getSerializableExtra("fname").toString());
		// startActivity(i5);
		//
	}

	public void usercloud(View v) {
		// Intent i3=new Intent(this,HTTPTest.class);
		// i3.putExtra("Uname",
		// getIntent().getSerializableExtra("iname").toString());
		// i3.putExtra("Uid",
		// getIntent().getSerializableExtra("fname").toString());
		// i3.putExtra("mainid",getIntent().getSerializableExtra("fname").toString());
		// startActivity(i3);

	}
	
	private class StableArrayAdapter extends ArrayAdapter<UserDataModel> {

	    HashMap<UserDataModel, Integer> mIdMap = new HashMap<UserDataModel, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<UserDataModel> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public  long getItemId(int position) {
	      UserDataModel item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }

	
}
