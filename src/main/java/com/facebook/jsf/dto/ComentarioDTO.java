package com.facebook.jsf.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.facebook.jsf.modelo.Comentario;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private UsuarioDTO usuario;
	private String texto;
	private List<RespostaDTO> respostas;
	private String dataPublicacao;

	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.usuario = new UsuarioDTO(comentario.getUsuario());
		this.texto = comentario.getTexto();
		this.respostas = RespostaDTO.listar(comentario.getRespostas());
		this.dataPublicacao = validacao(comentario.getDataPublicacao());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaDTO> respostas) {
		this.respostas = respostas;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public static List<ComentarioDTO> listar(List<Comentario> list) {
		return list.stream().map(ComentarioDTO::new).collect(Collectors.toList());
	}

	@SuppressWarnings("deprecation")
	public static String validacao(Date d) {

		LocalDate d1 = LocalDate.of((d.getYear() + 1900), d.getMonth(), d.getDate());
		LocalDate d2 = LocalDate.now();

		Period period = Period.between(d2, d1);

		int periodo = period.getDays() < 0 ? (period.getDays() * -1) : period.getDays();
		int periodoMes = period.getMonths() < 0 ? (period.getMonths() * -1) : period.getMonths();
		int periodoAno = period.getYears() < 0 ? (period.getYears() * -1) : period.getYears();
		if (periodo < 1) {
			return "menos de um dia";
		} else if (periodo >= 1 && periodo < 7) {

			if (periodo < 2) {
				return periodo + " dia";
			}

			return periodo + " dias";

		} else if (periodo >= 7 && periodo <= 31) {

			int x = periodo;
			String data = "";

			if (x >= 7 && x <= 13) {
				data = "1 sem";
			} else if (x >= 14 && x <= 20) {
				data = "2 sem";
			} else if (x >= 21 && x <= 29) {
				data = "3 sem";
			} else if (x >= 30 && x <= 31) {
				data = "4 sem";
			}

			return data;

		} else if (periodo >= 32 && periodoMes >= 1) {
			if (periodoMes < 2) {
				return periodoMes + " mês";
			}

			return periodoMes + " meses";

		} else if (periodoMes >= 12 && periodoAno >= 1) {
			if (periodoAno < 2) {
				return periodoAno + " ano";
			}

			return periodoAno + " anos";
		}

		return null;
	}

}
