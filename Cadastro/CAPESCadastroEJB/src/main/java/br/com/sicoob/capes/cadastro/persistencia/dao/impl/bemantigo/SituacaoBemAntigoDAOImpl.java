/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.SituacaoBemAntigoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.CAPESCadastroCrudDao;

/**
 * DAO para as situações dos bens. 
 * @author erico.junior
 */
public class SituacaoBemAntigoDAOImpl extends CAPESCadastroCrudDao<SituacaoBem> implements
		SituacaoBemAntigoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_SITUACAO_BEM_POR_SUBTIPO";

	/**
	 * Instancia um novo SituacaoBemDAOImpl.
	 */
	public SituacaoBemAntigoDAOImpl() {
		super(SituacaoBem.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<SituacaoBem> pesquisar(
			ConsultaDto<SituacaoBem> criterios) throws BancoobException {
		
		List<SituacaoBem> lista = pesquisarLista(SituacaoBem.class, criterios, NOME_COMANDO_PESQUISAR);
		criterios.setResultado(lista);
		
		return criterios;
	}
}
