package com.facebook.jsf.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Chat implements Serializable {

	private static final long serialVersionUID = 6578605209933249170L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuarioPrincipal;

	@ManyToOne
	private Usuario usuarioSecundario;

	@Column(columnDefinition = "TEXT")
	private String mensagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioPrincipal() {
		return usuarioPrincipal;
	}

	public void setUsuarioPrincipal(Usuario usuarioPrincipal) {
		this.usuarioPrincipal = usuarioPrincipal;
	}

	public Usuario getUsuarioSecundario() {
		return usuarioSecundario;
	}

	public void setUsuarioSecundario(Usuario usuarioSecundario) {
		this.usuarioSecundario = usuarioSecundario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
