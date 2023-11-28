package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Colecao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ManyToOne
	private Usuario usuario;

	@Column(columnDefinition = "TEXT")
	private String capa64;

	@OneToMany(mappedBy = "colecao")
	private Set<Salvo> salvos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCapa64() {
		return capa64;
	}

	public void setCapa64(String capa64) {
		this.capa64 = capa64;
	}

	public Set<Salvo> getSalvos() {
		return salvos;
	}

	public void setSalvos(Set<Salvo> salvos) {
		this.salvos = salvos;
	}

}
