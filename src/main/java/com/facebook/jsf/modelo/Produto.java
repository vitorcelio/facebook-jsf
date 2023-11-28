package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Categoria categoria;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Double preco;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(columnDefinition = "TEXT")
	private String descricao;

	@Column(nullable = false)
	private String cidade;

	@Column(columnDefinition = "TEXT")
	private String imagem64;

	private String latitude;

	private String longitude;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private String condicao;

	private String cidadeQuery;

	private String urlImagemAmazons3;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getImagem64() {
		return imagem64;
	}

	public void setImagem64(String imagem64) {
		this.imagem64 = imagem64;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getCidadeQuery() {
		return cidadeQuery;
	}

	public void setCidadeQuery(String cidadeQuery) {
		this.cidadeQuery = cidadeQuery;
	}

	public String getUrlImagemAmazons3() {
		return urlImagemAmazons3;
	}

	public void setUrlImagemAmazons3(String urlImagemAmazons3) {
		this.urlImagemAmazons3 = urlImagemAmazons3;
	}

}
