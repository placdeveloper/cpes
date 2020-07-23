/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.OrigemInformacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.processamento.negocio.anotacao.estrategia.EstrategiaAtualizarAnotacoesExternas;
import br.com.sicoob.capes.processamento.negocio.anotacao.estrategia.FabricaEstrategiaAtualizarAnotacoesExternas;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.AtualizarAnotacoesConsultasExternasServicoLocal;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.AtualizarAnotacoesConsultasExternasServicoRemote;

/**
 * Servico utilizado para atualizar as anotações de consultas externas.
 * 
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ AtualizarAnotacoesConsultasExternasServicoLocal.class })
@Remote({ AtualizarAnotacoesConsultasExternasServicoRemote.class })
public class AtualizarAnotacoesConsultasExternasServicoEJB extends CAPESProcessamentoServicoEJB implements
		AtualizarAnotacoesConsultasExternasServicoRemote, AtualizarAnotacoesConsultasExternasServicoLocal {

	/**
	 * {@inheritDoc}
	 */
	public void atualizarAnotacoesConsultasExternas(AnotacaoExternaDTO dto) throws BancoobException {

		if (dto != null) {

			getLogger().info("Atualizando as anotações para: " + dto.getNumCpfCnpj());

			try {
				atualizarAnotacoes(dto);
			} catch (PessoaNaoEncontradaException e) {
				getLogger().info(dto.getNumCpfCnpj() + " não existe na instituição: " + dto.getIdInstituicao());
			} catch (RuntimeException excecao) {// NOPMD
				throw new BancoobRuntimeException(
						"Erro ao atualizar anotações para o cpf/cnpj: " + dto.getNumCpfCnpj(), excecao);
			}
		} else {
			getLogger().alerta("Anotacoes externas nao atualizadas: O DTO nao pode ser nulo");
		}
	}

	/**
	 * Atualiza as anotações da pessoa informada com os dados do retorno da consulta externa.
	 * @param dto
	 *            O resultado da consulta externa.
	 * 
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na atualização.
	 */
	private void atualizarAnotacoes(AnotacaoExternaDTO dto) throws BancoobException {

		OrigemInformacao origemInformacao = obterOrigemInformacao(dto);
		EstrategiaAtualizarAnotacoesExternas estrategia = FabricaEstrategiaAtualizarAnotacoesExternas.getInstance()
				.criarEstrategiaAtualizacaoAnotacoes(origemInformacao);
		
		getLogger().debug("Estrategia para atualizacao das anotacoes: " + estrategia);
		
		if (estrategia != null) {
			estrategia.executarAtualizacaoAnotacaoExterna(origemInformacao, dto);
		}
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private OrigemInformacao obterOrigemInformacao(AnotacaoExternaDTO dto) throws BancoobException {

		OrigemInformacaoDelegate origemInformacaoDelegate = CAPESCadastroFabricaDelegates.getInstance()
				.criarOrigemInformacaoDelegate();
		OrigemInformacao origem = new OrigemInformacao();
		origem.setNomeOrigemInfo(dto.getNomeOrgaoOrigem());
		return origemInformacaoDelegate.obterOrigemPorNome(origem);
	}
}