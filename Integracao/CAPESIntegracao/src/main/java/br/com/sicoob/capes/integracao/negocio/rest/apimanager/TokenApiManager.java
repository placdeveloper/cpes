package br.com.sicoob.capes.integracao.negocio.rest.apimanager;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa um TOKEN do API MANAGER
 * 
 * @author Bruno.Carneiro
 */
public class TokenApiManager implements Serializable {
	private static final long serialVersionUID = -3579174536635382553L;

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("scope")
	private String escopo;

	@SerializedName("token_type")
	private String tipo;

	@SerializedName("expires_in")
	private Long expiraEm;

	@SerializedName("refresh_token")
	private String refreshToken;

	private transient Long tempoExpiracao;

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getExpiraEm() {
		return expiraEm;
	}

	public void setExpiraEm(Long expiraEm) {
		this.expiraEm = expiraEm;
		this.tempoExpiracao = System.currentTimeMillis() + expiraEm * 1000;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getTempoExpiracao() {
		return tempoExpiracao;
	}

	public void setTempoExpiracao(Long tempoExpiracao) {
		this.tempoExpiracao = tempoExpiracao;
	}

	public Boolean isExpirado() {
		return System.currentTimeMillis() > tempoExpiracao;
	}

}