package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.Usuario;

public interface UsuarioRepository {
	
	public void save(Usuario obj);
	
	public void update(Usuario obj);
	
	public void remove(Usuario obj);
	
	public List<Usuario> getAll();
	
	public Usuario findById(Long id);
	
	public Usuario login(String usernameOuEmail, String senha);
	
	public List<Usuario> listarSugestoes(Long id, Boolean limiteDeItens);
	
	public Usuario findByUsername(String username);
	
	public List<Usuario> buscarPorNome(String nome);
	
	public List<Usuario> buscarPorNomeAmigos(String nome, Long idUsuario);
}
