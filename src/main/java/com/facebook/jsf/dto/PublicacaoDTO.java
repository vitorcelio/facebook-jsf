package com.facebook.jsf.dto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Publicacao;

public class PublicacaoDTO {

	private Long id;
	private UsuarioDTO usuario;
	private String dataPublicacao;
	private String descricao;
	private String contentType;
	private List<ComentarioDTO> comentarios;
	private Integer ncurtidas;
	private Integer ncomentarios;
	private String imagemBase64;
	private Boolean criacaoConta;

	public PublicacaoDTO(Publicacao publi) {
		this.id = publi.getId();
		this.usuario = new UsuarioDTO(publi.getUsuario());
		this.dataPublicacao = new SimpleDateFormat("dd :1 MMMM :2 y", new Locale("pt", "BR"))
				.format(publi.getDataPublicacao()).replace(":1", "de").replace(":2", "de");
		this.descricao = publi.getDescricao();
		this.contentType = publi.getContentType();
		this.comentarios = ComentarioDTO.listar(publi.getComentarios());
		this.ncurtidas = publi.getCurtidas().size();
		this.ncomentarios = publi.getComentarios().size();
		this.imagemBase64 = publi.getImagemBase64();
		this.criacaoConta = publi.getCriacaoConta();
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

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}

	public Integer getNcurtidas() {
		return ncurtidas;
	}

	public void setNcurtidas(Integer ncurtidas) {
		this.ncurtidas = ncurtidas;
	}

	public Integer getNcomentarios() {
		return ncomentarios;
	}

	public void setNcomentarios(Integer ncomentarios) {
		this.ncomentarios = ncomentarios;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public Boolean getCriacaoConta() {
		return criacaoConta;
	}

	public void setCriacaoConta(Boolean criacaoConta) {
		this.criacaoConta = criacaoConta;
	}

	public static List<PublicacaoDTO> listar(List<Publicacao> list) {
		return list.stream().map(PublicacaoDTO::new).collect(Collectors.toList());
	}

}
