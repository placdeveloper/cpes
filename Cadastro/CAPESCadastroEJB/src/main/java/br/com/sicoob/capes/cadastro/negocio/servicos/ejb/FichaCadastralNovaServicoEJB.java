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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FichaCadastralNovaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FichaCadastralNovaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.FichaCadastralNovaDAO;

/**
 * EJB contendo servicos relacionados a FichaCadastralNova.
 */
@Stateless
@Local( { FichaCadastralNovaServicoLocal.class })
@Remote( { FichaCadastralNovaServicoRemote.class })
public class FichaCadastralNovaServicoEJB extends CAPESCadastroServicoEJB
		implements FichaCadastralNovaServicoLocal, FichaCadastralNovaServicoRemote {

	@Inject
	@Default
	private FichaCadastralNovaDAO fichaCadastralNovaDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <T> List<T> listar(Class<T> clazz, ConsultaDtoCUC<?> consultaDtoCUC) {
		return fichaCadastralNovaDAO.listar(clazz, consultaDtoCUC);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <E> E obterEntidadeAutorizacao(ConsultaAutorizacaoDTO dto) {
		return (E)fichaCadastralNovaDAO.obterEntidadeAutorizacao(dto);
	}
}
