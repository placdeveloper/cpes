package br.com.sicoob.capes.processamento.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * 
 * @author Lucas.Borges
 *
 */
public class PessoaSFNDTO extends BancoobDto{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -3179463658307694726L;

	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo dataCadastramentoSFN. */
	private DateTimeDB dataCadastramentoSFN;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;

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
	 * Recupera o valor de dataCadastramentoSFN.
	 *
	 * @return o valor de dataCadastramentoSFN
	 */
	public DateTimeDB getDataCadastramentoSFN() {
		return dataCadastramentoSFN;
	}

	/**
	 * Define o valor de dataCadastramentoSFN.
	 *
	 * @param dataCadastramentoSFN o novo valor de dataCadastramentoSFN
	 */
	public void setDataCadastramentoSFN(DateTimeDB dataCadastramentoSFN) {
		this.dataCadastramentoSFN = dataCadastramentoSFN;
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
	 * Recupera o valor de idUnidadeInst.
	 *
	 * @return o valor de idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 *
	 * @param idUnidadeInst o novo valor de idUnidadeInst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}
}
