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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Empreendimento obterProdLab(Integer codigo, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O m�todo Incluir prod lab.
	 *
	 * @param empreendimentoReplicacao o valor de empreendimento replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void incluirProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O m�todo Alterar prod lab.
	 *
	 * @param empreendimentoReplicacao o valor de empreendimento replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void alterarProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException;

}
