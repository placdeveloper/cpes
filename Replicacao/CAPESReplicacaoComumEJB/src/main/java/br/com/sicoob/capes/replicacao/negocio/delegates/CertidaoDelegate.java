/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.servicos.CertidaoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para as certidoes.
 * @author juan.damasceno
 */
public class CertidaoDelegate extends EntidadeReplicavelDelegate<Certidao, CertidaoServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarCertidaoServico();
	}
	
	/**
	 * Obter max sequencial por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Short
	 */
	public Short obterMaxSequencialPorPessoa(Pessoa pessoa) {
		return getServico().obterMaxSequencialPorPessoa(pessoa);
	}
}