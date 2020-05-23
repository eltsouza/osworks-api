package com.algaworks.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {
	
	private Integer Status;
	//private LocalDateTime dataHora;
	private OffsetDateTime dataHora;
	private String titulo;	
	private List<Campo> campos;
	
	public static class Campo {
		
		private String campo;
		private String mensagem;
		
		public Campo(String campo, String mensagem) {
			super();
			this.campo = campo;
			this.mensagem = mensagem;
		}
		public String getcampo() {
			return campo;
		}
		public void setcampo(String campo) {
			this.campo = campo;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
	}
			
	public List<Campo> getCampos() {
		return campos;
	}
	
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
