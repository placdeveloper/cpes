/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.TipoBemAntigoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.CAPESCadastroCrudDominioDao;

/**
 * Dao para tipos de bem
 * @author erico.junior
 */
public class TipoBemAntigoDAOImpl extends CAPESCadastroCrudDominioDao<TipoBem> implements
		TipoBemAntigoDAO {

	/**
	 * Instancia um novo TipoBemDAOImpl.
	 */
	public TipoBemAntigoDAOImpl() {
		super(TipoBem.class, "PESQUISAR_TIPO_BEM", "PESQUISAR_PROXIMO_CODIGO_TIPO_BEM");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TipoBem> listarTiposComSubtipo() throws BancoobException {
		
		return pesquisarLista(TipoBem.class, new ConsultaDto<TipoBem>(), "LISTAR_TIPO_BEM_COM_SUBTIPO");
	}
}
