/*
 * SICOOB
 * 
 * DadosClienteVO.java(br.com.sicoob.capes.api.negocio.vo.DadosClienteVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lucas.Borges
 */
public class DadosClienteVO extends AbstractPessoaVO{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 3427996523937888891L;

	// PESSOA INSTITUIÇÃO
	/** O atributo id pessoa instituicao. */
	private Integer idPessoaInstituicao;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo data hora inclusao. */
	private Date dataInclusao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	/** O atributo id funcionario responsavel. */
	private Integer idFuncionarioResponsavel;
	
	/** O atributo parecer gerencia. */
	private String parecerGerencia;
	
	/** O atributo emite aviso lancamento. */
	private Boolean emiteAvisoLancamento;
	
	// NUCLEO
	/** O atributo id nucleo. */
	private Integer idNucleo;
	
	/** O atributo descricao nucleo. */
	private String descricaoNucleo;
	
	/** O atributo num nucleo. */
	private Integer numNucleo;
	
	// PERFIL TARIFARIO
	/** O atributo codigo perfil tarifario. */
	private Integer codigoPerfilTarifario;
	
	/** O atributo sigla perfil tarifario. */
	private String siglaPerfilTarifario;
	
	/** O atributo descricao perfil tarifario. */
	private String descricaoPerfilTarifario;
	
	/** O atributo valor percentual perfil tarifario. */
	private BigDecimal valorPercentualPerfilTarifario;
	
	/** O atributo cod tipo percentual perfil tarifario. */
	private Short codTipoPercentualPerfilTarifario;
	
	/** O atributo cod tipo perfil conta perfil tarifario. */
	private Short codTipoPerfilContaPerfilTarifario;

	/** O atributo codigo tipo perfil perfil tarifario. */
	private Short codigoTipoPerfilPerfilTarifario;
	
	/** O atributo perfil tarifario isento. */
	private Boolean perfilTarifarioIsento;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;

	/**
	 * Cria uma nova instância de dados cliente vo.
	 */
	public DadosClienteVO() {

	}
	
	/**
	 * Cria uma nova instância de dados cliente vo.
	 * 
	 * @param idPessoaInstituicao
	 *            the id pessoa instituicao
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @param idFuncionarioResponsavel
	 *            the id funcionario responsavel
	 * @param parecerGerencia
	 *            the parecer gerencia
	 * @param emiteAvisoLancamento
	 *            the emite aviso lancamento
	 * @param idNucleo
	 *            the id nucleo
	 * @param descricaoNucleo
	 *            the descricao nucleo
	 * @param numNucleo
	 *            the num nucleo
	 * @param codigoPerfilTarifario
	 *            the codigo perfil tarifario
	 * @param siglaPerfilTarifario
	 *            the sigla perfil tarifario
	 * @param descricaoPerfilTarifario
	 *            the descricao perfil tarifario
	 * @param valorPercentualPerfilTarifario
	 *            the valor percentual perfil tarifario
	 * @param codTipoPercentualPerfilTarifario
	 *            the cod tipo percentual perfil tarifario
	 * @param codTipoPerfilContaPerfilTarifario
	 *            the cod tipo perfil conta perfil tarifario
	 * @param codigoTipoPerfilPerfilTarifario
	 *            the codigo tipo perfil perfil tarifario
	 * @param perfilTarifarioIsento
	 *            the perfil tarifario isento
	 * @param idPessoa
	 *            the id pessoa
	 */
	public DadosClienteVO(Integer idPessoaInstituicao, Date dataHoraInicio,
			Integer idUnidadeInst, Integer idFuncionarioResponsavel,
			String parecerGerencia, Boolean emiteAvisoLancamento,
			Integer idNucleo, String descricaoNucleo, Integer numNucleo,
			Integer codigoPerfilTarifario, String siglaPerfilTarifario,
			String descricaoPerfilTarifario,
			BigDecimal valorPercentualPerfilTarifario,
			Short codTipoPercentualPerfilTarifario,
			Short codTipoPerfilContaPerfilTarifario,
			Short codigoTipoPerfilPerfilTarifario, Boolean perfilTarifarioIsento,
			Integer idPessoa) {
		this.idPessoaInstituicao = idPessoaInstituicao;
		this.dataHoraInicio = dataHoraInicio;
		this.idUnidadeInst = idUnidadeInst;
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
		this.parecerGerencia = parecerGerencia;
		this.emiteAvisoLancamento = emiteAvisoLancamento;
		this.idNucleo = idNucleo;
		this.descricaoNucleo = descricaoNucleo;
		this.numNucleo = numNucleo;
		this.codigoPerfilTarifario = codigoPerfilTarifario;
		this.siglaPerfilTarifario = siglaPerfilTarifario;
		this.descricaoPerfilTarifario = descricaoPerfilTarifario;
		this.valorPercentualPerfilTarifario = valorPercentualPerfilTarifario;
		this.codTipoPercentualPerfilTarifario = codTipoPercentualPerfilTarifario;
		this.codTipoPerfilContaPerfilTarifario = codTipoPerfilContaPerfilTarifario;
		this.codigoTipoPerfilPerfilTarifario = codigoTipoPerfilPerfilTarifario;
		this.perfilTarifarioIsento = perfilTarifarioIsento;
		this.idPessoa = idPessoa;
	}
	
	public DadosClienteVO(Integer idPessoaInstituicao, Date dataHoraInicio,
			Integer idUnidadeInst, Integer idFuncionarioResponsavel,
			String parecerGerencia, Boolean emiteAvisoLancamento,
			Integer idNucleo, String descricaoNucleo, Integer numNucleo,
			Integer codigoPerfilTarifario, String siglaPerfilTarifario,
			String descricaoPerfilTarifario,
			BigDecimal valorPercentualPerfilTarifario,
			Short codTipoPercentualPerfilTarifario,
			Short codTipoPerfilContaPerfilTarifario,
			Short codigoTipoPerfilPerfilTarifario, Boolean perfilTarifarioIsento,
			Integer idPessoa, Date dataInclusao) {
		this(idPessoaInstituicao, dataHoraInicio, idUnidadeInst, idFuncionarioResponsavel, parecerGerencia,
				emiteAvisoLancamento, idNucleo, descricaoNucleo, numNucleo, codigoPerfilTarifario, siglaPerfilTarifario, 
				descricaoPerfilTarifario, valorPercentualPerfilTarifario, codTipoPercentualPerfilTarifario, codTipoPerfilContaPerfilTarifario,
				codigoTipoPerfilPerfilTarifario, perfilTarifarioIsento, idPessoa);
		this.dataInclusao = dataInclusao;
	}

	/**
	 * Recupera id pessoa instituicao.
	 * 
	 * @return id pessoa instituicao
	 */
	public Integer getIdPessoaInstituicao() {
		return idPessoaInstituicao;
	}

	/**
	 * Preenche id pessoa instituicao.
	 * 
	 * @param idPessoaInstituicao
	 *            o novo id pessoa instituicao
	 */
	public void setIdPessoaInstituicao(Integer idPessoaInstituicao) {
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera id unidade inst.
	 * 
	 * @return id unidade inst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Preenche id unidade inst.
	 * 
	 * @param idUnidadeInst
	 *            o novo id unidade inst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera id funcionario responsavel.
	 * 
	 * @return id funcionario responsavel
	 */
	public Integer getIdFuncionarioResponsavel() {
		return idFuncionarioResponsavel;
	}

	/**
	 * Preenche id funcionario responsavel.
	 * 
	 * @param idFuncionarioResponsavel
	 *            o novo id funcionario responsavel
	 */
	public void setIdFuncionarioResponsavel(Integer idFuncionarioResponsavel) {
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
	}

	/**
	 * Recupera parecer gerencia.
	 * 
	 * @return parecer gerencia
	 */
	public String getParecerGerencia() {
		return parecerGerencia;
	}

	/**
	 * Preenche parecer gerencia.
	 * 
	 * @param parecerGerencia
	 *            o novo parecer gerencia
	 */
	public void setParecerGerencia(String parecerGerencia) {
		this.parecerGerencia = parecerGerencia;
	}

	/**
	 * Recupera emite aviso lancamento.
	 * 
	 * @return emite aviso lancamento
	 */
	public Boolean getEmiteAvisoLancamento() {
		return emiteAvisoLancamento;
	}

	/**
	 * Preenche emite aviso lancamento.
	 * 
	 * @param emiteAvisoLancamento
	 *            o novo emite aviso lancamento
	 */
	public void setEmiteAvisoLancamento(Boolean emiteAvisoLancamento) {
		this.emiteAvisoLancamento = emiteAvisoLancamento;
	}

	/**
	 * Recupera id nucleo.
	 * 
	 * @return id nucleo
	 */
	public Integer getIdNucleo() {
		return idNucleo;
	}

	/**
	 * Preenche id nucleo.
	 * 
	 * @param idNucleo
	 *            o novo id nucleo
	 */
	public void setIdNucleo(Integer idNucleo) {
		this.idNucleo = idNucleo;
	}

	/**
	 * Recupera descricao nucleo.
	 * 
	 * @return descricao nucleo
	 */
	public String getDescricaoNucleo() {
		return descricaoNucleo;
	}

	/**
	 * Preenche descricao nucleo.
	 * 
	 * @param descricaoNucleo
	 *            o novo descricao nucleo
	 */
	public void setDescricaoNucleo(String descricaoNucleo) {
		this.descricaoNucleo = descricaoNucleo;
	}

	/**
	 * Recupera num nucleo.
	 * 
	 * @return num nucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * Preenche num nucleo.
	 * 
	 * @param numNucleo
	 *            o novo num nucleo
	 */
	public void setNumNucleo(Integer numNucleo) {
		this.numNucleo = numNucleo;
	}

	/**
	 * Recupera codigo perfil tarifario.
	 * 
	 * @return codigo perfil tarifario
	 */
	public Integer getCodigoPerfilTarifario() {
		return codigoPerfilTarifario;
	}

	/**
	 * Preenche codigo perfil tarifario.
	 * 
	 * @param codigoPerfilTarifario
	 *            o novo codigo perfil tarifario
	 */
	public void setCodigoPerfilTarifario(Integer codigoPerfilTarifario) {
		this.codigoPerfilTarifario = codigoPerfilTarifario;
	}

	/**
	 * Recupera sigla perfil tarifario.
	 * 
	 * @return sigla perfil tarifario
	 */
	public String getSiglaPerfilTarifario() {
		return siglaPerfilTarifario;
	}

	/**
	 * Preenche sigla perfil tarifario.
	 * 
	 * @param siglaPerfilTarifario
	 *            o novo sigla perfil tarifario
	 */
	public void setSiglaPerfilTarifario(String siglaPerfilTarifario) {
		this.siglaPerfilTarifario = siglaPerfilTarifario;
	}

	/**
	 * Recupera descricao perfil tarifario.
	 * 
	 * @return descricao perfil tarifario
	 */
	public String getDescricaoPerfilTarifario() {
		return descricaoPerfilTarifario;
	}

	/**
	 * Preenche descricao perfil tarifario.
	 * 
	 * @param descricaoPerfilTarifario
	 *            o novo descricao perfil tarifario
	 */
	public void setDescricaoPerfilTarifario(String descricaoPerfilTarifario) {
		this.descricaoPerfilTarifario = descricaoPerfilTarifario;
	}

	/**
	 * Recupera valor percentual perfil tarifario.
	 * 
	 * @return valor percentual perfil tarifario
	 */
	public BigDecimal getValorPercentualPerfilTarifario() {
		return valorPercentualPerfilTarifario;
	}

	/**
	 * Preenche valor percentual perfil tarifario.
	 * 
	 * @param valorPercentualPerfilTarifario
	 *            o novo valor percentual perfil tarifario
	 */
	public void setValorPercentualPerfilTarifario(
			BigDecimal valorPercentualPerfilTarifario) {
		this.valorPercentualPerfilTarifario = valorPercentualPerfilTarifario;
	}

	/**
	 * Recupera cod tipo percentual perfil tarifario.
	 * 
	 * @return cod tipo percentual perfil tarifario
	 */
	public Short getCodTipoPercentualPerfilTarifario() {
		return codTipoPercentualPerfilTarifario;
	}

	/**
	 * Preenche cod tipo percentual perfil tarifario.
	 * 
	 * @param codTipoPercentualPerfilTarifario
	 *            o novo cod tipo percentual perfil tarifario
	 */
	public void setCodTipoPercentualPerfilTarifario(
			Short codTipoPercentualPerfilTarifario) {
		this.codTipoPercentualPerfilTarifario = codTipoPercentualPerfilTarifario;
	}

	/**
	 * Recupera cod tipo perfil conta perfil tarifario.
	 * 
	 * @return cod tipo perfil conta perfil tarifario
	 */
	public Short getCodTipoPerfilContaPerfilTarifario() {
		return codTipoPerfilContaPerfilTarifario;
	}

	/**
	 * Preenche cod tipo perfil conta perfil tarifario.
	 * 
	 * @param codTipoPerfilContaPerfilTarifario
	 *            o novo cod tipo perfil conta perfil tarifario
	 */
	public void setCodTipoPerfilContaPerfilTarifario(
			Short codTipoPerfilContaPerfilTarifario) {
		this.codTipoPerfilContaPerfilTarifario = codTipoPerfilContaPerfilTarifario;
	}

	/**
	 * Recupera codigo tipo perfil perfil tarifario.
	 * 
	 * @return codigo tipo perfil perfil tarifario
	 */
	public Short getCodigoTipoPerfilPerfilTarifario() {
		return codigoTipoPerfilPerfilTarifario;
	}

	/**
	 * Preenche codigo tipo perfil perfil tarifario.
	 * 
	 * @param codigoTipoPerfilPerfilTarifario
	 *            o novo codigo tipo perfil perfil tarifario
	 */
	public void setCodigoTipoPerfilPerfilTarifario(
			Short codigoTipoPerfilPerfilTarifario) {
		this.codigoTipoPerfilPerfilTarifario = codigoTipoPerfilPerfilTarifario;
	}

	/**
	 * Recupera perfil tarifario isento.
	 * 
	 * @return perfil tarifario isento
	 */
	public Boolean getPerfilTarifarioIsento() {
		return perfilTarifarioIsento;
	}

	/**
	 * Preenche perfil tarifario isento.
	 * 
	 * @param perfilTarifarioIsento
	 *            o novo perfil tarifario isento
	 */
	public void setPerfilTarifarioIsento(Boolean perfilTarifarioIsento) {
		this.perfilTarifarioIsento = perfilTarifarioIsento;
	}

	/**
	 * Recupera id pessoa.
	 * 
	 * @return id pessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Preenche id pessoa.
	 * 
	 * @param idPessoa
	 *            o novo id pessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
