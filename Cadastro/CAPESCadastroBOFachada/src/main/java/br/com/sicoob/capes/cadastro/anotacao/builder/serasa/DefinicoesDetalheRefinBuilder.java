/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Fábrica de detalhes para o Serasa Refin. 
 * @author erico.junior
 */
public class DefinicoesDetalheRefinBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		
		lista.add(obterDefinicaoValorDTO("Valor", "valorOcorrencia", 75));
		lista.add(obterDefinicaoTextoDTO("Natureza", "natureza", 400));
		lista.add(obterDefinicaoTextoDTO("Cod. Empresa", "codigoEmpresaParticipante", 75));
		lista.add(obterDefinicaoTextoDTO("Agência", "agencia", 50));
		lista.add(obterDefinicaoTextoDTO("Cidade", "cidade", 195));
		lista.add(obterDefinicaoTextoDTO("UF", "uf", 45));
		lista.add(obterDefinicaoTextoDTO("Principal", "principal", 60));
	}


}
