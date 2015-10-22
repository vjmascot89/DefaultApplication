package com.example.napster.actions;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.defaultapplication.R;
import com.example.napster.model.LayoutConstantStrings;
import com.example.napster.model.LoginDataModel;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.model.UserDataModel;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordAction extends AbstractHttpPostAction {

	private Activity activityObject;
	private DialogFragment dialogObject;
	private String connectionUrl;
	private List<NameValuePair> urlParameters;
	private SharedPreferences sharedPreferencesOtp;

	public ForgotPasswordAction(Activity activityObject,
			DialogFragment dialogObject, String connectionUrl,
			List<NameValuePair> urlParameters,
			SharedPreferences sharedPreferencesOtp) {
		super(activityObject, dialogObject, connectionUrl, urlParameters,
				sharedPreferencesOtp);
		this.sharedPreferencesOtp = sharedPreferencesOtp;
		this.urlParameters = urlParameters;
		this.connectionUrl = connectionUrl;
		this.dialogObject = dialogObject;
		this.activityObject = activityObject;

	}

	@Override
	public HttpResponse makeConnection() {

		EditText userEmailEditText = (EditText) activityObject
				.findViewById(R.id.useremailvalue);

		String userEmailValue = userEmailEditText.getText().toString();

		urlParameters.add(new BasicNameValuePair(
				ModelConstantStrings.userEmail, userEmailValue));
		String forgotPasswordFlag = sharedPreferencesOtp.getString(
				ResponseConstantsForSignInPage.USER_HASHCODE.toString(), "");
		String newPasswordValue = sharedPreferencesOtp.getString(
			ModelConstantStrings.userPassword , "");
		if (forgotPasswordFlag.isEmpty()) {
		urlParameters
				.add(new BasicNameValuePair("USER_FORGOT_PASSWORD", "true"));
		}
		else{
			
			urlParameters
			.add(new BasicNameValuePair("USER_FORGOT_PASSWORD", forgotPasswordFlag));
		}
		if(newPasswordValue!=null &&!newPasswordValue.isEmpty())
			urlParameters
			.add(new BasicNameValuePair(ModelConstantStrings.userPassword, newPasswordValue));
		return super.makeConnection();
	}
	

	public Object connectionRespose() {
		HttpResponse response = makeConnection();

		StringBuffer textValue = new StringBuffer();

		textValue.append("Response Code : "
				+ response.getStatusLine().getStatusCode());
		textValue.append("Response Key : " + response.getEntity());
		System.out.println("LOG>>" + this.getClass() + "  " + textValue);
		Header[] hr = response.getAllHeaders();

		Toast.makeText(activityObject.getBaseContext(), textValue,
				Toast.LENGTH_LONG).show();
		Log.d("vijay", textValue.toString());

		String keyValue = null;
		String userLoggedIn = null;
		String userHashcodeResponse = null;
		String userforgotPasswordValue = null;
		for (Header h : hr) {
			textValue.append("" + h.getName() + " :" + h.getValue());

			if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_KEY.toString())) {
				keyValue = h.getValue();
			}
			if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_LOGGED_IN.toString())) {
				userLoggedIn = h.getValue();
			}
			if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_HASHCODE.toString())) {
				userHashcodeResponse = h.getValue();
			}
			if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD.toString())) {
					userforgotPasswordValue = h.getValue();

					SharedPreferences.Editor editor = sharedPreferencesOtp
							.edit();
					editor.putString(
							ResponseConstantsForSignInPage.USER_HASHCODE
									.toString(), h.getValue());
					editor.commit();
			}
		}

		if (keyValue != null
				&& (Integer.parseInt(keyValue) > 0)
				&& (userLoggedIn != null
						&& userLoggedIn
								.equals(ResponseConstantsForSignInPage.SUCCESS
										.toString()) || sharedPreferencesOtp
						.getString(
								ResponseConstantsForSignInPage.USER_HASHCODE
										.toString(),
								"-1").equals(userHashcodeResponse))) {
			Log.d("Vijay", "I am logged in successfully");
			Toast.makeText(activityObject.getBaseContext(),
					"I am Logged in Successfully", Toast.LENGTH_LONG).show();
		} else if (!sharedPreferencesOtp.getString(
				ResponseConstantsForSignInPage.USER_HASHCODE.toString(), "-1")
				.equals(userHashcodeResponse)) {
			activityObject.getFragmentManager().beginTransaction().remove(dialogObject).commit();
			dialogObject.show(activityObject.getFragmentManager(),
					LayoutConstantStrings.OTP_DIALOG);
		}

		return null;
	}

}
