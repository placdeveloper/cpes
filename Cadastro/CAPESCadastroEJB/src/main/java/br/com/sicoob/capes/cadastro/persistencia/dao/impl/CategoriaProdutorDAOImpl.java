/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;
import br.com.sicoob.capes.cadastro.persistencia.dao.CategoriaProdutorDAO;

/**
 * @author Erico.Junior
 * 
 */
public class CategoriaProdutorDAOImpl extends
		CAPESCadastroCrudDao<CategoriaProdutor> implements
		CategoriaProdutorDAO {

	/** A constante COMANDO_PESQUISAR. */
	private static final String COMANDO_PESQUISAR = "";
	
	/** A constante PESQUISA_POR_STATUS. */
	private static final String PESQUISA_POR_STATUS = "PESQUISA_CATEGORIAS_POR_STATUS";

	/**
	 * Construtor do DAO.
	 */
	public CategoriaProdutorDAOImpl() {
		super(CategoriaProdutor.class, COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CategoriaProdutor> obterCategoriasPorStatus(CategoriaProdutor categoria) throws BancoobException {
		ConsultaDto<CategoriaProdutor> criterios = new ConsultaDto<CategoriaProdutor>();
		criterios.setFiltro(categoria);
		
		return pesquisarLista(getClasse(), criterios, PESQUISA_POR_STATUS);
	}
}