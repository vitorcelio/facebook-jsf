package com.facebook.jsf.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.SolicitacaoEnviada;

public class SolicitacaoEnviadaDTO {

	private Long id;
	private UsuarioDTO usuario;

	public SolicitacaoEnviadaDTO(SolicitacaoEnviada enviada) {
		this.id = enviada.getId();
		this.usuario = new UsuarioDTO(enviada.getUsuario());
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

	public static List<SolicitacaoEnviadaDTO> listar(List<SolicitacaoEnviada> list) {
		return list.stream().map(SolicitacaoEnviadaDTO::new).collect(Collectors.toList());
	}

}
