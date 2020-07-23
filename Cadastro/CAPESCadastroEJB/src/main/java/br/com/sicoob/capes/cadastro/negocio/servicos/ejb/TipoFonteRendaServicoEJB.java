/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFonteRendaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFonteRendaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFonteRendaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Implementa as operações do serviço de tipo de fonte de renda.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { TipoFonteRendaServicoLocal.class })
@Remote( { TipoFonteRendaServicoRemote.class })
public class TipoFonteRendaServicoEJB extends
	CAPESCadastroCrudDominioServicoEJB<TipoFonteRenda> implements TipoFonteRendaServicoRemote, TipoFonteRendaServicoLocal {

	@Inject
	@Default
	protected TipoFonteRendaDAO tipoFonteRendaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFonteRendaDAO getDAO() {
		return tipoFonteRendaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoFonteRenda> listarPorTipoPessoa(TipoPessoa tipoPessoa) throws BancoobException {
		return getDAO().listarPorTipoPessoa(tipoPessoa);
	}

}
