package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.replicacao.negocio.vo.DataHoraServidorVO;

/**
 * Interface que define os metodos de serviço de BancoServidor.
 * @author Juan.Damasceno
 *
 */
public interface BancoServidorServico extends
		CAPESServicoReplicacaoCrudServico<BancoServidor> {
	
	/**
	 * Obter data hora servidor.
	 *
	 * @param bancoServidor o valor de banco servidor
	 * @return DataHoraServidorVO
	 */
	DataHoraServidorVO obterDataHoraServidor(BancoServidor bancoServidor);

}