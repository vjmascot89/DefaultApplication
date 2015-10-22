package com.example.napster;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.defaultapplication.R;
import com.example.napster.actions.ForgotPasswordAction;
import com.example.napster.actions.SignInAction;
import com.example.napster.model.LayoutConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.model.SavedContext;
import com.example.napster.utils.ConnectionDetailsUtility;

public class SignInActivity extends Activity {
	private SharedPreferences sharedPreferencesOtp = null;
	private SharedPreferences sharedPreferencesForgotPassword = null;
	private OtpDialog otpDialogObject = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final List<NameValuePair> urlParameters = new ArrayList<>();
		sharedPreferencesOtp = getSharedPreferences(SavedContext.OTP_CONTEXT,
				Context.MODE_PRIVATE);
		setContentView(R.layout.signin_layout);
		final TextView signIn = (TextView) findViewById(R.id.signin);
		final TextView forgotPassword = (TextView) findViewById(R.id.forgotpassword);
		final TextView signUp = (TextView) findViewById(R.id.signup);
		otpDialogObject = new OtpDialog(this, signIn);
		final EmailDialog forgotPasswordDialog = new EmailDialog(this,otpDialogObject,urlParameters);

		Bundle eBundle = getIntent().getExtras();

		
		sharedPreferencesOtp.edit().remove(ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD.toString()).commit();
		
		
		String key = null;

		if (eBundle != null) {
			key = eBundle.getString(ResponseConstantsForSignInPage.USER_KEY
					.toString());
			Log.d("Vijay", key);
			Log.d("Vijay", eBundle.toString());
			Log.d("Vijay",
					""
							+ eBundle
									.containsKey(ResponseConstantsForSignInPage.USER_KEY
											.toString()));
		}

		final String userKeyValue = key;
		Log.d("Vijay", "user key value" + userKeyValue);

		signIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				urlParameters.clear();
				SignInAction signInObject = new SignInAction(
						SignInActivity.this, otpDialogObject,
						ConnectionDetailsUtility.connectionUrlSignIn,
						urlParameters, userKeyValue, sharedPreferencesOtp);
				Object object = signInObject.connectionRespose();
			}
		});

		if (userKeyValue != null && !userKeyValue.equals("-1"))
			signIn.performClick();

		signUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), SignupActivity.class);
				startActivity(i);
			}
		});

		forgotPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				urlParameters.clear();
				getSharedPreferences(SavedContext.OTP_CONTEXT, Context.MODE_PRIVATE).edit().putString(ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD.toString(), "true").commit();
				getFragmentManager().beginTransaction().remove(otpDialogObject).commit();
				forgotPasswordDialog.show(getFragmentManager(),
						LayoutConstantStrings.OTP_DIALOG);

			}
		});
	}

	public void callBackOtpPassword( DialogFragment otpDialogObject, List<NameValuePair> urlParameters) {
		ForgotPasswordAction forgotPassword = new ForgotPasswordAction(
				SignInActivity.this, otpDialogObject,
				ConnectionDetailsUtility.connectionUrlSignIn,
				urlParameters, sharedPreferencesOtp);
		Object obj =forgotPassword.connectionRespose();

	}

	public void callForgotPasswordMethod(ForgotPasswordDialog forgotPassword) {
		Log.d("DIALOGFRAGMENT",getFragmentManager().findFragmentByTag("OTP_DIALOG").getTag());
		getFragmentManager().beginTransaction().remove(otpDialogObject).commit();
		forgotPassword.show(getFragmentManager(), LayoutConstantStrings.OTP_DIALOG);
		
	}
}
