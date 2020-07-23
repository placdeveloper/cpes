package br.com.sicoob.capes.api.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author rodrigo.chaves
 * @since 1.2.0.x
 * @param <V>
 *            o tipo do objeto a ser retornado pelos metodos genericos
 */
public interface CAPESApiDaoIF<V> extends CAPESApiDao {

	/**
	 * Recupera um registro utilizando a consulta e o ID recebido
	 * 
	 * @param id
	 *            o identificador do objeto a ser recuperado
	 * @return o objeto com o ID correpondente ou {@code null}, se nao for localizado
	 */
	V obterPorId(Serializable id) throws BancoobException;

	/**
	 * Obtem uma lista de registros "NAO VIGENTES", pelo ID da pessoa e da Insituicao
	 * 
	 * @param idPessoa
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da institucao
	 * @return uma lista com o(s) registro(s) localizado(s)
	 */ 
	List<V> obterNaoVigentePorPessoaInstituicao(Integer idPessoa, Integer idInstituicao)
	        throws BancoobException;

	/**
	 * Obtem o registro pelo ID da pessoa e da Insituicao
	 * 
	 * @param idPessoa
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da institucao
	 * @return o registro localizado ou {@code null} caso nao seja localizado
	 */
	V obterPorPessoaInstituicaoUnico(Integer idPessoa, Integer idInstituicao)
	        throws BancoobException;

	/**
	 * Obtem uma lista de registros pelo ID da pessoa e da Insituicao
	 * 
	 * @param idPessoa
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da institucao
	 * @return uma lista com o(s) registro(s) localizado(s)
	 */
	List<V> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao)
	        throws BancoobException;

}