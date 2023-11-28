package com.facebook.jsf.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.facebook.jsf.modelo.Publicacao;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.PublicacaoRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Model
public class CadastroBean {

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioRepository dao;
	
	@Inject
	private PublicacaoRepository publicacaoRepository;
	
	private Integer dia;
	private Integer mes;
	private Integer ano;
	
	@SuppressWarnings("deprecation")
	public String cadastrar() {
		Date dataNascimento = null;
		try {
			dataNascimento = new Date((ano - 1900), (mes - 1), dia);
			usuario.setDataQueEntrou(new Date());
			usuario.setDataNascimento(dataNascimento);
			usuario.setEstaCasado(false);
			usuario.setEstaEmRelacionamento(false);
			String username = FacebookUtils.removeAcentuacaoEEspaco(usuario.getNome()) + FacebookUtils.gerarHash(usuario.getNome(), usuario.getEmail(), usuario.hashCode());
			usuario.setUsername(username);
			dao.save(usuario);
			System.out.println("Usuário salvo!");
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}

		Publicacao publicacao = new Publicacao();
		publicacao.setContentType("image/png");
		publicacao.setDataPublicacao(dataNascimento);
		publicacao.setDescricao("Nasceu em " + new SimpleDateFormat("dd :1 MM :1 y", new Locale("pt", "BR")).format(usuario.getDataNascimento()).replace(":1", "de"));
		publicacao.setImagemBase64(FacebookUtils.imagemBase64PublicacaoNasceu);
		publicacao.setUsuario(usuario);
		publicacao.setCriacaoConta(true);
		publicacaoRepository.save(publicacao);
		return "login.jsf?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
