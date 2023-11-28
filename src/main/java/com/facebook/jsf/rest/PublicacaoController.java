package com.facebook.jsf.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.facebook.jsf.dto.PublicacaoDTO;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.service.PublicacaoService;

@Path("/publicacao")
public class PublicacaoController {
	
	@EJB
	private PublicacaoService service;
	
	@Path("/{idUsuario}")
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	public Response publicacoes(@PathParam("idUsuario") Long id) {
		List<Publicacao> amigos = service.getAllAmigosById(id);
		return Response.ok(PublicacaoDTO.listar(amigos)).build();
	}
	
	@Path("/imagem/{id}")
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	public String imagemPublicacao(@PathParam("id") Long id) {
		Publicacao publicacao = service.findById(id);
		
		if(publicacao != null) {
			return publicacao.getImagemBase64();
		}
		
		return null;
	}
}
