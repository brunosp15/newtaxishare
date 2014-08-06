package br.com.tinan.taxishare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
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
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SearchRoteFragment extends Fragment {

	private static final String TAG = SearchRoteFragment.class.getSimpleName();
	Context mContext;
	GoogleMap googleMap;
	LocationManager mLocationManager;

	@InjectView(R.id.edit_origin_search)
	EditText mOrigin;

	@InjectView(R.id.edit_destiny_search)
	EditText mDestiny;

	ArrayList<Rote> mRoteList;
	String[] mAdresses;

	ParseGeoPoint mOrigimPoint;
	ParseGeoPoint mDestinyPoint;
	private List<Address> mOrigimList;
	private List<Address> mDestinyList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.search_rote_fragment, container, false);
		iniatlizeView(rootView);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public void iniatlizeView(View view) {
		mContext = getActivity();
		mLocationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
		ButterKnife.inject(this, view);
		Parse.initialize(getActivity(), Constants.PARSE_APPLICATION_ID, Constants.PARSE_CLIENT_KEY);
		try {
			initilizeMap();
			MapsInitializer.initialize(mContext);
		} catch (Exception e) {
			Log.d(TAG + " initilizeMap", "Exception " + e.getMessage());
			e.printStackTrace();
		}

		mDestiny.setText("rua cervinho 191");
		mOrigin.setText("rua quata 300");
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

	public void searchRotes() {

		if (mDestinyPoint != null && mOrigimPoint != null) {
			mRoteList = new ArrayList<Rote>();

			ParseQuery<ParseObject> query = ParseQuery.getQuery("Rote");
			query.whereWithinKilometers("destiny", mDestinyPoint, 5);
			query.findInBackground(new FindCallback<ParseObject>() {

				@Override
				public void done(List<ParseObject> roteList, ParseException e) {
					if (e == null) {
						for (ParseObject roteItem : roteList) {
							checkRotesAndAddToList(roteItem);
						}
						goToCorrectPage();
					} else {
						ParseExceptios.showErrorMessage(mContext, e);
					}
				}

			});
		} else {
			Toast.makeText(mContext, "Ta errado isso ai", Toast.LENGTH_SHORT).show();
		}

		// ParseGeoPoint destinyPoint = new ParseGeoPoint(-23.5987627,
		// -46.67654730000004);
		// ParseUser user = ParseUser.getCurrentUser();
		//
		// ParseObject rote = new ParseObject("Rote");
		//
		// rote.put("destinyPoint", destinyPoint);
		// rote.put("user", user.getUsername());
		//
		// rote.saveInBackground(new SaveCallback() {
		//
		// @Override
		// public void done(ParseException e) {
		// if (e == null) {
		// Toast.makeText(mContext, "salvou", Toast.LENGTH_SHORT).show();
		// } else {
		// Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
		// }
		// }
		// });

	}

	public void goToCorrectPage() {
		if (mRoteList.size() > 0) {
			goToRoteList();
		} else {
			goToCreateRote();
		}
	}

	public void checkRotesAndAddToList(ParseObject roteItem) {
		double origimLat = roteItem.getDouble("origimLat");
		double origimLng = roteItem.getDouble("origimLng");
		ParseGeoPoint roteOrigimPoint = new ParseGeoPoint(origimLat, origimLng);
		double origimDistance = roteOrigimPoint.distanceInKilometersTo(mOrigimPoint);
		if (origimDistance <= 5) {
			String origimString = getEnderecoConvertido(getSingleAddress(origimLat, origimLng));
			// String origimString =
			// getEnderecoConvertido(getSingleAddress(origimLat,
			// origimLng));
			Rote rote = new Rote(roteOrigimPoint, roteItem.getParseGeoPoint("destiny"), roteItem.getString("user"), origimString, origimString);
			mRoteList.add(rote);
		}

	}

	public List<Address> getListaDeEnderecos(String endereco) {
		try {
			List<Address> enderecos = new ArrayList<Address>();
			android.location.Geocoder geocoder = new android.location.Geocoder(mContext);
			enderecos = geocoder.getFromLocationName(endereco, 10000);
			return enderecos;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Address getSingleAddress(double lat, double lng) {

		try {
			List<Address> enderecos = new ArrayList<Address>();
			android.location.Geocoder geocoder = new android.location.Geocoder(mContext);
			enderecos = geocoder.getFromLocation(lat, lng, 1);
			return enderecos.get(0);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public CharSequence[] getListaConvertida(List<Address> enderecos) {
		String[] strEnderecos = new String[enderecos.size()];

		for (int i = 0; i < enderecos.size(); i++) {
			Address address = enderecos.get(i);

			strEnderecos[i] = getEnderecoConvertido(address);
		}

		return strEnderecos;

	}

	public String getEnderecoConvertido(Address address) {

		String endereco = address.getThoroughfare();

		String numero = address.getSubThoroughfare() != null ? address.getSubThoroughfare() : "Sem numero";
		String bairro = address.getSubLocality() != null ? address.getSubLocality() : "Sem bairro";
		String cidade = address.getLocality() != null ? address.getLocality() : "Sem cidade";
		String estado = address.getAdminArea() != null ? address.getAdminArea() : "Sem estado";

		return endereco + ", " + numero + ", " + bairro + " - " + cidade + " / " + estado;

	}

	@OnClick(R.id.btn_search_search)
	public void vamosver() {

		String originString = mOrigin.getText().toString();
		String destinyString = mDestiny.getText().toString();

		// recebe uma lista de endere�os com objetos ADDRESS
		mOrigimList = getListaDeEnderecos(originString);
		mDestinyList = getListaDeEnderecos(destinyString);

		// Converte para uma lista de strings formatadas
		final CharSequence[] origimFormatedList = getListaConvertida(mOrigimList);
		final CharSequence[] destinyFormatedList = getListaConvertida(mDestinyList);

		// Checa se houve retorno para os dois endere�os
		if (origimFormatedList.length > 0 && destinyFormatedList.length > 0) {

			AlertDialog.Builder popupOrigem = new AlertDialog.Builder(mContext);
			final AlertDialog.Builder popupDestino = new AlertDialog.Builder(mContext);

			popupOrigem.setTitle("Selecione Origem");
			popupDestino.setTitle("Selecione Destino");

			popupOrigem.setItems(origimFormatedList, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Address origimAddress = mOrigimList.get(which);
					mOrigin.setText(origimFormatedList[which]);
					mOrigimPoint = new ParseGeoPoint(origimAddress.getLatitude(), origimAddress.getLongitude());
					popupDestino.show();
				}
			});

			// Define os itens da lista e coloca a��o no click do destino
			popupDestino.setItems(destinyFormatedList, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					// Define o objeto destino de acordo com a escolha na lista
					Address destinyAddress = mDestinyList.get(which);
					mDestiny.setText(destinyFormatedList[which]);
					mDestinyPoint = new ParseGeoPoint(destinyAddress.getLatitude(), destinyAddress.getLongitude());
					searchRotes();
				}
			});

			// Mostra a popup de origem primeiro
			popupOrigem.show();

		}
		// Se um endere�o n�o deu retorno
		else {
			// seta o erro aonde a busca n�o deu retorno

			Toast.makeText(mContext, "Sem resultados", Toast.LENGTH_SHORT).show();
		}

	}

	public void goToRoteList() {
		RoteListFragment fragment = new RoteListFragment();
		Bundle args = new Bundle();
		args.putParcelableArrayList("mRoteList", mRoteList);
		Utils.changeFragment(getFragmentManager(), fragment, args);
	}

	private void goToCreateRote() {
		CreateRoteFragment fragment = new CreateRoteFragment();
		Bundle args = new Bundle();
		args.putDouble("origimLat", mOrigimPoint.getLatitude());
		args.putDouble("origimLng", mOrigimPoint.getLongitude());
		args.putDouble("destinyLat", mDestinyPoint.getLatitude());
		args.putDouble("destinyLng", mDestinyPoint.getLongitude());
		args.putString("origimAddress", mOrigin.getText().toString());
		args.putString("destinyAddress", mDestiny.getText().toString());
		Utils.changeFragment(getFragmentManager(), fragment, args);
	}
}
