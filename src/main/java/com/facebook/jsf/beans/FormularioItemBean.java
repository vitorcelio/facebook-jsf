package com.facebook.jsf.beans;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.HttpMethod;

import com.facebook.jsf.form.CadastroRespostasCampoForm;
import com.facebook.jsf.form.FormularioForm;
import com.facebook.jsf.form.RespostasCampoForm;
import com.facebook.jsf.utils.FacebookUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class FormularioItemBean {

	private static Gson gson = new Gson();

	private String idForm;

	private FormularioForm formulario;

	private List<RespostasCampoForm> respostas;

	@PostConstruct
	public void init() {
		buscarFormulario();
	}

	public void teste() {
		// setIdForm(idForm);
	}

	public void buscarFormulario() {
		if (idForm != null) {
			String url = null;
			try {
				url = FacebookUtils.readUrl("http://localhost:8080/rest/form/" + idForm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (url != null) {
				FormularioForm form = gson.fromJson(url, FormularioForm.class);
				setFormulario(form);
			}

			if (getFormulario().getId() != null) {
				String json = gson.toJson(getFormulario());
				FacebookUtils.executeJavaScript("gerarForm(" + json + ");");
				FacebookUtils.updateAjax("frm:title-form-criado");
				FacebookUtils.updateAjax("frm:botaoForm");
				FacebookUtils.updateAjax("frm:imgForm");
			}
		}

	}

	public void salvarRespostas() {
		List<RespostasCampoForm> respostas = buscarRespostasJs();
		if (respostas != null && !respostas.isEmpty()) {
			CadastroRespostasCampoForm req = new CadastroRespostasCampoForm();
			req.setIdForm(getFormulario().getId());
			req.setRespostas(respostas);

			String json = gson.toJson(req);

			try {
				FacebookUtils.postRequestBearer("http://localhost:8080/rest/form/resposta", json, false, null,
						HttpMethod.POST, null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
						"Os dados foram salvos e enviados para o criador do formulário!"));
				buscarFormulario();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
						"Erro ao tentar salvar as respostas do formulário!"));
			}
		}
	}

	private List<RespostasCampoForm> buscarRespostasJs() {
		List<RespostasCampoForm> respostasGet = null;

		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();

		try {
			String url = requestParamMap.get("listaRespostas");
			if (url != null) {
				Type listRespostas = new TypeToken<List<RespostasCampoForm>>() {
				}.getType();

				respostasGet = gson.fromJson(url, listRespostas);
				this.setRespostas(respostasGet);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
					"Falha no envio das respostas para o formulário!"));
		}

		return respostasGet != null ? respostasGet : null;
	}

	public String getIdForm() {
		return idForm;
	}

	public void setIdForm(String idForm) {
		this.idForm = idForm;
	}

	public FormularioForm getFormulario() {
		if (formulario == null) {
			formulario = new FormularioForm();
		}
		return formulario;
	}

	public void setFormulario(FormularioForm formulario) {
		this.formulario = formulario;
	}

	public List<RespostasCampoForm> getRespostas() {
		if (respostas == null) {
			respostas = new ArrayList<>();
		}
		return respostas;
	}

	public void setRespostas(List<RespostasCampoForm> respostas) {
		this.respostas = respostas;
	}

}
