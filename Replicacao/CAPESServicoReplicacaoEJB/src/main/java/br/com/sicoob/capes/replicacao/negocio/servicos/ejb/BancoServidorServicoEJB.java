package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BancoServidorServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BancoServidorServicoRemote;
import br.com.sicoob.capes.replicacao.negocio.vo.DataHoraServidorVO;
import br.com.sicoob.capes.replicacao.persistencia.dao.BancoServidorDao;

/**
 * Classe que implementa logica de negocio de BancoServidor;
 * @author Juan.Damasceno
 *
 */
@Stateless
@Local({BancoServidorServicoLocal.class})
@Remote({BancoServidorServicoRemote.class})
public class BancoServidorServicoEJB extends
		CAPESServicoReplicacaoCrudServicoEJB<BancoServidor> implements BancoServidorServicoRemote, BancoServidorServicoLocal {

	@Inject
	@Default
	private BancoServidorDao bancoServidorDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BancoServidorDao getDAO() {
		return bancoServidorDao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<BancoServidor> listar(ConsultaDto<BancoServidor> criterios)
			throws BancoobException {
		return getDAO().listar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public DataHoraServidorVO obterDataHoraServidor(BancoServidor bancoServidor) {
		return bancoServidorDao.obterDataHoraServidor(bancoServidor);
	}

}
