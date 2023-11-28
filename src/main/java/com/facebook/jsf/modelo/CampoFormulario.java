package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CampoFormulario implements Serializable {

	private static final long serialVersionUID = -8911734696042062722L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Boolean obrigatorio;

	@Column(nullable = false)
	private String idInput;

	@Column(nullable = false)
	private String sugestao;

	@ManyToOne
	private Formulario formulario;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "campoFormulario")
	private List<RespostaCampoFormulario> listaResposta;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "campoFormulario")
	private List<OpcaoCampoFormulario> listaOpcoes;

	@Column(nullable = false)
	private Integer tipo;

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

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public List<RespostaCampoFormulario> getListaResposta() {
		return listaResposta;
	}

	public void setListaResposta(List<RespostaCampoFormulario> listaResposta) {
		this.listaResposta = listaResposta;
	}

	public List<OpcaoCampoFormulario> getListaOpcoes() {
		return listaOpcoes;
	}

	public void setListaOpcoes(List<OpcaoCampoFormulario> listaOpcoes) {
		this.listaOpcoes = listaOpcoes;
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

}
