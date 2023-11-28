package com.facebook.jsf.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Chat;

public class ChatDTO {
	private String mensagem;
	private Long idPrincipal;

	public ChatDTO(Chat chat) {
		this.mensagem = chat.getMensagem();
		this.idPrincipal = chat.getUsuarioPrincipal().getId();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getIdPrincipal() {
		return idPrincipal;
	}

	public void setIdPrincipal(Long idPrincipal) {
		this.idPrincipal = idPrincipal;
	}

	public static List<ChatDTO> listar(List<Chat> lista) {
		return lista.stream().map(ChatDTO::new).collect(Collectors.toList());
	}

}
