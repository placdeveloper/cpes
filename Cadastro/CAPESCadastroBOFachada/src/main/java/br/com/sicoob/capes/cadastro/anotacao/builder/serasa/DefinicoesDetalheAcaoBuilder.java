/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições de detalhe da ação.
 * @author erico.junior
 */
public class DefinicoesDetalheAcaoBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		
		lista.add(obterDefinicaoValorDTO("Valor", "valorOcorrencia", 75));
		lista.add(obterDefinicaoTextoDTO("Natureza", "natureza", 400));
		lista.add(obterDefinicaoTextoDTO("Vara civil", "varaCivil", 100));
		lista.add(obterDefinicaoTextoDTO("Cidade", "cidade", 200));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 50));
		lista.add(obterDefinicaoTextoDTO("Principal", "principal", 75));		
	}

}
