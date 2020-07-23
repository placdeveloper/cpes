/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;

/**
 * Fachada utilizada para a replicação dos bens e suas listas no cadastro da pessoa no legado.
 *  
 * @author Marcelo.Onofre
 *
 */
public class AtualizacaoBemAntigoFachada extends AtualizacaoCadastralFachada<Replicavel> {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEntidadeReplicavel(Replicavel entidade) {
		boolean retorno = super.isEntidadeReplicavel(entidade);
		
		if(entidade instanceof Ibem){
			Ibem iBem = (Ibem)entidade;
			Bem bemPai = iBem.getBem();
			retorno = bemPai.getIdInstituicaoAtualizacao() == null;
		}
		
		return retorno;
	}

	
}
