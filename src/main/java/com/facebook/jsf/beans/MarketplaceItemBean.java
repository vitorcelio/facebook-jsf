package com.facebook.jsf.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.facebook.jsf.dto.LonLatDTO;
import com.facebook.jsf.form.ProdutoForm;
import com.facebook.jsf.modelo.Categoria;
import com.facebook.jsf.openStreetMap.NominatimOpenStreetMap;
import com.facebook.jsf.openStreetMap.enums.FormatNominatim;
import com.facebook.jsf.repository.CategoriaRepository;
import com.facebook.jsf.service.ProdutoService;
import com.facebook.jsf.utils.FacebookUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class MarketplaceItemBean {

	@Inject
	private CategoriaRepository repository;

	@Inject
	private ProdutoService service;

	private List<LonLatDTO> lonlatList = new ArrayList<>();
	private ProdutoForm form = new ProdutoForm();
	private Locale locale = new Locale("pt", "BR");
	private List<SelectItem> categorias;
	private Categoria categoria;

	@PostConstruct
	public void init() {
		categorias = repository.getAllSelectItem();
	}

	public void carregarListLonLat() {
		NominatimOpenStreetMap nominatim = new NominatimOpenStreetMap();

		List<LonLatDTO> infos = null;
		try {
			infos = nominatim.getInfos(form.getCidade(), FormatNominatim.JSON);
		} catch (Exception e) {
		}
		
		if(infos != null) {
			setLonlatList(infos);
		}
	}

	public void salvarProduto() {
		try {
			form.setIdUsuario(LoginBean.getUsuarioLogado().getId());
			service.salvar(form);
			FacebookUtils.redirecionarPagina("/marketplace");
			FacebookUtils.mostrarMsg("Seu produto está no marketplace!");
		} catch (Exception e) {
			// TODO: handle exception
			FacebookUtils.mostrarMsg("Erro ao salvar seu produto!");
		}
	}

	public boolean validaBotaoSalvar() {
		return FacebookUtils.validaBotaoSalvar(form.getImagem(), form.getTitulo(), form.getPreco(),
				form.getIdCategoria(), form.getCondicao(), form.getCidade());
	}

	public void selectLocalizacao(LonLatDTO lonLat) {
		String cidade = gerarLonLat(lonLat);
		System.out.println(cidade);

		form.setLatitude(lonLat.getLat());
		form.setLongitude(lonLat.getLon());
		form.setCidade(cidade);
		form.setCidadeQuery(lonLat.getAddress().getCity() + ", " + lonLat.getAddress().getState());

		FacebookUtils.executeJavaScript("document.getElementById('frm:buttonLatLon').click();");
		FacebookUtils.executeJavaScript("document.getElementById('map').classList.remove('sumir');");
		FacebookUtils.executeJavaScript("document.getElementById('cidade2Previa').classList.remove('sumir');");
		FacebookUtils.executeJavaScript("document.getElementById('localizaPrevia').classList.remove('sumir');");
		FacebookUtils.executeJavaScript("atualizaMapa(" + lonLat.getLon() + ", " + lonLat.getLat() + ");");
	}

	public String gerarLonLat(LonLatDTO dto) {
		String lonlat = "";

		if (dto.getAddress().getRoad() != null && !dto.getAddress().getRoad().trim().isEmpty()) {
			lonlat += dto.getAddress().getRoad();
		}

		if (dto.getAddress().getTown() != null && !dto.getAddress().getTown().trim().isEmpty()) {
			if (!lonlat.trim().isEmpty()) {
				lonlat += ", ";
			}

			lonlat += dto.getAddress().getTown();
		}

		if (dto.getAddress().getCity() != null && !dto.getAddress().getCity().trim().isEmpty()) {
			if (!lonlat.trim().isEmpty()) {
				lonlat += ", ";
			}

			lonlat += dto.getAddress().getCity();
		}

		if (dto.getAddress().getState() != null && !dto.getAddress().getState().trim().isEmpty()) {
			if (!lonlat.trim().isEmpty()) {
				lonlat += ", ";
			}

			lonlat += dto.getAddress().getState();
		}

		if (dto.getAddress().getRegion() != null && !dto.getAddress().getRegion().trim().isEmpty()) {
			if (!lonlat.trim().isEmpty()) {
				lonlat += ", ";
			}

			lonlat += dto.getAddress().getRegion();
		}

		return lonlat;
	}

	public String converteMoeda(Double d) {
		return FacebookUtils.converterMoedaDouble(d);
	}

	public List<LonLatDTO> getLonlatList() {
		return lonlatList;
	}

	public void setLonlatList(List<LonLatDTO> lonlatList) {
		this.lonlatList = lonlatList;
	}

	public ProdutoForm getForm() {
		return form;
	}

	public void setForm(ProdutoForm form) {
		this.form = form;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
