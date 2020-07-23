/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Empreendimento;

/**
 * @author Erico.Junior
 *
 */
public interface EmpreendimentoServico extends CAPESReplicacaoComumCrudServico<Empreendimento> {

	/**
	 * Obter prod lab.
	 *
	 * @param codigo o valor de codigo
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @return Empreendimento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Empreendimento obterProdLab(Integer codigo, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O método Incluir prod lab.
	 *
	 * @param empreendimentoReplicacao o valor de empreendimento replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluirProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O método Alterar prod lab.
	 *
	 * @param empreendimentoReplicacao o valor de empreendimento replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void alterarProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException;

}
