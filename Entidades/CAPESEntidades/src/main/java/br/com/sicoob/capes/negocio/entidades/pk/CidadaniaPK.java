package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe CidadaniaPK.
 */
@Embeddable
public class CidadaniaPK extends BancoobChavePrimaria {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4537260283312645412L;

	/** O atributo idPessoaCompartilhamento. */
	@Column(name = "IDPESSOACOMPARTILHAMENTO")
	private Long idPessoaCompartilhamento;

	/** O atributo codigoNacionalidade. */
	@Column(name = "CODNACIONALIDADE")
	private Short codigoNacionalidade;

	/**
	 * Recupera o valor de idPessoaCompartilhamento.
	 *
	 * @return o valor de idPessoaCompartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * Define o valor de idPessoaCompartilhamento.
	 *
	 * @param idPessoaCompartilhamento o novo valor de idPessoaCompartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * Recupera o valor de codigoNacionalidade.
	 *
	 * @return o valor de codigoNacionalidade
	 */
	public Short getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	/**
	 * Define o valor de codigoNacionalidade.
	 *
	 * @param codigoNacionalidade o novo valor de codigoNacionalidade
	 */
	public void setCodigoNacionalidade(Short codigoNacionalidade) {
		this.codigoNacionalidade = codigoNacionalidade;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idPessoaCompartilhamento", "codigoNacionalidade");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idPessoaCompartilhamento", "codigoNacionalidade");
	}

}