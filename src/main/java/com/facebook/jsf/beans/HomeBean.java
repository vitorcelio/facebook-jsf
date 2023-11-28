package com.facebook.jsf.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import com.facebook.jsf.dto.PublicacaoDTO;
import com.facebook.jsf.dto.UsuarioDTO;
import com.facebook.jsf.form.ComentarioForm;
import com.facebook.jsf.form.PublicacaoForm;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.service.ComentarioService;
import com.facebook.jsf.service.CurtidaService;
import com.facebook.jsf.service.PublicacaoService;
import com.facebook.jsf.utils.FacebookUtils;

@Model
public class HomeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private ComentarioForm form = new ComentarioForm();
	private PublicacaoForm formPublic = new PublicacaoForm();
	private List<PublicacaoDTO> publicacaoes;
	private List<UsuarioDTO> amigos;
	private HtmlInputText nomesAmigos;

	@Inject
	private CurtidaService curtidaService;

	@Inject
	private ComentarioService comentarioService;

	@Inject
	private UsuarioRepository repository;

	@Inject
	private PublicacaoService publicacaoService;

	public UsuarioDTO getUsuarioLogado() {
		Usuario usuarioLogado = repository.findByUsername(LoginBean.getUsuarioLogado().getUsername());
		return new UsuarioDTO(usuarioLogado);
	}

	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		FacebookUtils.removerObjetoDaSessao("paginacaoperfil");
		carregarAmigos();
		carregaPublicacoes();
	}

	public void carregarAmigos() {
		String nome = "";
		try {
			nome = nomesAmigos.getValue().toString();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		List<Usuario> usuariosAmigos = repository.buscarPorNomeAmigos(nome, getUsuarioLogado().getId());
		setAmigos(UsuarioDTO.listar(usuariosAmigos));
	}

	public void carregaPublicacoes() {
		int valor = (Integer) FacebookUtils.buscarObjetoNaSessao("paginacao") != null ? (Integer) FacebookUtils.buscarObjetoNaSessao("paginacao") : 5;

		List<Publicacao> publicacaoesAmigos = publicacaoService
				.getAllAmigosByIdPaginado(LoginBean.getUsuarioLogado().getId(), 0, valor);
		publicacaoes = PublicacaoDTO.listar(publicacaoesAmigos);
	}

	public void aumentarPublicacao(Integer qtd) {
		int valor = 5 + qtd;
		
		try {
			valor = qtd + (Integer) FacebookUtils.buscarObjetoNaSessao("paginacao");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não existe objeto na sessão");
		}

		FacebookUtils.inserirObjetoNaSessao("paginacao", valor);

		List<Publicacao> publicacaoesAmigos = publicacaoService
				.getAllAmigosByIdPaginado(LoginBean.getUsuarioLogado().getId(), 0, valor);
		publicacaoes = PublicacaoDTO.listar(publicacaoesAmigos);
	}

	public void toPublicacao() throws IOException {
		publicacaoService.salvar(formPublic, LoginBean.getUsuarioLogado().getId());
		formPublic = new PublicacaoForm();
	}

	public void cadastrarComentario(Long idPublicacao) {
		form.setIdPublicacao(idPublicacao);
		comentarioService.salvar(form);
		form = new ComentarioForm();
	}

	public void consultarAmigos(AjaxBehaviorEvent event) {
		String nome = nomesAmigos.getValue().toString();
		List<Usuario> buscarPorNome = repository.buscarPorNomeAmigos(nome, getUsuarioLogado().getId());
		setAmigos(UsuarioDTO.listar(buscarPorNome));
	}

	public Boolean verificaCurtida(Long idPost) {
		return curtidaService.verificarCurtida(getUsuarioLogado().getId(), idPost);
	}

	public ComentarioForm getForm() {
		return form;
	}

	public void setForm(ComentarioForm form) {
		this.form = form;
	}

	public HtmlInputText getNomesAmigos() {
		return nomesAmigos;
	}

	public void setNomesAmigos(HtmlInputText nomesAmigos) {
		this.nomesAmigos = nomesAmigos;
	}

	public List<UsuarioDTO> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<UsuarioDTO> amigos) {
		this.amigos = amigos;
	}

	public List<PublicacaoDTO> getPublicacaoes() {
		return publicacaoes;
	}

	public void setPublicacaoes(List<PublicacaoDTO> publicacaoes) {
		this.publicacaoes = publicacaoes;
	}

	public PublicacaoForm getFormPublic() {
		return formPublic;
	}

	public void setFormPublic(PublicacaoForm formPublic) {
		this.formPublic = formPublic;
	}

}
