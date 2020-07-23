/*
 * SICOOB
 * 
 * ValidacaoCadastral.java(br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * The Class ValidacaoCadastral.
 */
@Entity
@Table(schema = "CLI", name = "VALIDACAOCADASTRAL")
public class ValidacaoCadastral extends CAPESEntidade<Long>{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -8831432245577570184L;

	/** O atributo id pessoa compartilhamento. */
	@Id
	@Column(name = "IDPESSOACOMPARTILHAMENTO")
	private Long idPessoaCompartilhamento;

	/** O atributo pessoaCompartilhamento. */
	@OneToOne
	@PrimaryKeyJoinColumn
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo data hora da ultima validacao. */
	@Column(name = "DATAHORAULTIMAVALIDACAO")
	private DateTimeDB dataHoraUltimaValidacao;
	
	/** O atributo data hora ultima atualizacao. */
	@Column(name = "DATAHORAULTIMAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraUltimaAtualizacao;
	
	/** O atributo idUsuarioEnvio. */
	@Column(name="IDUSUARIOENVIO")
	private String idUsuarioEnvio;

	/** O atributo idUsuarioAprov. */
	@Column(name="IDUSUARIOAPROV")
	private String idUsuarioAprov;

	/**
	 * Cria uma nova instância de validacao cadastral.
	 */
	public ValidacaoCadastral() {

	}

	/**
	 * Cria uma nova instância de validacao cadastral.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o id da PessoaCompartilhamento
	 */
	public ValidacaoCadastral(Long idPessoaCompartilhamento) {

		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * Recupera id pessoa compartilhamento.
	 * 
	 * @return id pessoa compartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {
	
		return idPessoaCompartilhamento;
	}

	/**
	 * Preenche id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o novo id pessoa compartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
	
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * Recupera data hora ultima validacao.
	 * 
	 * @return data hora ultima validacao
	 */
	public DateTimeDB getDataHoraUltimaValidacao() {
	
		return dataHoraUltimaValidacao;
	}

	/**
	 * Preenche data hora ultima validacao.
	 * 
	 * @param dataHoraUltimaValidacao
	 *            o novo data hora ultima validacao
	 */
	public void setDataHoraUltimaValidacao(DateTimeDB dataHoraUltimaValidacao) {
	
		this.dataHoraUltimaValidacao = dataHoraUltimaValidacao;
	}

	/**
	 * Recupera data hora ultima atualizacao.
	 * 
	 * @return data hora ultima atualizacao
	 */
	public DateTimeDB getDataHoraUltimaAtualizacao() {
	
		return dataHoraUltimaAtualizacao;
	}

	/**
	 * Preenche data hora ultima atualizacao.
	 * 
	 * @param dataHoraUltimaAtualizacao
	 *            o novo data hora ultima atualizacao
	 */
	public void setDataHoraUltimaAtualizacao(DateTimeDB dataHoraUltimaAtualizacao) {
	
		this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
	}

	/**
     * @return the pessoaCompartilhamento
     */
    public PessoaCompartilhamento getPessoaCompartilhamento() {
    	return pessoaCompartilhamento;
    }

	/**
     * @param pessoaCompartilhamento the pessoaCompartilhamento to set
     */
    public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
    	this.pessoaCompartilhamento = pessoaCompartilhamento;
    }

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {

		return getIdPessoaCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {

		setIdPessoaCompartilhamento(id);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

	/** 
	 * {@inheritDoc}
	 */
	public String getIdUsuarioAprov() {
		return idUsuarioAprov;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioAprov(String idUsuarioAprov) {
		this.idUsuarioAprov = idUsuarioAprov;
	}

}
