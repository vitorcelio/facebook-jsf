package com.facebook.jsf.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadastroRespostasCampoForm implements Serializable {

	private static final long serialVersionUID = -7098989494205919071L;

	private Integer idForm;
	private List<RespostasCampoForm> respostas;

	public Integer getIdForm() {
		return idForm;
	}

	public void setIdForm(Integer idForm) {
		this.idForm = idForm;
	}

	public List<RespostasCampoForm> getRespostas() {
		if (respostas == null) {
			respostas = new ArrayList<>();
		}
		return respostas;
	}

	public void setRespostas(List<RespostasCampoForm> respostas) {
		this.respostas = respostas;
	}

}
