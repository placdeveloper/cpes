/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.bacen;

import java.util.List;

import br.com.sicoob.capes.cadastro.anotacao.builder.DefinicoesDetalheAnotacaoBuilder;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições do detalhe de anotações do BACEN.
 * @author Erico.Junior
 */
public class DefinicoesDetalheAnotacaoBacenBuilder extends DefinicoesDetalheAnotacaoBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarDefinicoes(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoTextoDTO("Data Base", "dataBase", 95));
		lista.add(obterDefinicaoValorDTO("% Volume Processado", "percentualVolumeProcessado", 225));
		lista.add(obterDefinicaoValorDTO("% Documentos Processados", "percentualDocumentosProcessados", 225));
		lista.add(obterDefinicaoInteiroDTO("Quant. de Instituições", "quantidadeInstituicoes", 225));
		lista.add(obterDefinicaoInteiroDTO("Quant. de Operações", "quantidadeOperacoes", 225));
	}

}
