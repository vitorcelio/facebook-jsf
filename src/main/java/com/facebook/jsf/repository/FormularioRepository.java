package com.facebook.jsf.repository;

import java.util.List;

import com.facebook.jsf.form.FormularioForm;
import com.facebook.jsf.form.RespostasCampoForm;
import com.facebook.jsf.modelo.CampoFormulario;
import com.facebook.jsf.modelo.Formulario;
import com.facebook.jsf.modelo.OpcaoCampoFormulario;
import com.facebook.jsf.modelo.RespostaCampoFormulario;

public interface FormularioRepository {

	public void salvarForm(FormularioForm form);

	public Formulario findById(Integer id);

	public Formulario findByToken(String token);

	public CampoFormulario findByIdCampo(Integer id);

	public List<CampoFormulario> getAllByForm(Integer id);

	public List<OpcaoCampoFormulario> getAllByCampo(Integer id);

	public void salvarResposta(List<RespostasCampoForm> resposta);

	public List<String> getTokens(Integer idForm);

	public List<RespostaCampoFormulario> getRespostasByToken(String token);

	public List<Formulario> getAllForms();
}
