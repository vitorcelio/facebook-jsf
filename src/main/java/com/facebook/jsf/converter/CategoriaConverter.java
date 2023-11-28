package com.facebook.jsf.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.facebook.jsf.modelo.Categoria;
import com.facebook.jsf.repository.CategoriaRepository;

@FacesConverter(value = "categoriaConverter", forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria>, Serializable {

	private static final long serialVersionUID = -8644725106629718426L;
	
	@Inject
	private CategoriaRepository categoriaDao;

	@Override
	public Categoria getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Categoria categoria = null;
		
		try {
			categoria = categoriaDao.findById(Long.parseLong(arg2));
		} catch (Exception e) {
			System.err.println("Erro ao consultar categoria");
		}
		
		return categoria;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Categoria arg2) {
		if(arg2 == null) {
			return null;
		} else if(arg2 instanceof Categoria) {
			return arg2.getId().toString();
		} else {
			return arg2.toString();
		}
	}

}
