package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;

/**
 * A Interface FichaCadastralDAO.
 */
public interface FichaCadastralDAO {
	
	/**
	 * Listar.
	 *
	 * @param <T> o tipo generico
	 * @param clazz o valor de clazz
	 * @param consultaDtoCUC o valor de consulta dto cuc
	 * @return List
	 */
	<T> List<T> listar(Class<T> clazz, ConsultaDtoCUC<?> consultaDtoCUC);
	
	/**
	 * Obter entidade autorizacao.
	 *
	 * @param <E> o tipo generico
	 * @param dto o valor de dto
	 * @return E
	 */
	<E> E obterEntidadeAutorizacao(ConsultaAutorizacaoDTO dto);
}
