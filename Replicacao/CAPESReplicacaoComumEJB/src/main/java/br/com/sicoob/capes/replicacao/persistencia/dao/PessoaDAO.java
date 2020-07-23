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
 * Dao utilizado para replicação de pessoas.
 * 
 * @author Erico.Junior
 */
public interface PessoaDAO extends EntidadeReplicavelDaoIF<Pessoa> {
	
	/**
	 * O método Atualizar assinatura foto bancoob.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void atualizarAssinaturaFotoBancoob(Integer idPessoa,
			Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Método para alterar uma Pessoa e executar o flush.
	 * @param objeto o objeto a ser alterado.
	 * @parma numeroCooperativa o número da cooperativa.
	 * @throws BancoobException Caso ocorra alguma exceção na alteração.
	 */
	void alterarComFlush(Pessoa objeto, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Lista as pessoas que possuem o cpf informado como cônjuge.
	 * @param cpfConjuge O cpf do cônjuge utilizado na consulta.
	 * @param numCooperativa O número da cooperativa.
	 * @return as pessoas que possuem o cpf informado como cônjuge.
	 * @throws BancoobException
	 */
	List<PessoaFisica> listarPessoasPorCpfConjuge(String cpfConjuge, Integer numCooperativa) throws BancoobException;
	
	/**
	 * Atualiza as informações de conjuge
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