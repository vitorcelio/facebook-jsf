package com.facebook.jsf.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.facebook.jsf.modelo.Formulario;

public class FormularioForm {

	@NotBlank(message = "O formulário deve ter uma descrição!")
	private String descricao = "Edite o título do seu Formulário";
	private Integer id;
	private Boolean imagemAtiva;
	private String imagem = "/resources/img/form.png";
	private Boolean ativo = true;
	private List<CampoFormularioForm> campos;
	private String textoBotao = "Enviar";

	public FormularioForm() {
	}

	public FormularioForm(Formulario form) {
		this.id = form.getId();
		this.ativo = form.getAtivo();
		this.descricao = form.getDescricao();
		this.textoBotao = form.getTxtBotao();
		this.imagemAtiva = form.getImagemAtiva();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getImagemAtiva() {
		if (imagemAtiva == null) {
			return true;
		}

		return imagemAtiva;
	}

	public void setImagemAtiva(Boolean imagemAtiva) {
		this.imagemAtiva = imagemAtiva;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<CampoFormularioForm> getCampos() {
		if (this.campos == null) {
			this.campos = new ArrayList<>();
		}
		return campos;
	}

	public void setCampos(List<CampoFormularioForm> campos) {
		this.campos = campos;
	}

	public String getTextoBotao() {
		if (textoBotao.isEmpty()) {
			return "Enviar";
		}

		return textoBotao;
	}

	public void setTextoBotao(String textoBotao) {
		this.textoBotao = textoBotao;
	}

	public static List<FormularioForm> converter(List<Formulario> list) {
		return list.stream().map(FormularioForm::new).collect(Collectors.toList());
	}

}
