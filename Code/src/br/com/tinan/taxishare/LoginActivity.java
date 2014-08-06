package br.com.tinan.taxishare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	Context mContext;

	@InjectView(R.id.edit_username_login)
	EditText mLogin;

	@InjectView(R.id.edit_password_login)
	EditText mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity_layout);
		ButterKnife.inject(this);
		Parse.initialize(this, Constants.PARSE_APPLICATION_ID, Constants.PARSE_CLIENT_KEY);
		mContext = this;

	}

	@OnClick(R.id.btn_register_login)
	public void goToRegister() {
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.btn_reset_login)
	public void goToReset() {
		Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.btn_login_login)
	public void login() {
		String login = mLogin.getText().toString();
		String password = mPassword.getText().toString();

		ParseUser.logInInBackground(login, password, new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if (user != null) {
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					finish();

				} else {
					ParseExceptios.showErrorMessage(mContext, e);
				}
			}
		});

	}
}
