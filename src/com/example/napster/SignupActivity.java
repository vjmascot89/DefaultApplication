package com.example.napster;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.defaultapplication.R;
import com.example.napster.concurrencypackage.SignInAsyncTask;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.LoginDataModel;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.model.UserDataModel;
import com.example.napster.utils.ConnectionDetailsUtility;

public class SignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        final EditText userEmailId = (EditText)findViewById(R.id.usernamevaluesignup);

        setListenerForEditText(userEmailId);
        final EditText userPassword1 = (EditText)findViewById(R.id.userpasswordsignup1);
        setListenerForEditText(userPassword1);
        EditText userPassword2 = (EditText)findViewById(R.id.userpasswordsignup2);
        setListenerForEditText(userPassword2);
        final EditText userMobileNumber = (EditText)findViewById(R.id.usermobilenumbersignup);
        setListenerForEditText(userMobileNumber);
        final EditText userName = (EditText)findViewById(R.id.username);
        setListenerForEditText(userName);
        final EditText userBirthday = (EditText)findViewById(R.id.userbirthday);
        setListenerForEditText(userBirthday);
        final EditText userGender = (EditText)findViewById(R.id.usergender);
        setListenerForEditText(userGender);
        final EditText userFlatAddress = (EditText)findViewById(R.id.useraddress);
        setListenerForEditText(userFlatAddress);
        final EditText userCity = (EditText)findViewById(R.id.usercity);
        setListenerForEditText(userCity);
        final EditText userState = (EditText)findViewById(R.id.userstate);
        setListenerForEditText(userState);
        final EditText userCountry = (EditText)findViewById(R.id.usercountry);
        setListenerForEditText(userCountry);
        final EditText userStatus = (EditText)findViewById(R.id.userstatus);
        setListenerForEditText(userStatus);
        final LoginDataModel loginData = new LoginDataModel();
        final UserDataModel userData = new UserDataModel();
        TextView userCreateAccount = (TextView)findViewById(R.id.createanaccount);
        userCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginData.setUserEmail(userEmailId.getText().toString());
                loginData.setUserMobile(userMobileNumber.getText().toString());
                loginData.setUserPassword(userPassword1.getText().toString());
                userData.setUserName(userName.getText().toString());
                userData.setUserAge(userBirthday.getText().toString());
                userData.setUserGender(userGender.getText().toString());
                userData.setUserAddress(userFlatAddress.getText().toString());
                userData.setUserCity(userCity.getText().toString());
                userData.setUserCountry(userCountry.getText().toString());
                userData.setUserState(userState.getText().toString()) ;
                userData.setUserStatus(userStatus.getText().toString());
                try {

                    HttpPost post = new HttpPost(ConnectionDetailsUtility.connectionUrlSignUp);

                    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userPassword, loginData.getUserPassword()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userEmail, loginData.getUserEmail()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userMobile, loginData.getUserMobile()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userName, userData.getUserName()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userAge, userData.getUserAge()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userGender, userData.getUserGender()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userAddress, userData.getUserAddress()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userCity, userData.getUserCity()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userCountry, userData.getUserCountry()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userState, userData.getUserState()));
                    urlParameters.add(new BasicNameValuePair(ModelConstantStrings.userStatus, userData.getUserStatus()));
                    post.setEntity(new UrlEncodedFormEntity(urlParameters));
                    SignInAsyncTask signInAsyncTask = new SignInAsyncTask();
                    HttpResponse response = signInAsyncTask.execute(post).get();

                    StringBuffer textValue = new StringBuffer("\nSending 'POST' request to URL : " + ConnectionDetailsUtility.connectionUrlSignUp);
                    textValue.append("Post parameters : " + post.getEntity());
                    textValue.append("Response Code : " +
                            response.getStatusLine().getStatusCode());
                    textValue.append("Response Key : " +
                            response.getEntity());
                    Header[] hr = response.getAllHeaders();
                    String userKeyValue =null;
                    for (Header h : hr) {
                        textValue.append("" + h.getName() + " :" + h.getValue());
                        if(h.getName().equals(ResponseConstantsForSignInPage.USER_KEY.toString())){
                        	userKeyValue = h.getValue().toString();
                        	Log.d("Vijay",ResponseConstantsForSignInPage.USER_KEY.toString()  +"   User key value "+userKeyValue);
                        }
                    }
                    Toast.makeText(getBaseContext(), textValue, Toast.LENGTH_LONG).show();
                    Log.d("vijay", textValue.toString());
                    Intent intent = new Intent(getBaseContext(),SignInActivity.class);
                    intent.putExtra(ResponseConstantsForSignInPage.USER_KEY.toString(), userKeyValue);
                    startActivity(intent);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });

    }

	private void setListenerForEditText(final EditText editTextObject) {
		editTextObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
	}

}
