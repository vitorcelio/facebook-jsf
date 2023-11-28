package com.facebook.jsf.service;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook.jsf.form.UsuarioForm;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioRepository repository;

	public void editarUsuario(UsuarioForm form, Long id) {
		try {
			Usuario usuario = repository.findById(id);
			usuario.setNome(form.getNome());
			usuario.setSobrenome(form.getSobrenome());
			usuario.setEmpresaQueTrabalha(form.getEmpresaQueTrabalha());
			usuario.setEscolaQueFrequentou(form.getEscolaQueFrequentou());
			usuario.setCidadeQueMora(form.getCidadeQueMora());
			usuario.setCidadeQueNasceu(form.getCidadeQueNasceu());
			usuario.setEstaEmRelacionamento(form.getEstaEmRelacionamento());
			usuario.setEstaCasado(form.getEstaCasado());
			usuario.setRelacionamentoCom(form.getRelacionamentoCom());
			usuario.setInstagram(form.getInstagram());
			usuario.setWhatsapp(form.getWhatsapp());
			usuario.setBiografia(form.getBiografia());
			usuario.setLongitude(form.getLongitude());
			usuario.setLatitude(form.getLatitude());
			repository.save(usuario);
			FacebookUtils.mostrarMsg("Usuário atualizado com sucesso!");
		} catch (Exception e) {
			// TODO: handle exception
			FacebookUtils.mostrarMsg("Erro ao atualizar Usuário!");
		}
	}

	public void addFotoPerfil(UsuarioForm form, Long id) {
		try {
			String fotoPerfil64 = null;
			String fotoPerfilMiniatura64 = null;

			try {
				fotoPerfil64 = FacebookUtils.getImagemBase64(form.getFotoPerfil());
				fotoPerfilMiniatura64 = FacebookUtils.getImagemBase64Miniatura(form.getFotoPerfil());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Usuario usuario = repository.findById(id);
			usuario.setFotoPerfil64(fotoPerfil64);
			usuario.setFotoMiniatura(fotoPerfilMiniatura64);
			repository.save(usuario);
			FacebookUtils.mostrarMsg("Usuário atualizado com sucesso!");
		} catch (Exception e) {
			// TODO: handle exception
			FacebookUtils.mostrarMsg("Erro ao atualizar Usuário!");
		}
	}

	public void addFotoCapa(UsuarioForm form, Long id) {
		try {
			String fotoCapa64 = null;

			try {
				fotoCapa64 = FacebookUtils.getImagemBase64(form.getFotoCapa());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Usuario usuario = repository.findById(id);
			usuario.setFotoCapa64(fotoCapa64);
			FacebookUtils.mostrarMsg("Usuário atualizado com sucesso!");
		} catch (Exception e) {
			// TODO: handle exception
			FacebookUtils.mostrarMsg("Erro ao atualizar Usuário!");
		}
	}
}
