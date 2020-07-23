/*
 * SICOOB
 * 
 * DominioServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.DominioServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DominioServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DominioServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.DominioDAO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoDominioEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;

/**
 * @author Marcelo.Onofre
 * 
 */
@Stateless
@Local(DominioServicoLocal.class)
@Remote(DominioServicoRemote.class)
public class DominioServicoEJB extends CAPESApiServicoEJB implements DominioServicoRemote, DominioServicoLocal {

	@Inject
	@Default
	private DominioDAO dominioDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<DominioVO> obterByTipoDominio(TipoDominioEnum tipoDominio) throws BancoobException {
		if (tipoDominio == null) {
			throw new ParametroNaoInformadoException("Tipo de domínio");
		}
		return dominioDAO.obterDominioPorTipoDominio(TipoDominioEntidadeEnum.valueOf(tipoDominio));
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return dominioDAO;
	}

}