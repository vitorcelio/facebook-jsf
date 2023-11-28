package com.facebook.jsf.form;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.RespostaCampoFormulario;

public class RespostasCampoForm {

	private Integer id;
	private String resposta;
	private Integer idCampo;

	public RespostasCampoForm() {
	}

	public RespostasCampoForm(RespostaCampoFormulario resposta) {
		this.id = resposta.getId();
		this.resposta = resposta.getResposta();
		this.idCampo = resposta.getCampoFormulario().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Integer getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(Integer idCampo) {
		this.idCampo = idCampo;
	}

	public static List<RespostasCampoForm> converter(List<RespostaCampoFormulario> list) {
		return list.stream().map(RespostasCampoForm::new).collect(Collectors.toList());
	}
}
