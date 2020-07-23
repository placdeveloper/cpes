package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.pk.InformacaoUtilizadaPK;

/**
 * A Classe InformacaoUtilizada.
 */
@Entity
@Table(schema = "CLI", name = "TIPOINFUTILIZAINFPESSOA")
public class InformacaoUtilizada extends CAPESEntidade<InformacaoUtilizadaPK> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1164858216158627330L;

	/** O atributo pk. */
	@EmbeddedId
	private InformacaoUtilizadaPK pk;

	/** O atributo tipoInformacao. */
	@ManyToOne
	@JoinColumn(name = "IDTIPOINFORMACAOPESSOA", insertable = false, updatable = false)
	private TipoInformacao tipoInformacao;

	/** O atributo utilizaInformacao. */
	@JoinColumn(name = "IDUTILIZAINFORMACAOPESSOA", insertable = false, updatable = false)
	@ManyToOne
	private UtilizaInformacao utilizaInformacao;

	/**
	 * Recupera o valor de pk.
	 *
	 * @return o valor de pk
	 */
	public InformacaoUtilizadaPK getPk() {
		return pk;
	}

	/**
	 * Define o valor de pk.
	 *
	 * @param pk o novo valor de pk
	 */
	public void setPk(InformacaoUtilizadaPK pk) {
		this.pk = pk;
	}

	/**
	 * Recupera o valor de tipoInformacao.
	 *
	 * @return o valor de tipoInformacao
	 */
	public TipoInformacao getTipoInformacao() {
		return tipoInformacao;
	}

	/**
	 * Define o valor de tipoInformacao.
	 *
	 * @param tipoInformacao o novo valor de tipoInformacao
	 */
	public void setTipoInformacao(TipoInformacao tipoInformacao) {
		this.tipoInformacao = tipoInformacao;
	}

	/**
	 * Recupera o valor de utilizaInformacao.
	 *
	 * @return o valor de utilizaInformacao
	 */
	public UtilizaInformacao getUtilizaInformacao() {
		return utilizaInformacao;
	}

	/**
	 * Define o valor de utilizaInformacao.
	 *
	 * @param utilizaInformacao o novo valor de utilizaInformacao
	 */
	public void setUtilizaInformacao(UtilizaInformacao utilizaInformacao) {
		this.utilizaInformacao = utilizaInformacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InformacaoUtilizadaPK getId() {
		return getPk();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(InformacaoUtilizadaPK id) {
		setPk(id);
	}

}