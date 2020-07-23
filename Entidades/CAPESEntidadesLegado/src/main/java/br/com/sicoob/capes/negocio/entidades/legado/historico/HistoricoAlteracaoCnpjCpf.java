/*
 * SICOOB
 * 
 * HistoricoAlteracaoCnpjCpf.java(br.com.sicoob.capes.negocio.entidades.legado.HistoricoAlteracaoCnpjCpf)
 */
package br.com.sicoob.capes.negocio.entidades.legado.historico;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.negocio.entidades.legado.pk.HistoricoAlteracaoCnpjCpfPK;

/**
 * Entidade que representa a tabela de histórico de alteração de CPF/CNPJ
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "CLI_HistAltCNPJ_CPF")
public class HistoricoAlteracaoCnpjCpf extends
		EntidadeReplicavel<HistoricoAlteracaoCnpjCpfPK> {

	/** Serial UID. */
	private static final long serialVersionUID = 1053894845885026316L;

	/** Chave primária do historico de alteração. */
	@EmbeddedId
	private HistoricoAlteracaoCnpjCpfPK pk;

	/** O atributo id usuario. */
	private String idUsuario;
	
	/** O atributo nome solicitante. */
	private String nomeSolicitante;
	
	/** O atributo desc motivo. */
	private String descMotivo;

	/**
	 * @return the pk
	 */
	public HistoricoAlteracaoCnpjCpfPK getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(HistoricoAlteracaoCnpjCpfPK pk) {
		this.pk = pk;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the nomeSolicitante
	 */
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	/**
	 * @param nomeSolicitante
	 *            the nomeSolicitante to set
	 */
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	/**
	 * @return the descMotivo
	 */
	public String getDescMotivo() {
		return descMotivo;
	}

	/**
	 * @param descMotivo
	 *            the descMotivo to set
	 */
	public void setDescMotivo(String descMotivo) {
		this.descMotivo = descMotivo;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public HistoricoAlteracaoCnpjCpfPK getIdSQL() {
		return getPk();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof HistoricoAlteracaoCnpjCpfPK){
			setPk((HistoricoAlteracaoCnpjCpfPK)idSQL);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		
	}		
}
