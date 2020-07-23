package br.com.sicoob.capes.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Interface com os servi�os de integra��o com o sistema 'CARTEIRIZA��O'.
 * 
 * @author bruno.carneiro
 */
public interface CTZIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Atualiza a carteiriza��o.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idUnidadeInstituicao
	 * @param codigoTipoPessoa
	 * @throws BancoobException
	 */
	void atualizarCarteirizacao(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInstituicao, Integer idGerente, Short codigoTipoPessoa) throws BancoobException;

}