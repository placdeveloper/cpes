/*
 * SICOOB
 * 
 * EmailBase.java(br.com.sicoob.capes.negocio.entidades.EmailBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa a tabela responsável por armazenar os emails das pessoas.
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class EmailBase extends EntidadeCadastroBase {

	/** Serial UID.*/
	private static final long serialVersionUID = 1561737234922674416L;

	/** O atributo tipo email. */
	@JoinColumn( name = "CODTIPOEMAIL", referencedColumnName = "CODTIPOEMAIL" )
	@ManyToOne
	private TipoEmail tipoEmail;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo descricao. */
	@Column(name = "DESCEMAIL")
	private String descricao;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	/**
	 * @return the tipoEmail
	 */
	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	/**
	 * @param tipoEmail the tipoEmail to set
	 */
	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	/**
	 * @return the pessoaCompartilhamento
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * @param pessoaCompartilhamento the pessoaCompartilhamento to set
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoa) {
		this.pessoaCompartilhamento = pessoa;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
