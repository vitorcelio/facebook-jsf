package com.facebook.jsf.repository;

import com.facebook.jsf.modelo.Curtida;

public interface CurtidaRepository {
	
	public void save(Curtida curtida);
	
	public void remove(Long idPost, Long idusuario);
	
	public Boolean findByUsuarioAndPublicacao(Long idUsuario, Long idPost);
	
	public Integer numeroCurtidaByIdPublicacao(Long idPublicacao);
	
	public Curtida findById(Long id);
	
}
