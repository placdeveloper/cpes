/*
 * SICOOB
 * 
 * TipoDocumentoIdentificacao.java(br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa tipo de documento de identificação.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "TIPODOCUMENTOIDENTIFICACAO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCTIPODOCUMENTOIDENTIFICACAO", descendente = false)
public class TipoDocumentoIdentificacao extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -7781481588474606430L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPODOCUMENTOIDENTIFICACAO")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPODOCUMENTOIDENTIFICACAO")
	private String descricao;

	/**
     * Construtor
     */
    public TipoDocumentoIdentificacao() {
    }
    
	/**
	 * Construtor
	 * 
	 * @param codigo
	 */
	public TipoDocumentoIdentificacao(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Preenche codigo.
	 * 
	 * @param codigo
	 *            o novo codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
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
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

}
