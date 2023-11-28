package com.facebook.jsf.repository;

import java.util.List;

import javax.faces.model.SelectItem;

import com.facebook.jsf.modelo.Categoria;

public interface CategoriaRepository {
	public Categoria findById(Long id);
	public Categoria findByCategoria(String categoria);

	public List<Categoria> getAll();
	
	public List<SelectItem> getAllSelectItem();
}
