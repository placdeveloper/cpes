/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO utilizado na replicação do cadastro de uma pessoa.
 * 
 * @author Erico.Junior
 */
public class ReplicacaoCadastroDTO extends BancoobDto {

	/** Serial UID. */
	private static final long serialVersionUID = 1923840655732805842L;

	/** O atributo numPessoaOrigem. */
	private Integer numPessoaOrigem;
	
	/** O atributo idInstituicaoOrigem. */
	private Integer idInstituicaoOrigem;
	
	/**
	 * @return the numPessoaOrigem
	 */
	public Integer getNumPessoaOrigem() {
		return numPessoaOrigem;
	}

	/**
	 * @param numPessoaOrigem
	 *            the numPessoaOrigem to set
	 */
	public void setNumPessoaOrigem(Integer numPessoaOrigem) {
		this.numPessoaOrigem = numPessoaOrigem;
	}

	/**
	 * @return the idInstituicaoOrigem
	 */
	public Integer getIdInstituicaoOrigem() {
		return idInstituicaoOrigem;
	}

	/**
	 * @param idInstituicaoOrigem
	 *            the idInstituicaoOrigem to set
	 */
	public void setIdInstituicaoOrigem(Integer idInstituicaoOrigem) {
		this.idInstituicaoOrigem = idInstituicaoOrigem;
	}

}
