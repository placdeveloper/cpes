/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.anotacao.builder.DefinicoesDetalheAnotacaoBuilder;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Classe abstrata que define o Builder para as definições de detalhe do Serasa.
 * @author erico.junior
 */
public abstract class DefinicoesDetalheAnotacaoSerasaBuilder 
		extends DefinicoesDetalheAnotacaoBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarDefinicoes(List<DefinicaoDTO> lista) {
		adicionarDataOcorrencia(lista);
		adicionarColunas(lista);
	}

	/**
	 * Adiciona as definições a lista.
	 * 
	 * @param lista
	 *            A lista onde será adicionada a definição.
	 */
	protected abstract void adicionarColunas(List<DefinicaoDTO> lista);
	
	/**
	 * Este método pode ser sobrescrito se necessário.
	 * @param lista A lista onde será adicionada a definição da coluna.
	 */
	protected void adicionarDataOcorrencia(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoDataDTO("Data Ocorrência", "dataOcorrencia", 95));
	}
}
