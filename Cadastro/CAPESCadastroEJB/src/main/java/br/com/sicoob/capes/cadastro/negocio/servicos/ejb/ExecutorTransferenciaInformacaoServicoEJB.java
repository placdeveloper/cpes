package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ExecutorTransferenciaInformacaoServicoLocal;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe que executa a transferência de informações de forma assíncrona.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(ExecutorTransferenciaInformacaoServicoLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ExecutorTransferenciaInformacaoServicoEJB extends CAPESCadastroServicoEJB implements ExecutorTransferenciaInformacaoServicoLocal {

	@EJB
	private ExecutorTransferenciaInformacaoServicoLocal servicoLocal;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Asynchronous
	public void processar(TransfInformacoesDTO dto) {
		try {
			System.out.println("O TRANSFERENCIA INICIO TESTE: " + new DateTime());
			getLogger().info("ExecutorTransferenciaInformacao: Transferencia de Informações iniciada.");

			ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades = consultarEntidades(dto);
			getLogger().info("ExecutorTransferenciaInformacao: Foram encontrados ", String.valueOf(pesquisaEntidades.getResultado() != null ? pesquisaEntidades.getResultado().size() : 0), " registros para a transferência.");

			servicoLocal.executarTransferenciaLote(pesquisaEntidades, dto);

			getLogger().info("ExecutorTransferenciaInformacao: Transferencia de Informações finalizada.");
			System.out.println("O TRANSFERENCIA FIM TESTE: " + new DateTime());
		} catch (BancoobException e) {
			getLogger().erro(e, "ExecutorTransferenciaInformacao: Erro ao executar a transferencia das informacoes.");
			throw new BancoobRuntimeException(e);
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "ExecutorTransferenciaInformacao: Erro ao executar a transferencia das informacoes.");
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Consulta as entidades para transferência de informações.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	private ConsultaDto<? extends CAPESEntidade<?>> consultarEntidades(TransfInformacoesDTO dto) throws BancoobException {
		ConsultaDto<? extends CAPESEntidade<?>> retorno = null;
		CAPESCadastroFabricaDelegates fabricaDelegates = CAPESCadastroFabricaDelegates.getInstance();
		GrupoEconomicoPessoaDelegate delegateGrupoEconomicoPessoaDelegate = fabricaDelegates.criarGrupoEconomicoPessoaDelegate();
		PessoaInstituicaoDelegate delegatePessoaInstituicao = fabricaDelegates.criarPessoaInstituicaoDelegate();
		if (dto.isPessoaInstituicao()) {
			retorno = delegatePessoaInstituicao.pesquisarIdPessoaInstituicao((ConsultaDto<PessoaInstituicao>) dto.getCriterios());
		} else if (dto.getBuscaPorCpfCnpj()) {
			retorno = delegatePessoaInstituicao.pesquisarIdPessoaInstituicaoByCpfCnpj(dto.getListaCpfCnpj(), dto.getIdInstituicaoUsuarioLogado());
		} else {
			retorno = delegateGrupoEconomicoPessoaDelegate.pesquisar((ConsultaDto<GrupoEconomicoPessoa>) dto.getCriterios());
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void executarTransferencia(CAPESEntidade<?> entidade, TransfInformacoesDTO dto) {
		try {
			CAPESCadastroFabricaDelegates fabricaDelegates = CAPESCadastroFabricaDelegates.getInstance();
			if (dto.isPessoaInstituicao() || dto.getBuscaPorCpfCnpj()) {
				getLogger().info("ExecutorTransferenciaInformacao: Atualizacao de PessoaInstituicao iniciada.");
				fabricaDelegates.criarPessoaInstituicaoDelegate().atualizarInformacoes((PessoaInstituicao) entidade, dto.getIdUnidadeInst(), dto.getGerente(), dto.getNucleo(), dto.getIdUsuarioLogado());
			} else {
				getLogger().info("ExecutorTransferenciaInformacao: Atualizacao de GrupoEconomico iniciada.");
				fabricaDelegates.criarGrupoEconomicoPessoaDelegate().atualizarGrupo((GrupoEconomicoPessoa) entidade, dto.getGrupo(), dto.getIdUsuarioLogado());
			}
		} catch (NegocioException e) {
			getLogger().info("ExecutorTransferenciaInformacao: Erro ao atualizar registro na transferencia de informacoes: " + e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "ExecutorTransferenciaInformacao: Erro ao atualizar registro na transferencia de informacoes.");
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "ExecutorTransferenciaInformacao: Erro ao atualizar registro na transferencia de informacoes.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void executarTransferenciaLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, TransfInformacoesDTO dto) {
		try {
			CAPESCadastroFabricaDelegates fabricaDelegates = CAPESCadastroFabricaDelegates.getInstance();
			if (dto.isPessoaInstituicao() || dto.getBuscaPorCpfCnpj()) {
				getLogger().info("ExecutorTransferenciaInformacao: Atualizacao de PessoaInstituicao iniciada.");
				fabricaDelegates.criarPessoaInstituicaoDelegate().atualizarInformacoesLote(pesquisaEntidades, dto);
			} else {
				//DEFINIR SE VAI SER IMPLEMENTADO
				getLogger().info("ExecutorTransferenciaInformacao: Atualizacao de GrupoEconomico iniciada.");
				//fabricaDelegates.criarGrupoEconomicoPessoaDelegate().atualizarGrupoLote(pesquisaEntidades, dto);
			}
		} catch (NegocioException e) {
			getLogger().info("ExecutorTransferenciaInformacao: Erro ao atualizar registro na transferencia de informacoes: " + e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "ExecutorTransferenciaInformacao: Erro ao atualizar registro na transferencia de informacoes.");
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "ExecutorTransferenciaInformacao: Erro ao atualizar registro na transferencia de informacoes.");
		}
		
	}
}