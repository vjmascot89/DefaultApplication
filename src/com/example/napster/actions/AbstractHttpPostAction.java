package com.example.napster.actions;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import com.example.defaultapplication.R;
import com.example.napster.concurrencypackage.SignInAsyncTask;
import com.example.napster.model.LayoutConstantStrings;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.utils.ConnectionDetailsUtility;

public class AbstractHttpPostAction implements Action {

	final private String connectionUrl;
	final private List<NameValuePair> urlParameters;
	final private Activity activityObject;
	final private DialogFragment dialogObject;
	final private SharedPreferences sharedPreferencesOtp;

	public AbstractHttpPostAction(final Activity activityObject,
			final DialogFragment dialogObject, final String connectionUrl,
			final List<NameValuePair> urlParameters,
			final SharedPreferences sharedPreferencesOtp) {
		this.activityObject = activityObject;
		this.dialogObject = dialogObject;
		this.connectionUrl = connectionUrl;
		this.urlParameters = urlParameters;
		this.sharedPreferencesOtp = sharedPreferencesOtp;

	}

	@Override
	public HttpResponse makeConnection() {
		HttpResponse response = null;
		try {
			HttpPost post = new HttpPost(connectionUrl);

			if (!sharedPreferencesOtp
					.getString(
							ResponseConstantsForSignInPage.USER_HASHCODE
									.toString(),
							"").isEmpty())

				urlParameters.add(new BasicNameValuePair(
						ModelConstantStrings.userOtpHashcode,
						sharedPreferencesOtp.getString(
								ResponseConstantsForSignInPage.USER_HASHCODE
										.toString(), "")));
//			else {
//
//				dialogObject.show(activityObject.getFragmentManager(),
//						LayoutConstantStrings.OTP_DIALOG);
//			}
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			StringBuffer textValue = new StringBuffer(
					"\nSending 'POST' request to URL : "
							+ ConnectionDetailsUtility.connectionUrlSignIn);
			textValue.append("Post parameters : " + post.getEntity());
			System.out.println("LOG>>" + this.getClass() + "  " + textValue);
			SignInAsyncTask signInAsyncTask = new SignInAsyncTask();
			response = signInAsyncTask.execute(post).get();

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return response;

	}

}
