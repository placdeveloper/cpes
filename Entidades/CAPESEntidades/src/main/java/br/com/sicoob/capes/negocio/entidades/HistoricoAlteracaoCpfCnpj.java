/*
 * SICOOB
 * 
 * HistoricoAlteracaoCpfCnpj.java(br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import flexjson.JSON;

/**
 * Entidade que representa a tabela de histórico de alteração de CPF/CNPJ
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "HISTALTERACAOCPFCNPJ", schema = "CLI")
@EntityListeners( { ReplicacaoListener.class })
public class HistoricoAlteracaoCpfCnpj extends
		CAPESEntidade<Long> implements Replicavel {

	/** Serial UID. */
	private static final long serialVersionUID = 1053894845885026316L;

	/** O atributo id hist alteracao cpf cnpj. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTALTERACAOCPFCNPJ")
	private Long idHistAlteracaoCpfCnpj;
	
	/** O atributo id usuario. */
	private String idUsuario;
	
	/** O atributo nome solicitante. */
	private String nomeSolicitante;

	/** O atributo motivo. */
	@Column(name = "descMotivo")
	private String motivo;

	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOA")
	private Pessoa pessoa;
	
	/** O atributo cpf cnpj anterior. */
	@Column(name = "NUMCPFCNPJANTERIOR")
	private String cpfCnpjAnterior;
	
	/** O atributo data hora alteracao. */
	private DateTimeDB dataHoraAlteracao;

	/** O atributo cpf cnpj novo. */
	@Transient
	private String cpfCnpjNovo;
	
	/**
	 * Recupera id hist alteracao cpf cnpj.
	 * 
	 * @return id hist alteracao cpf cnpj
	 */
	public Long getIdHistAlteracaoCpfCnpj() {
		return idHistAlteracaoCpfCnpj;
	}

	/**
	 * Preenche id hist alteracao cpf cnpj.
	 * 
	 * @param idHistAlteracaoCpfCnpj
	 *            o novo id hist alteracao cpf cnpj
	 */
	public void setIdHistAlteracaoCpfCnpj(Long idHistAlteracaoCpfCnpj) {
		this.idHistAlteracaoCpfCnpj = idHistAlteracaoCpfCnpj;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the nomeSolicitante
	 */
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	/**
	 * @param nomeSolicitante
	 *            the nomeSolicitante to set
	 */
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the pessoa
	 */
	@JSON(include=false)
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the cpfCnpjAnterior
	 */
	public String getCpfCnpjAnterior() {
		return cpfCnpjAnterior;
	}

	/**
	 * @param cpfCnpjAnterior the cpfCnpjAnterior to set
	 */
	public void setCpfCnpjAnterior(String cpfCnpjAnterior) {
		this.cpfCnpjAnterior = cpfCnpjAnterior;
	}

	/**
	 * @return the dataHoraAlteracao
	 */
	public DateTimeDB getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	/**
	 * @param dataHoraAlteracao the dataHoraAlteracao to set
	 */
	public void setDataHoraAlteracao(DateTimeDB dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

	/**
	 * @return the cpfCnpjNovo
	 */
	public String getCpfCnpjNovo() {
		return cpfCnpjNovo;
	}

	/**
	 * @param cpfCnpjNovo the cpfCnpjNovo to set
	 */
	public void setCpfCnpjNovo(String cpfCnpjNovo) {
		this.cpfCnpjNovo = cpfCnpjNovo;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdHistAlteracaoCpfCnpj();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdHistAlteracaoCpfCnpj(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		// TODO Auto-generated method stub
		return null;
	}

}
