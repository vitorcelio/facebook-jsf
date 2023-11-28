package com.facebook.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.facebook.jsf.beans.LoginBean;
import com.facebook.jsf.modelo.Amigo;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.PublicacaoRepository;

@Stateless
public class PublicacaoDao implements PublicacaoRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Override
	public void save(Publicacao p) {
		em.persist(p);
	}

	@Override
	public void update(Publicacao p) {
		em.merge(p);
	}

	@Override
	public void remove(Publicacao p) {
		em.remove(p);
	}

	@Override
	public Publicacao findById(Long id) {
		return em.find(Publicacao.class, id);
	}

	@Override
	public List<Publicacao> getAllByIdUsuario(String username) {
		String sql = "SELECT p FROM Publicacao p WHERE p.usuario.username = :username";
		return em.createQuery(sql, Publicacao.class).setParameter("username", username).getResultList();
	}
	
	@Override
	public List<Publicacao> getAllByIdUsuario(Long id) {
		String sql = "SELECT p FROM Publicacao p WHERE p.usuario.id = :id";
		return em.createQuery(sql, Publicacao.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public List<Publicacao> getAllByIdUsuarioPaginada(Long id, Integer inicio, Integer ultimo) {
		String sql = "SELECT p FROM Publicacao p WHERE p.usuario.id = :id";
		TypedQuery<Publicacao> query = em.createQuery(sql, Publicacao.class).setParameter("id", id);
		
		if(inicio != null) {
			query.setFirstResult(inicio);
		}
		
		if(ultimo != null) {
			query.setMaxResults(ultimo);
		}
		
		return query.getResultList();
	}
	
	@Override
	public List<Publicacao> getAllAmigosById(Long id) {
		String sqlAmigos = "SELECT a FROM Amigo a WHERE a.usuario.id = :id";
		List<Amigo> listaDeAmigos = em.createQuery(sqlAmigos, Amigo.class).setParameter("id", id).getResultList();

		List<String> listaUsernamesAmigos = new ArrayList<>();
		listaUsernamesAmigos.add(LoginBean.getUsuarioLogado().getUsername());
		
		for (Amigo amigo : listaDeAmigos) {
			Usuario usuario = em.find(Usuario.class, amigo.getIdUsuarioAmigo());
			listaUsernamesAmigos.add(usuario.getUsername());
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p FROM Publicacao p ");
		sql.append(" WHERE p.usuario.username IN (:amigos) ");
		sql.append(" ORDER BY p.dataPublicacao DESC ");
		TypedQuery<Publicacao> query = em.createQuery(sql.toString(), Publicacao.class);
		query.setParameter("amigos", listaUsernamesAmigos);
		return query.getResultList();
	}

	@Override
	public List<Publicacao> getAllAmigosByIdPaginado(Long id, Integer primeiro, Integer ultimo) {
		String sqlAmigos = "SELECT a FROM Amigo a WHERE a.usuario.id = :id";
		List<Amigo> listaDeAmigos = em.createQuery(sqlAmigos, Amigo.class).setParameter("id", id).getResultList();

		List<String> listaUsernamesAmigos = new ArrayList<>();
		listaUsernamesAmigos.add(LoginBean.getUsuarioLogado().getUsername());
		
		for (Amigo amigo : listaDeAmigos) {
			Usuario usuario = em.find(Usuario.class, amigo.getIdUsuarioAmigo());
			listaUsernamesAmigos.add(usuario.getUsername());
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p FROM Publicacao p ");
		sql.append(" WHERE p.usuario.username IN (:amigos) ");
		sql.append(" ORDER BY p.dataPublicacao DESC ");
		TypedQuery<Publicacao> query = em.createQuery(sql.toString(), Publicacao.class);
		query.setParameter("amigos", listaUsernamesAmigos);
		
		if(primeiro != null) {
			query.setFirstResult(primeiro);
		}
		
		if(ultimo != null) {
			query.setMaxResults(ultimo);
		}
		
		return query.getResultList();
	}
	
}
