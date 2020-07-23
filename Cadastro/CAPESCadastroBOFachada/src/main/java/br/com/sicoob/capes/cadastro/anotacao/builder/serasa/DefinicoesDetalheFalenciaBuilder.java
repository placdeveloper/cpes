/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições de detalhe do Falencia. 
 * @author erico.junior
 */
public class DefinicoesDetalheFalenciaBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoTextoDTO("Natureza", "natureza", 350));
		lista.add(obterDefinicaoTextoDTO("Vara civil", "varaCivil", 100));
		lista.add(obterDefinicaoTextoDTO("Cidade", "cidade", 200));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 50));
	}

}
