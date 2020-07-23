package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * A Classe TelefoneDTO.
 * 
 * @author bruno.carneiro
 */
public class ConsultaTelefonePessoaDTO extends BancoobDto {

	/** O atributo serialVersionUID. */
	private static final long serialVersionUID = 8277546767584099804L;

	/** O atributo idTelefonePessoa. */
	@AtributoRetorno(posicao = 1)
	private Long idTelefonePessoa;

	/** O atributo codigoTipoTelefone. */
	@AtributoRetorno(posicao = 2)
	private Short codigoTipoTelefone;

	/** O atributo ddd. */
	@AtributoRetorno(posicao = 3)
	private String ddd;

	/** O atributo telefone. */
	@AtributoRetorno(posicao = 4)
	private String telefone;

	/** O atributo ramal. */
	@AtributoRetorno(posicao = 5)
	private String ramal;

	/** O atributo observacao. */
	@AtributoRetorno(posicao = 6)
	private String observacao;

	/**
	 * Recupera o valor de idTelefonePessoa.
	 * 
	 * @return o valor de idTelefonePessoa
	 */
	public Long getIdTelefonePessoa() {
		return idTelefonePessoa;
	}

	/**
	 * Define o valor de idTelefonePessoa.
	 * 
	 * @param idTelefonePessoa
	 *            o novo valor de idTelefonePessoa
	 */
	public void setIdTelefonePessoa(Long idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
	}

	/**
	 * Recupera o valor de codigoTipoTelefone.
	 * 
	 * @return o valor de codigoTipoTelefone
	 */
	public Short getCodigoTipoTelefone() {
		return codigoTipoTelefone;
	}

	/**
	 * Define o valor de codigoTipoTelefone.
	 * 
	 * @param codigoTipoTelefone
	 *            o novo valor de codigoTipoTelefone
	 */
	public void setCodigoTipoTelefone(Short codigoTipoTelefone) {
		this.codigoTipoTelefone = codigoTipoTelefone;
	}

	/**
	 * Recupera o valor de ddd.
	 * 
	 * @return o valor de ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Define o valor de ddd.
	 * 
	 * @param ddd
	 *            o novo valor de ddd
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * Recupera o valor de telefone.
	 * 
	 * @return o valor de telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Define o valor de telefone.
	 * 
	 * @param telefone
	 *            o novo valor de telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Recupera o valor de ramal.
	 * 
	 * @return o valor de ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * Define o valor de ramal.
	 * 
	 * @param ramal
	 *            o novo valor de ramal
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * Recupera o valor de observacao.
	 * 
	 * @return o valor de observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Define o valor de observacao.
	 * 
	 * @param observacao
	 *            o novo valor de observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}