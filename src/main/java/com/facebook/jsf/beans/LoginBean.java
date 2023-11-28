package com.facebook.jsf.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Model
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository dao;
	private String usernameEmail;
	private String senha;

	public void login() throws IOException {
		FacebookUtils.removerObjetoDaSessao("paginacao");
		FacebookUtils.removerObjetoDaSessao("paginacaoperfil");
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		Usuario usuario = dao.login(usernameEmail, senha);
		
		if(usuario != null && usuario.getId() != null) {
			externalContext.getSessionMap().put("usuarioLogado", usuario);
			redirecionarPosLogin("/home");
		} else {
			FacebookUtils.mostrarMsg("A senha inserida está incorreta.");
		}
	}

	public static Usuario getUsuarioLogado() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return (Usuario) externalContext.getSessionMap().get("usuarioLogado");
	}
	
	public void redirecionarPosLogin(String pagina) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
	}

	public String getUsernameEmail() {
		return usernameEmail;
	}

	public void setUsernameEmail(String usernameEmail) {
		this.usernameEmail = usernameEmail;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
