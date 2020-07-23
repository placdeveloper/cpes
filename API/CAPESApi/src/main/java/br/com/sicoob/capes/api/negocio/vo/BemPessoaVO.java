/*
 * SICOOB
 * 
 * BemPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.BemPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Lucas.Borges
 */
public class BemPessoaVO extends AbstractPessoaVO{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -351786049589956531L;

	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	// BEM PESSOA
	/** O atributo id bem. */
	private Long idBem;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo descricao bem. */
	private String descricaoBem;
	
	/** O atributo valor atual mercado. */
	private BigDecimal valorAtualMercado;
	
	/** O atributo percentual. */
	private BigDecimal percentual;

	/** O atributo valor propriedade. */
	private BigDecimal valorPropriedade;
	
	/** O atributo nome seguradora. */
	private String nomeSeguradora;
	
	/** O atributo data vencimento seguro. */
	private Date dataVencimentoSeguro;
	
	/** O atributo valor seguro. */
	private BigDecimal valorSeguro;
	
	// BEM IMOVEL
	/** O atributo cod localizacao imovel. */
	private String codLocalizacaoImovel;
	
	/** O atributo benfeitoria imovel. */
	private String benfeitoriaImovel;
	
	/** O atributo valor benfeitoria imovel. */
	private BigDecimal valorBenfeitoriaImovel;
	
	/** O atributo denominacao imovel. */
	private String denominacaoImovel;
	
	/** O atributo comarca imovel. */
	private String comarcaImovel;
	
	/** O atributo sigla uf comarca imovel. */
	private String siglaUFComarcaImovel;
	
	/** O atributo id localidade comarca imovel. */
	private Integer idLocalidadeComarcaImovel;
	
	/** O atributo id localidade imovel. */
	private Integer idLocalidadeImovel;
	
	/** O atributo municipio imovel. */
	private String municipioImovel;

	/** O atributo sigla uf municipio imovel. */
	private String siglaUFMunicipioImovel;

	/** O atributo area imovel. */
	private BigDecimal areaImovel;
	
	// UNIDADE MEDIDA BEM IMOVEL
	/** O atributo codigo unidade medida imovel. */
	private Short codigoUnidadeMedidaImovel;
	
	/** O atributo descricao unidade medida imovel. */
	private String descricaoUnidadeMedidaImovel;
	
	/** O atributo sigla unidade medida imovel. */
	private String siglaUnidadeMedidaImovel;
	
	// SITUACAO BEM
	/** O atributo codigo situacao bem. */
	private Short codigoSituacaoBem;

	/** O atributo descricao situacao bem. */
	private String descricaoSituacaoBem;
	
	// TIPO BEM
	/** O atributo codigo tipo bem. */
	private Short codigoTipoBem;
	
	/** O atributo descricao tipo bem. */
	private String descricaoTipoBem;
	
	// SUBTIPO BEM
	/** O atributo codigo sub tipo bem. */
	private Short codigoSubTipoBem;
	
	/** O atributo descricao sub tipo bem. */
	private String descricaoSubTipoBem;
	
	/** O atributo posses. */
	private List<BemPosseVO> posses;

	/**
	 * Cria uma nova instância de bem pessoa vo.
	 */
	public BemPessoaVO() {

	}
	
	/**
	 * Cria uma nova instância de bem pessoa vo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idBem
	 *            the id bem
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param descricaoBem
	 *            the descricao bem
	 * @param valorAtualMercado
	 *            the valor atual mercado
	 * @param percentual
	 *            the percentual
	 * @param valorPropriedade
	 *            the valor propriedade
	 * @param nomeSeguradora
	 *            the nome seguradora
	 * @param dataVencimentoSeguro
	 *            the data vencimento seguro
	 * @param valorSeguro
	 *            the valor seguro
	 * @param codLocalizacaoImovel
	 *            the cod localizacao imovel
	 * @param benfeitoriaImovel
	 *            the benfeitoria imovel
	 * @param valorBenfeitoriaImovel
	 *            the valor benfeitoria imovel
	 * @param denominacaoImovel
	 *            the denominacao imovel
	 * @param comarcaImovel
	 *            the comarca imovel
	 * @param siglaUFComarcaImovel
	 *            the sigla uf comarca imovel
	 * @param idLocalidadeComarcaImovel
	 *            the id localidade comarca imovel
	 * @param idLocalidadeImovel
	 *            the id localidade imovel
	 * @param municipioImovel
	 *            the municipio imovel
	 * @param siglaUFMunicipioImovel
	 *            the sigla uf municipio imovel
	 * @param areaImovel
	 *            the area imovel
	 * @param codigoUnidadeMedidaImovel
	 *            the codigo unidade medida imovel
	 * @param descricaoUnidadeMedidaImovel
	 *            the descricao unidade medida imovel
	 * @param siglaUnidadeMedidaImovel
	 *            the sigla unidade medida imovel
	 * @param codigoSituacaoBem
	 *            the codigo situacao bem
	 * @param descricaoSituacaoBem
	 *            the descricao situacao bem
	 * @param codigoTipoBem
	 *            the codigo tipo bem
	 * @param descricaoTipoBem
	 *            the descricao tipo bem
	 * @param codigoSubTipoBem
	 *            the codigo sub tipo bem
	 * @param descricaoSubTipoBem
	 *            the descricao sub tipo bem
	 */
	public BemPessoaVO(Integer idPessoa, Long idBem,
			Date dataHoraInicio, String descricaoBem,
			BigDecimal valorAtualMercado, BigDecimal percentual,
			BigDecimal valorPropriedade, String nomeSeguradora,
			Date dataVencimentoSeguro, BigDecimal valorSeguro,
			String codLocalizacaoImovel, String benfeitoriaImovel,
			BigDecimal valorBenfeitoriaImovel, String denominacaoImovel,
			String comarcaImovel, String siglaUFComarcaImovel,
			Integer idLocalidadeComarcaImovel, Integer idLocalidadeImovel,
			String municipioImovel, String siglaUFMunicipioImovel,
			BigDecimal areaImovel, Short codigoUnidadeMedidaImovel,
			String descricaoUnidadeMedidaImovel, String siglaUnidadeMedidaImovel,
			Short codigoSituacaoBem, String descricaoSituacaoBem,
			Short codigoTipoBem, String descricaoTipoBem,
			Short codigoSubTipoBem, String descricaoSubTipoBem) {
		this.idPessoa = idPessoa;
		this.idBem = idBem;
		this.dataHoraInicio = clonar(dataHoraInicio);
		this.descricaoBem = descricaoBem;
		this.valorAtualMercado = valorAtualMercado;
		this.percentual = percentual;
		this.valorPropriedade = valorPropriedade;
		this.nomeSeguradora = nomeSeguradora;
		this.dataVencimentoSeguro = clonar(dataVencimentoSeguro);
		this.valorSeguro = valorSeguro;
		this.codLocalizacaoImovel = codLocalizacaoImovel;
		this.benfeitoriaImovel = benfeitoriaImovel;
		this.valorBenfeitoriaImovel = valorBenfeitoriaImovel;
		this.denominacaoImovel = denominacaoImovel;
		this.comarcaImovel = comarcaImovel;
		this.siglaUFComarcaImovel = siglaUFComarcaImovel;
		this.idLocalidadeComarcaImovel = idLocalidadeComarcaImovel;
		this.idLocalidadeImovel = idLocalidadeImovel;
		this.municipioImovel = municipioImovel;
		this.siglaUFMunicipioImovel = siglaUFMunicipioImovel;
		this.areaImovel = areaImovel;
		this.codigoUnidadeMedidaImovel = codigoUnidadeMedidaImovel;
		this.descricaoUnidadeMedidaImovel = descricaoUnidadeMedidaImovel;
		this.siglaUnidadeMedidaImovel = siglaUnidadeMedidaImovel;
		this.codigoSituacaoBem = codigoSituacaoBem;
		this.descricaoSituacaoBem = descricaoSituacaoBem;
		this.codigoTipoBem = codigoTipoBem;
		this.descricaoTipoBem = descricaoTipoBem;
		this.codigoSubTipoBem = codigoSubTipoBem;
		this.descricaoSubTipoBem = descricaoSubTipoBem;
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
	 * Recupera id bem.
	 * 
	 * @return id bem
	 */
	public Long getIdBem() {
		return idBem;
	}

	/**
	 * Preenche id bem.
	 * 
	 * @param idBem
	 *            o novo id bem
	 */
	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return clonar(dataHoraInicio);
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = clonar(dataHoraInicio);
	}

	/**
	 * Recupera descricao bem.
	 * 
	 * @return descricao bem
	 */
	public String getDescricaoBem() {
		return descricaoBem;
	}

	/**
	 * Preenche descricao bem.
	 * 
	 * @param descricaoBem
	 *            o novo descricao bem
	 */
	public void setDescricaoBem(String descricaoBem) {
		this.descricaoBem = descricaoBem;
	}

	/**
	 * Recupera valor atual mercado.
	 * 
	 * @return valor atual mercado
	 */
	public BigDecimal getValorAtualMercado() {
		return valorAtualMercado;
	}

	/**
	 * Preenche valor atual mercado.
	 * 
	 * @param valorAtualMercado
	 *            o novo valor atual mercado
	 */
	public void setValorAtualMercado(BigDecimal valorAtualMercado) {
		this.valorAtualMercado = valorAtualMercado;
	}

	/**
	 * Recupera percentual.
	 * 
	 * @return percentual
	 */
	public BigDecimal getPercentual() {
		return percentual;
	}

	/**
	 * Preenche percentual.
	 * 
	 * @param percentual
	 *            o novo percentual
	 */
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	/**
	 * Recupera valor propriedade.
	 * 
	 * @return valor propriedade
	 */
	public BigDecimal getValorPropriedade() {
		return valorPropriedade;
	}

	/**
	 * Preenche valor propriedade.
	 * 
	 * @param valorPropriedade
	 *            o novo valor propriedade
	 */
	public void setValorPropriedade(BigDecimal valorPropriedade) {
		this.valorPropriedade = valorPropriedade;
	}

	/**
	 * Recupera nome seguradora.
	 * 
	 * @return nome seguradora
	 */
	public String getNomeSeguradora() {
		return nomeSeguradora;
	}

	/**
	 * Preenche nome seguradora.
	 * 
	 * @param nomeSeguradora
	 *            o novo nome seguradora
	 */
	public void setNomeSeguradora(String nomeSeguradora) {
		this.nomeSeguradora = nomeSeguradora;
	}

	/**
	 * Recupera data vencimento seguro.
	 * 
	 * @return data vencimento seguro
	 */
	public Date getDataVencimentoSeguro() {
		return clonar(dataVencimentoSeguro);
	}

	/**
	 * Preenche data vencimento seguro.
	 * 
	 * @param dataVencimentoSeguro
	 *            o novo data vencimento seguro
	 */
	public void setDataVencimentoSeguro(Date dataVencimentoSeguro) {
		this.dataVencimentoSeguro = clonar(dataVencimentoSeguro);
	}

	/**
	 * Recupera valor seguro.
	 * 
	 * @return valor seguro
	 */
	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	/**
	 * Preenche valor seguro.
	 * 
	 * @param valorSeguro
	 *            o novo valor seguro
	 */
	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	/**
	 * Recupera codigo situacao bem.
	 * 
	 * @return codigo situacao bem
	 */
	public Short getCodigoSituacaoBem() {
		return codigoSituacaoBem;
	}

	/**
	 * Preenche codigo situacao bem.
	 * 
	 * @param codigoSituacaoBem
	 *            o novo codigo situacao bem
	 */
	public void setCodigoSituacaoBem(Short codigoSituacaoBem) {
		this.codigoSituacaoBem = codigoSituacaoBem;
	}

	/**
	 * Recupera descricao situacao bem.
	 * 
	 * @return descricao situacao bem
	 */
	public String getDescricaoSituacaoBem() {
		return descricaoSituacaoBem;
	}

	/**
	 * Preenche descricao situacao bem.
	 * 
	 * @param descricaoSituacaoBem
	 *            o novo descricao situacao bem
	 */
	public void setDescricaoSituacaoBem(String descricaoSituacaoBem) {
		this.descricaoSituacaoBem = descricaoSituacaoBem;
	}

	/**
	 * Recupera codigo tipo bem.
	 * 
	 * @return codigo tipo bem
	 */
	public Short getCodigoTipoBem() {
		return codigoTipoBem;
	}

	/**
	 * Preenche codigo tipo bem.
	 * 
	 * @param codigoTipoBem
	 *            o novo codigo tipo bem
	 */
	public void setCodigoTipoBem(Short codigoTipoBem) {
		this.codigoTipoBem = codigoTipoBem;
	}

	/**
	 * Recupera descricao tipo bem.
	 * 
	 * @return descricao tipo bem
	 */
	public String getDescricaoTipoBem() {
		return descricaoTipoBem;
	}

	/**
	 * Preenche descricao tipo bem.
	 * 
	 * @param descricaoTipoBem
	 *            o novo descricao tipo bem
	 */
	public void setDescricaoTipoBem(String descricaoTipoBem) {
		this.descricaoTipoBem = descricaoTipoBem;
	}

	/**
	 * Recupera codigo sub tipo bem.
	 * 
	 * @return codigo sub tipo bem
	 */
	public Short getCodigoSubTipoBem() {
		return codigoSubTipoBem;
	}

	/**
	 * Preenche codigo sub tipo bem.
	 * 
	 * @param codigoSubTipoBem
	 *            o novo codigo sub tipo bem
	 */
	public void setCodigoSubTipoBem(Short codigoSubTipoBem) {
		this.codigoSubTipoBem = codigoSubTipoBem;
	}

	/**
	 * Recupera descricao sub tipo bem.
	 * 
	 * @return descricao sub tipo bem
	 */
	public String getDescricaoSubTipoBem() {
		return descricaoSubTipoBem;
	}

	/**
	 * Preenche descricao sub tipo bem.
	 * 
	 * @param descricaoSubTipoBem
	 *            o novo descricao sub tipo bem
	 */
	public void setDescricaoSubTipoBem(String descricaoSubTipoBem) {
		this.descricaoSubTipoBem = descricaoSubTipoBem;
	}

	/**
	 * Recupera cod localizacao imovel.
	 * 
	 * @return cod localizacao imovel
	 */
	public String getCodLocalizacaoImovel() {
		return codLocalizacaoImovel;
	}

	/**
	 * Preenche cod localizacao imovel.
	 * 
	 * @param codLocalizacaoImovel
	 *            o novo cod localizacao imovel
	 */
	public void setCodLocalizacaoImovel(String codLocalizacaoImovel) {
		this.codLocalizacaoImovel = codLocalizacaoImovel;
	}

	/**
	 * Recupera benfeitoria imovel.
	 * 
	 * @return benfeitoria imovel
	 */
	public String getBenfeitoriaImovel() {
		return benfeitoriaImovel;
	}

	/**
	 * Preenche benfeitoria imovel.
	 * 
	 * @param benfeitoriaImovel
	 *            o novo benfeitoria imovel
	 */
	public void setBenfeitoriaImovel(String benfeitoriaImovel) {
		this.benfeitoriaImovel = benfeitoriaImovel;
	}

	/**
	 * Recupera valor benfeitoria imovel.
	 * 
	 * @return valor benfeitoria imovel
	 */
	public BigDecimal getValorBenfeitoriaImovel() {
		return valorBenfeitoriaImovel;
	}

	/**
	 * Preenche valor benfeitoria imovel.
	 * 
	 * @param valorBenfeitoriaImovel
	 *            o novo valor benfeitoria imovel
	 */
	public void setValorBenfeitoriaImovel(BigDecimal valorBenfeitoriaImovel) {
		this.valorBenfeitoriaImovel = valorBenfeitoriaImovel;
	}

	/**
	 * Recupera denominacao imovel.
	 * 
	 * @return denominacao imovel
	 */
	public String getDenominacaoImovel() {
		return denominacaoImovel;
	}

	/**
	 * Preenche denominacao imovel.
	 * 
	 * @param denominacaoImovel
	 *            o novo denominacao imovel
	 */
	public void setDenominacaoImovel(String denominacaoImovel) {
		this.denominacaoImovel = denominacaoImovel;
	}

	/**
	 * Recupera comarca imovel.
	 * 
	 * @return comarca imovel
	 */
	public String getComarcaImovel() {
		return comarcaImovel;
	}

	/**
	 * Preenche comarca imovel.
	 * 
	 * @param comarcaImovel
	 *            o novo comarca imovel
	 */
	public void setComarcaImovel(String comarcaImovel) {
		this.comarcaImovel = comarcaImovel;
	}

	/**
	 * Recupera sigla uf comarca imovel.
	 * 
	 * @return sigla uf comarca imovel
	 */
	public String getSiglaUFComarcaImovel() {
		return siglaUFComarcaImovel;
	}

	/**
	 * Preenche sigla uf comarca imovel.
	 * 
	 * @param siglaUFComarcaImovel
	 *            o novo sigla uf comarca imovel
	 */
	public void setSiglaUFComarcaImovel(String siglaUFComarcaImovel) {
		this.siglaUFComarcaImovel = siglaUFComarcaImovel;
	}

	/**
	 * Recupera id localidade comarca imovel.
	 * 
	 * @return id localidade comarca imovel
	 */
	public Integer getIdLocalidadeComarcaImovel() {
		return idLocalidadeComarcaImovel;
	}

	/**
	 * Preenche id localidade comarca imovel.
	 * 
	 * @param idLocalidadeComarcaImovel
	 *            o novo id localidade comarca imovel
	 */
	public void setIdLocalidadeComarcaImovel(Integer idLocalidadeComarcaImovel) {
		this.idLocalidadeComarcaImovel = idLocalidadeComarcaImovel;
	}

	/**
	 * Recupera id localidade imovel.
	 * 
	 * @return id localidade imovel
	 */
	public Integer getIdLocalidadeImovel() {
		return idLocalidadeImovel;
	}

	/**
	 * Preenche id localidade imovel.
	 * 
	 * @param idLocalidadeImovel
	 *            o novo id localidade imovel
	 */
	public void setIdLocalidadeImovel(Integer idLocalidadeImovel) {
		this.idLocalidadeImovel = idLocalidadeImovel;
	}

	/**
	 * Recupera municipio imovel.
	 * 
	 * @return municipio imovel
	 */
	public String getMunicipioImovel() {
		return municipioImovel;
	}

	/**
	 * Preenche municipio imovel.
	 * 
	 * @param municipioImovel
	 *            o novo municipio imovel
	 */
	public void setMunicipioImovel(String municipioImovel) {
		this.municipioImovel = municipioImovel;
	}

	/**
	 * Recupera sigla uf municipio imovel.
	 * 
	 * @return sigla uf municipio imovel
	 */
	public String getSiglaUFMunicipioImovel() {
		return siglaUFMunicipioImovel;
	}

	/**
	 * Preenche sigla uf municipio imovel.
	 * 
	 * @param siglaUFMunicipioImovel
	 *            o novo sigla uf municipio imovel
	 */
	public void setSiglaUFMunicipioImovel(String siglaUFMunicipioImovel) {
		this.siglaUFMunicipioImovel = siglaUFMunicipioImovel;
	}

	/**
	 * Recupera area imovel.
	 * 
	 * @return area imovel
	 */
	public BigDecimal getAreaImovel() {
		return areaImovel;
	}

	/**
	 * Preenche area imovel.
	 * 
	 * @param areaImovel
	 *            o novo area imovel
	 */
	public void setAreaImovel(BigDecimal areaImovel) {
		this.areaImovel = areaImovel;
	}

	/**
	 * Recupera codigo unidade medida imovel.
	 * 
	 * @return codigo unidade medida imovel
	 */
	public Short getCodigoUnidadeMedidaImovel() {
		return codigoUnidadeMedidaImovel;
	}

	/**
	 * Preenche codigo unidade medida imovel.
	 * 
	 * @param codigoUnidadeMedidaImovel
	 *            o novo codigo unidade medida imovel
	 */
	public void setCodigoUnidadeMedidaImovel(Short codigoUnidadeMedidaImovel) {
		this.codigoUnidadeMedidaImovel = codigoUnidadeMedidaImovel;
	}

	/**
	 * Recupera descricao unidade medida imovel.
	 * 
	 * @return descricao unidade medida imovel
	 */
	public String getDescricaoUnidadeMedidaImovel() {
		return descricaoUnidadeMedidaImovel;
	}

	/**
	 * Preenche descricao unidade medida imovel.
	 * 
	 * @param descricaoUnidadeMedidaImovel
	 *            o novo descricao unidade medida imovel
	 */
	public void setDescricaoUnidadeMedidaImovel(String descricaoUnidadeMedidaImovel) {
		this.descricaoUnidadeMedidaImovel = descricaoUnidadeMedidaImovel;
	}

	/**
	 * Recupera sigla unidade medida imovel.
	 * 
	 * @return sigla unidade medida imovel
	 */
	public String getSiglaUnidadeMedidaImovel() {
		return siglaUnidadeMedidaImovel;
	}

	/**
	 * Preenche sigla unidade medida imovel.
	 * 
	 * @param siglaUnidadeMedidaImovel
	 *            o novo sigla unidade medida imovel
	 */
	public void setSiglaUnidadeMedidaImovel(String siglaUnidadeMedidaImovel) {
		this.siglaUnidadeMedidaImovel = siglaUnidadeMedidaImovel;
	}

	/**
	 * Recupera posses.
	 * 
	 * @return posses
	 */
	public List<BemPosseVO> getPosses() {
		return posses;
	}

	/**
	 * Preenche posses.
	 * 
	 * @param posses
	 *            o novo posses
	 */
	public void setPosses(List<BemPosseVO> posses) {
		this.posses = posses;
	}
	
}
