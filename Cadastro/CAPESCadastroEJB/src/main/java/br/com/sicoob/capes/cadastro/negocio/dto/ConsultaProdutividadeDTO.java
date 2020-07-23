/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;

/**
 * @author erico.junior
 *
 */
public class ConsultaProdutividadeDTO extends ConsultaDto<Produtividade> {

	/** Serial UID.*/
	private static final long serialVersionUID = -131916832344598902L;
	
	/** O atributo situacoes. */
	private List<Short> situacoes;

	/**
	 * Recupera o valor de situacoes.
	 *
	 * @return o valor de situacoes
	 */
	public List<Short> getSituacoes() {
		return situacoes;
	}

	/**
	 * Define o valor de situacoes.
	 *
	 * @param situacoes o novo valor de situacoes
	 */
	public void setSituacoes(List<Short> situacoes) {
		this.situacoes = situacoes;
	}
	
	
}
