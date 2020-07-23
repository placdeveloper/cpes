/*
 * SICOOB
 * 
 * ResponsavelCadastroBase.java(br.com.sicoob.capes.negocio.entidades.ResponsavelCadastroBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa o responsável pelo cadastro da pessoa. 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class ResponsavelCadastroBase extends EntidadeCadastroBase {

	/** Serial UID.*/
	private static final long serialVersionUID = -907625868382401346L;
	
	/** O atributo id pessoa compartilhamento. */
	@Id
	private Long idPessoaCompartilhamento;
	
	/** O atributo pessoa. */
	@OneToOne
	@PrimaryKeyJoinColumn
	private PessoaCompartilhamento pessoa;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
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
	 * @param idPessoa
	 *            o novo id pessoa compartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoa) {
		this.idPessoaCompartilhamento = idPessoa;
	}

	/** 
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoa;
	}

	/**
	 * Preenche pessoa compartilhamento.
	 * 
	 * @param pessoaCompartilhamento
	 *            o novo pessoa compartilhamento
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		setPessoa(pessoaCompartilhamento);
	}
	
	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(PessoaCompartilhamento pessoa) {
		this.pessoa = pessoa;
		if(pessoa != null) {
			this.idPessoaCompartilhamento = pessoa.getId();
		}
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
