/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.util.ReenvioMensagemJMSReplicacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.VerificarDLQReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.VerificarDLQReplicacaoCadastroServicoRemote;

/**
 * Servi�o utilizado para verificar a DLQ de replica��o do cadastr e reenviar
 * para a fila a mensagem encontrada caso a atualiza��o ainda precise ser feita.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { VerificarDLQReplicacaoCadastroServicoLocal.class })
@Remote( { VerificarDLQReplicacaoCadastroServicoRemote.class })
public class VerificarDLQReplicacaoCadastroServicoEJB< 
		E extends CAPESEntidade<?> & Replicavel & Vigente>
		extends CAPESServicoReplicacaoServicoEJB 
		implements VerificarDLQReplicacaoCadastroServicoRemote, VerificarDLQReplicacaoCadastroServicoLocal {

	/** F�brica de conex�es usada. */
	@Resource(mappedName = "WSMQCAPESQueueConnectionFactory")
	private javax.jms.QueueConnectionFactory fabricaConexoes;

	/** Fila de atualiza��o de canais. */
	@Resource(mappedName = "queue/QL.CONT.EXEC.ATUALIZA.CADASTRO.DLQ")
	private javax.jms.Queue dlqAtualizaCadastro;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void processarMensagens() throws BancoobException {

		getLogger().info("Verificando DLQ de replica��o do cadastro");
		
		ReenvioMensagemJMSReplicacao<E> reenvio = new ReenvioMensagemJMSReplicacao<E>();
		reenvio.processarMensagens(fabricaConexoes, dlqAtualizaCadastro);
		
	}

}
