package com.facebook.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.facebook.jsf.modelo.Produto;
import com.facebook.jsf.repository.ProdutoRepository;

@Stateless
public class ProdutoDao implements ProdutoRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Produto findById(Long id) {
		return this.em.find(Produto.class, id);
	}

	@Override
	public void salvar(Produto produto) {
		this.em.persist(produto);
	}

	@Override
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	@Override
	public void deletar(Produto produto) {
		this.em.remove(produto);
	}

	@Override
	public List<Produto> getAll() {
		String sql = "SELECT p FROM Produto p";
		List<Produto> list = this.em.createQuery(sql, Produto.class).getResultList();
		return list;
	}

	@Override
	public List<Produto> getAllByUsuarioId(Long id) {
		String sql = "SELECT p FROM Produto p WHERE p.usuario.id = :id";
		List<Produto> list = this.em.createQuery(sql, Produto.class).setParameter("id", id).getResultList();
		return list;
	}

	@Override
	public List<Produto> getAllByCategoriaId(Long id) {
		String sql = "SELECT p FROM Produto p WHERE p.categoria.id = :id";
		List<Produto> list = this.em.createQuery(sql, Produto.class).setParameter("id", id).getResultList();
		return list;
	}

	@Override
	public Produto findByToken(String token) {
		String sql = "SELECT p FROM Produto p WHERE p.token = :token";
		Produto produto = this.em.createQuery(sql, Produto.class).setParameter("token", token).getSingleResult();
		return produto;
	}

	@Override
	public List<Produto> getAll(Integer inicio, Integer fim) {
		String sql = "SELECT p FROM Produto p";

		TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class);

		if (inicio != null)
			query.setFirstResult(inicio);

		if (fim != null)
			query.setMaxResults(fim);

		return query.getResultList();
	}

	@Override
	public List<Produto> getAllByUsuarioId(Long id, Integer inicio, Integer fim) {
		String sql = "SELECT p FROM Produto p WHERE p.usuario.id = :id";

		TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class).setParameter("id", id);

		if (inicio != null)
			query.setFirstResult(inicio);

		if (fim != null)
			query.setMaxResults(fim);

		return query.getResultList();
	}

	@Override
	public List<Produto> getAllByCategoriaId(Long id, Integer inicio, Integer fim) {
		String sql = "SELECT p FROM Produto p WHERE 1=1 ";

		if (id != null) {
			sql += " AND p.categoria.id = :id ";
		}

		TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class);

		if (id != null) {
			query.setParameter("id", id);
		}

		if (inicio != null)
			query.setFirstResult(inicio);

		if (fim != null)
			query.setMaxResults(fim);

		return query.getResultList();
	}

	@Override
	public List<Produto> getAllByCategoriaNome(String nome, Integer inicio, Integer fim) {
		String sql = "SELECT p FROM Produto p WHERE 1=1 ";
		sql += " AND p.categoria.url = :nome ";

		TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class).setParameter("nome", nome);

		if (inicio != null)
			query.setFirstResult(inicio);

		if (fim != null)
			query.setMaxResults(fim);

		return query.getResultList();
	}

	@Override
	public List<Produto> getAllByCidadeAndCategoriaNome(String cidade, String categoria, Integer inicio, Integer fim) {
		String sql = "SELECT p FROM Produto p WHERE 1=1 ";

		if (categoria != null) {
			sql += " AND p.categoria.url = :nome ";
		}

		if (cidade != null) {
			sql += " AND p.cidadeQuery = :cidade ";
		}

		TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class);
				
		if(categoria != null)
			query.setParameter("nome", categoria);

		if (cidade != null)
			query.setParameter("cidade", cidade);

		if (inicio != null)
			query.setFirstResult(inicio);

		if (fim != null)
			query.setMaxResults(fim);

		return query.getResultList();
	}

}
