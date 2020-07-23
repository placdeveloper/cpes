/**
 * 
 */
package br.com.sicoob.capes.cadastro.vo.informacoesprofissionais;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Erico.Junior
 *
 */
public class InformacaoProfissionalVO extends BancoobVo {

	/** Serial UID.*/
	private static final long serialVersionUID = 4415039546952781728L;

	/** O atributo idInformacao. */
	private Integer idInformacao;
	
	/** O atributo dataHoraInicio. */
	private DateTime dataHoraInicio;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idPessoaEmpregador. */
	private Integer idPessoaEmpregador;
	
	/** O atributo cnpjPessoaEmpregador. */
	private String cnpjPessoaEmpregador;
	
	/** O atributo nomePessoaEmpregador. */
	private String nomePessoaEmpregador;
	
	/** O atributo numMatricula. */
	private String numMatricula;
	
	/** O atributo codigoTipoFuncionario. */
	private Short codigoTipoFuncionario;

	/** O atributo codigoTipoAfastamento. */
	private Short codigoTipoAfastamento;
	
	/** O atributo codSituacao. */
	private Short codSituacao;

	/** O atributo cargo. */
	private String cargo;
	
	/** O atributo dataAdmissao. */
	private DateTime dataAdmissao;
	
	/** O atributo diaMesFerias. */
	private String diaMesFerias;
	
	/** O atributo dataDesligamento. */
	private DateTime dataDesligamento;
	
	/** O atributo codigoConselhoRegional. */
	private Short codigoConselhoRegional;

	/** O atributo ufConselho. */
	private String ufConselho;
	
	/** O atributo numeroInscricaoConselho. */
	private String numeroInscricaoConselho;
	
	/** O atributo idUsuarioAprovacao. */
	private String idUsuarioAprovacao;

	/**
	 * Recupera o valor de idInformacao.
	 *
	 * @return o valor de idInformacao
	 */
	public Integer getIdInformacao() {
		return idInformacao;
	}

	/**
	 * Define o valor de idInformacao.
	 *
	 * @param idInformacao o novo valor de idInformacao
	 */
	public void setIdInformacao(Integer idInformacao) {
		this.idInformacao = idInformacao;
	}

	/**
	 * Recupera o valor de dataHoraInicio.
	 *
	 * @return o valor de dataHoraInicio
	 */
	public DateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Define o valor de dataHoraInicio.
	 *
	 * @param dataHoraInicio o novo valor de dataHoraInicio
	 */
	public void setDataHoraInicio(DateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de idPessoaEmpregador.
	 *
	 * @return o valor de idPessoaEmpregador
	 */
	public Integer getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}

	/**
	 * Define o valor de idPessoaEmpregador.
	 *
	 * @param idPessoaEmpregador o novo valor de idPessoaEmpregador
	 */
	public void setIdPessoaEmpregador(Integer idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

	/**
	 * Recupera o valor de cnpjPessoaEmpregador.
	 *
	 * @return o valor de cnpjPessoaEmpregador
	 */
	public String getCnpjPessoaEmpregador() {
		return cnpjPessoaEmpregador;
	}

	/**
	 * Define o valor de cnpjPessoaEmpregador.
	 *
	 * @param cnpjPessoaEmpregador o novo valor de cnpjPessoaEmpregador
	 */
	public void setCnpjPessoaEmpregador(String cnpjPessoaEmpregador) {
		this.cnpjPessoaEmpregador = cnpjPessoaEmpregador;
	}

	/**
	 * Recupera o valor de nomePessoaEmpregador.
	 *
	 * @return o valor de nomePessoaEmpregador
	 */
	public String getNomePessoaEmpregador() {
		return nomePessoaEmpregador;
	}

	/**
	 * Define o valor de nomePessoaEmpregador.
	 *
	 * @param nomePessoaEmpregador o novo valor de nomePessoaEmpregador
	 */
	public void setNomePessoaEmpregador(String nomePessoaEmpregador) {
		this.nomePessoaEmpregador = nomePessoaEmpregador;
	}

	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public String getNumMatricula() {
		return numMatricula;
	}

	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}

	/**
	 * Recupera o valor de codigoTipoFuncionario.
	 *
	 * @return o valor de codigoTipoFuncionario
	 */
	public Short getCodigoTipoFuncionario() {
		return codigoTipoFuncionario;
	}

	/**
	 * Define o valor de codigoTipoFuncionario.
	 *
	 * @param codigoTipoFuncionario o novo valor de codigoTipoFuncionario
	 */
	public void setCodigoTipoFuncionario(Short codigoTipoFuncionario) {
		this.codigoTipoFuncionario = codigoTipoFuncionario;
	}

	/**
	 * Recupera o valor de codigoTipoAfastamento.
	 *
	 * @return o valor de codigoTipoAfastamento
	 */
	public Short getCodigoTipoAfastamento() {
		return codigoTipoAfastamento;
	}

	/**
	 * Define o valor de codigoTipoAfastamento.
	 *
	 * @param codigoTipoAfastamento o novo valor de codigoTipoAfastamento
	 */
	public void setCodigoTipoAfastamento(Short codigoTipoAfastamento) {
		this.codigoTipoAfastamento = codigoTipoAfastamento;
	}

	/**
	 * Recupera o valor de codSituacao.
	 *
	 * @return o valor de codSituacao
	 */
	public Short getCodSituacao() {
		return codSituacao;
	}

	/**
	 * Define o valor de codSituacao.
	 *
	 * @param codSituacao o novo valor de codSituacao
	 */
	public void setCodSituacao(Short codSituacao) {
		this.codSituacao = codSituacao;
	}

	/**
	 * Recupera o valor de cargo.
	 *
	 * @return o valor de cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Define o valor de cargo.
	 *
	 * @param cargo o novo valor de cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Recupera o valor de dataAdmissao.
	 *
	 * @return o valor de dataAdmissao
	 */
	public DateTime getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * Define o valor de dataAdmissao.
	 *
	 * @param dataAdmissao o novo valor de dataAdmissao
	 */
	public void setDataAdmissao(DateTime dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Recupera o valor de diaMesFerias.
	 *
	 * @return o valor de diaMesFerias
	 */
	public String getDiaMesFerias() {
		return diaMesFerias;
	}

	/**
	 * Define o valor de diaMesFerias.
	 *
	 * @param diaMesFerias o novo valor de diaMesFerias
	 */
	public void setDiaMesFerias(String diaMesFerias) {
		this.diaMesFerias = diaMesFerias;
	}

	/**
	 * Recupera o valor de dataDesligamento.
	 *
	 * @return o valor de dataDesligamento
	 */
	public DateTime getDataDesligamento() {
		return dataDesligamento;
	}

	/**
	 * Define o valor de dataDesligamento.
	 *
	 * @param dataDesligamento o novo valor de dataDesligamento
	 */
	public void setDataDesligamento(DateTime dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	/**
	 * Recupera o valor de codigoConselhoRegional.
	 *
	 * @return o valor de codigoConselhoRegional
	 */
	public Short getCodigoConselhoRegional() {
		return codigoConselhoRegional;
	}

	/**
	 * Define o valor de codigoConselhoRegional.
	 *
	 * @param codigoConselhoRegional o novo valor de codigoConselhoRegional
	 */
	public void setCodigoConselhoRegional(Short codigoConselhoRegional) {
		this.codigoConselhoRegional = codigoConselhoRegional;
	}

	/**
	 * Recupera o valor de ufConselho.
	 *
	 * @return o valor de ufConselho
	 */
	public String getUfConselho() {
		return ufConselho;
	}

	/**
	 * Define o valor de ufConselho.
	 *
	 * @param ufConselho o novo valor de ufConselho
	 */
	public void setUfConselho(String ufConselho) {
		this.ufConselho = ufConselho;
	}

	/**
	 * Recupera o valor de numeroInscricaoConselho.
	 *
	 * @return o valor de numeroInscricaoConselho
	 */
	public String getNumeroInscricaoConselho() {
		return numeroInscricaoConselho;
	}

	/**
	 * Define o valor de numeroInscricaoConselho.
	 *
	 * @param numeroInscricaoConselho o novo valor de numeroInscricaoConselho
	 */
	public void setNumeroInscricaoConselho(String numeroInscricaoConselho) {
		this.numeroInscricaoConselho = numeroInscricaoConselho;
	}
	
	/**
	 * Recupera o valor de idUsuarioAprovacao.
	 *
	 * @return o valor de idUsuarioAprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}
	
	/**
	 * Define o valor de idUsuarioAprovacao.
	 *
	 * @param idUsuarioAprovacao o novo valor de idUsuarioAprovacao
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}
	
}
