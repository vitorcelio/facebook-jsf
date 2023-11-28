package com.facebook.jsf.openStreetMap.enums;

public enum FormatNominatim {
	JSON ("json"), XML ("xml");
	
	private String format;
	
	private FormatNominatim(String format) {
		this.format = format;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return format;
	}
}
