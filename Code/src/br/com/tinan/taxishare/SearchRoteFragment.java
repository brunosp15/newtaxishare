package br.com.tinan.taxishare;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SearchRoteFragment extends Fragment {

	Context mContext;
	GoogleMap googleMap;
	LocationManager mLocationManager;

	@InjectView(R.id.edit_origin_search)
	EditText mOrigin;

	@InjectView(R.id.edit_destiny_search)
	EditText mDestiny;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.search_rote_fragment, container, false);
		mContext = getActivity();
		mLocationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
		ButterKnife.inject(this, rootView);
		try {
			initilizeMap();
			MapsInitializer.initialize(mContext);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rootView;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_search)).getMap();

			if (googleMap == null) {
				Toast.makeText(mContext, "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			} else {

				googleMap.getUiSettings().setMyLocationButtonEnabled(true);
				googleMap.getProjection();

				setMapMarker(getMyLocation(), "You are here!", "", true);

				CameraPosition cp = new CameraPosition.Builder().target(getMyLocation()).zoom(11).tilt(34).build();

				googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

			}
		}
	}

	public void setMapMarker(LatLng latLng, String titile, String snippet, boolean isMyLocation) {
		MarkerOptions marker = new MarkerOptions().position(latLng).title(titile).snippet(snippet);
		if (isMyLocation) {
			marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		}
		googleMap.addMarker(marker);
	}

	public LatLng getMyLocation() {
		Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}

		LatLng mLatLng = new LatLng(-23.434553, -46.478126);

		if (location != null) {
			mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
		}

		return mLatLng;
	}

	@OnClick(R.id.btn_search_search)
	public void search() {
		LatLng myLocation = getMyLocation();
		ParseGeoPoint originPoint = new ParseGeoPoint(myLocation.latitude, myLocation.longitude);
		ParseGeoPoint destinyPoint = new ParseGeoPoint(-23.5987627, -46.67654730000004);

		ParseQuery<ParseObject> origimQuery = ParseQuery.getQuery("Rote");
		origimQuery.whereWithinKilometers("origim", originPoint, 3);
		origimQuery.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> roteList, ParseException e) {
				if (e == null) {
					for (int i = 0; i < roteList.size(); i++) {
						ParseObject obj = roteList.get(i);
						obj.getParseObject("detiny").fetchIfNeededInBackground(new GetCallback<ParseObject>() {
							public void done(ParseObject destiny, ParseException e) {
								ParseGeoPoint destinyRecoveredPoint = destiny.getParseGeoPoint("destiny");
								double distance = destinyRecoveredPoint.distanceInKilometersTo(destinyRecoveredPoint);
								if (distance <= 5) {
									
								}
							}
						});
					}
				} else {
					ParseExceptios.showErrorMessage(mContext, e);
				}
			}
		});

		// LatLng myLocation = getMyLocation();
		// ParseGeoPoint originPoint = new ParseGeoPoint(myLocation.latitude,
		// myLocation.longitude);
		// ParseUser user = ParseUser.getCurrentUser();
		//
		// ParseObject rote = new ParseObject("Rote");
		//
		// rote.put("origim", originPoint);
		// rote.put("user", user.getUsername());
		//
		// ParseGeoPoint destinyPoint = new ParseGeoPoint(-23.5987627,
		// -46.67654730000004);
		// ParseObject roteDestiny = new ParseObject("RoteDestiny");
		// roteDestiny.put("destiny", destinyPoint);
		//
		// rote.put("destiny", roteDestiny);
		//
		// rote.saveInBackground(new SaveCallback() {
		//
		// @Override
		// public void done(ParseException e) {
		// if (e == null) {
		// Toast.makeText(mContext, "salvou", Toast.LENGTH_SHORT).show();
		// } else {
		// Toast.makeText(mContext, "erro", Toast.LENGTH_SHORT).show();
		// Log.d("bruno", e.getMessage());
		// }
		// }
		// });

	}
}
