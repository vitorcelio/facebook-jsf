package com.facebook.jsf.dto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Produto;
import com.facebook.jsf.utils.FacebookUtils;

public class ProdutoDTO {

	private Long id;
	private UsuarioDTO usuario;
	private CategoriaDTO categoria;
	private String nome;
	private String preco;
	private String data;
	private String descricao;
	private String cidade;
	private String imagem64;
	private String latitude;
	private String longitude;
	private String token;
	private String condicao;
	private String cidadeQuery;
	private String urlAmazonS3;

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.usuario = new UsuarioDTO(produto.getUsuario());
		this.categoria = new CategoriaDTO(produto.getCategoria());
		this.nome = produto.getNome();
		this.data = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR")).format(produto.getData());
		this.preco = FacebookUtils.converterMoedaDouble(produto.getPreco());
		this.descricao = produto.getDescricao();
		this.cidade = produto.getCidade();
		this.imagem64 = produto.getImagem64();
		this.latitude = produto.getLatitude();
		this.longitude = produto.getLongitude();
		this.token = produto.getToken();
		this.condicao = produto.getCondicao();
		this.cidadeQuery = produto.getCidadeQuery();
		this.urlAmazonS3 = produto.getUrlImagemAmazons3();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
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

	public String getUrlAmazonS3() {
		return urlAmazonS3;
	}

	public void setUrlAmazonS3(String urlAmazonS3) {
		this.urlAmazonS3 = urlAmazonS3;
	}

	public static List<ProdutoDTO> listar(List<Produto> list) {
		return list.stream().map(ProdutoDTO::new).collect(Collectors.toList());
	}

}
