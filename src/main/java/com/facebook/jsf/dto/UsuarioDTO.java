package com.facebook.jsf.dto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.utils.FacebookUtils;

public class UsuarioDTO {

	private Long id;
	private String username;
	private String senha;
	private String nome;
	private String sobrenome;
	private String email;
	private String genero;
	private String fotoPerfil;
	private String fotoCapa;
	private String fotoMiniatura;
	private String dataNascimento;
	private String dataQueEntrou;
	private String empresaQueTrabalha;
	private String escolaQueFrequentou;
	private String cidadeQueMora;
	private String cidadeQueNasceu;
	private Boolean estaCasado;
	private Boolean estaEmRelacionamento;
	private String relacionamentoCom;
	private String instagram;
	private String whatsapp;
	private String biografia;
	private String latitude;
	private String longitude;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.username = usuario.getUsername();
		this.senha = usuario.getSenha();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.genero = usuario.getGenero();
		this.fotoPerfil = usuario.getFotoPerfil64();
		this.fotoCapa = usuario.getFotoCapa64();
		this.fotoMiniatura = usuario.getFotoMiniatura();
		this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"))
				.format(usuario.getDataNascimento());
		this.dataQueEntrou = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"))
				.format(usuario.getDataQueEntrou());
		this.empresaQueTrabalha = FacebookUtils.operadorTernario(usuario.getEmpresaQueTrabalha());
		this.escolaQueFrequentou = FacebookUtils.operadorTernario(usuario.getEscolaQueFrequentou());
		this.cidadeQueMora = FacebookUtils.operadorTernario(usuario.getCidadeQueMora());
		this.cidadeQueNasceu = FacebookUtils.operadorTernario(usuario.getCidadeQueNasceu());
		this.instagram = FacebookUtils.operadorTernario(usuario.getInstagram());
		this.whatsapp = FacebookUtils.operadorTernario(usuario.getWhatsapp());
		this.biografia = FacebookUtils.operadorTernario(usuario.getBiografia());
		this.estaCasado = usuario.getEstaCasado();
		this.estaEmRelacionamento = usuario.getEstaEmRelacionamento();
		this.relacionamentoCom = FacebookUtils.operadorTernario(usuario.getRelacionamentoCom());
		this.latitude = usuario.getLatitude();
		this.longitude = usuario.getLongitude();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getFotoCapa() {
		return fotoCapa;
	}

	public void setFotoCapa(String fotoCapa) {
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

	public Boolean getEstaCasado() {
		return estaCasado;
	}

	public void setEstaCasado(Boolean estaCasado) {
		this.estaCasado = estaCasado;
	}

	public Boolean getEstaEmRelacionamento() {
		return estaEmRelacionamento;
	}

	public void setEstaEmRelacionamento(Boolean estaEmRelacionamento) {
		this.estaEmRelacionamento = estaEmRelacionamento;
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

	public String getFotoMiniatura() {
		return fotoMiniatura;
	}

	public void setFotoMiniatura(String fotoMiniatura) {
		this.fotoMiniatura = fotoMiniatura;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataQueEntrou() {
		return dataQueEntrou;
	}

	public void setDataQueEntrou(String dataQueEntrou) {
		this.dataQueEntrou = dataQueEntrou;
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

	public static List<UsuarioDTO> listar(List<Usuario> list) {
		return list.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

}
