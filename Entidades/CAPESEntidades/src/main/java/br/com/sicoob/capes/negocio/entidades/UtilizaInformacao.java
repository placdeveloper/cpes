package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe UtilizaInformacao.
 */
@Entity
@Table(schema = "CLI", name = "UTILIZAINFORMACAOPESSOA")
public class UtilizaInformacao extends CAPESEntidade<Short> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3621176415977448548L;

	/** O atributo codigo. */
	@Id
	@Column(name = "IDUTILIZAINFORMACAOPESSOA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCUTILIZAINFORMACAOPESSOA", length = 100, nullable = false)
	private String descricao;

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Define o valor de codigo.
	 *
	 * @param codigo o novo valor de codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define o valor de descricao.
	 *
	 * @param descricao o novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodigo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short codigo) {
		setCodigo(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.descricao != null ? this.descricao : "";
	}

}