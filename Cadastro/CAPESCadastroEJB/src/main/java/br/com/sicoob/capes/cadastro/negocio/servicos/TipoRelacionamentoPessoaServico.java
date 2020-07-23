/* 
 * Sicoob
 * TipoRelacionamentoPessoaServico.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
public interface TipoRelacionamentoPessoaServico extends
		CAPESCadastroCrudDominioServico<TipoRelacionamentoPessoa> {

	/**
	 * Altera o {@code objeto} atualizando o {@code reverso} quando o
	 * {@link TipoRelacionamentoPessoa#getRelacionamentoReverso()} foi alterado no {@code objeto}
	 * 
	 * @param objeto
	 *            o objeto a ser alterado
	 * @param reversoOriginal
	 *            o reverso do {@code objeto} antes da alteração
	 */
	void alterar(TipoRelacionamentoPessoa objeto, TipoRelacionamentoPessoa reversoOriginal)
			throws BancoobException;
	
	/**
	 * Recupera lista de {@link TipoRelacionamentoPessoa} de acordo com o tipo da pessoa de
	 * relacionamento e relacionada
	 * 
	 * @see TipoPessoa
	 * @see TipoPessoaEnum
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
