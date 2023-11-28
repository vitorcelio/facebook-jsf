package com.facebook.jsf.websocket.encoder_decoder;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.facebook.jsf.websocket.model.Mensagem;

public class MensagemDecoder implements Decoder.Text<Mensagem> {

	@Override
	public Mensagem decode(String json) throws DecodeException {
		JsonObject object = Json.createReader(new StringReader(json)).readObject();
		String mensagem = object.getString("mensagem");
		String data = object.getString("data");
		String username = object.getString("username");
		String nome = object.getString("nome");
		String imagem = object.getString("imagem");
		
		return new Mensagem(mensagem, data, username, nome, imagem);
	}

	@Override
	public boolean willDecode(String json) {
		try {
			Json.createReader(new StringReader(json)).readObject();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

}
