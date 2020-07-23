package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe CooperativaAnotacaoPK.
 */
@Embeddable
public class CooperativaAnotacaoPK extends BancoobChavePrimaria {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5161144949421313555L;

	/** O atributo codTipoAnotacao. */
	@Column(name = "CODTIPOANOTACAO")
	private Integer codTipoAnotacao;

	/** O atributo idInstituicao. */
	@Column(name = "IDINSTITUICAO")
	private Integer idInstituicao;

	/**
	 * Recupera o valor de codTipoAnotacao.
	 *
	 * @return o valor de codTipoAnotacao
	 */
	public Integer getCodTipoAnotacao() {
		return codTipoAnotacao;
	}

	/**
	 * Define o valor de codTipoAnotacao.
	 *
	 * @param codTipoAnotacao o novo valor de codTipoAnotacao
	 */
	public void setCodTipoAnotacao(Integer codTipoAnotacao) {
		this.codTipoAnotacao = codTipoAnotacao;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codTipoAnotacao", "idInstituicao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codTipoAnotacao", "idInstituicao");
	}

}