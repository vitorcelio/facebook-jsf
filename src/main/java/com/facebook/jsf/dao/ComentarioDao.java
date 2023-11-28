package com.facebook.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.facebook.jsf.modelo.Comentario;
import com.facebook.jsf.repository.ComentarioRepository;

@Stateless
public class ComentarioDao implements ComentarioRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Override
	public void save(Comentario c) {
//		Publicacao publicacao = c.getPublicacao();
		em.persist(c);
//		FacebookUtils.mostrarMsg("Você fez um comentário em uma publicação de " + publicacao.getUsuario().getNome() + "!");
	}

	@Override
	public void update(Comentario c) {
		em.merge(c);
	}

	@Override
	public void remove(Long id) {
		Query query = em.createQuery("DELETE FROM Comentario c WHERE c.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Comentario> getAllByPublicacaoId(Long id) {
		List<Comentario> list = em
				.createQuery("SELECT c FROM Comentario c WHERE c.publicacao.id = :id", Comentario.class)
				.setParameter("id", id).getResultList();
		return list;
	}

}
