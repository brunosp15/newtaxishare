package br.com.tinan.taxishare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity {

	@InjectView(R.id.edit_username_login)
	EditText mLogin;

	@InjectView(R.id.edit_password_login)
	EditText mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity_layout);
		ButterKnife.inject(this);

	}

	@OnClick(R.id.btn_register)
	public void goToSignUp() {
		Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
	}

}
