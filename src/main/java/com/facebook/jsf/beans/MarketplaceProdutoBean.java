package com.facebook.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.facebook.jsf.dto.CategoriaDTO;
import com.facebook.jsf.dto.ProdutoDTO;
import com.facebook.jsf.modelo.Categoria;
import com.facebook.jsf.modelo.Produto;
import com.facebook.jsf.repository.CategoriaRepository;
import com.facebook.jsf.service.ProdutoService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class MarketplaceProdutoBean {

	@Inject
	private ProdutoService service;

	@Inject
	private CategoriaRepository categoriaRepository;

	private List<CategoriaDTO> categorias;
	private ProdutoDTO produto;
	private String tokenProduto;

	@PostConstruct
	public void carregarCategoria() {
		List<Categoria> all = categoriaRepository.getAll();
		setCategorias(CategoriaDTO.listar(all));
	}

	public void carregarProduto() {
		Produto produto = null;
		
		try {
			produto = service.findByToken(tokenProduto);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(produto != null) {
			setProduto(new ProdutoDTO(produto));
		}
	}

	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	public String getTokenProduto() {
		return tokenProduto;
	}

	public void setTokenProduto(String tokenProduto) {
		this.tokenProduto = tokenProduto;
	}

}
