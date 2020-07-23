package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.pk.CooperativaAnotacaoPK;

/**
 * A Classe CooperativaAnotacao.
 */
@Entity
@Table(schema = "CLI", name = "COOPERATIVAANOTACAO")
public class CooperativaAnotacao extends CAPESEntidade<CooperativaAnotacaoPK> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6763056176125390606L;

	/** O atributo pk. */
	@EmbeddedId
	private CooperativaAnotacaoPK pk;

	/** O atributo tipoAnotacao. */
	@JoinColumn(name = "CODTIPOANOTACAO", referencedColumnName = "CODTIPOANOTACAO", insertable = false, updatable = false)
	@ManyToOne
	private TipoAnotacao tipoAnotacao;

	/**
	 * Recupera o valor de pk.
	 *
	 * @return o valor de pk
	 */
	public CooperativaAnotacaoPK getPk() {
		return pk;
	}

	/**
	 * Define o valor de pk.
	 *
	 * @param pk o novo valor de pk
	 */
	public void setPk(CooperativaAnotacaoPK pk) {
		this.pk = pk;
	}

	/**
	 * Recupera o valor de tipoAnotacao.
	 *
	 * @return o valor de tipoAnotacao
	 */
	public TipoAnotacao getTipoAnotacao() {
		return tipoAnotacao;
	}

	/**
	 * Define o valor de tipoAnotacao.
	 *
	 * @param tipoAnotacao o novo valor de tipoAnotacao
	 */
	public void setTipoAnotacao(TipoAnotacao tipoAnotacao) {
		this.tipoAnotacao = tipoAnotacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CooperativaAnotacaoPK getId() {
		return getPk();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(CooperativaAnotacaoPK id) {
		setPk(id);
	}

}