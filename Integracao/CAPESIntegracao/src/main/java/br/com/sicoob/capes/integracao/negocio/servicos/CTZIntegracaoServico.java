package br.com.sicoob.capes.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Interface com os serviços de integração com o sistema 'CARTEIRIZAÇÃO'.
 * 
 * @author bruno.carneiro
 */
public interface CTZIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Atualiza a carteirização.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idUnidadeInstituicao
	 * @param codigoTipoPessoa
	 * @throws BancoobException
	 */
	void atualizarCarteirizacao(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInstituicao, Integer idGerente, Short codigoTipoPessoa) throws BancoobException;

}