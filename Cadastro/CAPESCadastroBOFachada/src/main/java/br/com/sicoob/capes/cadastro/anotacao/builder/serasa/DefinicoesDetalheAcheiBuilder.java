/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições de detalhe do achei. 
 * @author erico.junior
 */
public class DefinicoesDetalheAcheiBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		
		lista.add(obterDefinicaoValorDTO("Valor", "valorOcorrencia", 75));
		lista.add(obterDefinicaoTextoDTO("Banco", "banco", 130));
		lista.add(obterDefinicaoTextoDTO("Agência", "agencia", 75));
		lista.add(obterDefinicaoTextoDTO("Conta Corrente", "contaCorrente", 90));
		lista.add(obterDefinicaoTextoDTO("Num. Cheque", "cheque", 75));
		lista.add(obterDefinicaoTextoDTO("Alínea", "alinea", 230));
		lista.add(obterDefinicaoTextoDTO("Cidade", "cidade", 175));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 50));
	}

}
