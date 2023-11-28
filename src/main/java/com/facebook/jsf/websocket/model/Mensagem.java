package com.facebook.jsf.websocket.model;

public class Mensagem {

	private String mensagem;
	private String data;
	private String username;
	private String nome;
	private String imagem;

	public Mensagem(String mensagem, String data, String username, String nome, String imagem) {
		super();
		this.mensagem = mensagem;
		this.data = data;
		this.username = username;
		this.nome = nome;
		this.imagem = imagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
