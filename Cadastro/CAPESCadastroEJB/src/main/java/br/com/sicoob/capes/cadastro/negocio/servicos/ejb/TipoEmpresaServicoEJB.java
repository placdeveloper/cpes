/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEmpresaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEmpresaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEmpresaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { TipoEmpresaServicoLocal.class })
@Remote( { TipoEmpresaServicoRemote.class })
public class TipoEmpresaServicoEJB extends
		CAPESCadastroCrudDominioServicoEJB<TipoEmpresa> implements TipoEmpresaServicoRemote, TipoEmpresaServicoLocal {

	@Inject
	@Default
	protected TipoEmpresaDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmpresaDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoEmpresa> listar() throws BancoobException {
		return super.listar();
	}

	public TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda fonteRenda) throws BancoobException {
		return getDAO().obterTipoEmpresaPorFaixaRendaAnual(fonteRenda);
	}	
	
	
}
