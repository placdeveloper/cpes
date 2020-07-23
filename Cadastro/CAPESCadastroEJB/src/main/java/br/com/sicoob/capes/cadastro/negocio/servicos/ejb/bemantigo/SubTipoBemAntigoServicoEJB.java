/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb.bemantigo;

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
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SubTipoBemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SubTipoBemAntigoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.ejb.CAPESCadastroCrudDominioServicoEJB;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.SubTipoBemAntigoDAO;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;

/**
 * Servico para os subtipos de bens
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { SubTipoBemAntigoServicoLocal.class })
@Remote( { SubTipoBemAntigoServicoRemote.class })
public class SubTipoBemAntigoServicoEJB extends 
	CAPESCadastroCrudDominioServicoEJB<SubTipoBem> implements SubTipoBemAntigoServicoRemote, SubTipoBemAntigoServicoLocal {

	@Inject
	@Default
	protected SubTipoBemAntigoDAO subTipoBemDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SubTipoBemAntigoDAO getDAO() {
		return subTipoBemDAO;
	}
	
	/**
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 * @see br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.SubTipoBemAntigoDAO#pesquisarPorTipo(br.com.bancoob.negocio.dto.ConsultaDto)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SubTipoBem> pesquisarPorTipo(ConsultaDto<SubTipoBem> criterios)
			throws BancoobException {
		return subTipoBemDAO.pesquisarPorTipo(criterios);
	}
	
}
