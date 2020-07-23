package br.com.sicoob.capes.replicacao.negocio.servicos.interfaces;

import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoAssincronaServico;

/**
 * Interface do serviço local para replicação assíncrona.
 *
 * @author Bruno.Carneiro
 */
public interface ReplicacaoAssincronaServicoLocal extends ReplicacaoAssincronaServico {
	
	void replicarCadastroAssincrono(Integer numPessoaOrigem, Integer numCooperativaOrigem, Integer numCooperativaDestino);

}