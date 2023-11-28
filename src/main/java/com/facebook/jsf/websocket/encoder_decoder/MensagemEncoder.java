package com.facebook.jsf.websocket.encoder_decoder;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.facebook.jsf.websocket.model.Mensagem;

public class MensagemEncoder implements Encoder.Text<Mensagem>{

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public String encode(Mensagem mensagem) throws EncodeException {
		JsonObject jsonObject = Json.createObjectBuilder()
			.add("mensagem", mensagem.getMensagem())
			.add("data", mensagem.getData())
			.add("username", mensagem.getUsername())
			.add("nome", mensagem.getNome())
			.add("imagem", mensagem.getImagem())
			.build();
		
		StringWriter sw = new StringWriter();
		Json.createWriter(sw).writeObject(jsonObject);
		return sw.toString();
	}

}
