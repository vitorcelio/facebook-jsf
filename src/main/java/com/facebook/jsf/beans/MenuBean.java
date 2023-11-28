package com.facebook.jsf.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@Model
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String uri = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
	private HttpServletRequest url = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
	
	
	public Boolean menu(String ...menuAtual) {
		for(String menuItem : menuAtual) {
			if (uri.equals(menuItem)) {
				return true;
			}			
		}
		return false;
	}
	
	public Boolean menuAmigos(String menuAmigosAtual) {
		if(uri.equals(menuAmigosAtual)) {
			return true;
		}
		return false;
	}
	
	public Boolean menuProdutosCategoria(String menu) {
		try {
			if(url.getParameter("categoria").equals(menu)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Nenhum parametro encontrado!");
		}
		
		return false;
	}

	public void redirecionarMenu(String pagina) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/" + pagina);
	}
	
	public void redirecionarMesssenger(String pagina) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/messages/t/" + pagina);
	}
}
