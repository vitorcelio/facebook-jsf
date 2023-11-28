package com.facebook.jsf.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook.jsf.beans.LoginBean;
import com.facebook.jsf.form.ComentarioForm;
import com.facebook.jsf.modelo.Comentario;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.repository.ComentarioRepository;

@Stateless
public class ComentarioService {
	
	@Inject
	private ComentarioRepository repository;
	
	@Inject
	private PublicacaoService publicacaoService;
	
	public void salvar(ComentarioForm form) {
		Publicacao publicacao = publicacaoService.findById(form.getIdPublicacao());
		
		try {
			Comentario comentario = new Comentario();
			comentario.setTexto(form.getTexto());
			comentario.setUsuario(LoginBean.getUsuarioLogado());
			comentario.setDataPublicacao(new Date());
			comentario.setPublicacao(publicacao);
			repository.save(comentario);
//			FacebookUtils.mostrarMsg("Você comentou na publicacão " + publicacao.getUsuario().getNome() + "!");
		} catch (Exception e) {
			// TODO: handle exception
//			FacebookUtils.mostrarMsg("Houve um erro ao tentar comentar!");
		}
	}
}
