package com.facebook.jsf.form;

import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

public class PublicacaoForm {

	private Long id;

	@NotNull
	private Long idUsuario;

	private String descricao;

	private Part imagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Part getImagem() {
		return imagem;
	}

	public void setImagem(Part imagem) {
		this.imagem = imagem;
	}

}
