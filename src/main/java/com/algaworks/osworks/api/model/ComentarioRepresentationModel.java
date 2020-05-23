package com.algaworks.osworks.api.model;

import java.time.OffsetDateTime;

public class ComentarioRepresentationModel {
	
	private Long id;
	private String descricao;
	private OffsetDateTime dataEnvio;
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}	
}
