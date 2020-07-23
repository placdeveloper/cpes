/*
 * SICOOB
 * 
 * TipoRegraValidacaoCadastral.java(br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;

/**
 * The Class TipoRegraValidacaoCadastral.
 */
@Entity
@Table(schema = "CLI", name = "TIPOREGRAVALIDACAOCADASTRAL")
public class TipoRegraValidacaoCadastral extends CAPESEntidade<Character> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 8893969104932480798L;

	/** O atributo codigo tipo regra. */
	@Id
	@Column(name = "CODTIPOREGRA")
	private Character codigoTipoRegra;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOREGRA", length = 100, nullable = false)
	private String descricao;

	/**
	 * Cria uma nova instância de tipo regra validacao cadastral.
	 */
	public TipoRegraValidacaoCadastral() {
	}

	/**
	 * Cria uma nova instância de tipo regra validacao cadastral.
	 * 
	 * @param codigoTipoRegra
	 *            the codigo tipo regra
	 * @param descricao
	 *            the descricao
	 */
	public TipoRegraValidacaoCadastral(Character codigoTipoRegra, String descricao) {

		this.codigoTipoRegra = codigoTipoRegra;
		this.descricao = descricao;
	}

	/**
	 * Recupera codigo tipo regra.
	 * 
	 * @return codigo tipo regra
	 */
	public Character getCodigoTipoRegra() {

		return codigoTipoRegra;
	}

	/**
	 * Preenche codigo tipo regra.
	 * 
	 * @param codigoTipoRegra
	 *            o novo codigo tipo regra
	 */
	public void setCodigoTipoRegra(Character codigoTipoRegra) {

		this.codigoTipoRegra = codigoTipoRegra;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Character getId() {

		return getCodigoTipoRegra();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Character id) {

		setCodigoTipoRegra(id);
	}

	/**
	 * Recupera o enum que representa este tipo de regra.
	 * 
	 * @return enum
	 * @see TipoRegraValidacaoCadastralEnum
	 */
	public TipoRegraValidacaoCadastralEnum getEnum() {

		TipoRegraValidacaoCadastralEnum retorno = null;
		if (getId() != null) {
			retorno = TipoRegraValidacaoCadastralEnum.valueOf(String.valueOf(getId()));
		}
		return retorno;
	}
	
	/**
	 * Cria uma instancia de {@code TipoRegraValidacaoCadastral} que represente o {@code tipo} recebido como parametro.
	 * 
	 * @param tipo
	 *            o tipo
	 * @return nova instancia de {@code TipoRegraValidacaoCadastral} ou {@code null} caso o {@code tipo} seja nulo.
	 * @see TipoRegraValidacaoCadastralEnum
	 */
	public static TipoRegraValidacaoCadastral valueOf(TipoRegraValidacaoCadastralEnum tipo) {
		TipoRegraValidacaoCadastral entidade = null;
		if (tipo != null) {
			entidade = new TipoRegraValidacaoCadastral(tipo.getCodigo(), tipo.getDescricao());
		}
		return entidade;
	}
}
