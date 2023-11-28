package com.facebook.jsf.rest;

import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facebook.jsf.form.ComentarioForm;
import com.facebook.jsf.modelo.Comentario;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.ComentarioRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.service.PublicacaoService;

@Path("/comentario")
public class ComentarioController {
	
	@EJB
	private ComentarioRepository repository;
	
	@EJB
	private UsuarioRepository usuarioRepository;
	
	@EJB
	private PublicacaoService publicacaoService;
	
	@POST
	@Path("/comentar")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response comentar(ComentarioForm form) {
		Usuario usuario = usuarioRepository.findById(form.getIdUsuarioLogado());
		Publicacao publicacao = publicacaoService.findById(form.getIdPublicacao());
		
		Comentario comentario = new Comentario();
		comentario.setTexto(form.getTexto());
		comentario.setUsuario(usuario);
		comentario.setPublicacao(publicacao);
		comentario.setDataPublicacao(new Date());
		repository.save(comentario);
		
		return Response.status(201).build();
	}
	
}
