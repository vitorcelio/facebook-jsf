package com.facebook.jsf.repository;

import java.util.List;
import java.util.Set;

import com.facebook.jsf.modelo.Amigo;
import com.facebook.jsf.modelo.Usuario;

public interface AmigoRepository {
	
	public void save(Amigo amigo);
	
	public void update(Amigo amigo);
	
	public void remove(Amigo amigo);
	
	public Amigo findById(Long id);
	
	public Set<Amigo> getAllByIdUsuario(Long id);
	
	public List<Usuario> getAllListByIdUsuario(Long id);
	
	public List<Usuario> getAllListByIdUsuario(Long id, Integer inicio, Integer quantidade);
	
}
