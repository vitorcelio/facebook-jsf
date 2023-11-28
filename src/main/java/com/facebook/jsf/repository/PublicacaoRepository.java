package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.Publicacao;

public interface PublicacaoRepository {
	
	public void save(Publicacao p);
	
	public void update(Publicacao p);
	
	public void remove(Publicacao p);
	
	public Publicacao findById(Long id);
	
	public List<Publicacao> getAllByIdUsuario(Long id);
	
	public List<Publicacao> getAllAmigosById(Long id);
	
	public List<Publicacao> getAllAmigosByIdPaginado(Long id, Integer primeiro, Integer ultimo);

	public List<Publicacao> getAllByIdUsuario(String username);
	
	public List<Publicacao> getAllByIdUsuarioPaginada(Long id, Integer inicio, Integer ultimo);
	
}
