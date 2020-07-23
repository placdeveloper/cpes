/*
 * SICOOB
 * 
 * TipoAtualizacaoRenovacao.java(br.com.sicoob.capes.negocio.entidades.TipoAtualizacaoRenovacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entidade que representa o tipo de atualizacao do cadastro da pessoa.
 * 
 * @author Valdemar.Xavier
 */
@Entity
@Table(name = "TIPOATUALIZACAORENOVACAO", schema = "CLI")
public class TipoAtualizacaoRenovacao extends CAPESEntidade<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOATUALIZACAORENOVACAO")
	private Integer codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOATUALIZACAORENOVACAO")
	private String descricao;

	/** O atributo ativo. */
	@Column(name="BOLATIVO")
	private Boolean ativo;

	/**
	 * Recupera o valor de id.
	 *
	 * @return o valor de id
	 */
	@Override
	public Integer getId() {
		return getCodigo();
	}

	/**
	 * Define o valor de id.
	 *
	 * @param codigo o novo valor de id
	 */
	@Override
	public void setId(Integer id) {
		setCodigo(id);
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Define o valor de codigo.
	 *
	 * @param codigo o novo valor de codigo
	 */
	public void setCodigo(Integer codigo) {
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

}