package com.facebook.jsf.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RespostaIndividuaisForm {

	private String token;
	private List<RespostasCampoForm> respostaList;

	public RespostaIndividuaisForm() {
		// TODO Auto-generated constructor stub
	}

	public RespostaIndividuaisForm(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<RespostasCampoForm> getRespostaList() {
		if (respostaList == null) {
			respostaList = new ArrayList<>();
		}
		return respostaList;
	}

	public void setRespostaList(List<RespostasCampoForm> respostaList) {
		this.respostaList = respostaList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespostaIndividuaisForm other = (RespostaIndividuaisForm) obj;
		return Objects.equals(token, other.token);
	}

}
