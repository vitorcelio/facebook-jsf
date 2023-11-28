package com.facebook.jsf.form;

import javax.servlet.http.Part;

public class UsuarioForm {

	private Long id;
	private String nome;
	private String sobrenome;
	private Part fotoPerfil;
	private Part fotoCapa;
	private String empresaQueTrabalha;
	private String escolaQueFrequentou;
	private String cidadeQueMora;
	private String cidadeQueNasceu;
	private Boolean estaEmRelacionamento;
	private Boolean estaCasado;
	private String relacionamentoCom;
	private String instagram;
	private String whatsapp;
	private String biografia;
	private String latitude;
	private String longitude;

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Part getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(Part fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public Part getFotoCapa() {
		return fotoCapa;
	}

	public void setFotoCapa(Part fotoCapa) {
		this.fotoCapa = fotoCapa;
	}

	public String getEmpresaQueTrabalha() {
		return empresaQueTrabalha;
	}

	public void setEmpresaQueTrabalha(String empresaQueTrabalha) {
		this.empresaQueTrabalha = empresaQueTrabalha;
	}

	public String getEscolaQueFrequentou() {
		return escolaQueFrequentou;
	}

	public void setEscolaQueFrequentou(String escolaQueFrequentou) {
		this.escolaQueFrequentou = escolaQueFrequentou;
	}

	public String getCidadeQueMora() {
		return cidadeQueMora;
	}

	public void setCidadeQueMora(String cidadeQueMora) {
		this.cidadeQueMora = cidadeQueMora;
	}

	public String getCidadeQueNasceu() {
		return cidadeQueNasceu;
	}

	public void setCidadeQueNasceu(String cidadeQueNasceu) {
		this.cidadeQueNasceu = cidadeQueNasceu;
	}

	public Boolean getEstaEmRelacionamento() {
		return estaEmRelacionamento;
	}

	public void setEstaEmRelacionamento(Boolean estaEmRelacionamento) {
		this.estaEmRelacionamento = estaEmRelacionamento;
	}

	public Boolean getEstaCasado() {
		return estaCasado;
	}

	public void setEstaCasado(Boolean estaCasado) {
		this.estaCasado = estaCasado;
	}

	public String getRelacionamentoCom() {
		return relacionamentoCom;
	}

	public void setRelacionamentoCom(String relacionamentoCom) {
		this.relacionamentoCom = relacionamentoCom;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
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

}
