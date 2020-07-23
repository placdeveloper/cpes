package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.pk.CidadaniaPK;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import flexjson.JSON;

/**
 * A Classe Cidadania.
 */
@Entity
@Table(name = "CIDADANIAPESSOA", schema = "CLI")
public class Cidadania extends CAPESEntidade<CidadaniaPK> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7851431588073967798L;

	/** O atributo pk. */
	@EmbeddedId
	private CidadaniaPK pk;

	/** O atributo nacionalidade. */
	@ManyToOne
	@JoinColumn(name = "CODNACIONALIDADE", insertable = false, updatable = false)
	private Nacionalidade nacionalidade;
	
	/** O atributo pessoaFisica. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO", referencedColumnName = "IDPESSOACOMPARTILHAMENTO", insertable = false, updatable = false)
	@ManyToOne
	private PessoaFisica pessoaFisica;

	/**
	 * Recupera o valor de pk.
	 *
	 * @return o valor de pk
	 */
	public CidadaniaPK getPk() {
		return pk;
	}

	/**
	 * Define o valor de pk.
	 *
	 * @param pk o novo valor de pk
	 */
	public void setPk(CidadaniaPK pk) {
		this.pk = pk;
	}

	/**
	 * Recupera o valor de nacionalidade.
	 *
	 * @return o valor de nacionalidade
	 */
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	/**
	 * Define o valor de nacionalidade.
	 *
	 * @param nacionalidade o novo valor de nacionalidade
	 */
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CidadaniaPK getId() {
		return pk;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(CidadaniaPK id) {
		this.pk = id;
	}

	/**
	 * Recupera o valor de pessoaFisica.
	 *
	 * @return o valor de pessoaFisica
	 */
	@JSON(include=false)
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	/**
	 * Define o valor de pessoaFisica.
	 *
	 * @param pessoaFisica o novo valor de pessoaFisica
	 */
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
}