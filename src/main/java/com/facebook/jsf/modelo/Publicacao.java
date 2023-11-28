package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Publicacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;

	@Column(columnDefinition = "TEXT")
	private String descricao;

	@Column(columnDefinition = "TEXT")
	private String imagemBase64;

	private String contentType;

	private Boolean criacaoConta;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publicacao", cascade = CascadeType.ALL)
	private List<Comentario> comentarios;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publicacao", cascade = CascadeType.ALL)
	private Set<Curtida> curtidas;

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

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public Boolean getCriacaoConta() {
		return criacaoConta;
	}

	public void setCriacaoConta(Boolean criacaoConta) {
		this.criacaoConta = criacaoConta;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Set<Curtida> getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(Set<Curtida> curtidas) {
		this.curtidas = curtidas;
	}

}
