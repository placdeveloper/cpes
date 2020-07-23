/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder.serasa;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as definições de detalhe do Participante de empresa Falida. 
 * @author erico.junior
 */
public class DefinicoesDetalheParticipanteFalidaBuilder extends DefinicoesDetalheAnotacaoSerasaBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void adicionarColunas(List<DefinicaoDTO> lista) {
		
		lista.add(obterDefinicaoTextoDTO("Natureza", "natureza", 400));
		lista.add(obterDefinicaoTextoDTO("Vara Civil", "varaCivil", 100));
		lista.add(obterDefinicaoTextoDTO("Qualificação", "qualificacao", 100));
		lista.add(obterDefinicaoCnpjDTO("Cnpj Empresa", "cnpjEmpresa", 100));
		lista.add(obterDefinicaoTextoDTO("Nome Empresa", "nomeEmpresa", 200));
	}

}
