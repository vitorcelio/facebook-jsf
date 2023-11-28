package com.facebook.jsf.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.facebook.jsf.dto.PublicacaoDTO;
import com.facebook.jsf.form.PublicacaoForm;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.service.PublicacaoService;
import com.facebook.jsf.utils.FacebookUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class PublicarBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PublicacaoForm form = new PublicacaoForm();

	private List<PublicacaoDTO> publicacaoes;

	@Inject
	private PublicacaoService publicacaoService;

	private Integer paginacao = 5;

	@PostConstruct
	public void carregaPublicacoes() {
		FacebookUtils.removerObjetoDaSessao("paginacao");
		FacebookUtils.removerObjetoDaSessao("paginacaoperfil");
		
		List<Publicacao> publicacaoesAmigos = publicacaoService
				.getAllAmigosByIdPaginado(LoginBean.getUsuarioLogado().getId(), 0, paginacao);
		publicacaoes = PublicacaoDTO.listar(publicacaoesAmigos);
	}

	public void toPublicacao(Boolean redirecionar) throws IOException {
		publicacaoService.salvar(form, LoginBean.getUsuarioLogado().getId());
		form = new PublicacaoForm();

		if (redirecionar) {
			redirecionarPerfil(LoginBean.getUsuarioLogado().getUsername());
		}

	}
	
	public void redirecionarPerfil(String username) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/" + username);
	}

	public PublicacaoForm getForm() {
		return form;
	}

	public void setForm(PublicacaoForm form) {
		this.form = form;
	}

	public List<PublicacaoDTO> getPublicacaoes() {
		return publicacaoes;
	}

	public void setPublicacaoes(List<PublicacaoDTO> publicacaoes) {
		this.publicacaoes = publicacaoes;
	}

	public Integer getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Integer paginacao) {
		this.paginacao = paginacao;
	}

}
