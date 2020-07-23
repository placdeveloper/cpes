/*
 * SICOOB
 * 
 * BancoServidor.java(br.com.sicoob.capes.negocio.entidades.legado.BancoServidor)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;

/**
 * The Class BancoServidor.
 */
@Entity
@Table(name="VIW_BANCO_SERVIDOR")
public class BancoServidor extends CAPESEntidadeLegado<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo num cooperativa. */
	@Id
	private Integer numCooperativa;
	
	/** O atributo id server. */
	@Column(insertable = false, updatable = false)
	private Integer idServer;
	
	/** O atributo nome server. */
	@Column(insertable = false, updatable = false)
	private String nomeServer;

	/** O atributo nome banco dados. */
	@Column(insertable = false, updatable = false)
	private String nomeBancoDados;
	
	/** O atributo cod grupo compartilhamento. */
	@Transient
	private Short codGrupoCompartilhamento;
	
	/** O atributo id instituicao. */
	@Transient
	private Integer idInstituicao;
	
	/**
	 * Cria uma nova instância de banco servidor.
	 */
	public BancoServidor() {
		super();
	}
	
	/**
	 * Cria uma nova instância de banco servidor.
	 * 
	 * @param nomeServer
	 *            the nome server
	 * @param nomeBancoDados
	 *            the nome banco dados
	 * @param numCooperativa
	 *            the num cooperativa
	 */
	public BancoServidor(String nomeServer, String nomeBancoDados, Integer numCooperativa) {
		super();
		this.nomeServer = nomeServer;
		this.nomeBancoDados = nomeBancoDados;
		this.numCooperativa = numCooperativa;
	}
	
	/**
	 * @return the numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	/**
	 * @param numCooperativa the numCooperativa to set
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	/**
	 * @return the idServer
	 */
	public Integer getIdServer() {
		return idServer;
	}
	/**
	 * @param idServer the idServer to set
	 */
	public void setIdServer(Integer idServer) {
		this.idServer = idServer;
	}
	
	/**
	 * @return the nomeServer
	 */
	public String getNomeServer() {
		return nomeServer;
	}
	
	/**
	 * @param nomeServer the nomeServer to set
	 */
	public void setNomeServer(String nomeServer) {
		this.nomeServer = nomeServer;
	}
	
	/**
	 * @return the nomeBancoDados
	 */
	public String getNomeBancoDados() {
		return nomeBancoDados;
	}
	
	/**
	 * @param nomeBancoDados the nomeBancoDados to set
	 */
	public void setNomeBancoDados(String nomeBancoDados) {
		this.nomeBancoDados = nomeBancoDados;
	}
	
	/**
	 * Recupera cod grupo compartilhamento.
	 * 
	 * @return cod grupo compartilhamento
	 */
	public Short getCodGrupoCompartilhamento() {
		return codGrupoCompartilhamento;
	}

	/**
	 * Preenche cod grupo compartilhamento.
	 * 
	 * @param codGrupoCompartilhamento
	 *            o novo cod grupo compartilhamento
	 */
	public void setCodGrupoCompartilhamento(Short codGrupoCompartilhamento) {
		this.codGrupoCompartilhamento = codGrupoCompartilhamento;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public String toString() {
		return nomeServer + "." + nomeBancoDados;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getNumCooperativa();
	}
}