package com.facebook.jsf.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.facebook.jsf.form.CadastroRespostasCampoForm;
import com.facebook.jsf.form.CampoFormularioForm;
import com.facebook.jsf.form.FormularioForm;
import com.facebook.jsf.form.FormularioListForm;
import com.facebook.jsf.form.OpcaoCampoFormularioForm;
import com.facebook.jsf.form.RespostaIndividuaisForm;
import com.facebook.jsf.form.RespostasCampoForm;
import com.facebook.jsf.modelo.CampoFormulario;
import com.facebook.jsf.modelo.Formulario;
import com.facebook.jsf.modelo.OpcaoCampoFormulario;
import com.facebook.jsf.modelo.RespostaCampoFormulario;
import com.facebook.jsf.repository.FormularioRepository;

@Path("/form")
public class FormularioController {

	@EJB
	private FormularioRepository repository;

	@POST
	@Path("/salvar")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response salvarForm(FormularioForm formulario) {
		try {
			repository.salvarForm(formulario);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.status(Status.CREATED).entity("sucesso").build();
	}

	@GET
	@Path("/{idForm}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarForm(@PathParam("idForm") Integer idForm) {
		FormularioForm entity = null;
		Formulario formulario = repository.findByToken(String.valueOf(idForm));

		if (formulario != null) {
			entity = new FormularioForm(formulario);

			List<CampoFormulario> campos = repository.getAllByForm(formulario.getId());
			List<CampoFormularioForm> listCampos = CampoFormularioForm.converter(campos);
			for (CampoFormularioForm campoFormularioForm : listCampos) {
				List<OpcaoCampoFormulario> opcoes = repository.getAllByCampo(campoFormularioForm.getId());
				List<OpcaoCampoFormularioForm> listOpcoes = OpcaoCampoFormularioForm.converter(opcoes);

				campoFormularioForm.setOpcoes(listOpcoes);
			}

			entity.setCampos(listCampos);
			return Response.ok(entity).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("/resposta")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response salvarRespostas(CadastroRespostasCampoForm req) {
		List<RespostasCampoForm> respostas = req.getRespostas();
		if (respostas != null && !respostas.isEmpty()) {
			repository.salvarResposta(respostas);
			return Response.status(Status.CREATED).entity("sucesso").build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/list")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response buscarListForm() {
		List<Formulario> forms = repository.getAllForms();
		List<FormularioListForm> formularioListForms = new ArrayList<>();

		if (forms != null && !forms.isEmpty()) {
			for (Formulario formulario : forms) {

				FormularioListForm formList = new FormularioListForm();
				formList.setId(formulario.getId());
				formList.setDescricao(formulario.getDescricao());
				formList.setTextoBotao(formulario.getTxtBotao());
				formList.setToken(formulario.getToken());

				List<CampoFormulario> campos = repository.getAllByForm(formulario.getId());
				formList.setCamposList(CampoFormularioForm.converter(campos));

				List<RespostaIndividuaisForm> respostaIndividuais = new ArrayList<>();
				List<String> tokens = repository.getTokens(formulario.getId());
				if (tokens != null && !tokens.isEmpty()) {
					for (String tk : tokens) {
						List<RespostaCampoFormulario> respostasByToken = repository.getRespostasByToken(tk);
						RespostaIndividuaisForm respostas = new RespostaIndividuaisForm();
						respostas.setToken(tk);
						respostas.setRespostaList(RespostasCampoForm.converter(respostasByToken));

						respostaIndividuais.add(respostas);
					}

					formList.setRespostaIndividuais(respostaIndividuais);
				}

				formularioListForms.add(formList);
			}

			return Response.ok().entity(formularioListForms).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

}
