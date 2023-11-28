package com.facebook.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.facebook.jsf.modelo.Categoria;
import com.facebook.jsf.repository.CategoriaRepository;

@Stateless
public class CategoriaDao implements CategoriaRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Categoria findById(Long id) {
		return this.em.find(Categoria.class, id);
	}

	@Override
	public List<Categoria> getAll() {
		String sql = "SELECT ca FROM Categoria ca";
		return this.em.createQuery(sql, Categoria.class).getResultList();
	}

	@Override
	public List<SelectItem> getAllSelectItem() {
		List<SelectItem> selectItems = new ArrayList<>();
		
		String sql = "SELECT ca FROM Categoria ca";
		List<Categoria> categorias = this.em.createQuery(sql, Categoria.class).getResultList();
		
		categorias.forEach(categoria -> {
			selectItems.add(new SelectItem(categoria.getId(), categoria.getNome()));
		});
		
		return selectItems;
	}

	@Override
	public Categoria findByCategoria(String categoria) {
		String sql = "SELECT c FROM Categoria c WHERE c.url = :categoria";
		Categoria cate = null;
		
		try {
			cate = this.em.createQuery(sql, Categoria.class)
					.setParameter("categoria", categoria)
					.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return cate != null ? cate : null;
	}

}
