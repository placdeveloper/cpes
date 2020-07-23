package br.com.sicoob.capes.relatorio.negocio.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelCompartilhamentoVO.
 */
public class RelCompartilhamentoVO extends BancoobVo implements Serializable {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** O atributo central. */
	private Integer central;
	
	/** O atributo responsavel. */
	private Integer responsavel;
	
	/** O atributo solicitante. */
	private Integer solicitante;
	
	/** O atributo usuario. */
	private String usuario;
	
	/** O atributo dataHora. */
	private Timestamp dataHora;
	
	/** O atributo compartilhado. */
	private String compartilhado;
	
	/** O atributo cpfCnpjCadastroCompartilhado. */
	private String cpfCnpjCadastroCompartilhado;
	
	/** O atributo nomeCadastroCompartilhado. */
	private String nomeCadastroCompartilhado;
	
	/**
	 * Recupera o valor de central.
	 *
	 * @return o valor de central
	 */
	public Integer getCentral() {
		return central;
	}
	
	/**
	 * Define o valor de central.
	 *
	 * @param central o novo valor de central
	 */
	public void setCentral(Integer central) {
		this.central = central;
	}
	
	/**
	 * Recupera o valor de responsavel.
	 *
	 * @return o valor de responsavel
	 */
	public Integer getResponsavel() {
		return responsavel;
	}
	
	/**
	 * Define o valor de responsavel.
	 *
	 * @param responsavel o novo valor de responsavel
	 */
	public void setResponsavel(Integer responsavel) {
		this.responsavel = responsavel;
	}
	
	/**
	 * Recupera o valor de solicitante.
	 *
	 * @return o valor de solicitante
	 */
	public Integer getSolicitante() {
		return solicitante;
	}
	
	/**
	 * Define o valor de solicitante.
	 *
	 * @param solicitante o novo valor de solicitante
	 */
	public void setSolicitante(Integer solicitante) {
		this.solicitante = solicitante;
	}
	
	/**
	 * Recupera o valor de usuario.
	 *
	 * @return o valor de usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Define o valor de usuario.
	 *
	 * @param usuario o novo valor de usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Recupera o valor de dataHora.
	 *
	 * @return o valor de dataHora
	 */
	public Timestamp getDataHora() {
		return dataHora;
	}
	
	/**
	 * Define o valor de dataHora.
	 *
	 * @param datahora o novo valor de dataHora
	 */
	public void setDataHora(Timestamp datahora) {
		this.dataHora = datahora;
	}
	
	/**
	 * Recupera o valor de compartilhado.
	 *
	 * @return o valor de compartilhado
	 */
	public String getCompartilhado() {
		return compartilhado;
	}
	
	/**
	 * Define o valor de compartilhado.
	 *
	 * @param compartilhado o novo valor de compartilhado
	 */
	public void setCompartilhado(String compartilhado) {
		this.compartilhado = compartilhado;
	}
	
	/**
	 * Recupera o valor de cpfCnpjCadastroCompartilhado.
	 *
	 * @return o valor de cpfCnpjCadastroCompartilhado
	 */
	public String getCpfCnpjCadastroCompartilhado() {
		return cpfCnpjCadastroCompartilhado;
	}
	
	/**
	 * Define o valor de cpfCnpjCadastroCompartilhado.
	 *
	 * @param cpfCnpjCadastroCompartilhado o novo valor de cpfCnpjCadastroCompartilhado
	 */
	public void setCpfCnpjCadastroCompartilhado(String cpfCnpjCadastroCompartilhado) {
		this.cpfCnpjCadastroCompartilhado = cpfCnpjCadastroCompartilhado;
	}
	
	/**
	 * Recupera o valor de nomeCadastroCompartilhado.
	 *
	 * @return o valor de nomeCadastroCompartilhado
	 */
	public String getNomeCadastroCompartilhado() {
		return nomeCadastroCompartilhado;
	}
	
	/**
	 * Define o valor de nomeCadastroCompartilhado.
	 *
	 * @param nomeCadastroCompartilhado o novo valor de nomeCadastroCompartilhado
	 */
	public void setNomeCadastroCompartilhado(String nomeCadastroCompartilhado) {
		this.nomeCadastroCompartilhado = nomeCadastroCompartilhado;
	}
}