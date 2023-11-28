package com.facebook.jsf.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.SolicitacaoRepository;
import com.facebook.jsf.repository.UsuarioRepository;

@Path("/friends")
public class FriendsController {

	private static Logger log = Logger.getLogger(FriendsController.class);

	@EJB
	private UsuarioRepository repository;

	@EJB
	private SolicitacaoRepository solicitacaoRepository;

	@Path("/solicitacao/{idUsuario}/{idUsuarioLogado}")
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response enviarSolicitacao(@PathParam("idUsuario") Long idOutro, @PathParam("idUsuarioLogado") Long id) {
		Usuario usuario = repository.findById(idOutro);
		Usuario usuarioLogado = repository.findById(id);

		if (usuario != null && usuarioLogado != null) {
			if (solicitacaoRepository.enviada(id, idOutro)) {
				log.info("Solicitação já foi enviada");
				return Response.ok().build();
			} else {
				solicitacaoRepository.enviarSolicitacao(idOutro, id);
				log.info("A solicitação foi enviada com sucesso!");
			}
		}

		return Response.status(201).build();
	}

	@Path("/retirarsolicitacao/{idUsuario}/{idUsuarioLogado}")
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response cancelarSolicitacao(@PathParam("idUsuario") Long idOutro, @PathParam("idUsuarioLogado") Long id) {
		Usuario usuario = repository.findById(idOutro);
		Usuario usuarioLogado = repository.findById(id);

		if (usuario != null && usuarioLogado != null) {
			if (!solicitacaoRepository.enviada(id, idOutro)) {
				log.info("Solicitação já foi retirada!");
				return Response.ok().build();
			} else {
				solicitacaoRepository.retirarSolicitacao(idOutro, id);
				log.info("A solicitação foi retirada com sucesso!");
			}
		}

		return Response.status(201).build();
	}

}
