/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;


import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;

/**
 * DTO utlilizado para consultar as transições da pessoa.
 *  
 * @author Erico.Junior
 */
public class ConsultaTransicaoPessoaDTO extends ConsultaDto<TransicaoPessoa> {
	
	/** Serial UID.*/
	private static final long serialVersionUID = -2054194255647144741L;

	/** O atributo instituicoes. */
	private List<Instituicao> instituicoes;

	/**
	 * @return the instituicoes
	 */
	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	/**
	 * @param instituicoes the instituicoes to set
	 */
	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}
	
}
