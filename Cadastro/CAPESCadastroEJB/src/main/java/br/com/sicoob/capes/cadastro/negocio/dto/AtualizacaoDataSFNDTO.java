/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.io.Serializable;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 *
 */
public class AtualizacaoDataSFNDTO extends BancoobDto implements Replicavel {

	/** Serial UID.*/
	private static final long serialVersionUID = 2169939944611298307L;
	
	/** O atributo pessoaCompartilhamento. */
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/**
	 * Instancia um novo AtualizacaoDataSFNDTO.
	 */
	public AtualizacaoDataSFNDTO() {
	}
	
	/**
	 * Instancia um novo AtualizacaoDataSFNDTO.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 */
	public AtualizacaoDataSFNDTO(PessoaCompartilhamento pessoaCompartilhamento){
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return this.pessoaCompartilhamento;
	}

	/**
	 * Recupera o valor de id.
	 *
	 * @return o valor de id
	 */
	public Serializable getId() {
		return pessoaCompartilhamento.getId();
	}

}
