/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;

/**
 * Dao utilizado para replica��o de pessoas.
 * 
 * @author Erico.Junior
 */
public interface PessoaDAO extends EntidadeReplicavelDaoIF<Pessoa> {
	
	/**
	 * O m�todo Atualizar assinatura foto bancoob.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void atualizarAssinaturaFotoBancoob(Integer idPessoa,
			Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * M�todo para alterar uma Pessoa e executar o flush.
	 * @param objeto o objeto a ser alterado.
	 * @parma numeroCooperativa o n�mero da cooperativa.
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o.
	 */
	void alterarComFlush(Pessoa objeto, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Lista as pessoas que possuem o cpf informado como c�njuge.
	 * @param cpfConjuge O cpf do c�njuge utilizado na consulta.
	 * @param numCooperativa O n�mero da cooperativa.
	 * @return as pessoas que possuem o cpf informado como c�njuge.
	 * @throws BancoobException
	 */
	List<PessoaFisica> listarPessoasPorCpfConjuge(String cpfConjuge, Integer numCooperativa) throws BancoobException;
	
	/**
	 * Atualiza as informa��es de conjuge
	 * @param pessoa
	 * @param numeroCooperativa
	 * @throws BancoobException
	 */
	void atualizarConjuge(Pessoa pessoa, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Altera o email da pessoa.
	 * @param pessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	void alterarEmail(Pessoa pessoa, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * 
	 * @param pessoaLegado
	 * @throws BancoobException
	 */
	void atualizaCodTipoEmpresa(PessoaJuridica pjTemp) throws BancoobException;
	
}