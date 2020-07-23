package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe InformacaoUtilizadaPK.
 */
@Embeddable
public class InformacaoUtilizadaPK extends BancoobChavePrimaria {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 801176456408971993L;

	/** O atributo codigoInformacao. */
	@Column(name = "IDINFORMACAOPESSOA")
	private Long codigoInformacao;

	/** O atributo codigoUtilizaInformacao. */
	@Column(name = "IDUTILIZAINFORMACAOPESSOA")
	private Short codigoUtilizaInformacao;

	/** O atributo codigoTipoInformacao. */
	@Column(name = "IDTIPOINFORMACAOPESSOA")
	private Short codigoTipoInformacao;

	/**
	 * Recupera o valor de codigoInformacao.
	 *
	 * @return o valor de codigoInformacao
	 */
	public Long getCodigoInformacao() {
		return codigoInformacao;
	}

	/**
	 * Define o valor de codigoInformacao.
	 *
	 * @param codigoInformacao o novo valor de codigoInformacao
	 */
	public void setCodigoInformacao(Long codigoInformacao) {
		this.codigoInformacao = codigoInformacao;
	}

	/**
	 * Recupera o valor de codigoUtilizaInformacao.
	 *
	 * @return o valor de codigoUtilizaInformacao
	 */
	public Short getCodigoUtilizaInformacao() {
		return codigoUtilizaInformacao;
	}

	/**
	 * Define o valor de codigoUtilizaInformacao.
	 *
	 * @param codigoUtilizaInformacao o novo valor de codigoUtilizaInformacao
	 */
	public void setCodigoUtilizaInformacao(Short codigoUtilizaInformacao) {
		this.codigoUtilizaInformacao = codigoUtilizaInformacao;
	}

	/**
	 * Recupera o valor de codigoTipoInformacao.
	 *
	 * @return o valor de codigoTipoInformacao
	 */
	public Short getCodigoTipoInformacao() {
		return codigoTipoInformacao;
	}

	/**
	 * Define o valor de codigoTipoInformacao.
	 *
	 * @param codigoTipoInformacao o novo valor de codigoTipoInformacao
	 */
	public void setCodigoTipoInformacao(Short codigoTipoInformacao) {
		this.codigoTipoInformacao = codigoTipoInformacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigoInformacao", "codigoUtilizaInformacao", "codigoTipoInformacao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigoInformacao", "codigoUtilizaInformacao", "codigoTipoInformacao");
	}

}