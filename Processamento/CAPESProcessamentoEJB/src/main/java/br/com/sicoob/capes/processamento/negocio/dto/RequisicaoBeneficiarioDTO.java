/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * @author Erico.Junior
 *
 */
public class RequisicaoBeneficiarioDTO extends BancoobDto{

	/** Serial UID.*/
	private static final long serialVersionUID = -4640407996462947253L;
	
	/** O atributo pessoa. */
	private PessoaFisica pessoa;
	
	/** O atributo beneficiario. */
	private BeneficiarioDTO beneficiario;
	
	/** O atributo dataProduto. */
	private DateTimeDB dataProduto;

	/**
	 * @return the pessoa
	 */
	public PessoaFisica getPessoa() {
		return pessoa;
	}
	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}
	/**
	 * @return the beneficiario
	 */
	public BeneficiarioDTO getBeneficiario() {
		return beneficiario;
	}
	/**
	 * @param beneficiario the beneficiario to set
	 */
	public void setBeneficiario(BeneficiarioDTO beneficiario) {
		this.beneficiario = beneficiario;
	}
	/**
	 * @return the dataProduto
	 */
	public DateTimeDB getDataProduto() {
		return dataProduto;
	}
	/**
	 * @param dataProduto the dataProduto to set
	 */
	public void setDataProduto(DateTimeDB dataProduto) {
		this.dataProduto = dataProduto;
	}

}