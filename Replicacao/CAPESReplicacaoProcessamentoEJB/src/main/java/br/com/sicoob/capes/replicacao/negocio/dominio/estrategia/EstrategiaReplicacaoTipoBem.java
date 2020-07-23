/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.estrategia;

import br.com.sicoob.capes.negocio.entidades.legado.TipoBem;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.TipoBemDelegate;
import br.com.sicoob.capes.replicacao.negocio.enums.DominioReplicavelEnum;

/**
 * Delegate para os tipos de bens.
 * @author erico.junior
 */
public class EstrategiaReplicacaoTipoBem extends EstrategiaReplicacaoDominioAbstract<
		br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem, TipoBem> {

	/**
	 * Instancia um novo EstrategiaReplicacaoTipoBem.
	 *
	 * @param tabela o valor de tabela
	 */
	public EstrategiaReplicacaoTipoBem(DominioReplicavelEnum tabela) {
		super(tabela);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoBem converter(
			br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem entidadeCadastro) {
		TipoBem tipo = new TipoBem();
		tipo.setDescricao(entidadeCadastro.getDescricao());
		tipo.setId(entidadeCadastro.getId());
		tipo.setGrupo(null); // TODO Verificar 
		return tipo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoBemDelegate obterDelegate() {
		return CAPESReplicacaoComumFabricaDelegates.getInstance().criarTipoBemDelegate();
	}

}
