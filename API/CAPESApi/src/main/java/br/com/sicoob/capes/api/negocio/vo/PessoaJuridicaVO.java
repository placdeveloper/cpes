/*
 * SICOOB
 * 
 * PessoaJuridicaVO.java(br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Lucas.Borges
 */
public class PessoaJuridicaVO extends BancoobDto{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -8448059390204369786L;

	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;
	
	/** O atributo nome completo. */
	private String nomeCompleto;
	
	/** O atributo data constituicao. */
	private Date dataConstituicao;

	/** O atributo valor capital social. */
	private BigDecimal valorCapitalSocial;

	/** O atributo inscricao estadual. */
	private String inscricaoEstadual;
	
	/** O atributo inscricao municipal. */
	private String inscricaoMunicipal;

	/** O atributo codigo esfera administrativa. */
	private Short codigoEsferaAdministrativa;
	
	/** O atributo numero registro junta comercial. */
	private String numeroRegistroJuntaComercial;

	/** O atributo data registro junta comercial. */
	private Date dataRegistroJuntaComercial;

	/** O atributo numero ultima alteracao contrato social. */
	private String numeroUltimaAlteracaoContratoSocial;

	/** O atributo data ultima alteracao contrato social. */
	private Date dataUltimaAlteracaoContratoSocial;

	/** O atributo numero registro representacao. */
	private String numeroRegistroRepresentacao;

	/** O atributo data registro representacao. */
	private Date dataRegistroRepresentacao;

	// TIPO EMPRESA
	/** O atributo codigo tipo empresa. */
	private Short codigoTipoEmpresa;
	
	/** O atributo descricao tipo empresa. */
	private String descricaoTipoEmpresa;

	// Tipo Forma Constituicao
	/** O atributo codigo tipo forma constituicao. */
	private Short codigoTipoFormaConstituicao;
	
	/** O atributo descricao tipo forma constituicao. */
	private String descricaoTipoFormaConstituicao;
	
	/** O atributo importador. */
	private Boolean importador;
	
	/** O atributo exportador. */
	private Boolean exportador;
	
	
	/**
	 * Cria uma nova instância de pessoa juridica vo.
	 */
	public PessoaJuridicaVO() {

	}

	/**
	 * Cria uma nova instância de pessoa juridica vo.
	 * 
	 * @param dataConstituicao
	 *            the data constituicao
	 * @param valorCapitalSocial
	 *            the valor capital social
	 * @param inscricaoEstadual
	 *            the inscricao estadual
	 * @param codigoEsferaAdministrativa
	 *            the codigo esfera administrativa
	 * @param numeroRegistroJuntaComercial
	 *            the numero registro junta comercial
	 * @param dataRegistroJuntaComercial
	 *            the data registro junta comercial
	 * @param numeroUltimaAlteracaoContratoSocial
	 *            the numero ultima alteracao contrato social
	 * @param dataUltimaAlteracaoContratoSocial
	 *            the data ultima alteracao contrato social
	 * @param numeroRegistroRepresentacao
	 *            the numero registro representacao
	 * @param dataRegistroRepresentacao
	 *            the data registro representacao
	 * @param codigoTipoEmpresa
	 *            the codigo tipo empresa
	 * @param descricaoTipoEmpresa
	 *            the descricao tipo empresa
	 * @param codigoTipoFormaConstituicao
	 *            the codigo tipo forma constituicao
	 * @param descricaoTipoFormaConstituicao
	 *            the descricao tipo forma constituicao
	 */
	public PessoaJuridicaVO(Date dataConstituicao,
			BigDecimal valorCapitalSocial, String inscricaoEstadual,
			Short codigoEsferaAdministrativa,
			String numeroRegistroJuntaComercial,
			Date dataRegistroJuntaComercial,
			String numeroUltimaAlteracaoContratoSocial,
			Date dataUltimaAlteracaoContratoSocial,
			String numeroRegistroRepresentacao, Date dataRegistroRepresentacao,
			Short codigoTipoEmpresa, String descricaoTipoEmpresa,
			Short codigoTipoFormaConstituicao,
			String descricaoTipoFormaConstituicao,
			Boolean importador, Boolean exportador) {
		this.dataConstituicao = (Date) (dataConstituicao != null ? dataConstituicao.clone() : null);
		this.valorCapitalSocial = valorCapitalSocial;
		this.inscricaoEstadual = inscricaoEstadual;
		this.codigoEsferaAdministrativa = codigoEsferaAdministrativa;
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
		this.numeroUltimaAlteracaoContratoSocial = numeroUltimaAlteracaoContratoSocial;
		this.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracaoContratoSocial;
		this.numeroRegistroRepresentacao = numeroRegistroRepresentacao;
		this.dataRegistroRepresentacao = dataRegistroRepresentacao;
		this.codigoTipoEmpresa = codigoTipoEmpresa;
		this.descricaoTipoEmpresa = descricaoTipoEmpresa;
		this.codigoTipoFormaConstituicao = codigoTipoFormaConstituicao;
		this.descricaoTipoFormaConstituicao = descricaoTipoFormaConstituicao;
		this.importador = importador;
		this.exportador = exportador;
	}
	
	/**
	 * Cria uma nova instância de pessoa juridica vo.
	 * 
	 * @param dataConstituicao
	 *            the data constituicao
	 * @param valorCapitalSocial
	 *            the valor capital social
	 * @param inscricaoEstadual
	 *            the inscricao estadual
	 * @param codigoEsferaAdministrativa
	 *            the codigo esfera administrativa
	 * @param numeroRegistroJuntaComercial
	 *            the numero registro junta comercial
	 * @param dataRegistroJuntaComercial
	 *            the data registro junta comercial
	 * @param numeroUltimaAlteracaoContratoSocial
	 *            the numero ultima alteracao contrato social
	 * @param dataUltimaAlteracaoContratoSocial
	 *            the data ultima alteracao contrato social
	 * @param numeroRegistroRepresentacao
	 *            the numero registro representacao
	 * @param dataRegistroRepresentacao
	 *            the data registro representacao
	 * @param codigoTipoEmpresa
	 *            the codigo tipo empresa
	 * @param descricaoTipoEmpresa
	 *            the descricao tipo empresa
	 * @param codigoTipoFormaConstituicao
	 *            the codigo tipo forma constituicao
	 * @param descricaoTipoFormaConstituicao
	 *            the descricao tipo forma constituicao
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param nomeCompleto
	 *            the nome completo
	 */
	public PessoaJuridicaVO(Date dataConstituicao,
			BigDecimal valorCapitalSocial, 
			String inscricaoEstadual,
			String inscricaoMunicipal,
			Short codigoEsferaAdministrativa,
			String numeroRegistroJuntaComercial,
			Date dataRegistroJuntaComercial,
			String numeroUltimaAlteracaoContratoSocial,
			Date dataUltimaAlteracaoContratoSocial,
			String numeroRegistroRepresentacao, Date dataRegistroRepresentacao,
			Short codigoTipoEmpresa, String descricaoTipoEmpresa,
			Short codigoTipoFormaConstituicao,
			String descricaoTipoFormaConstituicao,
			Integer idPessoa, String cpfCnpj,String nomePessoa,String nomeCompleto,
			Boolean importador, Boolean exportador) {
		this(dataConstituicao, valorCapitalSocial, inscricaoEstadual, codigoEsferaAdministrativa, 
				numeroRegistroJuntaComercial, dataRegistroJuntaComercial, 
				numeroUltimaAlteracaoContratoSocial, dataUltimaAlteracaoContratoSocial, 
				numeroRegistroRepresentacao, dataRegistroRepresentacao, codigoTipoEmpresa, 
				descricaoTipoEmpresa, codigoTipoFormaConstituicao, descricaoTipoFormaConstituicao,
				importador, exportador);
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nomePessoa = nomePessoa;
		this.nomeCompleto = nomeCompleto;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.importador = importador;
		this.exportador = exportador;
	}

	/**
	 * Recupera data constituicao.
	 * 
	 * @return data constituicao
	 */
	public Date getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * Preenche data constituicao.
	 * 
	 * @param dataConstituicao
	 *            o novo data constituicao
	 */
	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * Recupera valor capital social.
	 * 
	 * @return valor capital social
	 */
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	/**
	 * Preenche valor capital social.
	 * 
	 * @param valorCapitalSocial
	 *            o novo valor capital social
	 */
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	/**
	 * Recupera inscricao estadual.
	 * 
	 * @return inscricao estadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * Preenche inscricao estadual.
	 * 
	 * @param inscricaoEstadual
	 *            o novo inscricao estadual
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	/**
	 * Recupera o valor de inscricaoMunicipal.
	 *
	 * @return o valor de inscricaoMunicipal
	 */
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	/**
	 * Define o valor de inscricaoMunicipal.
	 *
	 * @param inscricaoMunicipal o novo valor de inscricaoMunicipal
	 */
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	/**
	 * Recupera codigo esfera administrativa.
	 * 
	 * @return codigo esfera administrativa
	 */
	public Short getCodigoEsferaAdministrativa() {
		return codigoEsferaAdministrativa;
	}

	/**
	 * Preenche codigo esfera administrativa.
	 * 
	 * @param codigoEsferaAdministrativa
	 *            o novo codigo esfera administrativa
	 */
	public void setCodigoEsferaAdministrativa(Short codigoEsferaAdministrativa) {
		this.codigoEsferaAdministrativa = codigoEsferaAdministrativa;
	}

	/**
	 * Recupera numero registro junta comercial.
	 * 
	 * @return numero registro junta comercial
	 */
	public String getNumeroRegistroJuntaComercial() {
		return numeroRegistroJuntaComercial;
	}

	/**
	 * Preenche numero registro junta comercial.
	 * 
	 * @param numeroRegistroJuntaComercial
	 *            o novo numero registro junta comercial
	 */
	public void setNumeroRegistroJuntaComercial(String numeroRegistroJuntaComercial) {
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
	}

	/**
	 * Recupera data registro junta comercial.
	 * 
	 * @return data registro junta comercial
	 */
	public Date getDataRegistroJuntaComercial() {
		return dataRegistroJuntaComercial;
	}

	/**
	 * Preenche data registro junta comercial.
	 * 
	 * @param dataRegistroJuntaComercial
	 *            o novo data registro junta comercial
	 */
	public void setDataRegistroJuntaComercial(Date dataRegistroJuntaComercial) {
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
	}

	/**
	 * Recupera numero ultima alteracao contrato social.
	 * 
	 * @return numero ultima alteracao contrato social
	 */
	public String getNumeroUltimaAlteracaoContratoSocial() {
		return numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * Preenche numero ultima alteracao contrato social.
	 * 
	 * @param numeroUltimaAlteracaoContratoSocial
	 *            o novo numero ultima alteracao contrato social
	 */
	public void setNumeroUltimaAlteracaoContratoSocial(
			String numeroUltimaAlteracaoContratoSocial) {
		this.numeroUltimaAlteracaoContratoSocial = numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * Recupera data ultima alteracao contrato social.
	 * 
	 * @return data ultima alteracao contrato social
	 */
	public Date getDataUltimaAlteracaoContratoSocial() {
		return dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * Preenche data ultima alteracao contrato social.
	 * 
	 * @param dataUltimaAlteracaoContratoSocial
	 *            o novo data ultima alteracao contrato social
	 */
	public void setDataUltimaAlteracaoContratoSocial(
			Date dataUltimaAlteracaoContratoSocial) {
		this.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * Recupera numero registro representacao.
	 * 
	 * @return numero registro representacao
	 */
	public String getNumeroRegistroRepresentacao() {
		return numeroRegistroRepresentacao;
	}

	/**
	 * Preenche numero registro representacao.
	 * 
	 * @param numeroRegistroRepresentacao
	 *            o novo numero registro representacao
	 */
	public void setNumeroRegistroRepresentacao(String numeroRegistroRepresentacao) {
		this.numeroRegistroRepresentacao = numeroRegistroRepresentacao;
	}

	/**
	 * Recupera data registro representacao.
	 * 
	 * @return data registro representacao
	 */
	public Date getDataRegistroRepresentacao() {
		return dataRegistroRepresentacao;
	}

	/**
	 * Preenche data registro representacao.
	 * 
	 * @param dataRegistroRepresentacao
	 *            o novo data registro representacao
	 */
	public void setDataRegistroRepresentacao(Date dataRegistroRepresentacao) {
		this.dataRegistroRepresentacao = dataRegistroRepresentacao;
	}

	/**
	 * Recupera codigo tipo empresa.
	 * 
	 * @return codigo tipo empresa
	 */
	public Short getCodigoTipoEmpresa() {
		return codigoTipoEmpresa;
	}

	/**
	 * Preenche codigo tipo empresa.
	 * 
	 * @param codigoTipoEmpresa
	 *            o novo codigo tipo empresa
	 */
	public void setCodigoTipoEmpresa(Short codigoTipoEmpresa) {
		this.codigoTipoEmpresa = codigoTipoEmpresa;
	}

	/**
	 * Recupera descricao tipo empresa.
	 * 
	 * @return descricao tipo empresa
	 */
	public String getDescricaoTipoEmpresa() {
		return descricaoTipoEmpresa;
	}

	/**
	 * Preenche descricao tipo empresa.
	 * 
	 * @param descricaoTipoEmpresa
	 *            o novo descricao tipo empresa
	 */
	public void setDescricaoTipoEmpresa(String descricaoTipoEmpresa) {
		this.descricaoTipoEmpresa = descricaoTipoEmpresa;
	}

	/**
	 * Recupera codigo tipo forma constituicao.
	 * 
	 * @return codigo tipo forma constituicao
	 */
	public Short getCodigoTipoFormaConstituicao() {
		return codigoTipoFormaConstituicao;
	}

	/**
	 * Preenche codigo tipo forma constituicao.
	 * 
	 * @param codigoTipoFormaConstituicao
	 *            o novo codigo tipo forma constituicao
	 */
	public void setCodigoTipoFormaConstituicao(Short codigoTipoFormaConstituicao) {
		this.codigoTipoFormaConstituicao = codigoTipoFormaConstituicao;
	}

	/**
	 * Recupera descricao tipo forma constituicao.
	 * 
	 * @return descricao tipo forma constituicao
	 */
	public String getDescricaoTipoFormaConstituicao() {
		return descricaoTipoFormaConstituicao;
	}

	/**
	 * Preenche descricao tipo forma constituicao.
	 * 
	 * @param descricaoTipoFormaConstituicao
	 *            o novo descricao tipo forma constituicao
	 */
	public void setDescricaoTipoFormaConstituicao(
			String descricaoTipoFormaConstituicao) {
		this.descricaoTipoFormaConstituicao = descricaoTipoFormaConstituicao;
	}

	/**
	 * Recupera o atributo idPessoa
	 * @return o atributo idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor para o atributo idPessoa
	 * @param idPessoa o idPessoa a ser definido.
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o atributo cpfCnpj
	 * @return o atributo cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor para o atributo cpfCnpj
	 * @param cpfCnpj o cpfCnpj a ser definido.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o atributo nomePessoa
	 * @return o atributo nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor para o atributo nomePessoa
	 * @param nomePessoa o nomePessoa a ser definido.
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera o atributo nomeCompleto
	 * @return o atributo nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Define o valor para o atributo nomeCompleto
	 * @param nomeCompleto o nomeCompleto a ser definido.
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	/**
	 * Recupera importador.
	 * 
	 * @return importador
	 */
	public Boolean getImportador() {
		return importador;
	}

	/**
	 * Define o valor para o atributo importador
	 * @param importador o importador a ser definido.
	 */
	public void setImportador(Boolean importador) {
		this.importador = importador;
	}

	/**
	 * Recupera importador.
	 * 
	 * @return importador
	 */
	public Boolean getExportador() {
		return exportador;
	}

	/**
	 * Define o valor para o atributo exportador
	 * @param exportador o exportador a ser definido.
	 */
	public void setExportador(Boolean exportador) {
		this.exportador = exportador;
	}
	
}