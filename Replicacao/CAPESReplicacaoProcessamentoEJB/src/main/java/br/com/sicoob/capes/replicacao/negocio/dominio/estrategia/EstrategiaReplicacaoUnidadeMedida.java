/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.estrategia;

import br.com.sicoob.capes.negocio.entidades.legado.UnidadeMedida;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.UnidadeMedidaDelegate;
import br.com.sicoob.capes.replicacao.negocio.enums.DominioReplicavelEnum;

/**
 * Estratégia pera replicar as unidades de medida.
 * @author Erico.Junior
 */
public class EstrategiaReplicacaoUnidadeMedida extends EstrategiaReplicacaoDominioAbstract
	<br.com.sicoob.capes.negocio.entidades.UnidadeMedida, UnidadeMedida> {

	/**
	 * Instancia um novo EstrategiaReplicacaoUnidadeMedida.
	 *
	 * @param tabela o valor de tabela
	 */
	public EstrategiaReplicacaoUnidadeMedida(DominioReplicavelEnum tabela) {
		super(tabela);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UnidadeMedida converter(
			br.com.sicoob.capes.negocio.entidades.UnidadeMedida entidadeCadastro) {
		
		UnidadeMedida unidade = new UnidadeMedida();
		unidade.setDescricao(entidadeCadastro.getDescricao());
		unidade.setId(entidadeCadastro.getCodigo());
		unidade.setSigla(entidadeCadastro.getSigla());
		return unidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UnidadeMedidaDelegate obterDelegate() {
		return CAPESReplicacaoComumFabricaDelegates.getInstance().criarUnidadeMedidaDelegate();
	}

}
