/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
* Builder para as definições de detalhe do protesto.  
 * @author erico.junior
 */
public class DefinicoesDetalheProtestoBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		
		lista.add(obterDefinicaoValorDTO("Valor", "valorOcorrencia", 75));		
		lista.add(obterDefinicaoTextoDTO("Natureza", "natureza", 120));
		lista.add(obterDefinicaoTextoDTO("Cartório", "cartorio", 80));
		lista.add(obterDefinicaoTextoDTO("Cidade", "cidade", 200));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 50));
		lista.add(obterDefinicaoTextoDTO("Sub-judice", "subJudice", 375));
	}

}
