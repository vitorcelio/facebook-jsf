package com.facebook.jsf.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.facebook.jsf.dto.CategoriaDTO;
import com.facebook.jsf.dto.LonLatDTO;
import com.facebook.jsf.dto.ProdutoDTO;
import com.facebook.jsf.modelo.Categoria;
import com.facebook.jsf.modelo.Produto;
import com.facebook.jsf.openStreetMap.NominatimOpenStreetMap;
import com.facebook.jsf.openStreetMap.enums.FormatNominatim;
import com.facebook.jsf.repository.CategoriaRepository;
import com.facebook.jsf.service.ProdutoService;
import com.facebook.jsf.utils.FacebookUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class MarketplaceBean {

	@Inject
	private ProdutoService service;

	@Inject
	private CategoriaRepository categoriaRepository;

	private List<ProdutoDTO> produtos;
	private List<CategoriaDTO> categorias;
	private String pesquisarProduto;
	private String cidadeLocalizacao;
	private List<LonLatDTO> lonlatList = new ArrayList<>();
	private LonLatDTO lonlat = new LonLatDTO();
	private String longitude, latitude, zoom;

	@PostConstruct
	public void init() {
		carregarProduto();
		carregarCategoria();
	}
	
	public void carregarListLonLatCidadeQuery() {
		NominatimOpenStreetMap nominatim = new NominatimOpenStreetMap();

		List<LonLatDTO> infos = null;
		try {
			System.out.println(cidadeLocalizacao);
			infos = nominatim.getAllInfos(null, null, cidadeLocalizacao, null, null, null, null, FormatNominatim.JSON);
		} catch (Exception e) {
		}

		if (infos != null) {
			setLonlatList(infos);
		}

	}
	
	public String selectLonlat() throws IOException {
		if (lonlat != null) {
			System.out.println(lonlat);
			setCidadeLocalizacao(lonlat.getAddress().getCity() + ", " + lonlat.getAddress().getState());
			setLatitude(lonlat.getLat());
			setLongitude(lonlat.getLon());
			setZoom("14");
			carregarProduto();
		}

		return "";
	}
	
	public void resetLonlat() {
		lonlatList = new ArrayList<>();
	}

	public void redirectProduto(String token) throws IOException {
		FacebookUtils.redirecionarPagina("/marketplace/item/" + token);
	}

	public void redirectCategoria(String categoria) throws IOException {
		FacebookUtils.redirecionarPagina("/marketplace/category/" + categoria);
	}

	public void carregarProduto() {
		List<Produto> all = service.getAllByCidadeAndCategoriaNome(cidadeLocalizacao, null, 0, 20);
		setProdutos(ProdutoDTO.listar(all));
	}

	public void carregarCategoria() {
		List<Categoria> all = categoriaRepository.getAll();
		setCategorias(CategoriaDTO.listar(all));
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

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

	public String getPesquisarProduto() {
		return pesquisarProduto;
	}

	public void setPesquisarProduto(String pesquisarProduto) {
		this.pesquisarProduto = pesquisarProduto;
	}

	public String getCidadeLocalizacao() {
		return cidadeLocalizacao;
	}

	public void setCidadeLocalizacao(String cidadeLocalizacao) {
		this.cidadeLocalizacao = cidadeLocalizacao;
	}

	public List<LonLatDTO> getLonlatList() {
		return lonlatList;
	}

	public void setLonlatList(List<LonLatDTO> lonlatList) {
		this.lonlatList = lonlatList;
	}

	public LonLatDTO getLonlat() {
		return lonlat;
	}

	public void setLonlat(LonLatDTO lonlat) {
		this.lonlat = lonlat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

}
