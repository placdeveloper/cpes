// 23/04/2013 - 09:57:20
package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;

/**
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoRejeitarEspecifico extends EstrategiaAutorizacaoRejeitar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Aprovavel obterDadosAprovacao(Autorizacao autorizacao) throws BancoobException {
		return SerializacaoUtils.deserializarJson(Aprovavel.class, autorizacao.getAlteracao());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirDadosAprovacao(Aprovavel aprovavel) throws BancoobException {
		
	}
}
