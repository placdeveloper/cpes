/*
 * SICOOB
 * 
 * Cliente.java(br.com.sicoob.capes.negocio.entidades.legado.Cliente)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa a tabela responsável por armazenar os dados do cliente.
 * @author juan.damasceno
 * 
 */
@Entity
@Table(name="CLIENTE")
public class Cliente extends EntidadeReplicavel<Integer>{

	/** Serial UID. */
	private static final long serialVersionUID = 2825633013234059016L;

	/** O atributo id cliente. */
	@Id
	@Column(name="NUMCLIENTE")
	private Integer idCliente;

	/** O atributo cobrar ir. */
	@Column(name="BOLCOBRAIR")
	private Boolean cobrarIR = Boolean.TRUE;

	/** O atributo cobrar cpmf. */
	@Column(name="BOLCOBRARCPMF")
	private Boolean cobrarCPMF = Boolean.TRUE;

	/** O atributo cobrar iof. */
	@Column(name="BOLCOBRARIOF")
	private Boolean cobrarIOF = Boolean.TRUE;

	/** O atributo data ultima atualizacao. */
	private Date dataUltimaAtualizacao;

	/** O atributo pessoa. */
	@OneToOne
	@PrimaryKeyJoinColumn(name="NUMCLIENTE")
	private Pessoa pessoa;

	/** O atributo id perfil tarifario. */
	private Integer idPerfilTarifario;

	/** O atributo parecer gerencia. */
	@Column(name="DESCPARECER")
	private String parecerGerencia;

	/** O atributo emite aviso lancamento. */
	@Column(name="BOLEMITEAVISOLANCTO")
	private Boolean emiteAvisoLancamento;

	/** O atributo funcionario. */
	@OneToOne
	@JoinColumn(name="NUMPESSOAFUNCGER")
	private Funcionario funcionario;
	
	/** O atributo num nucleo. */
	private Integer numNucleo;
	
	/** O atributo num pac cliente. */
	private Integer numPacCliente;
	
	/** O atributo num cooperativa. */
	private Integer numCooperativa;
	
	/** O atributo num pac. */
	private Integer numPac;
	
	/** O atributo data cadastramento cliente. */
	private Date dataCadastramentoCliente;
	
	/** O atributo motivo risco. */
	private String motivoRisco;
	
	/** O atributo id nivel risco. */
	private String idNivelRisco;
	
	/** O atributo data risco. */
	private Date dataRisco;
	
	/** O atributo data conglomerado. */
	@Column(name = "DataConglomerado")
	private Date dataConglomerado;
	
	/** O atributo autoriza consulta. */
	@Column(name="BOLAUTORIZACONSULTA")
	private Boolean autorizaConsulta;
	
	/** O atributo data cadastramento sfn. */
	private Date dataCadastramentoSFN;
	
	/** O atributo id d b2. */
	@Transient
	private Integer idDB2;

	/**
	 * Recupera data cadastramento sfn.
	 * 
	 * @return data cadastramento sfn
	 */
	public Date getDataCadastramentoSFN() {
		return dataCadastramentoSFN;
	}

	/**
	 * Preenche data cadastramento sfn.
	 * 
	 * @param dataCadastramentoSFN
	 *            o novo data cadastramento sfn
	 */
	public void setDataCadastramentoSFN(Date dataCadastramentoSFN) {
		this.dataCadastramentoSFN = dataCadastramentoSFN;
	}

	/**
	 * Recupera autoriza consulta.
	 * 
	 * @return autoriza consulta
	 */
	public Boolean getAutorizaConsulta() {
		return autorizaConsulta;
	}

	/**
	 * Preenche autoriza consulta.
	 * 
	 * @param autorizaConsulta
	 *            o novo autoriza consulta
	 */
	public void setAutorizaConsulta(Boolean autorizaConsulta) {
		this.autorizaConsulta = autorizaConsulta;
	}

	/**
	 * Recupera data cadastramento cliente.
	 * 
	 * @return data cadastramento cliente
	 */
	public Date getDataCadastramentoCliente() {
		return dataCadastramentoCliente;
	}

	/**
	 * Preenche data cadastramento cliente.
	 * 
	 * @param dataCadastramentoCliente
	 *            o novo data cadastramento cliente
	 */
	public void setDataCadastramentoCliente(Date dataCadastramentoCliente) {
		this.dataCadastramentoCliente = dataCadastramentoCliente;
	}

	/**
	 * Recupera motivo risco.
	 * 
	 * @return motivo risco
	 */
	public String getMotivoRisco() {
		return motivoRisco;
	}

	/**
	 * Preenche motivo risco.
	 * 
	 * @param motivoRisco
	 *            o novo motivo risco
	 */
	public void setMotivoRisco(String motivoRisco) {
		this.motivoRisco = motivoRisco;
	}

	/**
	 * Recupera id nivel risco.
	 * 
	 * @return id nivel risco
	 */
	public String getIdNivelRisco() {
		return idNivelRisco;
	}

	/**
	 * Preenche id nivel risco.
	 * 
	 * @param idNivelRisco
	 *            o novo id nivel risco
	 */
	public void setIdNivelRisco(String idNivelRisco) {
		this.idNivelRisco = idNivelRisco;
	}

	/**
	 * Recupera data risco.
	 * 
	 * @return data risco
	 */
	public Date getDataRisco() {
		return dataRisco;
	}

	/**
	 * Preenche data risco.
	 * 
	 * @param dataRisco
	 *            o novo data risco
	 */
	public void setDataRisco(Date dataRisco) {
		this.dataRisco = dataRisco;
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the numNucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * @param numNucleo the numNucleo to set
	 */
	public void setNumNucleo(Integer numNucleo) {
		this.numNucleo = numNucleo;
	}

	/**
	 * @return the emiteAvisoLancamento
	 */
	public Boolean getEmiteAvisoLancamento() {
		return emiteAvisoLancamento;
	}

	/**
	 * @param emiteAvisoLancamento the emiteAvisoLancamento to set
	 */
	public void setEmiteAvisoLancamento(Boolean emiteAvisoLancamento) {
		this.emiteAvisoLancamento = emiteAvisoLancamento;
	}

	/**
	 * @return the idPerfilTarifario
	 */
	public Integer getIdPerfilTarifario() {
		return idPerfilTarifario;
	}

	/**
	 * @param idPerfilTarifario the idPerfilTarifario to set
	 */
	public void setIdPerfilTarifario(Integer idPerfilTarifario) {
		this.idPerfilTarifario = idPerfilTarifario;
	}

	/**
	 * @return the parecerGerencia
	 */
	public String getParecerGerencia() {
		return parecerGerencia;
	}

	/**
	 * @param parecerGerencia the parecerGerencia to set
	 */
	public void setParecerGerencia(String parecerGerencia) {
		this.parecerGerencia = parecerGerencia;
	}

	/**
	 * @return the idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the pessoa
	 */
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
	 * @return the cobrarIR
	 */
	public Boolean getCobrarIR() {
		return cobrarIR;
	}

	/**
	 * @param cobrarIR the cobrarIR to set
	 */
	public void setCobrarIR(Boolean cobrarIR) {
		this.cobrarIR = cobrarIR;
	}

	/**
	 * @return the cobrarCPMF
	 */
	public Boolean getCobrarCPMF() {
		return cobrarCPMF;
	}

	/**
	 * @param cobrarCPMF the cobrarCPMF to set
	 */
	public void setCobrarCPMF(Boolean cobrarCPMF) {
		this.cobrarCPMF = cobrarCPMF;
	}

	/**
	 * @return the cobrarIOF
	 */
	public Boolean getCobrarIOF() {
		return cobrarIOF;
	}

	/**
	 * @param cobrarIOF the cobrarIOF to set
	 */
	public void setCobrarIOF(Boolean cobrarIOF) {
		this.cobrarIOF = cobrarIOF;
	}

	/**
	 * @return the dataConglomerado
	 */
	public Date getDataConglomerado() {
		return dataConglomerado;
	}

	/**
	 * @param dataConglomerado the dataConglomerado to set
	 */
	public void setDataConglomerado(Date dataConglomerado) {
		this.dataConglomerado = dataConglomerado;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return this.idDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setIdCliente((Integer) idSQL);
		}
	}

	/**
	 * @param idDB2 the idDB2 to set
	 */
	public void setIdDB2(Integer idDB2) {
		this.idDB2 = idDB2;
	}

	/**
	 * @return the dataUltimaAtualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	/**
	 * @param dataUltimaAtualizacao the dataUltimaAtualizacao to set
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	/**
	 * @return the numPacCliente
	 */
	public Integer getNumPacCliente() {
		return numPacCliente;
	}

	/**
	 * @param numPacCliente the numPacCliente to set
	 */
	public void setNumPacCliente(Integer numPacCliente) {
		this.numPacCliente = numPacCliente;
	}

	/**
	 * Recupera num cooperativa.
	 * 
	 * @return num cooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	/**
	 * Preenche num cooperativa.
	 * 
	 * @param numCooperativa
	 *            o novo num cooperativa
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	/**
	 * Recupera num pac.
	 * 
	 * @return num pac
	 */
	public Integer getNumPac() {
		return numPac;
	}

	/**
	 * Preenche num pac.
	 * 
	 * @param numPac
	 *            o novo num pac
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return this.idCliente;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idCliente");
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idCliente");
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setIdDB2((Integer)idDB2);
		}
	}
	
}