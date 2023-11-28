package com.facebook.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.facebook.jsf.form.CampoFormularioForm;
import com.facebook.jsf.form.FormularioForm;
import com.facebook.jsf.form.OpcaoCampoFormularioForm;
import com.facebook.jsf.form.RespostasCampoForm;
import com.facebook.jsf.modelo.CampoFormulario;
import com.facebook.jsf.modelo.Formulario;
import com.facebook.jsf.modelo.OpcaoCampoFormulario;
import com.facebook.jsf.modelo.RespostaCampoFormulario;
import com.facebook.jsf.repository.FormularioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Stateless
public class FormularioDao implements FormularioRepository {

	@PersistenceContext(unitName = "facebook")
	private EntityManager em;

	@Override
	public void salvarForm(FormularioForm form) {
		Formulario formulario = new Formulario();
		formulario.setDescricao(form.getDescricao());
		formulario.setAtivo(form.getAtivo());
		formulario.setTxtBotao(form.getTextoBotao());
		formulario.setImagemAtiva(form.getImagemAtiva());

		String token = getTokenForm(form);
		formulario.setToken(token);

		this.em.persist(formulario);

		if (!form.getCampos().isEmpty()) {
			for (CampoFormularioForm c : form.getCampos()) {
				CampoFormulario campo = new CampoFormulario();
				campo.setDescricao(c.getDescricao());
				campo.setFormulario(formulario);
				campo.setTipo(c.getTipo());
				campo.setIdInput(c.getIdInput());
				campo.setSugestao(c.getSugestao());
				campo.setObrigatorio(c.getObrigatorio());
				this.em.persist(campo);

				if (!c.getOpcoes().isEmpty()) {
					for (OpcaoCampoFormularioForm o : c.getOpcoes()) {
						OpcaoCampoFormulario opcao = new OpcaoCampoFormulario();
						opcao.setDescricao(o.getDescricao());
						opcao.setCampoFormulario(campo);
						opcao.setIdInput(o.getIdInput());
						this.em.persist(opcao);
					}
				}
			}
		}
	}

	private String getTokenForm(FormularioForm form) {
		Integer token = FacebookUtils.gerarHash(form.getDescricao(), form.getTextoBotao(), form.hashCode());

		Formulario formulario = null;

		try {
			formulario = findByToken(String.valueOf(token));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return formulario == null ? String.valueOf(token) : getTokenForm(form);
	}

	@Override
	public Formulario findById(Integer id) {
		Formulario formulario = null;

		try {
			formulario = this.em.find(Formulario.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return formulario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CampoFormulario> getAllByForm(Integer id) {
		String sql = "select * from campoformulario where formulario_id = :idForm";

		List<CampoFormulario> list = null;
		try {
			list = (List<CampoFormulario>) this.em.createNativeQuery(sql, CampoFormulario.class)
					.setParameter("idForm", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpcaoCampoFormulario> getAllByCampo(Integer id) {
		String sql = "select * from opcaocampoformulario where campoformulario_id = :idCampo";

		List<OpcaoCampoFormulario> list = null;
		try {
			list = (List<OpcaoCampoFormulario>) this.em.createNativeQuery(sql, OpcaoCampoFormulario.class)
					.setParameter("idCampo", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Formulario findByToken(String token) {
		Formulario form = null;

		try {
			form = (Formulario) this.em
					.createNativeQuery("select * from formulario where token = :token", Formulario.class)
					.setParameter("token", String.valueOf(token)).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return form != null ? form : null;
	}

	@Override
	public CampoFormulario findByIdCampo(Integer id) {
		return this.em.find(CampoFormulario.class, id);
	}

	@Override
	public void salvarResposta(List<RespostasCampoForm> respostas) {
		Integer token = FacebookUtils.gerarHash(respostas.get(0).getResposta(),
				String.valueOf(respostas.get(0).getIdCampo()), respostas.get(0).hashCode());

		for (RespostasCampoForm resp : respostas) {
			CampoFormulario campo = findByIdCampo(resp.getIdCampo());

			RespostaCampoFormulario resposta = new RespostaCampoFormulario();
			resposta.setCampoFormulario(campo);
			resposta.setResposta(resp.getResposta());
			resposta.setToken(String.valueOf(token));

			this.em.persist(resposta);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getTokens(Integer idForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct respo.token from respostacampoformulario respo, campoformulario cmp where 1=1 and ");
		sql.append(" respo.campoformulario_id = cmp.id and cmp.formulario_id = :idForm ");

		List<String> tokens = null;
		try {
			tokens = (List<String>) this.em.createNativeQuery(sql.toString()).setParameter("idForm", idForm)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tokens != null ? tokens : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RespostaCampoFormulario> getRespostasByToken(String token) {
		String sql = " select * from respostacampoformulario respo where respo.token = :token ";

		List<RespostaCampoFormulario> respostas = null;
		try {
			respostas = (List<RespostaCampoFormulario>) this.em.createNativeQuery(sql, RespostaCampoFormulario.class)
					.setParameter("token", token).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respostas != null ? respostas : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Formulario> getAllForms() {
		return (List<Formulario>) this.em.createNativeQuery("select * from formulario", Formulario.class)
				.getResultList();
	}
}
