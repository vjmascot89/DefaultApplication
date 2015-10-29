package com.example.napster.dialog;

import com.example.defaultapplication.R;
import com.example.napster.SignInActivity;
import com.example.napster.model.LayoutConstantStrings;
import com.example.napster.model.ResponseConstantsForSignInPage;
import com.example.napster.model.SavedContext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtpDialog extends DialogFragment {
	private SignInActivity signInActivity;
	private TextView textViewObject;
	public OtpDialog(SignInActivity signInActivity, TextView textViewObject){
		this.signInActivity = signInActivity;
		this.textViewObject = textViewObject;
		
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle("Enter OTP");
		builder.setMessage("Enter OTP here");
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.fragment_otp, null);
		builder.setView(view);
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "Cancel was clicked" , Toast.LENGTH_LONG).show();
				dismiss();
			}
		});
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "Ok was clicked" , Toast.LENGTH_LONG).show();
				EditText otpHashCode = (EditText) view.findViewById(R.id.otpvalue);
				SharedPreferences sharedPreferences = signInActivity.getSharedPreferences(
						SavedContext.OTP_CONTEXT, Context.MODE_PRIVATE);
				
				if(!otpHashCode.getText().toString().isEmpty()&&otpHashCode.getText().length()==6){
					dismiss();
					
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString(
							ResponseConstantsForSignInPage.USER_HASHCODE.toString(),
							otpHashCode.getText().toString());
					
					editor.commit();
					String forgotPasswordValue = sharedPreferences.getString(ResponseConstantsForSignInPage.USER_FORGOT_PASSWORD.toString(), "");
					if(forgotPasswordValue.equals("true")){
						
						ForgotPasswordDialog forgotPassword = new ForgotPasswordDialog(signInActivity, textViewObject);
						signInActivity.callForgotPasswordMethod(forgotPassword);
					}
					else
					textViewObject.performClick();
				}

				
			}
		});
		Dialog dialog = builder.create();
		return dialog;
	}

}
