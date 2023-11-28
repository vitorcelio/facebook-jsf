package com.facebook.jsf.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Categoria;

public class CategoriaDTO {

	private String icone;
	private String nome;
	private String url;

	public CategoriaDTO(Categoria categoria) {
		this.icone = categoria.getIcone();
		this.nome = categoria.getNome();
		this.url = categoria.getUrl();
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static List<CategoriaDTO> listar(List<Categoria> list) {
		return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
	}
}
