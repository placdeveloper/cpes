/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições de detalhe do CCF.  
 * @author erico.junior
 */
public class DefinicoesDetalheCCFBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		lista.add(obterDefinicaoTextoDTO("Banco", "banco", 200));
		lista.add(obterDefinicaoTextoDTO("Agência", "agencia", 100));
		lista.add(obterDefinicaoTextoDTO("Qtde-cheques", "quantidadeCheques", 100));
		lista.add(obterDefinicaoTextoDTO("Cidade", "cidade", 200));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 50));
	}

}
