package com.facebook.jsf.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facebook.jsf.form.ChatForm;
import com.facebook.jsf.modelo.Chat;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.ChatRepository;
import com.facebook.jsf.repository.UsuarioRepository;

@Path("/chat")
public class ChatController {
	
	@EJB
	private ChatRepository repository;
	
	@EJB
	private UsuarioRepository usuarioRepository;
	
	@Path("/salvar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarMensagens(ChatForm form) {
		Usuario usuarioPrincipal = usuarioRepository.findById(form.getIdPrincipal());
		Usuario usuarioSecundario = usuarioRepository.findById(form.getIdSecundario());
		
		Chat chat = new Chat();
		chat.setMensagem(form.getMensagem());
		chat.setUsuarioPrincipal(usuarioPrincipal);
		chat.setUsuarioSecundario(usuarioSecundario);
		repository.salvar(chat);
		
		return Response.status(201).build();
	}
}
