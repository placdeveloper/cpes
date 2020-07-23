package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;

public interface PessoaLegadoDAO extends CAPESApiLegadoDao {
	
	/**
	 * Obtém a assinatura da pessoa
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	byte[] obterAssinatura(Integer idPessoaLegado, Integer numeroCooperativa) throws BancoobException;

	/**
	 * Obtém a imagem da pessoa
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	byte[] obterImagem(Integer idPessoaLegado, Integer numeroCooperativa) throws BancoobException;

}