package com.facebook.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import com.facebook.jsf.modelo.Amigo;
import com.facebook.jsf.modelo.SolicitacaoEnviada;
import com.facebook.jsf.modelo.SolicitacaoRecebida;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.AmigoRepository;
import com.facebook.jsf.repository.SolicitacaoRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Stateless
public class SolicitacaoDao implements SolicitacaoRepository {

	private static Logger log = Logger.getLogger(SolicitacaoDao.class);

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Inject
	private AmigoRepository amigoRepository;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public void enviarSolicitacao(Long id, Long idUsuarioLogado) {

		try {
			verificaAmizade(id, idUsuarioLogado);

		} catch (Exception e) {
			System.out.println("solicitação não encontrada!");
			Usuario usuario = usuarioRepository.findById(id);
			Usuario usuarioLogado = usuarioRepository.findById(idUsuarioLogado);

			SolicitacaoEnviada enviada = new SolicitacaoEnviada(usuarioLogado, id);
			SolicitacaoRecebida recebida = new SolicitacaoRecebida(usuario, idUsuarioLogado);

			em.persist(recebida);
			em.persist(enviada);

			try {
				verificaAmizade(id, idUsuarioLogado);
			} catch (Exception e2) {
				System.out.println("O outro usuário não aceitou a solicitação ainda");
			}
		}
	}

	private void verificaAmizade(Long id, Long idUsuarioLogado) {
		Usuario usuario = usuarioRepository.findById(id);
		Usuario usuarioLogado = usuarioRepository.findById(idUsuarioLogado);

		SolicitacaoEnviada enviadaq = enviada(usuarioLogado, id);
		SolicitacaoRecebida recebidaq = recebida(usuarioLogado, id);
		if (enviadaq != null && recebidaq != null) {

			SolicitacaoEnviada enviadaq2 = enviada(usuario, usuarioLogado.getId());
			SolicitacaoRecebida recebidaq2 = recebida(usuario, usuarioLogado.getId());

			em.remove(enviadaq);
			em.remove(recebidaq);
			em.remove(enviadaq2);
			em.remove(recebidaq2);

			Amigo amigo = new Amigo();
			amigo.setUsuario(usuario);
			amigo.setIdUsuarioAmigo(idUsuarioLogado);

			Amigo euAmigo = new Amigo();
			euAmigo.setUsuario(usuarioLogado);
			euAmigo.setIdUsuarioAmigo(id);

			amigoRepository.save(amigo);
			amigoRepository.save(euAmigo);
		}
	}

	@Override
	public void retirarSolicitacao(Long id, Long idUsuarioLogado) {
		Usuario usuarioLogado = usuarioRepository.findById(idUsuarioLogado);
		Usuario outroUsuario = usuarioRepository.findById(id);
		try {
			SolicitacaoEnviada enviadaq = enviada(usuarioLogado, id);
			SolicitacaoRecebida recebidaq = recebida(outroUsuario, idUsuarioLogado);

			if (enviadaq != null && recebidaq != null) {
				em.remove(enviadaq);
				em.remove(recebidaq);
				FacebookUtils.mostrarMsg("Solicitação de amizade retirada!");
			}
		} catch (Exception e) {
			System.out.println("Não existem solicitações!");
		}
	}

	@Override
	public SolicitacaoEnviada enviada(Usuario usuario, Long id) throws NoResultException {
		String sql = "SELECT s FROM SolicitacaoEnviada s WHERE s.usuario.id = :id AND s.idUsuarioRecebe = :id2";
		return em.createQuery(sql, SolicitacaoEnviada.class).setParameter("id", usuario.getId()).setParameter("id2", id)
				.getSingleResult();
	}

	@Override
	public SolicitacaoRecebida recebida(Usuario usuario, Long id) throws NoResultException {
		String sql = "SELECT s FROM SolicitacaoRecebida s WHERE s.usuario.id = :id AND s.idUsuarioDeEnvio = :id2";

		return em.createQuery(sql, SolicitacaoRecebida.class).setParameter("id", usuario.getId())
				.setParameter("id2", id).getSingleResult();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean enviada(Long idusuario, Long id) {
		String sql = "SELECT * FROM SolicitacaoEnviada s WHERE s.usuario_id = :id AND s.idUsuarioRecebe = :id2";
		List list = em.createNativeQuery(sql).setParameter("id", idusuario).setParameter("id2", id).getResultList();

		try {
			list.get(0);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Solicitação não foi enviada!");
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean recebida(Long idusuario, Long id) {
		String sql = "SELECT * FROM SolicitacaoRecebida s WHERE s.usuario_id = :id AND s.idUsuarioDeEnvio = :id2";

		List list = em.createNativeQuery(sql).setParameter("id", idusuario).setParameter("id2", id).getResultList();

		try {
			list.get(0);
			return true;
		} catch (Exception e) {
			log.info("Solicitação não foi recebida!");
			return false;
		}
	}

	@Override
	public List<SolicitacaoRecebida> recebidasPorIdUsuario(Long id) {
		String sql = "SELECT sr FROM SolicitacaoRecebida sr WHERE sr.usuario.id = :id";
		List<SolicitacaoRecebida> list = em.createQuery(sql, SolicitacaoRecebida.class).setParameter("id", id).getResultList();
		return list;
	}

	@Override
	public List<SolicitacaoEnviada> enviadasPorIdUsuario(Long id) {
		String sql = "SELECT se FROM SolicitacaoEnviada se WHERE se.usuario.id = :id";
		List<SolicitacaoEnviada> list = em.createQuery(sql, SolicitacaoEnviada.class).setParameter("id", id).getResultList();
		return list;
	}

}
