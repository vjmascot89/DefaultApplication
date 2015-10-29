package com.example.napster.dialog;

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
import com.example.napster.SignInActivity;
import com.example.napster.model.ModelConstantStrings;
import com.example.napster.model.SavedContext;

public class ForgotPasswordDialog extends DialogFragment {
	private SignInActivity signInActivity;
	private TextView signInTry;

	public ForgotPasswordDialog(SignInActivity signInActivity,
			TextView signInTry) {
		this.signInActivity = signInActivity;
		this.signInTry = signInTry;
		
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Forgot Password");
		builder.setMessage("Enter your Password:");
		LayoutInflater inflater = signInActivity.getLayoutInflater();
		final View view = inflater.inflate(R.layout.fragment_password, null);
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
						EditText forgotPasswordEmailId = (EditText) view
								.findViewById(R.id.forgotPassword1);
						if (!forgotPasswordEmailId.getText().toString()
								.isEmpty()) {
							dismiss();
							SharedPreferences sharedPreferences = signInActivity
									.getSharedPreferences(SavedContext.OTP_CONTEXT,
											Context.MODE_PRIVATE);
							SharedPreferences.Editor editor = sharedPreferences
									.edit();
							editor.putString(ModelConstantStrings.userPassword,
									forgotPasswordEmailId.getText().toString());
							editor.commit();
							signInTry.performClick();

						}
					}
				});
		Dialog dialog = builder.create();
		return dialog;
	}

}
