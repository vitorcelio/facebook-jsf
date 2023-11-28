package com.facebook.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.facebook.jsf.modelo.Chat;
import com.facebook.jsf.repository.ChatRepository;

@Stateless
public class ChatDao implements ChatRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Override
	public void salvar(Chat chat) {
		em.persist(chat);
	}

	@Override
	public void deletar(Chat chat) {
		em.remove(chat);
	}

	@Override
	public List<Chat> getAllMensagens(Long usuario1, Long usuario2) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ch FROM Chat ch WHERE 1=1 AND ");
		sql.append(" (ch.usuarioPrincipal.id = :usuario1 AND ch.usuarioSecundario.id = :usuario2) OR ");
		sql.append(" (ch.usuarioPrincipal.id = :usuario2 AND ch.usuarioSecundario.id = :usuario1) ");

		List<Chat> mensagens = new ArrayList<>();
		try {
			mensagens = em.createQuery(sql.toString(), Chat.class)
					.setParameter("usuario1", usuario1)
					.setParameter("usuario2", usuario2).getResultList();
		} catch (Exception e) {
			System.out.println("Não foi encontrada nenhuma mensagem");
		}

		return mensagens;
	}

}
