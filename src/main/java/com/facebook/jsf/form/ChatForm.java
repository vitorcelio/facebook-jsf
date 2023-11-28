package com.facebook.jsf.form;

public class ChatForm {

	private Long id;
	private Long idPrincipal;
	private Long idSecundario;
	private String mensagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPrincipal() {
		return idPrincipal;
	}

	public void setIdPrincipal(Long idPrincipal) {
		this.idPrincipal = idPrincipal;
	}

	public Long getIdSecundario() {
		return idSecundario;
	}

	public void setIdSecundario(Long idSecundario) {
		this.idSecundario = idSecundario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
