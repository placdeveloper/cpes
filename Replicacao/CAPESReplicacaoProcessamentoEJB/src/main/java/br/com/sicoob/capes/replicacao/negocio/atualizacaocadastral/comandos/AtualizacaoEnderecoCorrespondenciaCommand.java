/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;


/**
 * Command utilizado para atualização de endereço de correspondência.
 * @author Erico.Junior
 *
 */
public class AtualizacaoEnderecoCorrespondenciaCommand extends
		AtualizacaoCadastralCommandAbstract<Endereco> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doExecute(Endereco endereco, Instituicao instituicao,
			EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate) throws BancoobException {

		EnderecoDelegate enderecoDelegate = 
				CAPESReplicacaoComumFabricaDelegates.getInstance().criarEnderecoDelegate();
		Integer idInstituicao = instituicao.getIdInstituicao();
		getLogger().info("Definindo novo endereço de correspondência na instituição: " 
				+ idInstituicao);
		
		enderecoDelegate.tornarPadraoCorrespondencia(endereco, idInstituicao);
	}
	
}