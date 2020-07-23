/*
 * SICOOB
 * 
 * ValidacaoCadastralRegra.java(br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum;

/**
 * The Class ValidacaoCadastralRegra.
 */
@Entity
@Table(schema = "CLI", name = "REGRAVALIDACAOCADASTRAL")
public class ValidacaoCadastralRegra extends CAPESEntidade<Short> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7271216417231778477L;

	/** O atributo codigo regra. */
	@Id
	@Column(name = "CODREGRAVALIDACAOCADASTRAL")
	private Short codigoRegra;
	
	/** O atributo descricao. */
	@Column(name = "DESCREGRAVALIDACAOCADASTRAL", nullable = false, length = 800)
	private String descricao;
	
	/** O atributo query. */
	@Column(name = "DESCDETALHAMENTOREGRA", length = 10000)
	private String query;
	
	/** O atributo funcionalidade. */
	@Enumerated(EnumType.STRING)
	@Column(name = "DESCREFERENCIA", nullable = false)
	private FuncionalidadeValidacaoCadastralEnum funcionalidade;
	
	/** O atributo mensagem erro. */
	@Column(name = "DESCMENSAGEMERRO", nullable = false, length = 100)
	private String mensagemErro;
	
	/** Indica se uma regra esta ativa ou nao. */
	@Column(name = "BOLATIVO", nullable = false)	
	private Boolean ativo;
	
	/** Indica se a regra deve ser executada no proximo fechamento ou nao. */
	@Column(name = "BOLEXECUTAREGRA", nullable = false)
	private Boolean executarRegra;
	
	/** O atributo perfil cadastro. */
	@JoinColumn(name = "CODPERFILCADASTRO", referencedColumnName = "CODPERFILCADASTRO", nullable = false)
	@ManyToOne
	private PerfilCadastro perfilCadastro;
	
	/** O tipo da regra. 
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum
	 * @see TipoRegraValidacaoCadastral
	 */
	@ManyToOne
	@JoinColumn(name = "CODTIPOREGRA", nullable = false)
	private TipoRegraValidacaoCadastral tipoRegra;
	
	
	
	/**
	 * Cria uma nova isntancia de {@link ValidacaoCadastralRegra} com
	 * {@link #ativo} == {@link Boolean#FALSE}
	 */
	public ValidacaoCadastralRegra() {
	    this(Boolean.FALSE);
    }

	/**
	 * Cria uma nova isntancia de {@link ValidacaoCadastralRegra} 
	 * @param ativo
	 */
	public ValidacaoCadastralRegra(Boolean ativo) {
	    this.ativo = ativo;
    }

	/**
	 * Cria uma nova isntancia de {@link ValidacaoCadastralRegra}
	 * 
     * @param codigoRegra
     * @param descricao
     * @param funcionalidade
     * @param mensagemErro
     * @param ativo
     * @param tipoRegra
     */
	 public ValidacaoCadastralRegra(Short codigoRegra, String descricao,
	            FuncionalidadeValidacaoCadastralEnum funcionalidade, String mensagemErro,
	            Boolean ativo, TipoRegraValidacaoCadastral tipoRegra, Boolean executarRegra) {

	    	this.codigoRegra = codigoRegra;
		    this.descricao = descricao;
		    this.funcionalidade = funcionalidade;
		    this.mensagemErro = mensagemErro;
		    this.ativo = ativo;
		    this.tipoRegra = tipoRegra;
		    this.executarRegra = executarRegra;
	    }
	 
	 
	 
	 
	   /** 
	    * Cria uma nova instancia de {@link ValidacaoCadastralRegra}
	    * 
		* @param codigoRegra
		* @param descricao
		* @param funcionalidade
		* @param mensagemErro
		* @param ativo
		* @param tipoRegra
		* @param executarRegra
		* @param perfilCadastro
		*/
	public ValidacaoCadastralRegra(Short codigoRegra, String descricao,
	            FuncionalidadeValidacaoCadastralEnum funcionalidade, String mensagemErro,
	            Boolean ativo, TipoRegraValidacaoCadastral tipoRegra, Boolean executarRegra, 
	            PerfilCadastro perfilCadastro) {

	    	this.codigoRegra = codigoRegra;
		    this.descricao = descricao;
		    this.funcionalidade = funcionalidade;
		    this.mensagemErro = mensagemErro;
		    this.ativo = ativo;
		    this.tipoRegra = tipoRegra;
		    this.executarRegra = executarRegra;
		    this.perfilCadastro = perfilCadastro;
	    }
    
    

	/**
	 * Recupera codigo regra.
	 * 
	 * @return codigo regra
	 */
	public Short getCodigoRegra() {
	
		return codigoRegra;
	}

	/**
	 * Preenche codigo regra.
	 * 
	 * @param codigoRegra
	 *            o novo codigo regra
	 */
	public void setCodigoRegra(Short codigoRegra) {
	
		this.codigoRegra = codigoRegra;
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
	 * Recupera query.
	 * 
	 * @return query
	 */
	public String getQuery() {
	
		return query;
	}

	/**
	 * Preenche query.
	 * 
	 * @param query
	 *            o novo query
	 */
	public void setQuery(String query) {
	
		this.query = query;
	}

	/**
	 * Recupera funcionalidade.
	 * 
	 * @return funcionalidade
	 */
	public FuncionalidadeValidacaoCadastralEnum getFuncionalidade() {
	
		return funcionalidade;
	}

	/**
	 * Preenche funcionalidade.
	 * 
	 * @param funcionalidade
	 *            o novo funcionalidade
	 */
	public void setFuncionalidade(FuncionalidadeValidacaoCadastralEnum funcionalidade) {
	
		this.funcionalidade = funcionalidade;
	}

	/**
	 * Recupera mensagem erro.
	 * 
	 * @return mensagem erro
	 */
	public String getMensagemErro() {
	
		return mensagemErro;
	}

	/**
	 * Preenche mensagem erro.
	 * 
	 * @param mensagemErro
	 *            o novo mensagem erro
	 */
	public void setMensagemErro(String mensagemErro) {
	
		this.mensagemErro = mensagemErro;
	}

	/**
	 * Recupera ativo.
	 * 
	 * @return ativo
	 */
	public Boolean getAtivo() {
	
		return ativo;
	}

	/**
	 * Preenche ativo.
	 * 
	 * @param ativo
	 *            o novo ativo
	 */
	public void setAtivo(Boolean ativo) {
	
		this.ativo = ativo;
	}

	/**
	 * Recupera tipo regra.
	 * 
	 * @return tipo regra
	 */
	public TipoRegraValidacaoCadastral getTipoRegra() {
	
		return tipoRegra;
	}

	/**
	 * Preenche tipo regra.
	 * 
	 * @param tipoRegra
	 *            o novo tipo regra
	 */
	public void setTipoRegra(TipoRegraValidacaoCadastral tipoRegra) {
	
		this.tipoRegra = tipoRegra;
	}
	
	/**
	 * Recupera o valor de executarRegra.
	 *
	 * @return o valor de executarRegra
	 */
	public Boolean getExecutarRegra() {
		return executarRegra;
	}

	/**
	 * Define o valor de executarRegra.
	 *
	 * @param executarRegra o novo valor de executarRegra
	 */
	public void setExecutarRegra(Boolean executarRegra) {
		this.executarRegra = executarRegra;
	}

	/**
	 * @return the perfilCadastro
	 */
	public PerfilCadastro getPerfilCadastro() {
		return perfilCadastro;
	}

	/**
	 * @param perfilCadastro the perfilCadastro to set
	 */
	public void setPerfilCadastro(PerfilCadastro perfilCadastro) {
		this.perfilCadastro = perfilCadastro;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {

		return getCodigoRegra();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {

		setCodigoRegra(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
