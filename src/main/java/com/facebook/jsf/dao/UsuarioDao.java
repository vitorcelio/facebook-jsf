package com.facebook.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.facebook.jsf.modelo.Amigo;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Stateless
public class UsuarioDao implements UsuarioRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Override
	public void save(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public void update(Usuario usuario) {
		em.merge(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		em.remove(usuario);
	}

	@Override
	public List<Usuario> getAll() {
		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}

	@Override
	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public Usuario findByUsername(String username) {
		String sql = "SELECT u FROM Usuario u WHERE u.username = :username";

		TypedQuery<Usuario> queryUsername = em.createQuery(sql, Usuario.class).setParameter("username", username);

		try {
			return queryUsername.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Usuario> buscarPorNome(String nome) {
		String sql = "SELECT u FROM Usuario u, Amigo a WHERE UPPER(TRANSLATE(u.nome,'ÀÁáàÉÈéèÍíÓóÒòÚú','AAaaEEeeIiOoOoUu')) LIKE UPPER(:nome)";

		List<Usuario> list = em.createQuery(sql, Usuario.class).setParameter("nome", "%" + FacebookUtils.removeAcentuacao(nome)  + "%").getResultList();
		return list;

	}

	@Override
	public List<Usuario> buscarPorNomeAmigos(String nome, Long idUsuario) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT u FROM Usuario u, Amigo a ");
		sql.append(" WHERE UPPER(u.nome) LIKE UPPER(:nome) ");
		sql.append(" and a.idUsuarioAmigo = u.id ");
		sql.append(" and a.usuario.id = :id ");

		List<Usuario> list = em.createQuery(sql.toString(), Usuario.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("id", idUsuario)
				.getResultList();
		return list;
	}

	@Override
	public Usuario login(String usernameOuEmail, String senha) {
		StringBuilder sqlLogin = new StringBuilder();
		sqlLogin.append(" SELECT u FROM Usuario u ");
		sqlLogin.append(" WHERE (u.username = :usernameEmail OR u.email = :usernameEmail) ");
		sqlLogin.append(" AND u.senha = :senha ");

		TypedQuery<Usuario> queryLogin = em.createQuery(sqlLogin.toString(), Usuario.class);
		queryLogin.setParameter("usernameEmail", usernameOuEmail);
		queryLogin.setParameter("senha", senha);

		try {
			return queryLogin.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public List<Usuario> listarSugestoes(Long id, Boolean limiteDeItens) {

		List<Amigo> amigosDoUsuario = em.createQuery("SELECT a FROM Amigo a WHERE a.usuario.id = :id", Amigo.class)
				.setParameter("id", id).getResultList();

		List<String> listUsernameAmigos = new ArrayList<>();

		for (Amigo amigo : amigosDoUsuario) {
			Usuario usuario = em.find(Usuario.class, amigo.getIdUsuarioAmigo());
			listUsernameAmigos.add(usuario.getUsername());
		}

		String sql = " SELECT u FROM Usuario u WHERE u.id <> :id ";

		if (listUsernameAmigos.size() > 0) {
			sql += " AND u.username NOT IN (:usernamesAmigos) ";
		}

		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class).setParameter("id", id);

		if (listUsernameAmigos.size() > 0) {
			query.setParameter("usernamesAmigos", listUsernameAmigos);
		}

		if (limiteDeItens) {
			query.setFirstResult(0).setMaxResults(12);
		}

		List<Usuario> sugestoes = query.getResultList();
		return sugestoes;
	}

}
