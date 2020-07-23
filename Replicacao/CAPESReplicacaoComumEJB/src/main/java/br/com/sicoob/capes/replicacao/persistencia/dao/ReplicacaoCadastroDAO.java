/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;

/**
 * DAO para a replicação do cadastro de uma pessoa.
 * 
 * @author Erico.Junior
 */
public interface ReplicacaoCadastroDAO {

	/**
	 * Executa a SP que faz a cópia do cadastro de uma pessoa de uma cooperativa
	 * para outra.
	 * 
	 * @param replicacao
	 *            Os dados da pessoa que terá o cadastro replicado.
	 * @param numCooperativa
	 *            O número da cooperativa para onde o cadastro será replicado.
	 * @return O identificador da pessoa na nova cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	Integer replicarCadastro(Integer numPessoaOrigem, Integer numCooperativaOrigem, 
			Integer numCooperativaDestino) throws BancoobException;
	
	void replicarCadastroAssincrono(Integer numPessoaOrigem, Integer numCooperativaOrigem, Integer numCooperativaDestino) throws BancoobException;

	/**
	 * Executa a SP que faz a atualização da pessoa que foi mudada de grupo de compartilhamento
	 * 
	 * @param idPessoaOrigem
	 *            o ID da pessoa de origem (no legado)
	 * @param idPessoaDestino
	 *            o ID da pessoa de destino (no legado)
	 * @param numeroCoopOrigem
	 *            o número da cooperativa de origem
	 * @param numeroCoopDestino
	 *            o número da cooperativa de destino
	 */
	void atualizarPessoaAlteracaoGrupo(Integer idPessoaOrigem, Integer idPessoaDestino,
			Integer numeroCoopOrigem, Integer numeroCoopDestino) throws BancoobException;
}
