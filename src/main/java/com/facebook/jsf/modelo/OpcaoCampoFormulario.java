package com.facebook.jsf.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OpcaoCampoFormulario implements Serializable {

	private static final long serialVersionUID = -327690780553263203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String idInput;

	@ManyToOne
	private CampoFormulario campoFormulario;

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

	public CampoFormulario getCampoFormulario() {
		return campoFormulario;
	}

	public void setCampoFormulario(CampoFormulario campoFormulario) {
		this.campoFormulario = campoFormulario;
	}

	public String getIdInput() {
		return idInput;
	}

	public void setIdInput(String idInput) {
		this.idInput = idInput;
	}

}
