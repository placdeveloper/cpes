/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.UnidadeMedidaDAO;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;

/**
 * DAO para as unidades de medida.
 * @author erico.junior
 */
public class UnidadeMedidaDAOImpl extends CAPESCadastroCrudDao<UnidadeMedida>
		implements UnidadeMedidaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_UNIDADE_MEDIDA_POR_FILTRO";

	/**
	 * Instancia um novo UnidadeMedidaDAOImpl.
	 */
	public UnidadeMedidaDAOImpl() {
		super(UnidadeMedida.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<UnidadeMedida> pesquisar(
			ConsultaDto<UnidadeMedida> criterios) throws BancoobException {
		
		List<UnidadeMedida> lista = pesquisarLista(UnidadeMedida.class, criterios, NOME_COMANDO_PESQUISAR);
		criterios.setResultado(lista);
		
		return criterios;
	}
}
