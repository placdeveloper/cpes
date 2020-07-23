/*
 * SICOOB
 * 
 * MensagemBase.java(br.com.sicoob.capes.negocio.entidades.MensagemBase)
 */
package br.com.sicoob.capes.negocio.entidades;


import javax.persistence.Column;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * The Class MensagemBase.
 */
@MappedSuperclass
public abstract class MensagemBase extends EntidadeCadastroBase {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1633152495914279654L;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn(name="IDPESSOA")
	private Pessoa pessoa;
	
	/** O atributo id instituicao. */
	@Column(name="IDINSTITUICAO")
	private Integer idInstituicao;
	
	/** O atributo mensagem. */
	@Column(name="DESCMENSAGEM")
	private String mensagem;
	
	/** O atributo data cadastro mensagem. */
	@Column(name="DATACADASTRO")
	private DateTimeDB dataHoraInicio;
	
	/** O atributo validade. */
	@Column(name="DATAVALIDADEMSG")
	private DateTimeDB validade;
	
	/** O atributo codigoTipoMensagem. */
	@Column(name="CODTIPOMENSAGEM")
	private Short codigoTipoMensagem;
	
	/** O atributo codigoTipoDestinoExibicao. */
	@Column(name="CODTIPODESTINOEXIBICAO")
	private Short codigoTipoDestinoExibicao;
	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the validade
	 */
	public DateTimeDB getValidade() {
		return validade;
	}

	/**
	 * @param validade the validade to set
	 */
	public void setValidade(DateTimeDB validade) {
		this.validade = validade;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		
		PessoaCompartilhamento pessoaCompartilhamento = null;
		Pessoa p = getPessoa();
		if (p != null) {
			pessoaCompartilhamento = p.getPessoaCompartilhamento();
		}
		return pessoaCompartilhamento;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public Short getCodigoTipoMensagem() {
		return codigoTipoMensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCodigoTipoMensagem(Short codigoTipoMensagem) {
		this.codigoTipoMensagem = codigoTipoMensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	public Short getCodigoTipoDestinoExibicao() {
		return codigoTipoDestinoExibicao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCodigoTipoDestinoExibicao(Short codigoTipoDestinoExibicao) {
		this.codigoTipoDestinoExibicao = codigoTipoDestinoExibicao;
	}

}