package com.facebook.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import com.facebook.jsf.dto.ChatDTO;
import com.facebook.jsf.dto.UsuarioDTO;
import com.facebook.jsf.modelo.Chat;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.ChatRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ChatBean implements Serializable {

	private static final long serialVersionUID = -1431939824596700787L;

	@Inject
	private UsuarioRepository repository;

	@Inject
	private ChatRepository chatRepository;

	private String username;
	private List<UsuarioDTO> amigos;
	private UsuarioDTO usuario = new UsuarioDTO();
	private HtmlInputText nomesAmigos;
	private List<ChatDTO> mensagens;

	@PostConstruct
	public void carregarAmigos() {
		FacebookUtils.removerObjetoDaSessao("paginacao");
		FacebookUtils.removerObjetoDaSessao("paginacaoperfil");

		String nome = "";
		try {
			nome = nomesAmigos.getValue().toString();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		List<Usuario> usuariosAmigos = repository.buscarPorNomeAmigos(nome, LoginBean.getUsuarioLogado().getId());
		setAmigos(UsuarioDTO.listar(usuariosAmigos));
	}

	public String carregarChat() {

		Usuario usuarioDadosPerfil = null;
		try {
			usuarioDadosPerfil = repository.findByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("não foi encontrado um usuário com username: " + username);
		}

		if (usuarioDadosPerfil != null) {
			// TODO carregamento das mensagens do Chat
			List<Chat> listaMensagens = new ArrayList<>();
			try {
				listaMensagens = chatRepository.getAllMensagens(LoginBean.getUsuarioLogado().getId(),
						usuarioDadosPerfil.getId());
				setMensagens(ChatDTO.listar(listaMensagens));
			} catch (Exception e) {
				System.out.println("não foi encontrado nenhuma mensagem com " + username);
			}

			usuario = new UsuarioDTO(usuarioDadosPerfil);
			return "";
		}

		return "/pages/home?faces-redirect=true";
	}

	public void consultarAmigos(AjaxBehaviorEvent event) {
		String nome = nomesAmigos.getValue().toString();
		List<Usuario> buscarPorNome = repository.buscarPorNomeAmigos(nome, LoginBean.getUsuarioLogado().getId());
		setAmigos(UsuarioDTO.listar(buscarPorNome));
	}

	public String gerarUrlWebSocket() {
		if (LoginBean.getUsuarioLogado().getId() > usuario.getId()) {
			return LoginBean.getUsuarioLogado().getId().toString() + "/" + usuario.getId().toString();
		} else {
			return usuario.getId().toString() + "/" + LoginBean.getUsuarioLogado().getId().toString();
		}
	}

	public String urlMeuUsuario() {
		return String.valueOf(LoginBean.getUsuarioLogado().getId());
	}

	public String gerarUrlNotificacao() {
		return String.valueOf(usuario.getId());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UsuarioDTO> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<UsuarioDTO> amigos) {
		this.amigos = amigos;
	}

	public HtmlInputText getNomesAmigos() {
		return nomesAmigos;
	}

	public void setNomesAmigos(HtmlInputText nomesAmigos) {
		this.nomesAmigos = nomesAmigos;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public List<ChatDTO> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<ChatDTO> mensagens) {
		this.mensagens = mensagens;
	}

}
