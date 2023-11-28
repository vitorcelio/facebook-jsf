package com.facebook.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.facebook.jsf.modelo.Usuario;

@WebFilter(urlPatterns = { "/*" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession sessao = req.getSession();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		String url = req.getServletPath();

		if (usuarioLogado != null) {
			chain.doFilter(request, response);
		} else {
			if (url != null && (url.contains("login") || url.contains("resource") || url.contains("register") || url.contains("formulario") || url.contains("/rest"))) {
				chain.doFilter(request, response);
			} else {
				if (req.getRequestURL() != null) {
					RequestDispatcher rd = req.getRequestDispatcher("/login?faces-redirect=true");
					rd.forward(request, response);
				}
			}
		}
	}

}
