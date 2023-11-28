package com.facebook.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.facebook.jsf.modelo.Curtida;
import com.facebook.jsf.repository.CurtidaRepository;

@Stateless
public class CurtidaDao implements CurtidaRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Curtida curtida) {
		em.persist(curtida);
	}

	@Override
	public void remove(Long idPost, Long idUsuario) {
		Query query = em
				.createQuery("DELETE FROM Curtida c WHERE c.usuario.id = :idUsuario AND c.publicacao.id = :idPost")
				.setParameter("idUsuario", idUsuario).setParameter("idPost", idPost);

		query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean findByUsuarioAndPublicacao(Long idUsuario, Long idPost) {
		String sql = "SELECT * FROM Curtida c WHERE c.usuario_id = :idUsuario AND c.publicacao_id = :idPost";
		List listCurtida = em.createNativeQuery(sql).setParameter("idUsuario", idUsuario)
				.setParameter("idPost", idPost).getResultList();
		
		try {
			listCurtida.get(0);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public Integer numeroCurtidaByIdPublicacao(Long idPublicacao) {
		String sql = "SELECT c FROM Curtida c WHERE c.publicacao.id = :id";
		return em.createQuery(sql, Curtida.class).setParameter("id", idPublicacao).getResultList().size();
	}
	
	@Override
	public Curtida findById(Long id) {
		return em.find(Curtida.class, id);
	}

}
