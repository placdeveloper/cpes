// 09/08/2013
package br.com.sicoob.capes.replicacao.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.dto.ReplicacaoCadastroDTO;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoCadastroServico;

/**
 * A Interface ReplicacaoCadastroServicoLocal.
 */
public interface ReplicacaoCadastroServicoLocal extends ReplicacaoCadastroServico {
	
	Integer replicarCadastroCooperativa(ReplicacaoCadastroDTO replicacao, Integer cooperativaDestino) throws BancoobException;

}