/*
 * SICOOB
 * 
 * InformacaoProfissionalVO.java(br.com.sicoob.capes.api.negocio.vo.InformacaoProfissionalVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author erico.junior
 *
 */
public class InformacaoProfissionalVO extends AbstractPessoaVO {

	/** Serial UID.*/
	private static final long serialVersionUID = 1877405915722901318L;
	
	/** O atributo id informacao. */
	private Integer idInformacao;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo id pessoa empregador. */
	private Integer idPessoaEmpregador;
	
	/** O atributo num matricula. */
	private String numMatricula;
	
	/** O atributo codigo tipo funcionario. */
	private Short codigoTipoFuncionario;
	
	/** O atributo descricao tipo funcionario. */
	private String descricaoTipoFuncionario;
	
	/** O atributo codigo tipo afastamento. */
	private Short codigoTipoAfastamento;
	
	/** O atributo descricao tipo afastamento. */
	private String descricaoTipoAfastamento;
	
	/** O atributo cod situacao funcionario. */
	private Short codSituacaoFuncionario;
	
	/** O atributo cargo. */
	private String cargo;
	
	/** O atributo data admissao. */
	private Date dataAdmissao;
	
	/** O atributo dia mes ferias. */
	private String diaMesFerias;
	
	/** O atributo data desligamento. */
	private Date dataDesligamento;
	
	/** O atributo codigo conselho regional. */
	private Short codigoConselhoRegional;
	
	/** O atributo descricao conselho regional. */
	private String descricaoConselhoRegional;
	
	/** O atributo uf conselho. */
	private String ufConselho;
	
	/** O atributo numero inscricao conselho. */
	private String numeroInscricaoConselho;
	
	/**
	 * Cria uma nova instância de informacao profissional vo.
	 */
	public InformacaoProfissionalVO(){
	}
	
	/**
	 * Cria uma nova instância de informacao profissional vo.
	 * 
	 * @param idInformacao
	 *            the id informacao
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idPessoa
	 *            the id pessoa
	 * @param idPessoaEmpregador
	 *            the id pessoa empregador
	 * @param numMatricula
	 *            the num matricula
	 * @param codigoTipoFuncionario
	 *            the codigo tipo funcionario
	 * @param descricaoTipoFuncionario
	 *            the descricao tipo funcionario
	 * @param codigoTipoAfastamento
	 *            the codigo tipo afastamento
	 * @param descricaoTipoAfastamento
	 *            the descricao tipo afastamento
	 * @param codSituacaoFuncionario
	 *            the cod situacao funcionario
	 * @param cargo
	 *            the cargo
	 * @param dataAdmissao
	 *            the data admissao
	 * @param diaMesFerias
	 *            the dia mes ferias
	 * @param dataDesligamento
	 *            the data desligamento
	 * @param codigoConselhoRegional
	 *            the codigo conselho regional
	 * @param descricaoConselhoRegional
	 *            the descricao conselho regional
	 * @param ufConselho
	 *            the uf conselho
	 * @param numeroInscricaoConselho
	 *            the numero inscricao conselho
	 */
	public InformacaoProfissionalVO(Integer idInformacao, Date dataHoraInicio,
			Integer idInstituicao, Integer idPessoa,
			Integer idPessoaEmpregador, String numMatricula,
			Short codigoTipoFuncionario, String descricaoTipoFuncionario,
			Short codigoTipoAfastamento, String descricaoTipoAfastamento,
			Short codSituacaoFuncionario, String cargo, Date dataAdmissao,
			String diaMesFerias, Date dataDesligamento,
			Short codigoConselhoRegional, String descricaoConselhoRegional,
			String ufConselho, String numeroInscricaoConselho) {
		super();
		this.idInformacao = idInformacao;
		this.dataHoraInicio = dataHoraInicio;
		this.idInstituicao = idInstituicao;
		this.idPessoa = idPessoa;
		this.idPessoaEmpregador = idPessoaEmpregador;
		this.numMatricula = numMatricula;
		this.codigoTipoFuncionario = codigoTipoFuncionario;
		this.descricaoTipoFuncionario = descricaoTipoFuncionario;
		this.codigoTipoAfastamento = codigoTipoAfastamento;
		this.descricaoTipoAfastamento = descricaoTipoAfastamento;
		this.codSituacaoFuncionario = codSituacaoFuncionario;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
		this.diaMesFerias = diaMesFerias;
		this.dataDesligamento = dataDesligamento;
		this.codigoConselhoRegional = codigoConselhoRegional;
		this.descricaoConselhoRegional = descricaoConselhoRegional;
		this.ufConselho = ufConselho;
		this.numeroInscricaoConselho = numeroInscricaoConselho;
	}
	
	/**
	 * Recupera id informacao.
	 * 
	 * @return id informacao
	 */
	public Integer getIdInformacao() {
		return idInformacao;
	}
	
	/**
	 * Preenche id informacao.
	 * 
	 * @param idInformacao
	 *            o novo id informacao
	 */
	public void setIdInformacao(Integer idInformacao) {
		this.idInformacao = idInformacao;
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
	
	/**
	 * Recupera id pessoa empregador.
	 * 
	 * @return id pessoa empregador
	 */
	public Integer getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}
	
	/**
	 * Preenche id pessoa empregador.
	 * 
	 * @param idPessoaEmpregador
	 *            o novo id pessoa empregador
	 */
	public void setIdPessoaEmpregador(Integer idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}
	
	/**
	 * Recupera num matricula.
	 * 
	 * @return num matricula
	 */
	public String getNumMatricula() {
		return numMatricula;
	}
	
	/**
	 * Preenche num matricula.
	 * 
	 * @param numMatricula
	 *            o novo num matricula
	 */
	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}
	
	/**
	 * Recupera codigo tipo funcionario.
	 * 
	 * @return codigo tipo funcionario
	 */
	public Short getCodigoTipoFuncionario() {
		return codigoTipoFuncionario;
	}
	
	/**
	 * Preenche codigo tipo funcionario.
	 * 
	 * @param codigoTipoFuncionario
	 *            o novo codigo tipo funcionario
	 */
	public void setCodigoTipoFuncionario(Short codigoTipoFuncionario) {
		this.codigoTipoFuncionario = codigoTipoFuncionario;
	}
	
	/**
	 * Recupera descricao tipo funcionario.
	 * 
	 * @return descricao tipo funcionario
	 */
	public String getDescricaoTipoFuncionario() {
		return descricaoTipoFuncionario;
	}
	
	/**
	 * Preenche descricao tipo funcionario.
	 * 
	 * @param descricaoTipoFuncionario
	 *            o novo descricao tipo funcionario
	 */
	public void setDescricaoTipoFuncionario(String descricaoTipoFuncionario) {
		this.descricaoTipoFuncionario = descricaoTipoFuncionario;
	}
	
	/**
	 * Recupera codigo tipo afastamento.
	 * 
	 * @return codigo tipo afastamento
	 */
	public Short getCodigoTipoAfastamento() {
		return codigoTipoAfastamento;
	}
	
	/**
	 * Preenche codigo tipo afastamento.
	 * 
	 * @param codigoTipoAfastamento
	 *            o novo codigo tipo afastamento
	 */
	public void setCodigoTipoAfastamento(Short codigoTipoAfastamento) {
		this.codigoTipoAfastamento = codigoTipoAfastamento;
	}
	
	/**
	 * Recupera descricao tipo afastamento.
	 * 
	 * @return descricao tipo afastamento
	 */
	public String getDescricaoTipoAfastamento() {
		return descricaoTipoAfastamento;
	}
	
	/**
	 * Preenche descricao tipo afastamento.
	 * 
	 * @param descricaoTipoAfastamento
	 *            o novo descricao tipo afastamento
	 */
	public void setDescricaoTipoAfastamento(String descricaoTipoAfastamento) {
		this.descricaoTipoAfastamento = descricaoTipoAfastamento;
	}
	
	/**
	 * Recupera cod situacao funcionario.
	 * 
	 * @return cod situacao funcionario
	 */
	public Short getCodSituacaoFuncionario() {
		return codSituacaoFuncionario;
	}
	
	/**
	 * Preenche cod situacao funcionario.
	 * 
	 * @param codSituacaoFuncionario
	 *            o novo cod situacao funcionario
	 */
	public void setCodSituacaoFuncionario(Short codSituacaoFuncionario) {
		this.codSituacaoFuncionario = codSituacaoFuncionario;
	}
	
	/**
	 * Recupera cargo.
	 * 
	 * @return cargo
	 */
	public String getCargo() {
		return cargo;
	}
	
	/**
	 * Preenche cargo.
	 * 
	 * @param cargo
	 *            o novo cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	/**
	 * Recupera data admissao.
	 * 
	 * @return data admissao
	 */
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	
	/**
	 * Preenche data admissao.
	 * 
	 * @param dataAdmissao
	 *            o novo data admissao
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	/**
	 * Recupera data desligamento.
	 * 
	 * @return data desligamento
	 */
	public Date getDataDesligamento() {
		return dataDesligamento;
	}
	
	/**
	 * Preenche data desligamento.
	 * 
	 * @param dataDesligamento
	 *            o novo data desligamento
	 */
	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}
	
	/**
	 * Recupera codigo conselho regional.
	 * 
	 * @return codigo conselho regional
	 */
	public Short getCodigoConselhoRegional() {
		return codigoConselhoRegional;
	}
	
	/**
	 * Preenche codigo conselho regional.
	 * 
	 * @param codigoConselhoRegional
	 *            o novo codigo conselho regional
	 */
	public void setCodigoConselhoRegional(Short codigoConselhoRegional) {
		this.codigoConselhoRegional = codigoConselhoRegional;
	}
	
	/**
	 * Recupera descricao conselho regional.
	 * 
	 * @return descricao conselho regional
	 */
	public String getDescricaoConselhoRegional() {
		return descricaoConselhoRegional;
	}
	
	/**
	 * Preenche descricao conselho regional.
	 * 
	 * @param descricaoConselhoRegional
	 *            o novo descricao conselho regional
	 */
	public void setDescricaoConselhoRegional(String descricaoConselhoRegional) {
		this.descricaoConselhoRegional = descricaoConselhoRegional;
	}
	
	/**
	 * Recupera uf conselho.
	 * 
	 * @return uf conselho
	 */
	public String getUfConselho() {
		return ufConselho;
	}
	
	/**
	 * Preenche uf conselho.
	 * 
	 * @param ufConselho
	 *            o novo uf conselho
	 */
	public void setUfConselho(String ufConselho) {
		this.ufConselho = ufConselho;
	}
	
	/**
	 * Recupera numero inscricao conselho.
	 * 
	 * @return numero inscricao conselho
	 */
	public String getNumeroInscricaoConselho() {
		return numeroInscricaoConselho;
	}
	
	/**
	 * Preenche numero inscricao conselho.
	 * 
	 * @param numeroInscricaoConselho
	 *            o novo numero inscricao conselho
	 */
	public void setNumeroInscricaoConselho(String numeroInscricaoConselho) {
		this.numeroInscricaoConselho = numeroInscricaoConselho;
	}
	
	/**
	 * Recupera dia mes ferias.
	 * 
	 * @return dia mes ferias
	 */
	public String getDiaMesFerias() {
		return diaMesFerias;
	}
	
	/**
	 * Preenche dia mes ferias.
	 * 
	 * @param diaMesFerias
	 *            o novo dia mes ferias
	 */
	public void setDiaMesFerias(String diaMesFerias) {
		this.diaMesFerias = diaMesFerias;
	}
}
