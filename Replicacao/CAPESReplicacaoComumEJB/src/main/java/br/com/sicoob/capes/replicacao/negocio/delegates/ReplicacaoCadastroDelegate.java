/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.dto.ReplicacaoCadastroDTO;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoCadastroServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate utilizada para a replicação do cadastro de uma pessoa.
 * 
 * @author Erico.Junior
 */
public class ReplicacaoCadastroDelegate extends
		CAPESReplicacaoComumDelegate<ReplicacaoCadastroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReplicacaoCadastroServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance()
				.localizarReplicacaoCadastroServico();
	}

	/**
	 * Executa a SP que faz a cópia do cadastro de uma pessoa de uma cooperativa
	 * para outra.
	 * 
	 * @param replicacao
	 *            Os dados da pessoa que terá o cadastro replicado.
	 * @return O identificador da pessoa na nova cooperativa.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public Integer replicarCadastro(ReplicacaoCadastroDTO replicacao)
			throws BancoobException {
		return getServico().replicarCadastro(replicacao);
	}
	
	/**
	 * Executa a SP que faz a cópia do cadastro de uma pessoa de uma cooperativa
	 * para outra.
	 * 
	 * @param replicacao
	 *            Os dados da pessoa que terá o cadastro replicado.
	 * @param idInstituicaoDestino
	 *            O identificador da instituição para onde o cadastro será replicado.
	 * @return O identificador da pessoa na nova cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public Integer replicarCadastro(ReplicacaoCadastroDTO replicacao, Integer idInstituicaoDestino)
			throws BancoobException {
		return getServico().replicarCadastro(replicacao, idInstituicaoDestino);
	}

	/**
	 * Realiza a atualização da pessoa que foi mudada de grupo de compartilhamento
	 * 
	 * @param idPessoaOrigem
	 *            o ID da pessoa de origem (no legado)
	 * @param idPessoaDestino
	 *            o ID da pessoa de destino (no legado)
	 * @param idInstituicaoOrigem
	 *            o ID da instituição de origem
	 * @param idInstituicaoDestino
	 *            o ID da instituição de destino
	 */
	public void atualizarPessoaAlteracaoGrupo(Integer idPessoaOrigem, Integer idPessoaDestino,
			Integer idInstituicaoOrigem, Integer idInstituicaoDestino) throws BancoobException {
		getServico().atualizarPessoaAlteracaoGrupo(idPessoaOrigem, idPessoaDestino,
				idInstituicaoOrigem, idInstituicaoDestino);
	}

}
