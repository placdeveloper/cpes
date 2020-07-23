package br.com.sicoob.capes.api.persistencia.dao.impl;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.api.persistencia.dao.ClienteDAO;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * A Classe ClienteDAOImpl.
 */
public class ClienteDAOImpl extends CAPESApiDAO<ClienteVO> implements ClienteDAO {
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();

	/**
	 * Instancia um novo ClienteDAOImpl.
	 */
	public ClienteDAOImpl() {
		super("PESQUISAR_CLIENTE", "PESQUISAR_QUANTIDADE_CLIENTE");
	}

	/**
	 * {@inheritDoc}
	 */
	public ClienteVO obterCliente(ConsultaDto<ClienteVO> criterios) throws BancoobException {
		Comando comando = getComando("OBTER_CLIENTE");
		try {
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();

			ClienteVO singleResult = (ClienteVO) criarQuery(comando).getSingleResult();
			logger.debug("ClienteDAOImpl obterCliente - Retorno:" + singleResult);
			return singleResult;
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
}