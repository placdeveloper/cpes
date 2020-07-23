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
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SituacaoBemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SituacaoBemAntigoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.ejb.CAPESCadastroCrudServicoEJB;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.SituacaoBemAntigoDAO;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;

/**
 * Servico para os tipos de bens
 * 
 * @author erico.junior
 */
@Stateless
@Local({SituacaoBemAntigoServicoLocal.class})
@Remote({SituacaoBemAntigoServicoRemote.class})
public class SituacaoBemAntigoServicoEJB extends CAPESCadastroCrudServicoEJB<SituacaoBem>
		implements SituacaoBemAntigoServicoRemote, SituacaoBemAntigoServicoLocal {

	@Inject
	@Default
	protected SituacaoBemAntigoDAO situacaoBemDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SituacaoBemAntigoDAO getDAO() {
		return situacaoBemDAO;
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SituacaoBem> listar(ConsultaDto<SituacaoBem> criterios)
			throws BancoobException {
		return super.listar(criterios);
	}

}
