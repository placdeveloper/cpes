/*
 * SICOOB
 * 
 * DadosReceitaFederalVO.java(br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * @author Rodrigo.Chaves
 */
public abstract class DadosReceitaFederalVO extends BancoobVo {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -8478867107983887251L;
	
	/** O atributo codigo situacao cadastral. */
	private Integer codigoSituacaoCadastral;
	
	/** O atributo data ultima atualizacao. */
	private Date dataUltimaAtualizacao;
	
	/** O atributo id consulta. */
	private Integer idConsulta;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;

	/**
	 * Recupera codigo situacao cadastral.
	 * 
	 * @return codigo situacao cadastral
	 */
	public Integer getCodigoSituacaoCadastral() {
		return codigoSituacaoCadastral;
	}

	/**
	 * Preenche codigo situacao cadastral.
	 * 
	 * @param codigoSituacaoCadastral
	 *            o novo codigo situacao cadastral
	 */
	public void setCodigoSituacaoCadastral(Integer codigoSituacaoCadastral) {
		this.codigoSituacaoCadastral = codigoSituacaoCadastral;
	}

	/**
	 * Recupera data ultima atualizacao.
	 * 
	 * @return data ultima atualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	
	/**
	 * Preenche data ultima atualizacao.
	 * 
	 * @param dataUltimaAtualizacao
	 *            o novo data ultima atualizacao
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	/**
	 * Recupera o valor de idConsulta.
	 *
	 * @return o valor de idConsulta
	 */
	public Integer getIdConsulta() {
	
		return idConsulta;
	}

	/**
	 * Preenche id consulta.
	 * 
	 * @param idConsulta
	 *            o novo id consulta
	 */
	public void setIdConsulta(Integer idConsulta) {
	
		this.idConsulta = idConsulta;
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
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public abstract String getNome();

	/**
	 * Recupera numero inscricao.
	 * 
	 * @return numero inscricao
	 */
	public abstract String getNumeroInscricao();

}
