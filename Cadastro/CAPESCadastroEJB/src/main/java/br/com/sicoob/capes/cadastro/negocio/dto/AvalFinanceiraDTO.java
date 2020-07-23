package br.com.sicoob.capes.cadastro.negocio.dto;

import java.io.Serializable;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe AvalFinanceiraDTO.
 */
public class AvalFinanceiraDTO extends BancoobDto implements Replicavel {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1935644344540056078L;

	/** O atributo pessoaCompartilhamento. */
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * Define o valor de pessoaCompartilhamento.
	 *
	 * @param pessoaCompartilhamento o novo valor de pessoaCompartilhamento
	 */
	public void setPessoaCompartilhamento(
			PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
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
