package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.Chat;

public interface ChatRepository {

	public void salvar(Chat chat);
	
	public void deletar(Chat chat);
	
	public List<Chat> getAllMensagens(Long usuario1, Long usario2);
	
}
