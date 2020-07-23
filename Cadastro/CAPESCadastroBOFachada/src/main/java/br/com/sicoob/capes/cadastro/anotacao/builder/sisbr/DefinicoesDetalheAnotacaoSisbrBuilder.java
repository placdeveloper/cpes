/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.sisbr;

import java.util.List;

import br.com.sicoob.capes.cadastro.anotacao.builder.DefinicoesDetalheAnotacaoBuilder;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições do detalhe de anotações de origem SISBR.
 * 
 * @author Erico.Junior
 */
public class DefinicoesDetalheAnotacaoSisbrBuilder extends DefinicoesDetalheAnotacaoBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarDefinicoes(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoTextoDTO("Cooperativa", "numeroCooperativa", 80));
		lista.add(obterDefinicaoTextoDTO("Produto", "descricaoProduto", 300));
		lista.add(obterDefinicaoTextoDTO("Modalidade", "descricaoModalidade", 300));
		lista.add(obterDefinicaoTextoDTO("Contrato", "contrato", 140));
		lista.add(obterDefinicaoDataDTO("Dt. de Vencimento", "dataVencimento", 80));
	}
}
