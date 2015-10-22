package com.example.napster;

import java.util.List;

import org.apache.http.NameValuePair;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.defaultapplication.R;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.model.SavedContext;

public class EmailDialog extends DialogFragment {
	private SignInActivity signInActivity;
	private OtpDialog otpDialogObject;
	private List<NameValuePair> urlParameters;

	public EmailDialog(SignInActivity signInActivity,
			OtpDialog otpDialogObject, List<NameValuePair> urlParameters) {
		this.signInActivity = signInActivity;
		this.otpDialogObject = otpDialogObject;
		this.urlParameters = urlParameters;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Forgot Password");
		builder.setMessage("Enter Email Id:");
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.fragment_email, null);
		builder.setView(view);
		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), "Cancel was clicked",
								Toast.LENGTH_LONG).show();
						dismiss();
					}
				});
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), "Ok was clicked",
								Toast.LENGTH_LONG).show();
						EditText forgotEmailId = (EditText) view
								.findViewById(R.id.forgotemailvalue);
						SharedPreferences sharedPreferences = signInActivity
								.getSharedPreferences(SavedContext.OTP_CONTEXT,
										Context.MODE_PRIVATE);

						if (!forgotEmailId.getText().toString().isEmpty()) {
							dismiss();

							((EditText) signInActivity
									.findViewById(R.id.useremailvalue))
									.setText(forgotEmailId.getText().toString());
							((EditText) signInActivity
									.findViewById(R.id.passwordvalue))
									.setText("-1");

							SharedPreferences.Editor editor = sharedPreferences
									.edit();
							editor.putString(
									ResponseConstantsForSignInPage.USER_HASHCODE
											.toString(), "");
							editor.putString(ModelConstantStrings.userEmail,
									forgotEmailId.getText().toString());
							editor.commit();
							signInActivity.callBackOtpPassword(otpDialogObject,
									urlParameters);
							// //textViewObject.performClick();
							// Toast.makeText(signInActivity,sharedPreferences.getString(ModelConstantStrings.userEmail,
							// "-1"), Toast.LENGTH_LONG).show();
							// textViewObject.performClick();
						}

					}
				});
		Dialog dialog = builder.create();
		return dialog;
	}

}
