package com.facebook.jsf.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facebook.jsf.service.CurtidaService;

@Path("/curtida")
public class CurtidaController {

	@EJB
	private CurtidaService service;
	
	@POST
	@Path("/{idUsuario}/{idPost}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response curtir(@PathParam("idUsuario") Long idUsuario, @PathParam("idPost") Long id) {
		
		if (service.verificarCurtida(idUsuario, id)) {
			service.descurtir(id, idUsuario);
			return Response.ok().build();
		}

		service.curtir(id, idUsuario);
		return Response.status(201).build();
	}

}
