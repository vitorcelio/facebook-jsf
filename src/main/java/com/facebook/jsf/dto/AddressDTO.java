package com.facebook.jsf.dto;

public class AddressDTO {
	private String road;
	private String city;
	private String state;
	private String country;
	private String region;
	private String town;

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "AddressDTO [road=" + road + ", city=" + city + ", state=" + state + ", country=" + country + ", region="
				+ region + ", town=" + town + "]";
	}

}
