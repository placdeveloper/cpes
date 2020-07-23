/*
 * SICOOB
 * 
 * PessoaFisicaVO.java(br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Lucas.Borges
 */
public class PessoaFisicaVO extends BancoobDto{
	
	/** Serial UID.*/
	private static final long serialVersionUID = 8888078881722588939L;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;
	
	/** O atributo nome completo. */
	private String nomeCompleto;
	
	/** O atributo data nascimento. */
	private Date dataNascimento;

	/** O atributo menor emancipado. */
	private Boolean menorEmancipado;

	/** O atributo nome pai. */
	private String nomePai;

	/** O atributo nome mae. */
	private String nomeMae;
	
	/** O atributo numero documento. */
	private String numeroDocumento;

	/** O atributo orgao expedidor documento. */
	private String orgaoExpedidorDocumento;

	/** O atributo uf orgao expedidor documento. */
	private String ufOrgaoExpedidorDocumento;

	/** O atributo data emissao documento. */
	private Date dataEmissaoDocumento;

	/** O atributo tipo sexo. */
	private Character tipoSexo;
	
	/** O atributo quantidade dependentes. */
	private Short quantidadeDependentes;
	
	/** O atributo desc naturalidade. */
	private String descNaturalidade;	
	
	/** O atributo id naturalidade. */
	private Integer idNaturalidade;
	
	// Tipo Documento
	/** O atributo codigo tipo documento. */
	private Short codigoTipoDocumento;

	/** O atributo descricao tipo documento. */
	private String descricaoTipoDocumento;

	// Ocupacao Profissional
	/** O atributo id ocupacao profissional. */
	private Integer idOcupacaoProfissional;
	
	/** O atributo codigo ocupacao profissional. */
	private String codigoOcupacaoProfissional;
	
	/** O atributo descricao ocupacao profissional. */
	private String descricaoOcupacaoProfissional;
	
	// Estado Civil 
	/** O atributo codigo estado civil. */
	private Short codigoEstadoCivil;

	/** O atributo descricao estado civil. */
	private String descricaoEstadoCivil;
	
	// Regime Casamento
	/** O atributo codigo regime casamento. */
	private Short codigoRegimeCasamento;

	/** O atributo descricao regime casamento. */
	private String descricaoRegimeCasamento;
	
	// Grau Instrucao
	/** O atributo codigo grau instrucao. */
	private Short codigoGrauInstrucao;

	/** O atributo descricao grau instrucao. */
	private String descricaoGrauInstrucao;

	/** O atributo codigo grau instrucao bndes. */
	private Integer codigoGrauInstrucaoBNDES;
	
	// Vinculo Empregaticio
	/** O atributo codigo vinculo empregaticio. */
	private Short codigoVinculoEmpregaticio;

	/** O atributo descricao vinculo empregaticio. */
	private String descricaoVinculoEmpregaticio;
	
	// Nacionalidade
	/** O atributo codigo nacionalidade. */
	private Short codigoNacionalidade;

	/** O atributo descricao nacionalidade. */
	private String descricaoNacionalidade;
	
	/** O atributo uniao estavel. */
	private Boolean uniaoEstavel;
	
	/** O atributo id PessoaConjuge. */
	private Integer idPessoaConjuge;
	
	/** O atributo importador. */
	private Boolean importador;
	
	/** O atributo exportador. */
	private Boolean exportador;
	
	/**
	 * Cria uma nova instância de pessoa fisica vo.
	 */
	public PessoaFisicaVO() {

	}

	/**
	 * Cria uma nova instância de pessoa fisica vo.
	 * 
	 * @param dataNascimento
	 *            the data nascimento
	 * @param menorEmancipado
	 *            the menor emancipado
	 * @param nomePai
	 *            the nome pai
	 * @param nomeMae
	 *            the nome mae
	 * @param numeroDocumento
	 *            the numero documento
	 * @param orgaoExpedidorDocumento
	 *            the orgao expedidor documento
	 * @param ufOrgaoExpedidorDocumento
	 *            the uf orgao expedidor documento
	 * @param dataEmissaoDocumento
	 *            the data emissao documento
	 * @param tipoSexo
	 *            the tipo sexo
	 * @param quantidadeDependentes
	 *            the quantidade dependentes
	 * @param descNaturalidade
	 *            the desc naturalidade
	 * @param idNaturalidade
	 *            the id naturalidade
	 * @param codigoTipoDocumento
	 *            the codigo tipo documento
	 * @param descricaoTipoDocumento
	 *            the descricao tipo documento
	 * @param idOcupacaoProfissional
	 *            the id ocupacao profissional
	 * @param descricaoOcupacaoProfissional
	 *            the descricao ocupacao profissional
	 * @param codigoEstadoCivil
	 *            the codigo estado civil
	 * @param descricaoEstadoCivil
	 *            the descricao estado civil
	 * @param codigoRegimeCasamento
	 *            the codigo regime casamento
	 * @param descricaoRegimeCasamento
	 *            the descricao regime casamento
	 * @param codigoGrauInstrucao
	 *            the codigo grau instrucao
	 * @param descricaoGrauInstrucao
	 *            the descricao grau instrucao
	 * @param codigoGrauInstrucaoBNDES
	 *            the codigo grau instrucao bndes
	 * @param codigoVinculoEmpregaticio
	 *            the codigo vinculo empregaticio
	 * @param descricaoVinculoEmpregaticio
	 *            the descricao vinculo empregaticio
	 * @param codigoNacionalidade
	 *            the codigo nacionalidade
	 * @param descricaoNacionalidade
	 *            the descricao nacionalidade
	 * @param uniaoEstavel
	 *            the uniao estavel
	 * @param codigoOcupacaoProfissional
	 *            the codigo ocupacao profissional
	 */
	public PessoaFisicaVO(Date dataNascimento, Boolean menorEmancipado,
			String nomePai, String nomeMae, String numeroDocumento,
			String orgaoExpedidorDocumento, String ufOrgaoExpedidorDocumento,
			Date dataEmissaoDocumento, Character tipoSexo,
			Short quantidadeDependentes, String descNaturalidade,
			Integer idNaturalidade, Short codigoTipoDocumento,
			String descricaoTipoDocumento, Integer idOcupacaoProfissional,
			String descricaoOcupacaoProfissional, Short codigoEstadoCivil,
			String descricaoEstadoCivil, Short codigoRegimeCasamento,
			String descricaoRegimeCasamento, Short codigoGrauInstrucao,
			String descricaoGrauInstrucao, Integer codigoGrauInstrucaoBNDES,
			Short codigoVinculoEmpregaticio,
			String descricaoVinculoEmpregaticio, Short codigoNacionalidade,
			String descricaoNacionalidade, Boolean uniaoEstavel,
			String codigoOcupacaoProfissional, Integer idPessoaConjuge, 
			Boolean importador, Boolean exportador) {
		this.dataNascimento = dataNascimento;
		this.menorEmancipado = menorEmancipado;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.numeroDocumento = numeroDocumento;
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
		this.dataEmissaoDocumento = dataEmissaoDocumento;
		this.tipoSexo = tipoSexo;
		this.quantidadeDependentes = quantidadeDependentes;
		this.descNaturalidade = descNaturalidade;
		this.idNaturalidade = idNaturalidade;
		this.codigoTipoDocumento = codigoTipoDocumento;
		this.descricaoTipoDocumento = descricaoTipoDocumento;
		this.idOcupacaoProfissional = idOcupacaoProfissional;
		this.descricaoOcupacaoProfissional = descricaoOcupacaoProfissional;
		this.codigoEstadoCivil = codigoEstadoCivil;
		this.descricaoEstadoCivil = descricaoEstadoCivil;
		this.codigoRegimeCasamento = codigoRegimeCasamento;
		this.descricaoRegimeCasamento = descricaoRegimeCasamento;
		this.codigoGrauInstrucao = codigoGrauInstrucao;
		this.descricaoGrauInstrucao = descricaoGrauInstrucao;
		this.codigoGrauInstrucaoBNDES = codigoGrauInstrucaoBNDES;
		this.codigoVinculoEmpregaticio = codigoVinculoEmpregaticio;
		this.descricaoVinculoEmpregaticio = descricaoVinculoEmpregaticio;
		this.codigoNacionalidade = codigoNacionalidade;
		this.descricaoNacionalidade = descricaoNacionalidade;
		this.uniaoEstavel = uniaoEstavel;
		this.codigoOcupacaoProfissional = codigoOcupacaoProfissional;
		this.idPessoaConjuge = idPessoaConjuge;
		this.importador = importador;
		this.exportador = exportador;
	}

	/**
	 * Cria uma nova instância de pessoa fisica vo.
	 * 
	 * @param dataNascimento
	 *            the data nascimento
	 * @param menorEmancipado
	 *            the menor emancipado
	 * @param nomePai
	 *            the nome pai
	 * @param nomeMae
	 *            the nome mae
	 * @param numeroDocumento
	 *            the numero documento
	 * @param orgaoExpedidorDocumento
	 *            the orgao expedidor documento
	 * @param ufOrgaoExpedidorDocumento
	 *            the uf orgao expedidor documento
	 * @param dataEmissaoDocumento
	 *            the data emissao documento
	 * @param tipoSexo
	 *            the tipo sexo
	 * @param quantidadeDependentes
	 *            the quantidade dependentes
	 * @param descNaturalidade
	 *            the desc naturalidade
	 * @param idNaturalidade
	 *            the id naturalidade
	 * @param codigoTipoDocumento
	 *            the codigo tipo documento
	 * @param descricaoTipoDocumento
	 *            the descricao tipo documento
	 * @param idOcupacaoProfissional
	 *            the id ocupacao profissional
	 * @param descricaoOcupacaoProfissional
	 *            the descricao ocupacao profissional
	 * @param codigoEstadoCivil
	 *            the codigo estado civil
	 * @param descricaoEstadoCivil
	 *            the descricao estado civil
	 * @param codigoRegimeCasamento
	 *            the codigo regime casamento
	 * @param descricaoRegimeCasamento
	 *            the descricao regime casamento
	 * @param codigoGrauInstrucao
	 *            the codigo grau instrucao
	 * @param descricaoGrauInstrucao
	 *            the descricao grau instrucao
	 * @param codigoGrauInstrucaoBNDES
	 *            the codigo grau instrucao bndes
	 * @param codigoVinculoEmpregaticio
	 *            the codigo vinculo empregaticio
	 * @param descricaoVinculoEmpregaticio
	 *            the descricao vinculo empregaticio
	 * @param codigoNacionalidade
	 *            the codigo nacionalidade
	 * @param descricaoNacionalidade
	 *            the descricao nacionalidade
	 * @param uniaoEstavel
	 *            the uniao estavel
	 * @param codigoOcupacaoProfissional
	 *            the codigo ocupacao profissional
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param nomeCompleto
	 *            the nome completo
	 */
	public PessoaFisicaVO(Date dataNascimento, Boolean menorEmancipado,
			String nomePai, String nomeMae, String numeroDocumento,
			String orgaoExpedidorDocumento, String ufOrgaoExpedidorDocumento,
			Date dataEmissaoDocumento, Character tipoSexo,
			Short quantidadeDependentes, String descNaturalidade,
			Integer idNaturalidade, Short codigoTipoDocumento,
			String descricaoTipoDocumento, Integer idOcupacaoProfissional,
			String descricaoOcupacaoProfissional, Short codigoEstadoCivil,
			String descricaoEstadoCivil, Short codigoRegimeCasamento,
			String descricaoRegimeCasamento, Short codigoGrauInstrucao,
			String descricaoGrauInstrucao, Integer codigoGrauInstrucaoBNDES,
			Short codigoVinculoEmpregaticio,
			String descricaoVinculoEmpregaticio, Short codigoNacionalidade,
			String descricaoNacionalidade, Boolean uniaoEstavel, String codigoOcupacaoProfissional,
			Integer idPessoa, String cpfCnpj,String nomePessoa,String nomeCompleto, Integer idPessoaConjuge, Boolean importador, Boolean exportador) {
		this(dataNascimento, menorEmancipado, nomePai, nomeMae, numeroDocumento, 
				orgaoExpedidorDocumento, ufOrgaoExpedidorDocumento, dataEmissaoDocumento, 
				tipoSexo, quantidadeDependentes, descNaturalidade, idNaturalidade, 
				codigoTipoDocumento, descricaoTipoDocumento, idOcupacaoProfissional, 
				descricaoOcupacaoProfissional, codigoEstadoCivil, descricaoEstadoCivil, 
				codigoRegimeCasamento, descricaoRegimeCasamento, codigoGrauInstrucao, 
				descricaoGrauInstrucao, codigoGrauInstrucaoBNDES, codigoVinculoEmpregaticio, 
				descricaoVinculoEmpregaticio, codigoNacionalidade, descricaoNacionalidade, 
				uniaoEstavel, codigoOcupacaoProfissional, idPessoaConjuge, importador, exportador);
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nomePessoa = nomePessoa;
		this.nomeCompleto = nomeCompleto;
	}
	
	
	/**
	 * Recupera data nascimento.
	 * 
	 * @return data nascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Preenche data nascimento.
	 * 
	 * @param dataNascimento
	 *            o novo data nascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Recupera menor emancipado.
	 * 
	 * @return menor emancipado
	 */
	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	/**
	 * Preenche menor emancipado.
	 * 
	 * @param menorEmancipado
	 *            o novo menor emancipado
	 */
	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	/**
	 * Recupera nome pai.
	 * 
	 * @return nome pai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * Preenche nome pai.
	 * 
	 * @param nomePai
	 *            o novo nome pai
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * Recupera nome mae.
	 * 
	 * @return nome mae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * Preenche nome mae.
	 * 
	 * @param nomeMae
	 *            o novo nome mae
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * Recupera numero documento.
	 * 
	 * @return numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Preenche numero documento.
	 * 
	 * @param numeroDocumento
	 *            o novo numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Recupera orgao expedidor documento.
	 * 
	 * @return orgao expedidor documento
	 */
	public String getOrgaoExpedidorDocumento() {
		return orgaoExpedidorDocumento;
	}

	/**
	 * Preenche orgao expedidor documento.
	 * 
	 * @param orgaoExpedidorDocumento
	 *            o novo orgao expedidor documento
	 */
	public void setOrgaoExpedidorDocumento(String orgaoExpedidorDocumento) {
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
	}

	/**
	 * Recupera uf orgao expedidor documento.
	 * 
	 * @return uf orgao expedidor documento
	 */
	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}

	/**
	 * Preenche uf orgao expedidor documento.
	 * 
	 * @param ufOrgaoExpedidorDocumento
	 *            o novo uf orgao expedidor documento
	 */
	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}

	/**
	 * Recupera data emissao documento.
	 * 
	 * @return data emissao documento
	 */
	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	/**
	 * Preenche data emissao documento.
	 * 
	 * @param dataEmissaoDocumento
	 *            o novo data emissao documento
	 */
	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	/**
	 * Recupera tipo sexo.
	 * 
	 * @return tipo sexo
	 */
	public Character getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * Preenche tipo sexo.
	 * 
	 * @param tipoSexo
	 *            o novo tipo sexo
	 */
	public void setTipoSexo(Character tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * Recupera quantidade dependentes.
	 * 
	 * @return quantidade dependentes
	 */
	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	/**
	 * Preenche quantidade dependentes.
	 * 
	 * @param quantidadeDependentes
	 *            o novo quantidade dependentes
	 */
	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	/**
	 * Recupera desc naturalidade.
	 * 
	 * @return desc naturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	/**
	 * Preenche desc naturalidade.
	 * 
	 * @param descNaturalidade
	 *            o novo desc naturalidade
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	/**
	 * Recupera id naturalidade.
	 * 
	 * @return id naturalidade
	 */
	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}

	/**
	 * Preenche id naturalidade.
	 * 
	 * @param idNaturalidade
	 *            o novo id naturalidade
	 */
	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	/**
	 * Recupera codigo tipo documento.
	 * 
	 * @return codigo tipo documento
	 */
	public Short getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * Preenche codigo tipo documento.
	 * 
	 * @param codigoTipoDocumento
	 *            o novo codigo tipo documento
	 */
	public void setCodigoTipoDocumento(Short codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * Recupera descricao tipo documento.
	 * 
	 * @return descricao tipo documento
	 */
	public String getDescricaoTipoDocumento() {
		return descricaoTipoDocumento;
	}

	/**
	 * Preenche descricao tipo documento.
	 * 
	 * @param descricaoTipoDocumento
	 *            o novo descricao tipo documento
	 */
	public void setDescricaoTipoDocumento(String descricaoTipoDocumento) {
		this.descricaoTipoDocumento = descricaoTipoDocumento;
	}

	/**
	 * Recupera id ocupacao profissional.
	 * 
	 * @return id ocupacao profissional
	 */
	public Integer getIdOcupacaoProfissional() {
		return idOcupacaoProfissional;
	}

	/**
	 * Preenche id ocupacao profissional.
	 * 
	 * @param idOcupacaoProfissional
	 *            o novo id ocupacao profissional
	 */
	public void setIdOcupacaoProfissional(Integer idOcupacaoProfissional) {
		this.idOcupacaoProfissional = idOcupacaoProfissional;
	}

	/**
	 * Recupera descricao ocupacao profissional.
	 * 
	 * @return descricao ocupacao profissional
	 */
	public String getDescricaoOcupacaoProfissional() {
		return descricaoOcupacaoProfissional;
	}

	/**
	 * Preenche descricao ocupacao profissional.
	 * 
	 * @param descricaoOcupacaoProfissional
	 *            o novo descricao ocupacao profissional
	 */
	public void setDescricaoOcupacaoProfissional(
			String descricaoOcupacaoProfissional) {
		this.descricaoOcupacaoProfissional = descricaoOcupacaoProfissional;
	}

	/**
	 * Recupera codigo estado civil.
	 * 
	 * @return codigo estado civil
	 */
	public Short getCodigoEstadoCivil() {
		return codigoEstadoCivil;
	}

	/**
	 * Preenche codigo estado civil.
	 * 
	 * @param codigoEstadoCivil
	 *            o novo codigo estado civil
	 */
	public void setCodigoEstadoCivil(Short codigoEstadoCivil) {
		this.codigoEstadoCivil = codigoEstadoCivil;
	}

	/**
	 * Recupera descricao estado civil.
	 * 
	 * @return descricao estado civil
	 */
	public String getDescricaoEstadoCivil() {
		return descricaoEstadoCivil;
	}

	/**
	 * Preenche descricao estado civil.
	 * 
	 * @param descricaoEstadoCivil
	 *            o novo descricao estado civil
	 */
	public void setDescricaoEstadoCivil(String descricaoEstadoCivil) {
		this.descricaoEstadoCivil = descricaoEstadoCivil;
	}

	/**
	 * Recupera codigo regime casamento.
	 * 
	 * @return codigo regime casamento
	 */
	public Short getCodigoRegimeCasamento() {
		return codigoRegimeCasamento;
	}

	/**
	 * Preenche codigo regime casamento.
	 * 
	 * @param codigoRegimeCasamento
	 *            o novo codigo regime casamento
	 */
	public void setCodigoRegimeCasamento(Short codigoRegimeCasamento) {
		this.codigoRegimeCasamento = codigoRegimeCasamento;
	}

	/**
	 * Recupera descricao regime casamento.
	 * 
	 * @return descricao regime casamento
	 */
	public String getDescricaoRegimeCasamento() {
		return descricaoRegimeCasamento;
	}

	/**
	 * Preenche descricao regime casamento.
	 * 
	 * @param descricaoRegimeCasamento
	 *            o novo descricao regime casamento
	 */
	public void setDescricaoRegimeCasamento(String descricaoRegimeCasamento) {
		this.descricaoRegimeCasamento = descricaoRegimeCasamento;
	}

	/**
	 * Recupera codigo grau instrucao.
	 * 
	 * @return codigo grau instrucao
	 */
	public Short getCodigoGrauInstrucao() {
		return codigoGrauInstrucao;
	}

	/**
	 * Preenche codigo grau instrucao.
	 * 
	 * @param codigoGrauInstrucao
	 *            o novo codigo grau instrucao
	 */
	public void setCodigoGrauInstrucao(Short codigoGrauInstrucao) {
		this.codigoGrauInstrucao = codigoGrauInstrucao;
	}

	/**
	 * Recupera descricao grau instrucao.
	 * 
	 * @return descricao grau instrucao
	 */
	public String getDescricaoGrauInstrucao() {
		return descricaoGrauInstrucao;
	}

	/**
	 * Preenche descricao grau instrucao.
	 * 
	 * @param descricaoGrauInstrucao
	 *            o novo descricao grau instrucao
	 */
	public void setDescricaoGrauInstrucao(String descricaoGrauInstrucao) {
		this.descricaoGrauInstrucao = descricaoGrauInstrucao;
	}

	/**
	 * Recupera codigo grau instrucao bndes.
	 * 
	 * @return codigo grau instrucao bndes
	 */
	public Integer getCodigoGrauInstrucaoBNDES() {
		return codigoGrauInstrucaoBNDES;
	}

	/**
	 * Preenche codigo grau instrucao bndes.
	 * 
	 * @param codigoGrauInstrucaoBNDES
	 *            o novo codigo grau instrucao bndes
	 */
	public void setCodigoGrauInstrucaoBNDES(Integer codigoGrauInstrucaoBNDES) {
		this.codigoGrauInstrucaoBNDES = codigoGrauInstrucaoBNDES;
	}

	/**
	 * Recupera codigo vinculo empregaticio.
	 * 
	 * @return codigo vinculo empregaticio
	 */
	public Short getCodigoVinculoEmpregaticio() {
		return codigoVinculoEmpregaticio;
	}

	/**
	 * Preenche codigo vinculo empregaticio.
	 * 
	 * @param codigoVinculoEmpregaticio
	 *            o novo codigo vinculo empregaticio
	 */
	public void setCodigoVinculoEmpregaticio(Short codigoVinculoEmpregaticio) {
		this.codigoVinculoEmpregaticio = codigoVinculoEmpregaticio;
	}

	/**
	 * Recupera descricao vinculo empregaticio.
	 * 
	 * @return descricao vinculo empregaticio
	 */
	public String getDescricaoVinculoEmpregaticio() {
		return descricaoVinculoEmpregaticio;
	}

	/**
	 * Preenche descricao vinculo empregaticio.
	 * 
	 * @param descricaoVinculoEmpregaticio
	 *            o novo descricao vinculo empregaticio
	 */
	public void setDescricaoVinculoEmpregaticio(String descricaoVinculoEmpregaticio) {
		this.descricaoVinculoEmpregaticio = descricaoVinculoEmpregaticio;
	}

	/**
	 * Recupera codigo nacionalidade.
	 * 
	 * @return codigo nacionalidade
	 */
	public Short getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	/**
	 * Preenche codigo nacionalidade.
	 * 
	 * @param codigoNacionalidade
	 *            o novo codigo nacionalidade
	 */
	public void setCodigoNacionalidade(Short codigoNacionalidade) {
		this.codigoNacionalidade = codigoNacionalidade;
	}

	/**
	 * Recupera descricao nacionalidade.
	 * 
	 * @return descricao nacionalidade
	 */
	public String getDescricaoNacionalidade() {
		return descricaoNacionalidade;
	}

	/**
	 * Preenche descricao nacionalidade.
	 * 
	 * @param descricaoNacionalidade
	 *            o novo descricao nacionalidade
	 */
	public void setDescricaoNacionalidade(String descricaoNacionalidade) {
		this.descricaoNacionalidade = descricaoNacionalidade;
	}

	/**
	 * Recupera uniao estavel.
	 * 
	 * @return uniao estavel
	 */
	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	/**
	 * Preenche uniao estavel.
	 * 
	 * @param uniaoEstavel
	 *            o novo uniao estavel
	 */
	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
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
	 * Recupera codigo ocupacao profissional.
	 * 
	 * @return codigo ocupacao profissional
	 */
	public String getCodigoOcupacaoProfissional() {
		return codigoOcupacaoProfissional;
	}

	/**
	 * Preenche codigo ocupacao profissional.
	 * 
	 * @param codigoOcupacaoProfissional
	 *            o novo codigo ocupacao profissional
	 */
	public void setCodigoOcupacaoProfissional(String codigoOcupacaoProfissional) {
		this.codigoOcupacaoProfissional = codigoOcupacaoProfissional;
	}

	public Integer getIdPessoaConjuge() {
		return idPessoaConjuge;
	}

	public void setIdPessoaConjuge(Integer idPessoaConjuge) {
		this.idPessoaConjuge = idPessoaConjuge;
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