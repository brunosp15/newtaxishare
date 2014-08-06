package br.com.tinan.taxishare;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetActivity extends Activity {

	@InjectView(R.id.edit_email_reset)
	EditText mEmail;

	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password_activity);
		mContext = this;
		ButterKnife.inject(this);
	}

	@OnClick(R.id.btn_reset_reset)
	public void reset() {
		String email = mEmail.getText().toString();
		ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
			public void done(ParseException e) {
				if (e == null) {
					Toast.makeText(mContext, "Um e-mail foi enviado", Toast.LENGTH_SHORT).show();
				} else {
					ParseExceptios.showErrorMessage(mContext, e);
				}
			}
		});
	}
}
