package com.sistema.blog.seguridad;

public class JWTAuthResponseDTO {
	private String tokeDeAcceso;
	private String tipoDeToken = "Bearer";

	public JWTAuthResponseDTO(String tokeDeAcceso, String tipoDeToken) {
		super();
		this.tokeDeAcceso = tokeDeAcceso;
		this.tipoDeToken = tipoDeToken;
	}

	public String getTokeDeAcceso() {
		return tokeDeAcceso;
	}

	public void setTokeDeAcceso(String tokeDeAcceso) {
		this.tokeDeAcceso = tokeDeAcceso;
	}

	public String getTipoDeToken() {
		return tipoDeToken;
	}

	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}

	public JWTAuthResponseDTO(String tokeDeAcceso) {
		super();
		this.tokeDeAcceso = tokeDeAcceso;
	}

}
