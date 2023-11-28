package com.facebook.jsf.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RespostaCampoFormulario implements Serializable {

	private static final long serialVersionUID = 2046397702730019362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String resposta;

//	@ManyToOne
//	private Usuario usuarioResposta;

	@ManyToOne
	private CampoFormulario campoFormulario;

	@Column(nullable = false)
	private String token;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Usuario getUsuarioResposta() {
//		return usuarioResposta;
//	}
//
//	public void setUsuarioResposta(Usuario usuarioResposta) {
//		this.usuarioResposta = usuarioResposta;
//	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public CampoFormulario getCampoFormulario() {
		return campoFormulario;
	}

	public void setCampoFormulario(CampoFormulario campoFormulario) {
		this.campoFormulario = campoFormulario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
