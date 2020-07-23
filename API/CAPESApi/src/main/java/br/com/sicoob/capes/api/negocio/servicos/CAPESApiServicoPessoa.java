/*
 * SICOOB
 * 
 * CAPESApiServicoPessoa.java(br.com.sicoob.capes.api.negocio.servicos.CAPESApiServicoPessoa)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;

/**
 * Interface que define o servico padrao do sistema CAPES-API
 * 
 * @author Lucas.Borges
 */
public interface CAPESApiServicoPessoa<K> extends CAPESApiServico {

	/**
	 * Obter por id.
	 * 
	 * @param id
	 *            the id
	 * @return k
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	K obterByID(Serializable id) throws BancoobException;
	
	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<K> obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter nao vigente por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<K> obterNaoVigenteByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
}