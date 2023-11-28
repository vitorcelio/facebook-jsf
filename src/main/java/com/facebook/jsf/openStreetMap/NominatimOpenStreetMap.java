package com.facebook.jsf.openStreetMap;

import java.lang.reflect.Type;
import java.util.List;

import com.facebook.jsf.dto.LonLatDTO;
import com.facebook.jsf.openStreetMap.enums.FormatNominatim;
import com.facebook.jsf.openStreetMap.interfaces.Nominatim;
import com.facebook.jsf.utils.FacebookUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NominatimOpenStreetMap implements Nominatim {

	@Override
	public List<LonLatDTO> getAllInfos(String query, String street, String city, String county, String state,
			String country, String postalCode, FormatNominatim format) {
		Gson gson = new Gson();
		String urlGet = null;

		try {
			String url = "https://nominatim.openstreetmap.org/search?";
			String params = "";
			if (!FacebookUtils.isEmpty(query)) {
				params += "q=" + query.replace(" ", "+");
			}

			if (!FacebookUtils.isEmpty(street)) {
				if (!params.isEmpty()) {
					params += "&";
				}

				params += "street=" + street.replace(" ", "+");
			}

			if (!FacebookUtils.isEmpty(city)) {
				if (!params.isEmpty()) {
					params += "&";
				}

				params += "city=" + city.replace(" ", "+");
			}

			if (!FacebookUtils.isEmpty(postalCode)) {
				if (!params.isEmpty()) {
					params += "&";
				}

				params += "postalCode=" + postalCode.replace(" ", "");
			}

			if (!FacebookUtils.isEmpty(county)) {
				if (!params.isEmpty()) {
					params += "&";
				}

				params += "county=" + county.replace(" ", "+");
			}

			if (!FacebookUtils.isEmpty(state)) {
				if (!params.isEmpty()) {
					params += "&";
				}

				params += "state=" + state.replace(" ", "+");
			}

			if (!FacebookUtils.isEmpty(country)) {
				if (!params.isEmpty()) {
					params += "&";
				}

				params += "country=" + country.replace(" ", "+");
			}
			url += params;
			url += "&format=" + (format != null ? format.toString() : "json");
			url += "&polygon_geojson=0&addressdetails=1&limit=5";

			urlGet = FacebookUtils.readUrl(url);

			if (urlGet != null) {
				Type typeLonLat = new TypeToken<List<LonLatDTO>>() {
				}.getType();

				return gson.fromJson(urlGet, typeLonLat);
			}

			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	@Override
	public List<LonLatDTO> getInfos(String query, FormatNominatim format) {
		return getAllInfos(query, null, null, null, null, null, null, format);
	}
	
	public static String teste(String query, String street, String city, String county, String state,
			String country, String postalCode, FormatNominatim format) {
		String url = "https://nominatim.openstreetmap.org/search?";
		String params = "";
		if (!FacebookUtils.isEmpty(query)) {
			params += "q=" + query.replace(" ", "+");
		}

		if (!FacebookUtils.isEmpty(street)) {
			if (!params.isEmpty()) {
				params += "&";
			}

			params += "street=" + street.replace(" ", "+");
		}

		if (!FacebookUtils.isEmpty(city)) {
			if (!params.isEmpty()) {
				params += "&";
			}

			params += "city=" + city.replace(" ", "+");
		}

		if (!FacebookUtils.isEmpty(postalCode)) {
			if (!params.isEmpty()) {
				params += "&";
			}

			params += "postalCode=" + postalCode.replace(" ", "");
		}

		if (!FacebookUtils.isEmpty(county)) {
			if (!params.isEmpty()) {
				params += "&";
			}

			params += "county=" + county.replace(" ", "+");
		}

		if (!FacebookUtils.isEmpty(state)) {
			if (!params.isEmpty()) {
				params += "&";
			}

			params += "state=" + state.replace(" ", "+");
		}

		if (!FacebookUtils.isEmpty(country)) {
			if (!params.isEmpty()) {
				params += "&";
			}

			params += "country=" + country.replace(" ", "+");
		}
		url += params;
		url += "&format=" + (format != null ? format.toString() : "json");
		url += "&polygon_geojson=0&addressdetails=1&limit=5";
		return url;
	}

	public static void main(String[] args) {
		System.out.println(teste(null, null, "Caxias", null, null, null, null, FormatNominatim.JSON));
	}

}
