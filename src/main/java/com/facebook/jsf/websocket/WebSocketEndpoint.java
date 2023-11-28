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

@ServerEndpoint(value = "/chatprivado/{usuario1}/{usuario2}", 
				encoders = { MensagemEncoder.class }, 
				decoders = { MensagemDecoder.class })
public class WebSocketEndpoint {

	private static Queue<Session> sessions = new ConcurrentLinkedQueue<>();

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig, @PathParam("usuario1") String usuario1,
			@PathParam("usuario2") String usuario2) {
		System.out.println("Conexão aberta");
		session.getUserProperties().put("usuario1", usuario1);
		session.getUserProperties().put("usuario2", usuario2);
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

		String usuario1 = (String) session.getUserProperties().get("usuario1");
		String usuario2 = (String) session.getUserProperties().get("usuario2");

		for (Session session2 : session.getOpenSessions()) {
			if (usuario1.equals(session2.getUserProperties().get("usuario1"))
					&& usuario2.equals(session2.getUserProperties().get("usuario2")) && (session2.isOpen())) {
				session2.getBasicRemote().sendObject(mensagem);
			}
		}
	}

}
