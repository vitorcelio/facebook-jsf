package com.facebook.jsf.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Formulario implements Serializable {

	private static final long serialVersionUID = -3263650345864700507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Boolean ativo;

	private String txtBotao;

	@Column(nullable = false)
	private Boolean imagemAtiva;

	@Column(columnDefinition = "TEXT")
	private String imagem;

//	@ManyToOne
//	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "formulario", fetch = FetchType.EAGER)
	private List<CampoFormulario> camposFormularios;

	@Column(nullable = false, unique = true)
	private String token;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	public Boolean getImagemAtiva() {
		return imagemAtiva;
	}

	public void setImagemAtiva(Boolean imagemAtiva) {
		this.imagemAtiva = imagemAtiva;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTxtBotao() {
		return txtBotao;
	}

	public void setTxtBotao(String txtBotao) {
		this.txtBotao = txtBotao;
	}

	public List<CampoFormulario> getCamposFormularios() {
		return camposFormularios;
	}

	public void setCamposFormularios(List<CampoFormulario> camposFormularios) {
		this.camposFormularios = camposFormularios;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
