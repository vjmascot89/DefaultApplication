package com.example.napster.actions;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.defaultapplication.R;
import com.example.napster.model.LayoutConstantStrings;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;

public class SignInAction extends AbstractHttpPostAction {

	private Activity activityObject;
	private DialogFragment dialogObject;
	private List<NameValuePair> urlParameters;
	private String userKeyValue;
	private SharedPreferences sharedPreferencesOtp;

	public SignInAction(Activity activityObject, DialogFragment dialogObject,
			String connectionUrl, List<NameValuePair> urlParameters,
			String userKeyValue, SharedPreferences sharedPreferencesOtp) {
		super(activityObject, dialogObject, connectionUrl, urlParameters,
				sharedPreferencesOtp);

		this.activityObject = activityObject;
		this.dialogObject = dialogObject;
		this.urlParameters = urlParameters;
		this.userKeyValue = userKeyValue;
		this.sharedPreferencesOtp = sharedPreferencesOtp;
	}

	@Override
	public HttpResponse makeConnection() {
		if (userKeyValue == null || userKeyValue.equals("-1")) {
			EditText userEmailEditText = (EditText) activityObject
					.findViewById(R.id.useremailvalue);
			EditText passwordValue = (EditText) activityObject
					.findViewById(R.id.passwordvalue);

			String userEmailValue = userEmailEditText.getText().toString();
			String passwordStringValue = passwordValue.getText().toString();

			urlParameters.add(new BasicNameValuePair(
					ModelConstantStrings.userEmail, userEmailValue));
			if (forgotPasswordCase()) {
				urlParameters.add(new BasicNameValuePair(
						ModelConstantStrings.userPassword, sharedPreferencesOtp
								.getString(ModelConstantStrings.userPassword,
										"-1")));
				urlParameters.add(new BasicNameValuePair(
						ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD
								.toString(), "true"));
			} else
				urlParameters
						.add(new BasicNameValuePair(
								ModelConstantStrings.userPassword,
								passwordStringValue));

		} else {
			urlParameters.add(new BasicNameValuePair(
					ModelConstantStrings.userId, userKeyValue));
		}
		return super.makeConnection();
	}

	private boolean forgotPasswordCase() {
		// TODO Auto-generated method stub
		return sharedPreferencesOtp.getString(
				ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD.toString(),
				"false").equals("true")
				&& !sharedPreferencesOtp.getString(
						ModelConstantStrings.userPassword, "").isEmpty();
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
		for (Header h : hr) {
			textValue.append("" + h.getName() + " :" + h.getValue());

			if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_KEY.toString())) {
				keyValue = h.getValue();
			} else if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_LOGGED_IN.toString())) {
				userLoggedIn = h.getValue();
			} else if (h.getName().equals(
					ResponseConstantsForSignInPage.USER_HASHCODE.toString())) {
				userHashcodeResponse = h.getValue();
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

			sharedPreferencesOtp
					.edit()
					.remove(ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD
							.toString()).commit();
		} else if (keyValue != null && (Integer.parseInt(keyValue) < 0)
				|| "Wrong UserName or Password".equals(userLoggedIn)) {
			Log.d("Vijay", userLoggedIn);
			Toast.makeText(activityObject.getBaseContext(), userLoggedIn,
					Toast.LENGTH_LONG).show();
		} else if (!sharedPreferencesOtp.getString(
				ResponseConstantsForSignInPage.USER_HASHCODE.toString(), "-1")
				.equals(userHashcodeResponse)) {
			activityObject.getFragmentManager().beginTransaction()
					.remove(dialogObject).commit();
			dialogObject.show(activityObject.getFragmentManager(),
					LayoutConstantStrings.OTP_DIALOG);
		}

		return null;
	}

}
