package com.facebook.jsf.form;

import java.util.ArrayList;
import java.util.List;

public class FormularioListForm {

	private Integer id;
	private String descricao;
	private String textoBotao;
	private String token;
	private List<CampoFormularioForm> camposList;
	private List<RespostaIndividuaisForm> respostaIndividuais;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTextoBotao() {
		return textoBotao;
	}

	public void setTextoBotao(String textoBotao) {
		this.textoBotao = textoBotao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CampoFormularioForm> getCamposList() {
		if (camposList == null) {
			camposList = new ArrayList<>();
		}
		return camposList;
	}

	public void setCamposList(List<CampoFormularioForm> camposList) {
		this.camposList = camposList;
	}

	public List<RespostaIndividuaisForm> getRespostaIndividuais() {
		if (respostaIndividuais == null) {
			respostaIndividuais = new ArrayList<>();
		}
		return respostaIndividuais;
	}

	public void setRespostaIndividuais(List<RespostaIndividuaisForm> respostaIndividuais) {
		this.respostaIndividuais = respostaIndividuais;
	}

	public RespostaIndividuaisForm respostasItem(String token) {
		for (RespostaIndividuaisForm respostaIndividuaisForm : respostaIndividuais) {
			if (respostaIndividuaisForm.equals(new RespostaIndividuaisForm(token))) {
				return respostaIndividuaisForm;
			}
		}

		return new RespostaIndividuaisForm();
	}

}
