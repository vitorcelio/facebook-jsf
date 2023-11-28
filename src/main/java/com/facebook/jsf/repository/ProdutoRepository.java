package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.Produto;

public interface ProdutoRepository {

	public Produto findById(Long id);

	public void salvar(Produto produto);

	public void atualizar(Produto produto);

	public void deletar(Produto produto);

	public List<Produto> getAll();
	
	public List<Produto> getAll(Integer inicio, Integer fim);

	public List<Produto> getAllByUsuarioId(Long id);
	
	public List<Produto> getAllByUsuarioId(Long id, Integer inicio, Integer fim);

	public List<Produto> getAllByCategoriaId(Long id);
	
	public List<Produto> getAllByCategoriaId(Long id, Integer inicio, Integer fim);
	
	public List<Produto> getAllByCategoriaNome(String nome, Integer inicio, Integer fim);
	
	public List<Produto> getAllByCidadeAndCategoriaNome(String cidade, String categoria, Integer inicio, Integer fim);

	public Produto findByToken(String token);

}
