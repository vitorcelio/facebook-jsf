package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column(nullable = false)
	@Length(min = 6)
	private String senha;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Temporal(TemporalType.DATE)
	private Date dataQueEntrou;

	@NotBlank
	private String genero;

	@Column(columnDefinition = "TEXT")
	private String fotoPerfil64;

	@Column(columnDefinition = "TEXT")
	private String fotoMiniatura;

	@Column(columnDefinition = "TEXT")
	private String fotoCapa64;

	private String empresaQueTrabalha;

	private String escolaQueFrequentou;

	private String cidadeQueMora;

	private String cidadeQueNasceu;

	private Boolean estaEmRelacionamento;

	private Boolean estaCasado;

	private String relacionamentoCom;

	private String instagram;

	private String whatsapp;

	@Column(columnDefinition = "TEXT")
	private String biografia;

	private String longitude;

	private String latitude;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getFotoMiniatura() {
		return fotoMiniatura;
	}

	public void setFotoMiniatura(String fotoMiniatura) {
		this.fotoMiniatura = fotoMiniatura;
	}

	public Date getDataQueEntrou() {
		return dataQueEntrou;
	}

	public void setDataQueEntrou(Date dataQueEntrou) {
		this.dataQueEntrou = dataQueEntrou;
	}

	public String getFotoPerfil64() {
		return fotoPerfil64;
	}

	public void setFotoPerfil64(String fotoPerfil64) {
		this.fotoPerfil64 = fotoPerfil64;
	}

	public String getFotoCapa64() {
		return fotoCapa64;
	}

	public void setFotoCapa64(String fotoCapa64) {
		this.fotoCapa64 = fotoCapa64;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, id, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(username, other.username);
	}

}
