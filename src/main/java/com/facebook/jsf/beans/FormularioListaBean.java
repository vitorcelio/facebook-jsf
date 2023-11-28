package com.facebook.jsf.beans;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.facebook.jsf.form.FormularioListForm;
import com.facebook.jsf.utils.FacebookUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class FormularioListaBean {

	private static Gson gson = new Gson();
	private List<FormularioListForm> formularioList;

	@PostConstruct
	public void init() {
		buscarFormularioList();
	}

	public void buscarFormularioList() {
		try {
			String resp = FacebookUtils.readUrl("http://localhost:8080/rest/form/list");
			if (resp != null) {
				Type listForms = new TypeToken<List<FormularioListForm>>() {
				}.getType();

				List<FormularioListForm> list = gson.fromJson(resp, listForms);
				this.setFormularioList(list);
				FacebookUtils.updateAjax("listForms");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao listar formulários!"));
		}
	}

	public void actionLinkForm(String token) {
		try {
			FacebookUtils.redirecionarPagina("/formulario/" + token);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<FormularioListForm> getFormularioList() {
		if (formularioList == null) {
			formularioList = new ArrayList<>();
		}
		return formularioList;
	}

	public void setFormularioList(List<FormularioListForm> formularioList) {
		this.formularioList = formularioList;
	}

}
