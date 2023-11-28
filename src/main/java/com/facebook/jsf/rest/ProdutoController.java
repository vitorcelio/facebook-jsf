package com.facebook.jsf.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facebook.jsf.dto.ProdutoDTO;
import com.facebook.jsf.modelo.Produto;
import com.facebook.jsf.service.ProdutoService;

@Path("/produto")
public class ProdutoController {

	@EJB
	private ProdutoService service;

	@GET
	@Path("/map/{cidade}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getImoveisMapa(@PathParam("cidade") String cidade) {
		List<Produto> produtosList = null;

		try {
			if(!cidade.contains("sem-cidade"))
				produtosList = service.getAllByCidadeAndCategoriaNome(cidade, "propertyrentals", null, null);
		} catch (Exception e) {
			// TODO: handle exception
		}

		List<ProdutoDTO> produtos = new ArrayList<>();
		if (produtosList != null)
			produtos = ProdutoDTO.listar(produtosList);

		return Response.ok(produtos).build();
	}

}
