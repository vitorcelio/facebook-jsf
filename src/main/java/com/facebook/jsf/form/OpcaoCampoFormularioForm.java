package com.facebook.jsf.form;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.OpcaoCampoFormulario;

public class OpcaoCampoFormularioForm {

	private Integer id;
	private String descricao;
	private String idInput;

	public OpcaoCampoFormularioForm() {
	}

	public OpcaoCampoFormularioForm(OpcaoCampoFormulario opcao) {
		this.id = opcao.getId();
		this.descricao = opcao.getDescricao();
		this.idInput = opcao.getIdInput();
	}

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

	public String getIdInput() {
		return idInput;
	}

	public void setIdInput(String idInput) {
		this.idInput = idInput;
	}

	public static List<OpcaoCampoFormularioForm> converter(List<OpcaoCampoFormulario> list) {
		return list.stream().map(OpcaoCampoFormularioForm::new).collect(Collectors.toList());
	}

}
