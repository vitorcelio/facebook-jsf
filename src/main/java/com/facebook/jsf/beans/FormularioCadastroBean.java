package com.facebook.jsf.beans;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.HttpMethod;

import com.facebook.jsf.form.CampoFormularioForm;
import com.facebook.jsf.form.FormularioForm;
import com.facebook.jsf.form.OpcaoCampoFormularioForm;
import com.facebook.jsf.utils.FacebookUtils;
import com.google.gson.Gson;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class FormularioCadastroBean {

	private static Gson gson = new Gson();
	private FormularioForm formulario;
	private CampoFormularioForm campo;
	private OpcaoCampoFormularioForm opcao;

	@PostConstruct
	public void init() {

	}

	public void salvar() {
		if(getFormulario().getDescricao() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "O formulário está sem nome!"));
			
			return;
		}
		
		if(getFormulario().getCampos().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "É necessário adicionar ao menos um campo!"));
			
			return;
		}
		
		String json = gson.toJson(getFormulario());

		try {
			String resp = FacebookUtils.postRequestBearer("http://localhost:8080/rest/form/salvar", json, false, null,
					HttpMethod.POST, null);
			
			if(resp != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Formulário salvo com sucesso!"));
				
				this.setFormulario(new FormularioForm());
				FacebookUtils.updateAjax("frm");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCampoForm() {
		if ((campo.getDescricao() != null && !campo.getDescricao().isEmpty()) && (campo.getTipo() != null)) {

			String idInput = FacebookUtils.removeAcentuacaoEEspaco(campo.getDescricao()).trim().toLowerCase();
			campo.setIdInput(idInput);
			campo.setSugestao(campo.getDescricao());

			if (getFormulario().getCampos().contains(campo)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Já existe um campo com esse nome!"));

				return;
			}

			if (campo.getTipo() == 4 || campo.getTipo() == 5 || campo.getTipo() == 6) {
				int qtdOpcoes = campo.getOpcoes().size();
				int idInputOpcao = qtdOpcoes++;
				OpcaoCampoFormularioForm opcao = new OpcaoCampoFormularioForm();
				opcao.setDescricao(campo.getDescricao());
				opcao.setIdInput(campo.getIdInput() + String.valueOf((idInputOpcao)));
				campo.getOpcoes().add(opcao);
			}

			getFormulario().getCampos().add(campo);
			this.setCampo(new CampoFormularioForm());

			gerarForm();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Campo adicionado!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção",
					"É necessário preencher a descrição e o tipo!"));
		}
	}

	public void removerCampoForm(Integer index) {
		if (index != null) {
			getFormulario().getCampos().remove(index.intValue());

			gerarForm();
		}
	}

	public void moverCampo(CampoFormularioForm campo, Integer index, String direction) {
		int orderPosition = index;
		boolean mudarPosicao = false;

		try {
			if (direction.equals("up") && orderPosition != 0) {
				Collections.swap(getFormulario().getCampos(), orderPosition - 1, orderPosition);
				mudarPosicao = true;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Subindo!"));
			}

			if (direction.equals("down") && orderPosition != getFormulario().getCampos().size()) {
				Collections.swap(getFormulario().getCampos(), orderPosition, orderPosition + 1);
				mudarPosicao = true;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Descendo!"));
			}

			if (mudarPosicao) {
				for (int i = 0; i < getFormulario().getCampos().size(); i++) {
					campo = getFormulario().getCampos().get(i);
				}
			}

			gerarForm();
		} catch (Exception e) {
			System.out.println("Erro ao mudar direção");
		}
	}

	public void addOpcaoCampo(CampoFormularioForm campo) {
		if (opcao.getDescricao() != null && !opcao.getDescricao().isEmpty()) {
			int qtdOpcoes = campo.getOpcoes().size();
			int idInputOpcao = qtdOpcoes++;

			opcao.setIdInput(campo.getIdInput() + String.valueOf((idInputOpcao)));
			campo.getOpcoes().add(opcao);
			this.setOpcao(new OpcaoCampoFormularioForm());

			gerarForm();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"opção adicionado no campo " + campo.getDescricao() + "!"));
		}
	}

	public void removerOpcaoCampo(CampoFormularioForm campo, Integer index) {
		if (index != null) {
			campo.getOpcoes().remove(index.intValue());
			gerarForm();
		}
	}

	public void moverOpcao(List<OpcaoCampoFormularioForm> opcoes, OpcaoCampoFormularioForm opcao, Integer index,
			String direction) {
		int orderPosition = index;
		boolean mudarPosicao = false;

		try {
			if (direction.equals("up") && orderPosition != 0) {
				Collections.swap(opcoes, orderPosition - 1, orderPosition);
				mudarPosicao = true;
			}

			if (direction.equals("down") && orderPosition != opcoes.size()) {
				Collections.swap(opcoes, orderPosition, orderPosition + 1);
				mudarPosicao = true;
			}
			if (mudarPosicao) {
				for (int i = 0; i < opcoes.size(); i++) {
					opcao = opcoes.get(i);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao mudar direção");
		}

		gerarForm();
	}

	public void gerarForm() {
		String json = gson.toJson(getFormulario());
		FacebookUtils.executeJavaScript("gerarForm(" + json + ");");
	}

	public String getTipoCampoNome(Integer tipo) {
		switch (tipo) {
		case 1:
			return "Texto";
		case 2:
			return "Área de texto";
		case 3:
			return "Número";
		case 4:
			return "Caixa de selecão";
		case 5:
			return "Selecione";
		case 6:
			return "Rádio";
		case 7:
			return "Email";
		case 8:
			return "Data";
		}

		return null;
	}

	public void verificarTitulo(CampoFormularioForm campo, Integer index) {
		List<CampoFormularioForm> campos = getFormulario().getCampos();

		if (campos.contains(campo) && campos.indexOf(campo) != index) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Já existe um campo com esse nome!"));

			return;
		} else {
			String idInput = FacebookUtils.removeAcentuacao(campo.getDescricao()).trim().toLowerCase();
			campo.setIdInput(idInput);
			FacebookUtils.updateAjax("titulo");
			FacebookUtils.updateAjax("frm:accordionCampos:" + index + ":tabTitle");
			gerarForm();
		}
	}

//	public void onColorChange(AjaxBehaviorEvent e) {
//		ColorPicker picker = (ColorPicker) e.getComponent();
//		getFormulario().setBackgroundColor(picker.getValue().toString());
//		FacebookUtils.updateAjax("form-builder:form-criado");
//		gerarForm();
//	}

	public FormularioForm getFormulario() {
		if (formulario == null) {
			this.formulario = new FormularioForm();
		}
		return formulario;
	}

	public void setFormulario(FormularioForm formulario) {
		this.formulario = formulario;
	}

	public CampoFormularioForm getCampo() {
		if (campo == null) {
			this.campo = new CampoFormularioForm();
		}
		return campo;
	}

	public void setCampo(CampoFormularioForm campo) {
		this.campo = campo;
	}

	public OpcaoCampoFormularioForm getOpcao() {
		if (opcao == null) {
			this.opcao = new OpcaoCampoFormularioForm();
		}
		return opcao;
	}

	public void setOpcao(OpcaoCampoFormularioForm opcao) {
		this.opcao = opcao;
	}

}
