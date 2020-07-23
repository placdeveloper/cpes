/* 
 * Sicoob
 * TipoRelacionamentoPessoaDAO.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;

/**
 * DAO para {@link TipoRelacionamentoPessoa}
 *
 * 08/08/2011
 * @author Rodrigo.Chaves
 */
public interface TipoRelacionamentoPessoaDAO extends
		CAPESCadastroCrudDominioDaoIF<TipoRelacionamentoPessoa> {

	/**
	 * Recupera lista de {@link TipoRelacionamentoPessoa} de acordo com o tipo da pessoa de
	 * relacionamento e relacionada
	 * 
	 * @see TipoPessoa
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum
	 * 
	 * @param tipoPessoaRelacionamento
	 *            o tipo da pessoa de relacionamento
	 * @param tipoPessoaRelacionada
	 *            o tipo da pessoa relacioada
	 * @return lista de {@code TipoRelacionamentoPessoa}
	 */
	List<TipoRelacionamentoPessoa> pesquisarPorTiposPessoa(
			TipoPessoa tipoPessoaRelacionamento, TipoPessoa tipoPessoaRelacionada)
			throws BancoobException;

	/**
	 * Retorna tipos relacionamentos para produtos bancoob.
	 * @return
	 * @throws BancoobException
	 */
	List<TipoRelacionamentoPessoa> pesquisarTiposRelacionamentosProdutosBancoob()
			throws BancoobException;
}
