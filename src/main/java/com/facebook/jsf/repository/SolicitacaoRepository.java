package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.modelo.SolicitacaoEnviada;
import com.facebook.jsf.modelo.SolicitacaoRecebida;
import com.facebook.jsf.modelo.Usuario;

public interface SolicitacaoRepository {

	public void enviarSolicitacao(Long id, Long idUsuarioLogado);

	public void retirarSolicitacao(Long id, Long idUsuarioLogado);

	public SolicitacaoEnviada enviada(Usuario usuario, Long id);

	public SolicitacaoRecebida recebida(Usuario usuario, Long id);

	public Boolean enviada(Long usuario, Long id);

	public Boolean recebida(Long usuario, Long id);
	
	public List<SolicitacaoRecebida> recebidasPorIdUsuario(Long id);
	
	public List<SolicitacaoEnviada> enviadasPorIdUsuario(Long id);

}
