package com.facebook.jsf.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.facebook.jsf.modelo.Amigo;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.AmigoRepository;
import com.facebook.jsf.repository.UsuarioRepository;

@Stateless
public class AmigoDao implements AmigoRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Inject
	private UsuarioRepository repository;

	@Override
	public Amigo findById(Long id) {
		return em.find(Amigo.class, id);
	}

	@Override
	public Set<Amigo> getAllByIdUsuario(Long id) {
		return new HashSet<Amigo>(
				em.createQuery("SELECT a FROM Amigo a", Amigo.class).setParameter("id", id).getResultList());
	}

	@Override
	public void save(Amigo amigo) {
		em.persist(amigo);
	}

	@Override
	public void update(Amigo amigo) {
		em.merge(amigo);
	}

	@Override
	public void remove(Amigo amigo) {
		em.remove(amigo);
	}

	@Override
	public List<Usuario> getAllListByIdUsuario(Long id) {
		List<Amigo> amigos = em.createQuery("SELECT a FROM Amigo a WHERE a.usuario.id = :id", Amigo.class)
				.setParameter("id", id).getResultList();
		List<Usuario> usuariosAmigos = new ArrayList<>();

		for (Amigo amigo : amigos) {
			Usuario usuario = repository.findById(amigo.getIdUsuarioAmigo());
			usuariosAmigos.add(usuario);
		}

		return usuariosAmigos;
	}

	@Override
	public List<Usuario> getAllListByIdUsuario(Long id, Integer inicio, Integer quantidade) {
		TypedQuery<Amigo> query = em.createQuery("SELECT a FROM Amigo a WHERE a.usuario.id = :id", Amigo.class)
				.setParameter("id", id);

		if(inicio != null) {
			query.setFirstResult(inicio);
		}
		
		if(quantidade != null) {
			query.setMaxResults(quantidade);
		}
		
		List<Amigo> amigos = query.getResultList();
		
		List<Usuario> usuariosAmigos = new ArrayList<>();

		for (Amigo amigo : amigos) {
			Usuario usuario = repository.findById(amigo.getIdUsuarioAmigo());
			usuariosAmigos.add(usuario);
		}

		return usuariosAmigos;
	}

}
