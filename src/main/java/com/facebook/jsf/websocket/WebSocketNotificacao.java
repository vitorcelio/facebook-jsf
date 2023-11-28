package com.facebook.jsf.websocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.facebook.jsf.websocket.encoder_decoder.MensagemDecoder;
import com.facebook.jsf.websocket.encoder_decoder.MensagemEncoder;
import com.facebook.jsf.websocket.model.Mensagem;

@ServerEndpoint(value = "/notificacao/{usuario}", 
				encoders = { MensagemEncoder.class }, 
				decoders = { MensagemDecoder.class })
public class WebSocketNotificacao {

	private static Queue<Session> sessions = new ConcurrentLinkedQueue<>();

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig, @PathParam("usuario") String usuario) {
		System.out.println("Conexão aberta");
		session.getUserProperties().put("usuario", usuario);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException {
		System.out.println("Conexão fechada");
		sessions.remove(session);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("Erro");
		throwable.printStackTrace();
	}

	@OnMessage
	public void onMessage(Session session, Mensagem mensagem) throws IOException, EncodeException {
		System.out.println("Mensagem recebida: " + mensagem);

		String usuario = (String) session.getUserProperties().get("usuario");

		for (Session session2 : session.getOpenSessions()) {
			if (usuario.equals(session2.getUserProperties().get("usuario")) && (session2.isOpen())) {
				session2.getBasicRemote().sendObject(mensagem);
			}
		}
	}

}
