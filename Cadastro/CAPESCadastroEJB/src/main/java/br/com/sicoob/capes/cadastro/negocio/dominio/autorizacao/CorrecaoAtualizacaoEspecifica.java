// 12/03/2013 - 08:54:58
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class CorrecaoAtualizacaoEspecifica extends CorrecaoAtualizacaoGenerica {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <A extends CAPESEntidade<Long> & Aprovavel> void
			corrigirAtualizacao(Autorizacao autorizacao, A aprovavel) throws BancoobException {

		AutorizacaoDelegate autorizacaoDelegate = fabricaComum.criarAutorizacaoDelegate();
		autorizacao.setAlteracao(SerializacaoUtils.serilizarAprovavel(aprovavel,
				autorizacao.getTipoOperacao()));
		autorizacaoDelegate.alterar(autorizacao);
	}

}
