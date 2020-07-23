/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * DTO utilizado na alteração de cpf/cnpj da pessoa.
 * 
 * @author Erico.Junior
 */
public class AlteracaoDocumentoPessoaDTO extends BancoobDto {

	/** Serial UID. */
	private static final long serialVersionUID = -4460836196543701440L;
	
	/** O atributo pessoa. */
	private Pessoa pessoa;
	
	/** O atributo cpfCnpjNovo. */
	private String cpfCnpjNovo;
	
	/** O atributo solicitante. */
	private String solicitante;
	
	/** O atributo motivo. */
	private String motivo;

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the cpfCnpjNovo
	 */
	public String getCpfCnpjNovo() {
		return cpfCnpjNovo;
	}

	/**
	 * @param cpfCnpjNovo the cpfCnpjNovo to set
	 */
	public void setCpfCnpjNovo(String cpfCnpjNovo) {
		this.cpfCnpjNovo = cpfCnpjNovo;
	}

	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
