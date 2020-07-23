/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.SubTipoBemAntigoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.CAPESCadastroCrudDominioDao;

/**
 * Dao para sub tipos de bem
 * 
 * @author juan.damasceno
 */
public class SubTipoBemAntigoDAOImpl extends CAPESCadastroCrudDominioDao<SubTipoBem> implements
	SubTipoBemAntigoDAO {

	/**
	 * Instancia um novo SubTipoBemDAOImpl.
	 */
	public SubTipoBemAntigoDAOImpl() {
		super(SubTipoBem.class, "PESQUISA_SUBTIPO_BEM_POR_FILTRO",
				"PESQUISAR_PROXIMO_CODIGO_SUBTIPO_BEM");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<SubTipoBem> pesquisarPorTipo(ConsultaDto<SubTipoBem> criterios)
			throws BancoobException {
		
		return pesquisarLista(SubTipoBem.class, criterios, "PESQUISA_SUBTIPO_BEM_POR_TIPO");
	}

}