package com.facebook.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.facebook.jsf.dto.UsuarioDTO;
import com.facebook.jsf.modelo.SolicitacaoEnviada;
import com.facebook.jsf.modelo.SolicitacaoRecebida;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.SolicitacaoRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Model
public class FriendsBean {

	@Inject
	private UsuarioRepository repository;

	@Inject
	private SolicitacaoRepository solicitacaoRepository;

	private UsuarioDTO usuarioPrevia;
	private List<UsuarioDTO> sugestoes;
	private List<UsuarioDTO> solicitacoesRecebidas;

	@PostConstruct
	public void init() {
		FacebookUtils.removerObjetoDaSessao("paginacao");
		
		carregarSugestoes();
		carregarSolicitacoes();
	}

	public void carregarSugestoes() {
		List<Usuario> usuarios = repository.listarSugestoes(LoginBean.getUsuarioLogado().getId(), true);
		setSugestoes(UsuarioDTO.listar(usuarios));
	}

	public void carregarSolicitacoes() {
		List<SolicitacaoRecebida> solicitacoesRecebidas = solicitacaoRepository
				.recebidasPorIdUsuario(LoginBean.getUsuarioLogado().getId());

		List<Usuario> usuariosQueMeEnviaramSolicitacoes = new ArrayList<>();
		for (SolicitacaoRecebida solicitacaoRecebida : solicitacoesRecebidas) {
			Usuario usuarioQueMeEnviou = repository.findById(solicitacaoRecebida.getIdUsuarioDeEnvio());
			usuariosQueMeEnviaramSolicitacoes.add(usuarioQueMeEnviou);
		}

		setSolicitacoesRecebidas(UsuarioDTO.listar(usuariosQueMeEnviaramSolicitacoes));
	}

	public Boolean solicitado(Long id) {
		return solicitacaoRepository.enviada(LoginBean.getUsuarioLogado().getId(), id);
	}

	public List<UsuarioDTO> getSolicitacoesEnviadas() {
		List<SolicitacaoEnviada> solicitacoesEnviadas = solicitacaoRepository
				.enviadasPorIdUsuario(LoginBean.getUsuarioLogado().getId());

		List<Usuario> usuariosQueEuEnvieiSolicitacoes = new ArrayList<>();
		for (SolicitacaoEnviada solicitacaoEnviada : solicitacoesEnviadas) {
			Usuario usuarioQueEuEnviei = repository.findById(solicitacaoEnviada.getIdUsuarioRecebe());
			usuariosQueEuEnvieiSolicitacoes.add(usuarioQueEuEnviei);
		}

		return UsuarioDTO.listar(usuariosQueEuEnvieiSolicitacoes);
	}

	public UsuarioDTO getUsuarioPrevia() {
		return usuarioPrevia;
	}

	public void setUsuarioPrevia(UsuarioDTO usuarioPrevia) {
		this.usuarioPrevia = usuarioPrevia;
	}

	public List<UsuarioDTO> getSugestoes() {
		return sugestoes;
	}

	public void setSugestoes(List<UsuarioDTO> sugestoes) {
		this.sugestoes = sugestoes;
	}

	public void setSolicitacoesRecebidas(List<UsuarioDTO> solicitacoesRecebidas) {
		this.solicitacoesRecebidas = solicitacoesRecebidas;
	}

	public List<UsuarioDTO> getSolicitacoesRecebidas() {
		return solicitacoesRecebidas;
	}

}
