/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.interna;

import java.util.List;

import br.com.sicoob.capes.cadastro.anotacao.builder.DefinicoesDetalheAnotacaoBuilder;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * @author Erico.Junior
 *
 */
public class DefinicoesDetalheAnotacaoInternaBuilder extends DefinicoesDetalheAnotacaoBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarDefinicoes(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoDataDTO("Data Ocorrência", "dataOcorrencia", 95));
		lista.add(obterDefinicaoValorDTO("Valor", "valorOcorrencia", 90));
		lista.add(obterDefinicaoTextoDTO("Cooperativa", "numero", 75));
		lista.add(obterDefinicaoInteiroDTO("PAC", "idUnidadeInst", 70));
		lista.add(obterDefinicaoTextoDTO("Responsável", "responsavel", 220));
		lista.add(obterDefinicaoTextoDTO("Observação", "observacao", 350));
	}

}
