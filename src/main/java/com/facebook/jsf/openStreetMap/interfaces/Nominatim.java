package com.facebook.jsf.openStreetMap.interfaces;

import java.util.List;

import com.facebook.jsf.dto.LonLatDTO;
import com.facebook.jsf.openStreetMap.enums.FormatNominatim;

public interface Nominatim {

	public List<LonLatDTO> getAllInfos(String query, String street, String city, String county, String state,
			String country, String postalCode, FormatNominatim format);

	public List<LonLatDTO> getInfos(String query, FormatNominatim format);
}
