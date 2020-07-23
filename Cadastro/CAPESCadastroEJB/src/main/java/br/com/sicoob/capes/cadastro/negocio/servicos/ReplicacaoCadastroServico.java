/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 * 
 */
public interface ReplicacaoCadastroServico extends CAPESCadastroServico {

	/**
	 * Inicia um relacionamento com a instituição do usuário logado.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Inicia um relacionamento com a instituição informada.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @param numeroCooperativa
	 *            a cooperativa informada.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException;

	/**
	 * Inicia um relacionamento com o Bancoob.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	PessoaCompartilhamento iniciarRelacionamentoBancoob(PessoaCompartilhamento pessoa) throws BancoobException;
	
	
    /**
     * @param pessoa
     * @return
     * @throws BancoobException
     */
    PessoaCompartilhamento incluirRelacionamentoBancoob(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Inicia um relacionamento com a instituição informada.
	 * 
	 * @param pessoa
	 *            A pessoa para o relacionamento.
	 * @param destino
	 * 			  A instituição de destino.
	 * @return A pessoa com o novo relacionamento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa, Instituicao destino)
			throws BancoobException;

	/**
	 * Realiza a atualização da pessoa que foi mudada de grupo de compartilhamento
	 * @param vo TODO
	 * @param idPessoaOrigem
	 *            o ID da pessoa de origem (no legado)
	 * @param idPessoaDestino
	 *            o ID da pessoa de destino (no legado)
	 * @param idInstituicaoOrigem
	 *            o ID da instituição de origem
	 * @param idInstituicaoDestino
	 *            o ID da instituição de destino
	 */
	void atualizarPessoaAlteracaoGrupo(DadosAlteracaoGrupoVO vo) throws BancoobException;
}
