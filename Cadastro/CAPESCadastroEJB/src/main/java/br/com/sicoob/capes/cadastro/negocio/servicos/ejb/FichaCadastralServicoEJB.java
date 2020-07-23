package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FichaCadastralServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FichaCadastralServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.FichaCadastralDAO;

/**
 * EJB contendo servicos relacionados a FichaCadastral.
 */
@Stateless
@Local( { FichaCadastralServicoLocal.class })
@Remote( { FichaCadastralServicoRemote.class })
public class FichaCadastralServicoEJB extends CAPESCadastroServicoEJB
		implements FichaCadastralServicoRemote, FichaCadastralServicoLocal {

	@Inject
	@Default
	private FichaCadastralDAO fichaCadastralDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <T> List<T> listar(Class<T> clazz, ConsultaDtoCUC<?> consultaDtoCUC) {
		return fichaCadastralDAO.listar(clazz, consultaDtoCUC);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <E> E obterEntidadeAutorizacao(ConsultaAutorizacaoDTO dto) {
		return (E)fichaCadastralDAO.obterEntidadeAutorizacao(dto);
	}

}
