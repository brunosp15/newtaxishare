package br.com.tinan.taxishare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {

//	private final static String TAG = RegisterActivity.class.getSimpleName();

	@InjectView(R.id.edit_username_register)
	EditText mUsername;

	@InjectView(R.id.edit_password_register)
	EditText mPassword;

	@InjectView(R.id.edit_email_register)
	EditText mEmail;

	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity_layout);
		ButterKnife.inject(this);
		Parse.initialize(this, Constants.PARSE_APPLICATION_ID, Constants.PARSE_CLIENT_KEY);
		mContext = this;
	}

	@OnClick(R.id.btn_save_register)
	public void save() {
		ParseUser user = new ParseUser();

		String email = mEmail.getText().toString();
		String password = mPassword.getText().toString();
		String username = mUsername.getText().toString();

		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);

		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					Toast.makeText(mContext, "Cadastro Efetuado com sucesso!", Toast.LENGTH_SHORT).show();
					goMain();

				} else {
					ParseExceptios.showErrorMessage(mContext, e);
				}
			}
		});
	}

	private void goMain() {
		Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
