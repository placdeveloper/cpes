/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições de detalhe do Convem Devedores. 
 * @author erico.junior
 */
public class DefinicoesDetalheConvemDevedoresBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		
		lista.add(obterDefinicaoValorDTO("Valor", "valorOcorrencia", 75));		
		lista.add(obterDefinicaoTextoDTO("Natureza", "natureza", 475));
		lista.add(obterDefinicaoCnpjDTO("Cnpj Credor", "cnpjCredor", 100));
		lista.add(obterDefinicaoTextoDTO("Nome Instituição", "nomeInstituicao", 200));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 50));
		
	}

}
