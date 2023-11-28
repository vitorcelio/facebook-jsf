package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.Comentario;

public interface ComentarioRepository {
	
	public void save(Comentario c);
	
	public void update(Comentario c);
	
	public void remove(Long id);
	
	public List<Comentario> getAllByPublicacaoId(Long id);
	
}
