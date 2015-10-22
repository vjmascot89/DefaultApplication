package com.example.napster.concurrencypackage;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Napster on 10/11/2015.
 */
public class SignInAsyncTask extends AsyncTask<HttpPost,Void,HttpResponse> {

    @Override
    protected HttpResponse doInBackground(HttpPost... params) {
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response =client.execute(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(HttpResponse httpResponse) {
        super.onPostExecute(httpResponse);
    }
}
