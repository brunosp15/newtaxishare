package br.com.tinan.taxishare;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseGeoPoint;

public class Rote implements Parcelable {

	public ParseGeoPoint origimPoint;
	public ParseGeoPoint destinyPoint;
	public String hostUsername;
	public String destinyString;
	public String origimString;

	public Rote(ParseGeoPoint origimPoint, ParseGeoPoint destinyPoint, String hostUsername, String destinyString, String origimString) {
		this.origimPoint = origimPoint;
		this.destinyPoint = destinyPoint;
		this.hostUsername = hostUsername;
		this.destinyString = destinyString;
		this.origimString = origimString;
	}

	

	public ParseGeoPoint getOrigimPoint() {
		return origimPoint;
	}



	public void setOrigimPoint(ParseGeoPoint origimPoint) {
		this.origimPoint = origimPoint;
	}



	public ParseGeoPoint getDestinyPoint() {
		return destinyPoint;
	}



	public void setDestinyPoint(ParseGeoPoint destinyPoint) {
		this.destinyPoint = destinyPoint;
	}



	public String getHostUsername() {
		return hostUsername;
	}



	public void setHostUsername(String hostUsername) {
		this.hostUsername = hostUsername;
	}



	public String getDestinyString() {
		return destinyString;
	}



	public void setDestinyString(String destinyString) {
		this.destinyString = destinyString;
	}



	public String getOrigimString() {
		return origimString;
	}



	public void setOrigimString(String origimString) {
		this.origimString = origimString;
	}



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
