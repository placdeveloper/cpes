package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe TipoDestinoExibicao.
 */
@Entity
@Table(name = "TIPODESTINOEXIBICAO", schema = "CLI")
public class TipoDestinoExibicao extends CAPESEntidade<Short> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2466951269020917757L;

	/** O atributo codTipoDestinoExibicao. */
	@Id
	@Column(name = "CODTIPODESTINOEXIBICAO")
	private Short codTipoDestinoExibicao;

	/** O atributo descricao. */
	@Column(name = "DESCTIPODESTINOEXIBICAO")
	private String descTipoDestinoExibicao;

	/** O atributo ativo. */
	@Column(name = "BOLATIVO")
	private Boolean ativo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodTipoDestinoExibicao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodTipoDestinoExibicao(id);
	}

	/**
	 * Recupera o valor de codTipoDestinoExibicao.
	 *
	 * @return o valor de codTipoDestinoExibicao
	 */
	public Short getCodTipoDestinoExibicao() {
		return codTipoDestinoExibicao;
	}

	/**
	 * Define o valor de codTipoDestinoExibicao.
	 *
	 * @param codTipoDestinoExibicao o novo valor de codTipoDestinoExibicao
	 */
	public void setCodTipoDestinoExibicao(Short codTipoDestinoExibicao) {
		this.codTipoDestinoExibicao = codTipoDestinoExibicao;
	}

	/**
	 * Recupera o valor de descTipoDestinoExibicao.
	 *
	 * @return o valor de descTipoDestinoExibicao
	 */
	public String getDescTipoDestinoExibicao() {
		return descTipoDestinoExibicao;
	}

	/**
	 * Define o valor de descTipoDestinoExibicao.
	 *
	 * @param descTipoDestinoExibicao o novo valor de descTipoDestinoExibicao
	 */
	public void setDescTipoDestinoExibicao(String descTipoDestinoExibicao) {
		this.descTipoDestinoExibicao = descTipoDestinoExibicao;
	}

	/**
	 * Recupera o valor de ativo.
	 *
	 * @return o valor de ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Define o valor de ativo.
	 *
	 * @param ativo o novo valor de ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codTipoDestinoExibicao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codTipoDestinoExibicao");
	}

}