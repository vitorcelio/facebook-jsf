package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.Resposta;

public interface RespostaRepository {

	public void save(Resposta r);

	public void update(Resposta r);

	public void remove(Long id);

	public List<Resposta> getAllRespostasByComentarioId(Long id);

}
