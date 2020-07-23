/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.anotacao.builder.DefinicoesDetalheAnotacaoBuilder;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Classe abstrata que define o Builder para as defini��es de detalhe do Serasa.
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
	 * Adiciona as defini��es a lista.
	 * 
	 * @param lista
	 *            A lista onde ser� adicionada a defini��o.
	 */
	protected abstract void adicionarColunas(List<DefinicaoDTO> lista);
	
	/**
	 * Este m�todo pode ser sobrescrito se necess�rio.
	 * @param lista A lista onde ser� adicionada a defini��o da coluna.
	 */
	protected void adicionarDataOcorrencia(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoDataDTO("Data Ocorr�ncia", "dataOcorrencia", 95));
	}
}
