package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.util.Date;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;


/**
 * A Classe PessoaFisicaDTO.
 * 
 * @author bruno.carneiro
 */
public class PessoaFisicaDTO extends PessoaDTO {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4976136127530722025L;

	/** O atributo dataNascimento. */
	private Date dataNascimento;

	/** O atributo menorEmancipado. */
	private Boolean menorEmancipado = Boolean.FALSE;

	/** O atributo nomePai. */
	private String nomePai;

	/** O atributo nomeMae. */
	private String nomeMae;

	/** O atributo codigoTipoDocumento. */
	private Short codigoTipoDocumento;

	/** O atributo numeroDocumento. */
	private String numeroDocumento;

	/** O atributo orgaoExpedidorDocumento. */
	private String orgaoExpedidorDocumento;

	/** O atributo ufOrgaoExpedidorDocumento. */
	private String ufOrgaoExpedidorDocumento;

	/** O atributo dataEmissaoDocumento. */
	private Date dataEmissaoDocumento;

	/** O atributo tipoSexo. */
	private Character tipoSexo;

	/** O atributo codigoOcupacaoProfissional. */
	private Integer codigoOcupacaoProfissional;

	/** O atributo codigoEstadoCivil. */
	private Short codigoEstadoCivil;

	/** O atributo codigoRegimeCasamento. */
	private Short codigoRegimeCasamento;

	/** O atributo quantidadeDependentes. */
	private Short quantidadeDependentes;

	/** O atributo uniaoEstavel. */
	private Boolean uniaoEstavel = Boolean.FALSE;

	/** O atributo codigoGrauInstrucao. */
	private Short codigoGrauInstrucao;

	/** O atributo descNaturalidade. */
	private String descNaturalidade;

	/** O atributo idNaturalidade. */
	private Integer idNaturalidade;

	/** O atributo codigoVinculoEmpregaticio. */
	private Short codigoVinculoEmpregaticio;

	/** O atributo codigoNacionalidade. */
	private Short codigoNacionalidade;
	
	/** O atributo idPessoaConjuge. */
	private Integer idPessoaConjuge;
	
	/** O atributo idInstituicaoConjuge. */
	private Integer idInstituicaoConjuge;

	/**
	 * Recupera o valor de dataNascimento.
	 * 
	 * @return o valor de dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Define o valor de dataNascimento.
	 * 
	 * @param dataNascimento
	 *            o novo valor de dataNascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Recupera o valor de menorEmancipado.
	 * 
	 * @return o valor de menorEmancipado
	 */
	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	/**
	 * Define o valor de menorEmancipado.
	 * 
	 * @param menorEmancipado
	 *            o novo valor de menorEmancipado
	 */
	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	/**
	 * Recupera o valor de nomePai.
	 * 
	 * @return o valor de nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * Define o valor de nomePai.
	 * 
	 * @param nomePai
	 *            o novo valor de nomePai
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * Recupera o valor de nomeMae.
	 * 
	 * @return o valor de nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * Define o valor de nomeMae.
	 * 
	 * @param nomeMae
	 *            o novo valor de nomeMae
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * Recupera o valor de codigoTipoDocumento.
	 * 
	 * @return o valor de codigoTipoDocumento
	 */
	public Short getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * Define o valor de codigoTipoDocumento.
	 * 
	 * @param codigoTipoDocumento
	 *            o novo valor de codigoTipoDocumento
	 */
	public void setCodigoTipoDocumento(Short codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * Recupera o valor de numeroDocumento.
	 * 
	 * @return o valor de numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Define o valor de numeroDocumento.
	 * 
	 * @param numeroDocumento
	 *            o novo valor de numeroDocumento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Recupera o valor de orgaoExpedidorDocumento.
	 * 
	 * @return o valor de orgaoExpedidorDocumento
	 */
	public String getOrgaoExpedidorDocumento() {
		return orgaoExpedidorDocumento;
	}

	/**
	 * Define o valor de orgaoExpedidorDocumento.
	 * 
	 * @param orgaoExpedidorDocumento
	 *            o novo valor de orgaoExpedidorDocumento
	 */
	public void setOrgaoExpedidorDocumento(String orgaoExpedidorDocumento) {
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
	}

	/**
	 * Recupera o valor de ufOrgaoExpedidorDocumento.
	 * 
	 * @return o valor de ufOrgaoExpedidorDocumento
	 */
	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}

	/**
	 * Define o valor de ufOrgaoExpedidorDocumento.
	 * 
	 * @param ufOrgaoExpedidorDocumento
	 *            o novo valor de ufOrgaoExpedidorDocumento
	 */
	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}

	/**
	 * Recupera o valor de dataEmissaoDocumento.
	 * 
	 * @return o valor de dataEmissaoDocumento
	 */
	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	/**
	 * Define o valor de dataEmissaoDocumento.
	 * 
	 * @param dataEmissaoDocumento
	 *            o novo valor de dataEmissaoDocumento
	 */
	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	/**
	 * Recupera o valor de tipoSexo.
	 * 
	 * @return o valor de tipoSexo
	 */
	public Character getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * Define o valor de tipoSexo.
	 * 
	 * @param tipoSexo
	 *            o novo valor de tipoSexo
	 */
	public void setTipoSexo(Character tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * Recupera o valor de codigoOcupacaoProfissional.
	 * 
	 * @return o valor de codigoOcupacaoProfissional
	 */
	public Integer getCodigoOcupacaoProfissional() {
		return codigoOcupacaoProfissional;
	}

	/**
	 * Define o valor de codigoOcupacaoProfissional.
	 * 
	 * @param codigoOcupacaoProfissional
	 *            o novo valor de codigoOcupacaoProfissional
	 */
	public void setCodigoOcupacaoProfissional(Integer codigoOcupacaoProfissional) {
		this.codigoOcupacaoProfissional = codigoOcupacaoProfissional;
	}

	/**
	 * Recupera o valor de codigoEstadoCivil.
	 * 
	 * @return o valor de codigoEstadoCivil
	 */
	public Short getCodigoEstadoCivil() {
		return codigoEstadoCivil;
	}

	/**
	 * Define o valor de codigoEstadoCivil.
	 * 
	 * @param codigoEstadoCivil
	 *            o novo valor de codigoEstadoCivil
	 */
	public void setCodigoEstadoCivil(Short codigoEstadoCivil) {
		this.codigoEstadoCivil = codigoEstadoCivil;
	}

	/**
	 * Recupera o valor de codigoRegimeCasamento.
	 * 
	 * @return o valor de codigoRegimeCasamento
	 */
	public Short getCodigoRegimeCasamento() {
		return codigoRegimeCasamento;
	}

	/**
	 * Define o valor de codigoRegimeCasamento.
	 * 
	 * @param codigoRegimeCasamento
	 *            o novo valor de codigoRegimeCasamento
	 */
	public void setCodigoRegimeCasamento(Short codigoRegimeCasamento) {
		this.codigoRegimeCasamento = codigoRegimeCasamento;
	}

	/**
	 * Recupera o valor de quantidadeDependentes.
	 * 
	 * @return o valor de quantidadeDependentes
	 */
	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	/**
	 * Define o valor de quantidadeDependentes.
	 * 
	 * @param quantidadeDependentes
	 *            o novo valor de quantidadeDependentes
	 */
	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	/**
	 * Recupera o valor de uniaoEstavel.
	 * 
	 * @return o valor de uniaoEstavel
	 */
	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	/**
	 * Define o valor de uniaoEstavel.
	 * 
	 * @param uniaoEstavel
	 *            o novo valor de uniaoEstavel
	 */
	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
	}

	/**
	 * Recupera o valor de codigoGrauInstrucao.
	 * 
	 * @return o valor de codigoGrauInstrucao
	 */
	public Short getCodigoGrauInstrucao() {
		return codigoGrauInstrucao;
	}

	/**
	 * Define o valor de codigoGrauInstrucao.
	 * 
	 * @param codigoGrauInstrucao
	 *            o novo valor de codigoGrauInstrucao
	 */
	public void setCodigoGrauInstrucao(Short codigoGrauInstrucao) {
		this.codigoGrauInstrucao = codigoGrauInstrucao;
	}

	/**
	 * Recupera o valor de descNaturalidade.
	 * 
	 * @return o valor de descNaturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	/**
	 * Define o valor de descNaturalidade.
	 * 
	 * @param descNaturalidade
	 *            o novo valor de descNaturalidade
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	/**
	 * Recupera o valor de idNaturalidade.
	 * 
	 * @return o valor de idNaturalidade
	 */
	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}

	/**
	 * Define o valor de idNaturalidade.
	 * 
	 * @param idNaturalidade
	 *            o novo valor de idNaturalidade
	 */
	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	/**
	 * Recupera o valor de codigoVinculoEmpregaticio.
	 * 
	 * @return o valor de codigoVinculoEmpregaticio
	 */
	public Short getCodigoVinculoEmpregaticio() {
		return codigoVinculoEmpregaticio;
	}

	/**
	 * Define o valor de codigoVinculoEmpregaticio.
	 * 
	 * @param codigoVinculoEmpregaticio
	 *            o novo valor de codigoVinculoEmpregaticio
	 */
	public void setCodigoVinculoEmpregaticio(Short codigoVinculoEmpregaticio) {
		this.codigoVinculoEmpregaticio = codigoVinculoEmpregaticio;
	}

	/**
	 * Recupera o valor de codigoNacionalidade.
	 * 
	 * @return o valor de codigoNacionalidade
	 */
	public Short getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	/**
	 * Define o valor de codigoNacionalidade.
	 * 
	 * @param codigoNacionalidade
	 *            o novo valor de codigoNacionalidade
	 */
	public void setCodigoNacionalidade(Short codigoNacionalidade) {
		this.codigoNacionalidade = codigoNacionalidade;
	}

	public Integer getIdPessoaConjuge() {
		return idPessoaConjuge;
	}

	public void setIdPessoaConjuge(Integer idPessoaConjuge) {
		this.idPessoaConjuge = idPessoaConjuge;
	}

	public Integer getIdInstituicaoConjuge() {
		return idInstituicaoConjuge;
	}

	public void setIdInstituicaoConjuge(Integer idInstituicaoConjuge) {
		this.idInstituicaoConjuge = idInstituicaoConjuge;
	}
	
	@Override
	public Short getCodigoTipoPessoa() {
		return TipoPessoaEnum.PESSOA_FISICA.getCodigo();
	}

}