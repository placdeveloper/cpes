/*
 * SICOOB
 * 
 * PerfilTarifario.java(br.com.sicoob.capes.negocio.entidades.PerfilTarifario)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK;

/**
 * Classe que representa o perfil tarifario.
 * @author juan.damasceno
 *
 */
@Entity
@Table(name="PerfilTarifario",schema="CLI")
public class PerfilTarifario extends
		CAPESEntidade<PerfilTarifarioPK> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1491515586549937675L;
	
	/** O atributo pk. */
	@EmbeddedId
	private PerfilTarifarioPK pk;
	
	/** O atributo sigla. */
	@Column(name="SIGLAPERFILTARIFARIO")
	private String sigla;
	
	/** O atributo descricao. */
	@Column(name="DESCPERFILTARIFARIO")
	private String descricao;
	
	/** O atributo valor percentual. */
	private BigDecimal valorPercentual;
	
	/** O atributo cod tipo percentual. */
	private Short codTipoPercentual;
	
	/** O atributo cod tipo perfil conta. */
	private Short codTipoPerfilConta;

	/** O atributo codigo tipo perfil. */
	@Column(name = "CODTIPOPERFIL")
	private Short codigoTipoPerfil;
	
	/** O atributo isento. */
	@Column(name="BOLISENTO")
	private Boolean isento;
	
	/**
	 * Recupera pk.
	 * 
	 * @return pk
	 */
	public PerfilTarifarioPK getPk() {
		return pk;
	}

	/**
	 * Preenche pk.
	 * 
	 * @param pk
	 *            o novo pk
	 */
	public void setPk(PerfilTarifarioPK pk) {
		this.pk = pk;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valorPercentual
	 */
	public BigDecimal getValorPercentual() {
		return valorPercentual;
	}

	/**
	 * @param valorPercentual the valorPercentual to set
	 */
	public void setValorPercentual(BigDecimal valorPercentual) {
		this.valorPercentual = valorPercentual;
	}

	/**
	 * @return the codTipoPercentual
	 */
	public Short getCodTipoPercentual() {
		return codTipoPercentual;
	}

	/**
	 * @param codTipoPercentual the codTipoPercentual to set
	 */
	public void setCodTipoPercentual(Short codTipoPercentual) {
		this.codTipoPercentual = codTipoPercentual;
	}

	/**
	 * @return the codTipoPerfilConta
	 */
	public Short getCodTipoPerfilConta() {
		return codTipoPerfilConta;
	}

	/**
	 * @param codTipoPerfilConta the codTipoPerfilConta to set
	 */
	public void setCodTipoPerfilConta(Short codTipoPerfilConta) {
		this.codTipoPerfilConta = codTipoPerfilConta;
	}

	/**
	 * Recupera codigo tipo perfil.
	 * 
	 * @return codigo tipo perfil
	 */
	public Short getCodigoTipoPerfil() {
		return codigoTipoPerfil;
	}

	/**
	 * Preenche codigo tipo perfil.
	 * 
	 * @param codigoTipoPerfil
	 *            o novo codigo tipo perfil
	 */
	public void setCodigoTipoPerfil(Short codigoTipoPerfil) {
		this.codigoTipoPerfil = codigoTipoPerfil;
	}

	/**
	 * @return the isento
	 */
	public Boolean getIsento() {
		return isento;
	}

	/**
	 * @param isento the isento to set
	 */
	public void setIsento(Boolean isento) {
		this.isento = isento;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PerfilTarifarioPK getId() {
		return pk;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(PerfilTarifarioPK id) {
		this.pk = id;
	}
}