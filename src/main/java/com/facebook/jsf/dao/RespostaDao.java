package com.facebook.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.facebook.jsf.modelo.Resposta;
import com.facebook.jsf.repository.RespostaRepository;

@Stateless
public class RespostaDao implements RespostaRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Override
	public void save(Resposta r) {
		// TODO Auto-generated method stub
		em.persist(r);
	}

	@Override
	public void update(Resposta r) {
		// TODO Auto-generated method stub
		em.merge(r);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("DELETE FROM Resposta r WHERE r.id = :id").setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Resposta> getAllRespostasByComentarioId(Long id) {
		List<Resposta> list = em.createQuery("SELECT r FROM Resposta r WHERE r.comentario.id = :id", Resposta.class)
				.setParameter("id", id).getResultList();
		return list;
	}

}
