package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SolicitacaoRecebida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@Column(nullable = false)
	private Long idUsuarioDeEnvio;

	public SolicitacaoRecebida() {
		// TODO Auto-generated constructor stub
	}

	public SolicitacaoRecebida(Usuario usuario, Long idUsuarioDeEnvio) {
		super();
		this.usuario = usuario;
		this.idUsuarioDeEnvio = idUsuarioDeEnvio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdUsuarioDeEnvio() {
		return idUsuarioDeEnvio;
	}

	public void setIdUsuarioDeEnvio(Long idUsuarioDeEnvio) {
		this.idUsuarioDeEnvio = idUsuarioDeEnvio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idUsuarioDeEnvio, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitacaoRecebida other = (SolicitacaoRecebida) obj;
		return Objects.equals(id, other.id) && Objects.equals(idUsuarioDeEnvio, other.idUsuarioDeEnvio)
				&& Objects.equals(usuario, other.usuario);
	}

}
