package com.facebook.jsf.form;

import javax.servlet.http.Part;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProdutoForm {

	private Long id;

	@NotNull(message = "Faça o upload de uma foto.")
	private Part imagem;

	@NotBlank(message = "Insira um título válido.")
	@Size(min = 0, max = 100, message = "Insira um título mais curto.")
	private String titulo;

	@NotNull(message = "Insira um preço para o seu item.")
	@Max(value = 999_999_999, message = "O valor máximo é R$ 999.999.999,00")
	private Double preco;

	@NotNull(message = "Selecione uma categoria")
	private Long idCategoria;

	@NotBlank(message = "Selecione uma condição")
	private String condicao;

	private String descricao;

	private String longitude;

	private String latitude;

	private Long idUsuario;

	@NotBlank(message = "Insira a localização do produto.")
	private String cidade;

	private String cidadeQuery;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Part getImagem() {
		return imagem;
	}

	public void setImagem(Part imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCidadeQuery() {
		return cidadeQuery;
	}

	public void setCidadeQuery(String cidadeQuery) {
		this.cidadeQuery = cidadeQuery;
	}

}
