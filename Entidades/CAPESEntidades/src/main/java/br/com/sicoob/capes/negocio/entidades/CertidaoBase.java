/*
 * SICOOB
 * 
 * CertidaoBase.java(br.com.sicoob.capes.negocio.entidades.CertidaoBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa a tabela responsável por armazenar as certidôes de uma pessoa.
 * 
 * @author juan.damasceno
 */
@MappedSuperclass
public abstract class CertidaoBase extends EntidadeCadastroBase {

	/** Serial UID. */
	private static final long serialVersionUID = 2825633013234059016L;

	/** O atributo tipo certidao. */
	@JoinColumn( name = "CODTIPOCERTIDAO", referencedColumnName = "CODTIPOCERTIDAO")
	@ManyToOne
	private TipoCertidao tipoCertidao;
	
	/** O atributo pessoa. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoa;

	/** O atributo numero. */
	@Column(name = "NUMCERTIDAO")
	private String numero;
	
	/** O atributo observacao. */
	@Column(name = "DESCOBSERVACAO")
	private String observacao;
	
	/** O atributo data emissao. */
	private DateTimeDB dataEmissao;
	
	/** O atributo data vencimento. */
	@Column(name = "dataVencimentoCertidao")
	private DateTimeDB dataVencimento;
	
	/** O atributo numero crea. */
	@Column(name = "NUMCREA")
	private String numeroCrea;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	/**
	 * @return the tipoCertidao
	 */
	public TipoCertidao getTipoCertidao() {
		return tipoCertidao;
	}

	/**
	 * @param tipoCertidao the tipoCertidao to set
	 */
	public void setTipoCertidao(TipoCertidao tipoCertidao) {
		this.tipoCertidao = tipoCertidao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return getPessoa();
	}

	/**
	 * @return the pessoa
	 */
	public PessoaCompartilhamento getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(PessoaCompartilhamento pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the dataEmissao
	 */
	public DateTimeDB getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * @param dataEmissao the dataEmissao to set
	 */
	public void setDataEmissao(DateTimeDB dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return the dataVencimento
	 */
	public DateTimeDB getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(DateTimeDB dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @return the numeroCrea
	 */
	public String getNumeroCrea() {
		return numeroCrea;
	}

	/**
	 * @param numeroCrea the numeroCrea to set
	 */
	public void setNumeroCrea(String numeroCrea) {
		this.numeroCrea = numeroCrea;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
}