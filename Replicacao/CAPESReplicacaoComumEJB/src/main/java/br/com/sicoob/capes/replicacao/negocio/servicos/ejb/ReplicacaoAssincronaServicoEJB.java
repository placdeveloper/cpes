package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicacaoAssincronaServicoLocal;
import br.com.sicoob.capes.replicacao.persistencia.dao.ReplicacaoCadastroDAO;

/**
 * Classe para execução da replicação assíncrona de pessoa.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Asynchronous
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Local(ReplicacaoAssincronaServicoLocal.class)
public class ReplicacaoAssincronaServicoEJB extends CAPESReplicacaoComumServicoEJB implements ReplicacaoAssincronaServicoLocal {

	@Inject
	@Default
	private ReplicacaoCadastroDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void replicarCadastroAssincrono(Integer numPessoaOrigem, Integer numCooperativaOrigem, Integer numCooperativaDestino) {
		getLogger().info("Iniciando a replicacao assincrona.");
		try {
			dao.replicarCadastroAssincrono(numPessoaOrigem, numCooperativaOrigem, numCooperativaDestino);
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao executar a replicacao assincrona: " + e.getMessage());
		}
	}

}