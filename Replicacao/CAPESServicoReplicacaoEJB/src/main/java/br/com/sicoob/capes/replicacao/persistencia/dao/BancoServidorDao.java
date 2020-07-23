package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.replicacao.negocio.vo.DataHoraServidorVO;

/**
 * Interface que define os métodos de persistencia de BancoServidor.
 * @author Juan.Damasceno
 *
 */
public interface BancoServidorDao extends CAPESServicoReplicacaoCrudDaoIF<BancoServidor> {

	/**
	 * Obter data hora servidor.
	 *
	 * @param bancoServidor o valor de banco servidor
	 * @return DataHoraServidorVO
	 */
	DataHoraServidorVO obterDataHoraServidor(BancoServidor bancoServidor);

}
