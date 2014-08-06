package br.com.tinan.taxishare;

import com.parse.ParseGeoPoint;

public class Rote {

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

}
