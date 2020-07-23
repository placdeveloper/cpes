package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IRelacionamentoPessoaDelegate extends IAbstractCAPESApiPessoaDelegate<RelacionamentoPessoaVO> {

	/**
	 * Obter conjuges por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<RelacionamentoPessoaVO> obterConjugesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter quadro societario por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<RelacionamentoPessoaVO> obterQuadroSocietarioPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param codigoTipoRelacionamento
	 *            the codigo tipo relacionamento
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<RelacionamentoPessoaVO> obterPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Short codigoTipoRelacionamento)
			throws BancoobException;

}
