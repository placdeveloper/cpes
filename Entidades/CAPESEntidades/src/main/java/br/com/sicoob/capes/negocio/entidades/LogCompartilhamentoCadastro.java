/*
 * SICOOB
 * 
 * LogCompartilhamentoCadastro.java(br.com.sicoob.capes.negocio.entidades.LogCompartilhamentoCadastro)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name="LOGCOMPARTILHAMENTOPESSOA", schema = "CLI")
public class LogCompartilhamentoCadastro extends CAPESEntidade<Integer> {

	/** Serial UID.*/
	private static final long serialVersionUID = -1631787078758754971L;

	/** O atributo id log compartilhamento. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDLOGCOMPARTILHAMENTOPESSOA")
	private Integer idLogCompartilhamento;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;

	/** O atributo id instituicao demandante. */
	private Integer idInstituicaoDemandante;

	/** O atributo id usuario. */
	@Column(name = "IDUSUARIOCONSULTA")
	private String idUsuario;

	/** O atributo data hora consulta. */
	@Column(name = "DATAHORACONSULTA")
	private DateTimeDB dataHoraConsulta;

	/** O atributo id instituicao responsavel. */
	private Integer idInstituicaoResponsavel;

	/** O atributo compartilhado. */
	@Column(name = "BOLCOMPARTILHADO")
	private Boolean compartilhado = Boolean.FALSE;

	/**
	 * @return the idLogCompartilhamento
	 */
	public Integer getIdLogCompartilhamento() {
		return idLogCompartilhamento;
	}

	/**
	 * @param idLogCompartilhamento the idLogCompartilhamento to set
	 */
	public void setIdLogCompartilhamento(Integer idLogCompartilhamento) {
		this.idLogCompartilhamento = idLogCompartilhamento;
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
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoa) {
		this.pessoaCompartilhamento = pessoa;
	}

	/**
	 * @return the idInstituicaoDemandante
	 */
	public Integer getIdInstituicaoDemandante() {
		return idInstituicaoDemandante;
	}

	/**
	 * @param idInstituicaoDemandante the idInstituicaoDemandante to set
	 */
	public void setIdInstituicaoDemandante(Integer idInstituicaoDemandante) {
		this.idInstituicaoDemandante = idInstituicaoDemandante;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the dataHoraConsulta
	 */
	public DateTimeDB getDataHoraConsulta() {
		return dataHoraConsulta;
	}

	/**
	 * @param dataHoraConsulta the dataHoraConsulta to set
	 */
	public void setDataHoraConsulta(DateTimeDB dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}

	/**
	 * @return the idInstituicaoResponsavel
	 */
	public Integer getIdInstituicaoResponsavel() {
		return idInstituicaoResponsavel;
	}

	/**
	 * @param idInstituicaoResponsavel the idInstituicaoResponsavel to set
	 */
	public void setIdInstituicaoResponsavel(Integer idInstituicaoResponsavel) {
		this.idInstituicaoResponsavel = idInstituicaoResponsavel;
	}

	/**
	 * @return the compartilhado
	 */
	public Boolean getCompartilhado() {
		return compartilhado;
	}

	/**
	 * @param compartilhado the compartilhado to set
	 */
	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdLogCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdLogCompartilhamento(id);
	}
	
}
