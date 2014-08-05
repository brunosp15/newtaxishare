package br.com.tinan.taxishare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends Activity {

	@InjectView(R.id.edit_username_register)
	EditText mUsername;

	@InjectView(R.id.edit_password_register)
	EditText mPassword;

	@InjectView(R.id.edit_email_register)
	EditText mEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity_layout);
	}

	@OnClick(R.id.btn_save_register)
	public void onSave() {

	}
}
