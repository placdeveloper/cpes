/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ReplicacaoCadastroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 * 
 */
public class ReplicacaoCadastroDelegate extends
		CAPESCadastroDelegate<ReplicacaoCadastroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReplicacaoCadastroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarReplicacaoCadastroServico();
	}

	/**
	 * Inicia um relacionamento com a institui��o do usu�rio logado.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(
			PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().iniciarRelacionamentoInstituicao(pessoa);
	}
	
	/**
	 * Inicia um relacionamento com a institui��o informada.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @param numeroCooperativa
	 *            a cooperativa informada.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		return getServico().iniciarRelacionamentoInstituicao(pessoa, numeroCooperativa, unidadeInstituicao);
	}

	/**
	 * Inicia um relacionamento com o Bancoob.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public PessoaCompartilhamento iniciarRelacionamentoBancoob(
			PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().iniciarRelacionamentoBancoob(pessoa);
	}
	
	
	/**
	 * Incluir relacionamento bancoob.
	 *
	 * @param pessoa o valor de pessoa
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public PessoaCompartilhamento incluirRelacionamentoBancoob(PessoaCompartilhamento pessoa) 
			throws BancoobException {
		return getServico().incluirRelacionamentoBancoob(pessoa);
	}

	/**
	 * Inicia um relacionamento com a institui��o informada.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @param destino
	 *            A institui��o de destino.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(
			PessoaCompartilhamento pessoa, Instituicao destino)
			throws BancoobException {
		return getServico().iniciarRelacionamentoInstituicao(pessoa, destino);
	}

	/**
	 * Realiza a atualiza��o da pessoa que foi mudada de grupo de
	 * compartilhamento
	 * 
	 * @param vo
	 *            TODO
	 */
	public void atualizarPessoaAlteracaoGrupo(DadosAlteracaoGrupoVO vo)
			throws BancoobException {
		getServico().atualizarPessoaAlteracaoGrupo(vo);
	}
}
