package com.facebook.jsf.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;

import com.facebook.jsf.beans.LoginBean;
import com.facebook.jsf.form.PublicacaoForm;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.PublicacaoRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Stateless
public class PublicacaoService {

	@Inject
	private PublicacaoRepository dao;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	public void salvar(PublicacaoForm form, Long id) {
		
		try {
			Usuario usuario = usuarioRepository.findById(id);

			String contentType = null;
			String imagemBase64 = null;

			if (form.getImagem() != null) {
				contentType = form.getImagem().getContentType();
				imagemBase64 = FacebookUtils.getImagemBase64(form.getImagem());
			}
			
			Publicacao publicacao = new Publicacao();
			publicacao.setUsuario(usuario);
			publicacao.setContentType(contentType);
			publicacao.setDataPublicacao(new Date());
			publicacao.setDescricao(form.getDescricao() != null ? form.getDescricao() : null);
			publicacao.setImagemBase64(imagemBase64);
			publicacao.setCriacaoConta(false);
			dao.save(publicacao);
			FacebookUtils.mostrarMsg("Publicação feita com sucesso!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacebookUtils.mostrarMsg("Erro ao publicar!");
		}
	}

	public void atualizar(Long id, String descricao, Part arquivo) {
		Publicacao p = dao.findById(id);

		if (p.getUsuario().equals(LoginBean.getUsuarioLogado())) {

			if (arquivo != null) {
			}

			Publicacao publicacao = new Publicacao();
			publicacao.setUsuario(LoginBean.getUsuarioLogado());
			publicacao.setDescricao(descricao);

			dao.save(publicacao);
		}
	}
	
	public void deletar(Long id) {
		Publicacao p = dao.findById(id);
		if(p.getUsuario().equals(LoginBean.getUsuarioLogado())) {
			dao.remove(p);
		}
	}
	
	public List<Publicacao> getAllByUsuario(Long id) {
		return dao.getAllByIdUsuario(id);
	}
	
	public List<Publicacao> getAllByUsuario(String username) {
		return dao.getAllByIdUsuario(username);
	}
	
	public List<Publicacao> getAllByUsuarioPaginada(Long id, Integer inicio, Integer fim) {
		return dao.getAllByIdUsuarioPaginada(id, inicio, fim);
	}
	
	public List<Publicacao> getAllAmigosById(Long id) {
		return dao.getAllAmigosById(id);
	}
	
	public List<Publicacao> getAllAmigosByIdPaginado(Long id, Integer inicio, Integer fim) {
		return dao.getAllAmigosByIdPaginado(id, inicio, fim);
	}
	
	public Publicacao findById(Long id) {
		return dao.findById(id);
	}

}
