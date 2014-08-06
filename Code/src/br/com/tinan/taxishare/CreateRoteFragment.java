package br.com.tinan.taxishare;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreateRoteFragment extends Fragment {

	private Context mContext;

	@InjectView(R.id.edit_destiny_create_rote)
	EditText mDestiny;

	@InjectView(R.id.edit_origim_create_rote)
	EditText mOrigim;

	@InjectView(R.id.time_time_create_rote)
	TimePicker mTime;

	ParseGeoPoint mDestinyPoint;
	ParseGeoPoint mOrigimPoint;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.create_rote_fragment, container, false);
		ButterKnife.inject(this, rootView);
		Parse.initialize(getActivity(), Constants.PARSE_APPLICATION_ID, Constants.PARSE_CLIENT_KEY);
		getExtras();
		mContext = getActivity();

		return rootView;
	}

	private void getExtras() {
		Bundle args = getArguments();
		mDestinyPoint = new ParseGeoPoint(args.getDouble("origimLat"), args.getDouble("origimLng"));
		mOrigimPoint = new ParseGeoPoint(args.getDouble("destinyLat"), args.getDouble("destinyLng"));
		mDestiny.setText(args.getString("origimAddress"));
		mOrigim.setText(args.getString("destinyAddress"));

	}

	@OnClick(R.id.btn_create_rote)
	public void createRote() {
		ParseObject rote = new ParseObject("Rote");
		rote.put("destiny", mDestinyPoint);
		rote.put("origimLat", mOrigimPoint.getLatitude());
		rote.put("origimLng", mOrigimPoint.getLongitude());
		rote.put("destinyAddress", mDestiny.getText().toString());
		rote.put("origimAddress", mOrigim.getText().toString());
		rote.put("spaces", 1);
		rote.put("userHost", ParseUser.getCurrentUser().getUsername());
		rote.saveInBackground(new SaveCallback() {

			@Override
			public void done(ParseException e) {

				if (e == null) {
					Toast.makeText(mContext, "Salvou", Toast.LENGTH_SHORT).show();

				} else {
					ParseExceptios.showErrorMessage(mContext, e);
				}
			}
		});

	}

}
