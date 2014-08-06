package br.com.tinan.taxishare;

import android.content.Context;
import android.widget.Toast;

import com.parse.ParseException;

public class ParseExceptios {

	public static final int INVALID_EMAIL_ADDRESS = 125;
	public static final int USERNAME_TAKEN = 202;
	public static final int EMAIL_TAKEN = 203;
	public static final int CONNECTION_FAILED = 100;

	public static final void showErrorMessage(Context context, ParseException e) {

		String message = "";
		int errorCode = e.getCode();
		switch (errorCode) {
		case USERNAME_TAKEN:
			message = "Username já utilizado";
			break;

		case EMAIL_TAKEN:
			message = "Email já utilizado";
			break;

		case INVALID_EMAIL_ADDRESS:
			message = "Email inválido";
			break;

		case CONNECTION_FAILED:
			message = "Não foi possível cadastrar, verifique sua conexão";
			break;

		default:
			message = e.getMessage();
			break;
		}
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
