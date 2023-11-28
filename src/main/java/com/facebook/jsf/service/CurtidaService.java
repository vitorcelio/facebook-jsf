package com.facebook.jsf.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook.jsf.modelo.Curtida;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.CurtidaRepository;
import com.facebook.jsf.repository.UsuarioRepository;

@Stateless
public class CurtidaService {

	@Inject
	private CurtidaRepository repository;

	@Inject
	private PublicacaoService publicacaoService;

	@Inject
	private UsuarioRepository usuarioRepository;

	public void curtir(Long idPost, Long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario);
		Publicacao publicacao = publicacaoService.findById(idPost);
		Curtida curtida = new Curtida();
		curtida.setPublicacao(publicacao);
		curtida.setUsuario(usuario);
		
		try {
			repository.save(curtida);
//			FacebookUtils.mostrarMsg("Você curtiu uma publicação de " + publicacao.getUsuario().getNome() + "!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void descurtir(Long idPost, Long idUsuario) {
//		Publicacao publicacao = publicacaoService.findById(idPost);
		
		try {
			repository.remove(idPost, idUsuario);
//			FacebookUtils.mostrarMsg("Você descurtiu uma publicação de " + publicacao.getUsuario().getNome() + "!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Boolean verificarCurtida(Long idUsuario, Long idPost) {
		return repository.findByUsuarioAndPublicacao(idUsuario, idPost);
	}

	public Integer getAllByIdPublicacao(Long id) {
		return repository.numeroCurtidaByIdPublicacao(id);
	}

}
