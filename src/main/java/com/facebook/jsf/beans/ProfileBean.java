package com.facebook.jsf.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.facebook.jsf.dto.LonLatDTO;
import com.facebook.jsf.dto.PublicacaoDTO;
import com.facebook.jsf.dto.UsuarioDTO;
import com.facebook.jsf.form.ComentarioForm;
import com.facebook.jsf.form.PublicacaoForm;
import com.facebook.jsf.form.UsuarioForm;
import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.openStreetMap.NominatimOpenStreetMap;
import com.facebook.jsf.openStreetMap.enums.FormatNominatim;
import com.facebook.jsf.repository.AmigoRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.service.CurtidaService;
import com.facebook.jsf.service.PublicacaoService;
import com.facebook.jsf.service.UsuarioService;
import com.facebook.jsf.utils.FacebookUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ProfileBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AmigoRepository amigoRepository;

	@Inject
	private UsuarioRepository repository;

	@Inject
	private UsuarioService service;

	@Inject
	private PublicacaoService publicacaoService;

	@Inject
	private CurtidaService curtidaService;

	private String username;
	private UsuarioDTO usuario = new UsuarioDTO();
	private UsuarioForm form = new UsuarioForm();
	private ComentarioForm formComen = new ComentarioForm();
	private PublicacaoForm formPublic = new PublicacaoForm();
	private List<PublicacaoDTO> publicacoes;
	private List<LonLatDTO> lonlatList = new ArrayList<>();

	public void carregarListLonLatCidadeNasceu() {
		NominatimOpenStreetMap nominatim = new NominatimOpenStreetMap();

		List<LonLatDTO> infos = null;
		try {
			infos = nominatim.getAllInfos(null, null, usuario.getCidadeQueNasceu(), null, null, null, null,
					FormatNominatim.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (infos != null) {
			setLonlatList(infos);
		}
	}

	public void carregarListLonLatCidadeAtual() {
		NominatimOpenStreetMap nominatim = new NominatimOpenStreetMap();

		List<LonLatDTO> infos = null;
		System.out.println(usuario.getCidadeQueMora());
		try {
			infos = nominatim.getAllInfos(null, null, usuario.getCidadeQueMora(), null, null, null, null,
					FormatNominatim.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (infos != null) {
			setLonlatList(infos);
		}
	}

	public void selectLocalizacaoOndeMora(LonLatDTO lonLat) {
		String cidade = gerarLonLat(lonLat);
		System.out.println(cidade);

		usuario.setLatitude(lonLat.getLat());
		usuario.setLongitude(lonLat.getLon());
		usuario.setCidadeQueMora(cidade);
		lonlatList = new ArrayList<>();
	}

	public void selectLocalizacaoOndeNasceu(LonLatDTO lonLat) {
		String cidade = gerarLonLat(lonLat);
		System.out.println(cidade);

		usuario.setCidadeQueNasceu(cidade);
		lonlatList = new ArrayList<>();
	}

	public String gerarLonLat(LonLatDTO dto) {
		String lonlat = "";

		if (dto.getAddress().getCity() != null && !dto.getAddress().getCity().trim().isEmpty()) {
			lonlat += dto.getAddress().getCity();
		}

		if (dto.getAddress().getState() != null && !dto.getAddress().getState().trim().isEmpty()) {
			if (!lonlat.trim().isEmpty()) {
				lonlat += ", ";
			}

			lonlat += dto.getAddress().getState();
		}

		return lonlat;
	}

	public UsuarioDTO getUsuarioLogado() {
		Usuario usuarioLogado = repository.findByUsername(LoginBean.getUsuarioLogado().getUsername());
		return new UsuarioDTO(usuarioLogado);
	}

	public String carregarPerfil() {
		FacebookUtils.removerObjetoDaSessao("paginacao");
		int valor = (Integer) FacebookUtils.buscarObjetoNaSessao("paginacaoperfil") != null
				? (Integer) FacebookUtils.buscarObjetoNaSessao("paginacaoperfil")
				: 5;

		Usuario usuarioDadosPerfil = null;
		try {
			usuarioDadosPerfil = repository.findByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("não foi encontrado um usuário com username: " + username);
		}

		if (usuarioDadosPerfil != null) {
			List<Publicacao> list = publicacaoService.getAllByUsuarioPaginada(usuarioDadosPerfil.getId(), 0, valor);
			setPublicacoes(PublicacaoDTO.listar(list));
			usuario = new UsuarioDTO(usuarioDadosPerfil);
			return "";
		}

		return "/pages/home?faces-redirect=true";
	}

	public Boolean verificaCurtida(Long idPost) {
		return curtidaService.verificarCurtida(getUsuarioLogado().getId(), idPost);
	}

	public void aumentarPublicacao(Integer qtd) {
		int valor = 5 + qtd;

		try {
			valor = qtd + (Integer) FacebookUtils.buscarObjetoNaSessao("paginacaoperfil");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não existe objeto na sessão");
		}

		FacebookUtils.inserirObjetoNaSessao("paginacaoperfil", valor);

		List<Publicacao> publicacaoesAmigos = publicacaoService
				.getAllByUsuarioPaginada(LoginBean.getUsuarioLogado().getId(), 0, valor);
		publicacoes = PublicacaoDTO.listar(publicacaoesAmigos);
	}

	public void toPublicacao() throws IOException {
		publicacaoService.salvar(formPublic, LoginBean.getUsuarioLogado().getId());
		formPublic = new PublicacaoForm();
	}

	public List<UsuarioDTO> getAmigos(Integer inicio, Integer quantidade) {
		List<Usuario> amigosDoPerfil = amigoRepository.getAllListByIdUsuario(usuario.getId(), inicio, quantidade);
		return UsuarioDTO.listar(amigosDoPerfil);
	}

	public void editarFotoPerfil() {
		service.addFotoPerfil(form, LoginBean.getUsuarioLogado().getId());
		try {
			redirecionarPerfil(LoginBean.getUsuarioLogado().getUsername());
		} catch (Exception e) {
			FacebookUtils.mostrarMsg("Erro ao atualizar Usuário!");
		}
	}

	public void editarFotoCapa() {
		service.addFotoCapa(form, LoginBean.getUsuarioLogado().getId());
		try {
			redirecionarPerfil(LoginBean.getUsuarioLogado().getUsername());
		} catch (Exception e) {
			FacebookUtils.mostrarMsg("Erro ao atualizar Usuário!");
		}
	}

	public void editarUsuario() {
		form.setNome(usuario.getNome());
		form.setSobrenome(usuario.getSobrenome());
		form.setEmpresaQueTrabalha(usuario.getEmpresaQueTrabalha());
		form.setEscolaQueFrequentou(usuario.getEscolaQueFrequentou());
		form.setCidadeQueMora(usuario.getCidadeQueMora());
		form.setCidadeQueNasceu(usuario.getCidadeQueNasceu());
		form.setEstaEmRelacionamento(usuario.getEstaEmRelacionamento());
		form.setEstaCasado(usuario.getEstaCasado());
		form.setRelacionamentoCom(usuario.getRelacionamentoCom());
		form.setInstagram(usuario.getInstagram());
		form.setWhatsapp(usuario.getWhatsapp());
		form.setBiografia(usuario.getBiografia());
		form.setLongitude(usuario.getLongitude());
		form.setLatitude(usuario.getLatitude());
		service.editarUsuario(form, LoginBean.getUsuarioLogado().getId());
		FacebookUtils.updateAjax("postsPerfil");
	}

	public void redirecionarPerfil(String username) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/" + username);
	}

	public void redirecionarInstagram(String username) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("https://instagram.com/" + username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public UsuarioForm getForm() {
		return form;
	}

	public void setForm(UsuarioForm form) {
		this.form = form;
	}

	public List<PublicacaoDTO> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<PublicacaoDTO> publicacoes) {
		this.publicacoes = publicacoes;
	}

	public PublicacaoForm getFormPublic() {
		return formPublic;
	}

	public void setFormPublic(PublicacaoForm formPublic) {
		this.formPublic = formPublic;
	}

	public ComentarioForm getFormComen() {
		return formComen;
	}

	public void setFormComen(ComentarioForm formComen) {
		this.formComen = formComen;
	}

	public List<LonLatDTO> getLonlatList() {
		return lonlatList;
	}

	public void setLonlatList(List<LonLatDTO> lonlatList) {
		this.lonlatList = lonlatList;
	}

}
