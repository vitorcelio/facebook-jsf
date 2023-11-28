package com.facebook.jsf.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.CampoFormulario;

public class CampoFormularioForm {

	private Integer id;
	private String descricao;
	private Integer tipo;
	private Boolean obrigatorio = true;
	private String idInput;
	private String sugestao;

	private List<OpcaoCampoFormularioForm> opcoes;

	public CampoFormularioForm() {
	}

	public CampoFormularioForm(CampoFormulario campo) {
		this.id = campo.getId();
		this.descricao = campo.getDescricao();
		this.tipo = campo.getTipo();
		this.obrigatorio = campo.getObrigatorio();
		this.idInput = campo.getIdInput();
		this.sugestao = campo.getSugestao();
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Boolean getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(Boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public List<OpcaoCampoFormularioForm> getOpcoes() {
		if (this.opcoes == null) {
			this.opcoes = new ArrayList<>();
		}
		return opcoes;
	}

	public void setOpcoes(List<OpcaoCampoFormularioForm> opcoes) {
		this.opcoes = opcoes;
	}

	public String getIdInput() {
		return idInput;
	}

	public void setIdInput(String idInput) {
		this.idInput = idInput;
	}

	public String getSugestao() {
		return sugestao;
	}

	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idInput);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampoFormularioForm other = (CampoFormularioForm) obj;
		return Objects.equals(idInput, other.idInput);
	}

	public static List<CampoFormularioForm> converter(List<CampoFormulario> list) {
		return list.stream().map(CampoFormularioForm::new).collect(Collectors.toList());
	}

}
