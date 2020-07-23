/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaIntegracaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.UnidadeInstitucionalInexistenteException;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.negocio.entidades.legado.Funcionario;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ClienteDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.CooperativaDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;

/**
 * @author erico.junior
 *
 */
public class AtualizacaoClienteFachada {

	/** O atributo cooperativaDelegate. */
	private transient CooperativaDelegate cooperativaDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarCooperativaDelegate();
	
	/** O atributo clienteDelegate. */
	private transient ClienteDelegate clienteDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarClienteDelegate();
	
	/** O atributo pessoaDelegate. */
	private transient PessoaDelegate pessoaDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate();
	
	/** O atributo pessoaIntegracaoDelegate. */
	private transient PessoaIntegracaoDelegate pessoaIntegracaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate();
	
	/** O atributo transicaoPessoaDelegate. */
	private transient TransicaoPessoaDelegate transicaoPessoaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
	
	/** O atributo sciDelegate. */
	private SCIIntegracaoDelegate  sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
	
	/**
	 * O método Replicar dados cliente.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param atualizarRisco o valor de atualizar risco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void replicarDadosCliente(PessoaInstituicao pessoaInstituicao, boolean atualizarRisco) 
			throws BancoobException {
		
		Integer idInstituicao = pessoaInstituicao.getIdInstituicao();
		
		TransicaoPessoa transicaoPessoa = transicaoPessoaDelegate.obterTransicaoPorPessoaInstituicao(pessoaInstituicao.getPessoa(), idInstituicao);
		
		Cliente clientePersistente = clienteDelegate.obter(transicaoPessoa.getIdPessoaLegado(),
				idInstituicao);
		Cliente cliente = criarCliente(clientePersistente, pessoaInstituicao, transicaoPessoa,
				atualizarRisco);
		
		if (clientePersistente == null) {
			clienteDelegate.incluir(cliente, idInstituicao);
		} else { 
			clienteDelegate.alterar(cliente, idInstituicao);
		}
	}	
	
	/**
	 * O método Replicar dados cliente em Lote.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param atualizarRisco o valor de atualizar risco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void replicarDadosClienteLote(PessoaInstituicao pessoaInstituicao, boolean atualizarRisco) 
			throws BancoobException {
		
		Integer idInstituicao = pessoaInstituicao.getIdInstituicao();
		
		TransicaoPessoa transicaoPessoa = transicaoPessoaDelegate.obterTransicaoPorPessoaInstituicao(pessoaInstituicao.getPessoa(), idInstituicao);
		
		Cliente clientePersistente = clienteDelegate.obter(transicaoPessoa.getIdPessoaLegado(),
				idInstituicao);
		Cliente cliente = criarCliente(clientePersistente, pessoaInstituicao, transicaoPessoa,
				atualizarRisco);
		
		if (clientePersistente == null) {
			clienteDelegate.incluir(cliente, idInstituicao);
		} else { 
			clienteDelegate.alterar(cliente, idInstituicao);
		}
	}	
	
	/**
	 * O método Replicar tributacao.
	 *
	 * @param tributacao o valor de tributacao
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void replicarTributacao(Tributacao tributacao, Integer idInstituicao)
			throws BancoobException {
		
		PessoaPlataformaVO pessoaProxy = obterPessoaProxy(tributacao.getPessoaCompartilhamento()
				.getPessoa(), idInstituicao);
		Cliente cliente = clienteDelegate.obter(pessoaProxy.getIdPessoaLegado(), idInstituicao);
		cliente.setCobrarCPMF(tributacao.getCobrarCPMF());
		cliente.setCobrarIOF(tributacao.getCobrarIOF());
		cliente.setCobrarIR(tributacao.getCobrarIR());
		clienteDelegate.alterar(cliente, idInstituicao);
	}
	
	/**
	 * Criar cliente.
	 *
	 * @param existente o valor de existente
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param transicaoPessoa o valor de transicao pessoa
	 * @param atualizarRisco o valor de atualizar risco
	 * @return Cliente
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Cliente criarCliente(Cliente existente, PessoaInstituicao pessoaInstituicao,
			TransicaoPessoa transicaoPessoa, boolean atualizarRisco) throws BancoobException {
		
		Date dataAtual = DataUtil.obterDataAtual();
		Integer idInstituicao = pessoaInstituicao.getIdInstituicao();
		
		// Replicação
		
		br.com.sicoob.capes.negocio.entidades.legado.Pessoa pessoaLegado = 
				pessoaDelegate.obter(transicaoPessoa.getIdPessoaLegado(), idInstituicao);
		
		Cliente cliente = existente;
		if (existente == null) {
			cliente = new Cliente();
			cliente.setDataCadastramentoCliente(dataAtual);
		}

		cliente.setIdCliente(transicaoPessoa.getIdPessoaLegado());
		cliente.setPessoa(pessoaLegado);
		cliente.setParecerGerencia(pessoaInstituicao.getParecerGerencia());
		cliente.setEmiteAvisoLancamento(pessoaInstituicao.getEmiteAvisoLancamento());
		cliente.setDataUltimaAtualizacao(dataAtual);
		
		Cooperativa cooperativa = recuperarCooperativa(idInstituicao,
				pessoaInstituicao.getIdUnidadeInst());
		cliente.setNumCooperativa(cooperativa.getId().getNumCooperativa());
		cliente.setNumPac(cooperativa.getId().getNumPac());
		
		if(pessoaInstituicao.getNucleo() != null) {
			cliente.setNumNucleo(pessoaInstituicao.getNucleo().getNumNucleo());
		}

		Funcionario funcionario = recuperarFuncionarioLegado(pessoaInstituicao, idInstituicao);
		cliente.setFuncionario(funcionario);
		cliente.setIdPerfilTarifario(null);

		if (pessoaInstituicao.getPerfilTarifario() != null) {
			cliente.setIdPerfilTarifario(pessoaInstituicao.getPerfilTarifario().getId().getCodPerfilTarifario());
		}

		if (atualizarRisco) {
			cliente.setDataRisco(pessoaInstituicao.getDataEnquadramentoRisco());
			cliente.setIdNivelRisco(pessoaInstituicao.getNivelRisco());
			cliente.setMotivoRisco(pessoaInstituicao.getMotivoRisco());
		}
		
		cliente.setDataCadastramentoSFN(transicaoPessoa.getPessoaCompartilhamento().getDataInclusaoSFN());
		cliente.setAutorizaConsulta(transicaoPessoa.getPessoaCompartilhamento().getAutorizaConsultaBacen());
		
		return cliente;
	}
	
	/**
	 * Recuperar cooperativa.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param idUnidadeInst o valor de id unidade inst
	 * @return Cooperativa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Cooperativa recuperarCooperativa(Integer idInstituicao, Integer idUnidadeInst)
			throws BancoobException {
		
		Cooperativa cooperativa = null;
		ConsultaDto<Cooperativa> dto = new ConsultaDto<Cooperativa>();
		Cooperativa filtro = new Cooperativa();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setIdUnidadeInst(idUnidadeInst);
		dto.setFiltro(filtro);
		List<Cooperativa> cooperativas = cooperativaDelegate.listar(dto, idInstituicao);
		if ((cooperativas != null) && !cooperativas.isEmpty()) {
			cooperativa = cooperativas.get(0);
		}
		
		if(cooperativa == null) {
			throw new UnidadeInstitucionalInexistenteException();
		}
		
		return cooperativa;
	}
	
	/**
	 * Recuperar funcionario legado.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param idInstituicao o valor de id instituicao
	 * @return Funcionario
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Funcionario recuperarFuncionarioLegado(PessoaInstituicao pessoaInstituicao, Integer idInstituicao) 
			throws BancoobException {
		
		Funcionario funcionario = null;
		
		if (pessoaInstituicao.getFuncionario() != null) {
			
			PessoaPlataformaVO proxyFuncionario = obterPessoaProxy(
					pessoaInstituicao.getFuncionario().getPessoa(), idInstituicao);
					
			CAPESReplicacaoComumFabricaDelegates fabrica = CAPESReplicacaoComumFabricaDelegates.getInstance();			
			FuncionarioDelegate funcionarioDelegate = fabrica.criarFuncionarioDelegate();
			funcionario = funcionarioDelegate.obter(proxyFuncionario.getIdPessoaLegado(), idInstituicao);
		}
		
		return funcionario;
	}	

	/**
	 * Obter pessoa proxy.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaPlataformaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaPlataformaVO obterPessoaProxy(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		return pessoaIntegracaoDelegate.obterPessoaPlataforma(pessoa.getId(), idInstituicao);
	}

	/**
	 * O método Replicar dados cliente em Lote.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param atualizarRisco o valor de atualizar risco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void replicarClienteLegadoLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades,	boolean atualizarRisco)  throws BancoobException{
		List<Cliente> clientesIncluir = new ArrayList<Cliente>();
		List<Cliente> clientesAlterar = new ArrayList<Cliente>();
		Integer idInstituicao = null;
		for (int i = 0; i < pesquisaEntidades.getResultado().size(); i++) {
			PessoaInstituicao pessoaInstituicao = (PessoaInstituicao) pesquisaEntidades.getResultado().get(i);
			idInstituicao = pessoaInstituicao.getIdInstituicao();

			TransicaoPessoa transicaoPessoa = transicaoPessoaDelegate.obterTransicaoPorPessoaInstituicao(pessoaInstituicao.getPessoa(), idInstituicao);
			Cliente clientePersistente = clienteDelegate.obter(transicaoPessoa.getIdPessoaLegado(),	idInstituicao);
			Cliente cliente = criarCliente(clientePersistente, pessoaInstituicao, transicaoPessoa,atualizarRisco);
			
			if (clientePersistente == null) {
				clientesIncluir.add(cliente);
			}else {
				clientesAlterar.add(cliente);
			}

		}
		clienteDelegate.replicarClienteLegadoLote(clientesIncluir, clientesAlterar, sciDelegate.obterNumeroCooperativa(idInstituicao));
	}
}
