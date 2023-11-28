package com.facebook.jsf.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Resposta;

public class RespostaDTO {

	private Long id;
	private UsuarioDTO usuario;
	private String texto;

	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.usuario = new UsuarioDTO(resposta.getUsuario());
		this.texto = resposta.getTexto();
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public static List<RespostaDTO> listar(List<Resposta> list) {
		return list.stream().map(RespostaDTO::new).collect(Collectors.toList());
	}

}
