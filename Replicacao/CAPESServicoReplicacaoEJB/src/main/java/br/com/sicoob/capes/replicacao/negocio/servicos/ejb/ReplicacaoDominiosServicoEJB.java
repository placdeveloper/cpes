/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicacaoDominiosServicoRemote;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicacaoDominiosServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.dominio.estrategia.EstrategiaReplicacaoDominio;
import br.com.sicoob.capes.replicacao.negocio.dominio.fabrica.FabricaEstrategiaReplicacaoDominio;
import br.com.sicoob.capes.replicacao.negocio.enums.DominioReplicavelEnum;

/**
 * Seriço utilizado para replicação no legado dos domínios do cadastro único.
 * @author Erico.Junior
 */
@Stateless
@Local( { ReplicacaoDominiosServicoLocal.class })
@Remote( { ReplicacaoDominiosServicoRemote.class })
public class ReplicacaoDominiosServicoEJB extends CAPESServicoReplicacaoServicoEJB
		implements ReplicacaoDominiosServicoRemote, ReplicacaoDominiosServicoLocal {

	
	/**
	 * {@inheritDoc}
	 */
	public void replicar() throws BancoobException {

		DominioReplicavelEnum[] tabelas = DominioReplicavelEnum.values();
		
		FabricaEstrategiaReplicacaoDominio fabrica = 
				FabricaEstrategiaReplicacaoDominio.getInstance();
		EstrategiaReplicacaoDominio estrategia = null;
		
		for (DominioReplicavelEnum dominioReplicavel : tabelas) {

			estrategia = fabrica.criarEstrategiaReplicacaoDominio(dominioReplicavel);
			if(estrategia != null) {
				estrategia.replicar();	
			}
		}
	}
	
}
