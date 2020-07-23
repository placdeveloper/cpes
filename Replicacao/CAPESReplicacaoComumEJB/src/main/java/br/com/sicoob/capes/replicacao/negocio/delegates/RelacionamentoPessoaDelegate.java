/* 
 * Sicoob
 * RelacionamentoPessoaDelegate.java 
 * Criado em: 28/10/2011
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.RelacionamentoPessoa;
import br.com.sicoob.capes.replicacao.negocio.servicos.RelacionamentoPessoaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para a replicação de relacionamentos.
 * 
 * 28/10/2011
 * 
 * @author Rodrigo.Chaves
 */
public class RelacionamentoPessoaDelegate extends
		EntidadeReplicavelDelegate<RelacionamentoPessoa, RelacionamentoPessoaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance()
				.localizarRelacionamentoPessoaServico();
	}

}
