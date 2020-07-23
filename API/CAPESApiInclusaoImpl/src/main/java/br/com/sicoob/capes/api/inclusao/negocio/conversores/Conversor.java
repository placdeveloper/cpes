package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A Interface para os conversores de entidades/DTO's.
 * 
 * @param <E>
 *            a entidade usada para a conversão
 * @param <D>
 *            o DTO usado para a conversao
 * @author bruno.carneiro
 */
public interface Conversor<E extends CAPESEntidade<?>, D extends RegistroInclusaoDTO<?>> {

	/**
	 * Obter entidade.
	 * 
	 * @param dto
	 *            o valor de dto
	 * @return E
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	E obterEntidade(D dto) throws BancoobException;

	/**
	 * Obter dto.
	 * 
	 * @param entidade
	 *            o valor de entidade
	 * @return D
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	D obterDTO(E entidade) throws BancoobException;

}