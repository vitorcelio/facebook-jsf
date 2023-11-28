package com.facebook.jsf.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.SolicitacaoRecebida;

public class SolicitacaoRecebidaDTO {

	private Long id;
	private UsuarioDTO usuario;

	public SolicitacaoRecebidaDTO(SolicitacaoRecebida recebida) {
		this.id = recebida.getId();
		this.usuario = new UsuarioDTO(recebida.getUsuario());
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

	public static List<SolicitacaoRecebidaDTO> listar(List<SolicitacaoRecebida> list) {
		return list.stream().map(SolicitacaoRecebidaDTO::new).collect(Collectors.toList());
	}

}
