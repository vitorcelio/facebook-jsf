package com.facebook.jsf.dto;

public class LonLatDTO {
	private String lat;
	private String lon;
	private AddressDTO address;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "LonLatDTO [lat=" + lat + ", lon=" + lon + ", address=" + address + "]";
	}
	
	

}
