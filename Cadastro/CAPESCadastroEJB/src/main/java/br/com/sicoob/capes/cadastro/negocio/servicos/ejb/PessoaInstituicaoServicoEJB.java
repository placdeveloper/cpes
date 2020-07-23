/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TributacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralErroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoClienteFachada;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.NivelRiscoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaComErrosCadastraisRestritivosException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaInstituicaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaClienteVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.PessoaInstituicaoDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.comum.negocio.vo.RiscoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.CRLIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ClienteDelegate;

/**
 * Implementa as operações do serviço Pessoa Instituição.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local({ PessoaInstituicaoServicoLocal.class })
@Remote({ PessoaInstituicaoServicoRemote.class })
public class PessoaInstituicaoServicoEJB extends CAPESCadastroCrudServicoEJB<PessoaInstituicao> implements PessoaInstituicaoServicoRemote,
		PessoaInstituicaoServicoLocal {

	/** A constante ID_PARAMETRO_PILOTO_COP. */
	private static final Integer ID_PARAMETRO_PILOTO_COP = 1603;

	@Inject
	@Default
	protected PessoaInstituicaoDAO pessoaInstituicaoDAO;

	/** O atributo pessoaCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaCompartilhamentoServico")
	private PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;

	/** O atributo grupoCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/GrupoCompartilhamentoServico")
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico;

	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TransicaoPessoaServico")
	private TransicaoPessoaServicoLocal transicaoPessoaServico;
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	protected PessoaInstituicaoDAO getDAO() {
		return pessoaInstituicaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaInstituicao obterPorPessoaInstituicaoSemValidacao(PessoaInstituicao pessoaInstituicaoFiltro) throws RegistroNaoEncontradoNegocioException {
		PessoaInstituicao pessoaInstituicao = null;

		try {
			pessoaInstituicao = getDAO().obterPorPessoaInstituicao(pessoaInstituicaoFiltro);
			pessoaInstituicao.getGruposEconomicos().size();
		} catch (RegistroNaoEncontradoException e) {
		}

		return pessoaInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaInstituicao obterPorPessoaInstituicao(PessoaInstituicao pessoaInstituicaoFiltro) throws RegistroNaoEncontradoNegocioException {
		PessoaInstituicao pessoaInstituicao = null;

		try {
			pessoaInstituicao = getDAO().obterPorPessoaInstituicao(pessoaInstituicaoFiltro);
		} catch (RegistroNaoEncontradoException e) {
			throw new RegistroNaoEncontradoNegocioException(e, "pessoa instituicao");
		}

		return pessoaInstituicao;
	}

	/*
	 * Este método passa por aprovação.
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaInstituicao incluir(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		return incluirPessoaInstituicao(pessoaInstituicao, obterInstituicaoUsuarioLogado(), false);
	}

	/*
	 * Este método não passa por aprovação.
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaInstituicao incluir(PessoaInstituicao pessoaInstituicao, Instituicao instituicao, boolean produtoBancoob) throws BancoobException {
		return incluirPessoaInstituicao(pessoaInstituicao, instituicao, produtoBancoob);
	}
	
	/**
	 * Alterar.
	 * 
	 * @param objeto
	 * @param notificarCTZ
	 * @throws BancoobException
	 */
	@Override
	public void alterar(PessoaInstituicao objeto) throws BancoobException {
		super.alterar(objeto);
		replicarClienteLegado(objeto, false);
		atualizarTransicao(objeto);
		
		if (InformacoesUsuarioCAPES.getInstance() != null) {
			renovarCadastroAutomatico(objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarInformacoes(PessoaInstituicao pessoaInstituicao, Integer idUnidadeInst, Funcionario gerente, Nucleo nucleo, String idUsuario) throws BancoobException {
		pessoaInstituicao = getDAO().obter(pessoaInstituicao.getIdPessoaInstituicao());
		if (idUnidadeInst != null) {
			pessoaInstituicao.setIdUnidadeInst(idUnidadeInst);
		}
		if (gerente != null) {
			pessoaInstituicao.setFuncionario(gerente);
		}
		if (nucleo != null) {
			pessoaInstituicao.setNucleo(nucleo);
		}
		
		pessoaInstituicao.setIdUsuarioAprovacao(idUsuario);
		
		// altera a pessoa sem validar o cadastro
		super.alterar(pessoaInstituicao);
		replicarClienteLegado(pessoaInstituicao, false);
		atualizarTransicao(pessoaInstituicao);
	}

	/**
	 * O método Atualizar transicao.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void atualizarTransicao(PessoaInstituicao pessoaInstituicao) throws BancoobException {

		TransicaoPessoa transicao = transicaoPessoaServico.obterTransicaoPorPessoaInstituicao(pessoaInstituicao.getPessoa(),
				pessoaInstituicao.getIdInstituicao());
		transicao.getInstituicao().setIdUnidadeInst(pessoaInstituicao.getIdUnidadeInst());
		transicaoPessoaServico.alterar(transicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long pesquisarNumeroRegistros(ConsultaDto<PessoaInstituicao> criterios) {
		return getDAO().pesquisarNumeroRegistros(criterios);
	}

	/**
	 * O método Validar cadastro pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarCadastroPessoa(PessoaCompartilhamento pessoaCompartilhamento, Instituicao instituicao) throws BancoobException {
		ValidacaoCadastralErroDelegate validacaoCadastralDelegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralErroDelegate();
		List<ValidacaoCadastralVO> listaErros = validacaoCadastralDelegate.listarFalhasRegrasValidacaoCadastralPerfilCadastro(pessoaCompartilhamento.getId(), 
				instituicao.getIdInstituicao(), TipoRegraValidacaoCadastralEnum.R,
				pessoaCompartilhamento.getPerfilCadastro().getCodigo());

		if (listaErros != null && CollectionUtils.isNotEmpty(listaErros)) {
			throw new PessoaComErrosCadastraisRestritivosException();
		}
	}
	
	/**
	 * Obter pessoa compartilhamento.
	 *
	 * @param pessoaExistente o valor de pessoa existente
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaCompartilhamento obterPessoaCompartilhamento(Pessoa pessoaExistente, Integer idInstituicao) throws BancoobException {

		GrupoCompartilhamento grupoCompartilhamento = grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);
		CompartilhamentoCadastro compartilhamentoCadastro = grupoCompartilhamento.getCompartilhamentoCadastro();
		return pessoaCompartilhamentoServico.consultar(pessoaExistente, compartilhamentoCadastro);
	}

	/**
	 * Inclui a tributacao com valores padrao (todos habilitados) e aprovados.
	 * Ou seja, esta inclusao nao passa pelo processo de aprovacao
	 * 
	 * @param pessoa
	 *            a pessoa para a qual a tributacao sera incluida
	 * @param instituicao
	 *            a instituicao na qual a tributacao sera cadastrada
	 * @throws BancoobException
	 *             caso ocorra algum imprevisto
	 */
	private void incluirTributacao(PessoaCompartilhamento pessoa, Instituicao instituicao) throws BancoobException {

		CAPESCadastroFabricaDelegates fabrica = null;

		fabrica = CAPESCadastroFabricaDelegates.getInstance();
		TributacaoDelegate delegate = fabrica.criarTributacaoDelegate();

		Tributacao tributacao = delegate.obter(pessoa.getIdPessoaCompartilhamento());
		if (tributacao == null) {

			// O cliente deve ser inserido sempre com as cobranças habilitadas
			tributacao = new Tributacao();
			tributacao.setPessoaCompartilhamento(pessoa);
			tributacao.setVerificarAutorizacao(false);
			delegate.incluir(tributacao, instituicao);
		} else {
			replicarTributacao(tributacao, instituicao);
		}
	}

	/**
	 * O método Replicar tributacao.
	 *
	 * @param tributacao o valor de tributacao
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void replicarTributacao(Tributacao tributacao, Instituicao instituicao) throws BancoobException {
		AtualizacaoClienteFachada fachada = new AtualizacaoClienteFachada();
		fachada.replicarTributacao(tributacao, instituicao.getIdInstituicao());
	}

	/**
	 * O método Replicar cliente legado.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param atualizarRisco o valor de atualizar risco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void replicarClienteLegado(PessoaInstituicao pessoaInstituicao, boolean atualizarRisco) throws BancoobException {

		AtualizacaoClienteFachada fachada = new AtualizacaoClienteFachada();
		fachada.replicarDadosCliente(pessoaInstituicao, atualizarRisco);
	}

	/**
	 * Incluir pessoa instituicao.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param instituicao o valor de instituicao
	 * @param produtoBancoob o valor de produto bancoob
	 * @return PessoaInstituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaInstituicao incluirPessoaInstituicao(PessoaInstituicao pessoaInstituicao, Instituicao instituicao, boolean produtoBancoob) throws BancoobException {
		PessoaInstituicao retorno = null;
		
		if(pessoaInstituicao != null && instituicao != null) {
			Pessoa pessoa = pessoaInstituicao.getPessoa();

			Integer idInstituicao = instituicao.getIdInstituicao();
			pessoaInstituicao.setIdInstituicao(idInstituicao);
			
			// Verifica a integração e atualiza o nível de risco caso necessário.
			atualizarNivelRisco(pessoaInstituicao, produtoBancoob);
			
			retorno = super.incluir(pessoaInstituicao);

			// Isso foi feito assim por causa da importação de beneficiários.
			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(pessoa, idInstituicao);
			replicarClienteLegado(pessoaInstituicao, true);

			atualizarTransicao(pessoaInstituicao);
			incluirTributacao(pessoaCompartilhamento, instituicao);
			
			if (InformacoesUsuarioCAPES.getInstance() != null) {
				renovarCadastroAutomatico(pessoaInstituicao.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
			}
			
		}
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verificarCadastroPessoa(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		PessoaCompartilhamento pessoaCompartilhamento = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().consultarPorIdPessoaInstituicao(pessoaInstituicao.getPessoa().getId(), pessoaInstituicao.getIdInstituicao());
		revalidarCadastro(pessoaCompartilhamento.getId());
		validarCadastroPessoa(pessoaCompartilhamento, obterInstituicaoUsuarioLogado());
	}
	
	/**
	 * O método Revalidar cadastro.
	 *
	 * @param idPessoaCompartilhamento o valor de id pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void revalidarCadastro(Long idPessoaCompartilhamento) throws BancoobException {
		ValidacaoCadastralRegraDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralRegraDelegate();
		delegate.revalidarCadastroPerfilCadastro(idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaInstituicao incorporar(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(pessoaInstituicao.getIdInstituicao());
		instituicao.setIdUnidadeInst(pessoaInstituicao.getIdUnidadeInst());

		Pessoa pessoa = pessoaInstituicao.getPessoa();
		PessoaInstituicao retorno = super.incluir(pessoaInstituicao);
		replicarClienteLegado(retorno, true);

		// Isso foi feito assim por causa dos batchs.
		PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(pessoa, instituicao.getIdInstituicao());
		incluirTributacao(pessoaCompartilhamento, instituicao);
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaInstituicao> listarPorFuncionarioResponsavel(Funcionario funcionario) {
		return getDAO().listarPorFuncionarioResponsavel(funcionario);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long obterQuantidadeClientesPorNucleo(Nucleo nucleo) {
		return getDAO().obterQuantidadeClientesPorNucleo(nucleo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean obterStatusCOP(boolean produtoBancoob) throws BancoobException {
		Boolean status = Boolean.FALSE;
		Integer idInstituicao = null;

		if (produtoBancoob) {
			idInstituicao = Constantes.Comum.ID_INSTITUICAO_BANCOOB;
		} else {
			idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		}

		ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
		String parametro = admIntegracaoDelegate.obterValorParametro(ID_PARAMETRO_PILOTO_COP, idInstituicao);

		if (parametro != null) {
			status = parametro.equals("1");
		}
		
		return status;
	}

	/**
	 * Informação necessária para a criação de clientes na
	 * replicação. O nível de risco não será mais passado pelo capes,
	 * será calculado em um sistema próprio.
	 * 
	 * @param pessoaInstituicao
	 * @throws BancoobException
	 */
	private void atualizarNivelRisco(PessoaInstituicao pessoaInstituicao, boolean produtoBancoob) throws BancoobException {
		//Verifica se o nível de risco está vazio e se a integração está habilitada.
		if((pessoaInstituicao.getNivelRisco() == null || StringUtils.isEmpty(pessoaInstituicao.getNivelRisco())) && !obterStatusCOP(produtoBancoob)){
			throw new NivelRiscoNaoInformadoException();
		}
		//Se o nível risco estiver vazio e a integração desabilitada, atualiza as informações do nível de risco.
		else if ((pessoaInstituicao.getNivelRisco() == null || StringUtils.isEmpty(pessoaInstituicao.getNivelRisco())) && obterStatusCOP(produtoBancoob)) {
			Integer idPessoa = pessoaInstituicao.getPessoa().getId();
			Integer idInstituicao = pessoaInstituicao.getIdInstituicao();

			getLogger().debug("[CAPES] obtendo o risco para idPessoa: ", String.valueOf(idPessoa), " idInstituicao: ", String.valueOf(idInstituicao));

			CRLIntegracaoDelegate CRLDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarCRLIntegracaoDelegate();
			RiscoVO risco = CRLDelegate.obterRisco(idPessoa, idInstituicao);

			if (risco != null) {
				pessoaInstituicao.setDataEnquadramentoRisco(risco.getDataEnquadramentoRisco());
				pessoaInstituicao.setNivelRisco(risco.getNivelRisco());
				pessoaInstituicao.setMotivoRisco("ATRIBUÍDO PELO CRL");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StatusTransferenciaClienteVO> obterStatusTransferenciaCliente() throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		return getDAO().obterStatusTransferenciaCliente(idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicao(ConsultaDto<PessoaInstituicao> criterios) throws BancoobException {
		return getDAO().pesquisarIdPessoaInstituicao(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verificaFuncionarioAssociadoClienteHistorico(Funcionario funcionario) throws BancoobException {
		return getDAO().verificaFuncionarioAssociadoClienteHistorico(funcionario);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {
		CAPESReplicacaoComumFabricaDelegates capesReplicacaoComumFabricaDelegates = CAPESReplicacaoComumFabricaDelegates.getInstance();
		ClienteDelegate clienteDelegate = capesReplicacaoComumFabricaDelegates.criarClienteDelegate();
		clienteDelegate.atualizarRiscoCliente(converterDtoReplicacaoCliente(dto));
	}
	
	/**
	 * Converter dto replicacao cliente.
	 * 
	 * @param dto
	 *            the dto
	 * @return br.com.sicoob.capes.replicacao.negocio.dto. atualizacao risco
	 *         cliente dto
	 */
	private br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO converterDtoReplicacaoCliente(AtualizacaoRiscoClienteDTO dto) {
		br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO atualizacaoRiscoClienteDTO = new br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO();
		atualizacaoRiscoClienteDTO.setIdInstituicao(dto.getIdInstituicao());
		atualizacaoRiscoClienteDTO.setIdNivelRisco(dto.getIdNivelRisco());
		atualizacaoRiscoClienteDTO.setMotivoRisco(dto.getMotivoRisco());
		atualizacaoRiscoClienteDTO.setNumCliente(dto.getNumCliente());
		return atualizacaoRiscoClienteDTO;
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicaoByCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicaoUsuarioLogado) throws BancoobException {
		return getDAO().pesquisarIdPessoaInstituicaoByCpfCnpj(listaCpfCnpj, idInstituicaoUsuarioLogado);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UnidadeInstituicaoVO> obterListaUnidadesInstituicao(Integer idInstituicao, PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		List<UnidadeInstituicaoVO> unidadesInstituicao = null;
		
		Anotacao filtro = new Anotacao();
		TipoAnotacao tipoAnotacao = new TipoAnotacao();
		tipoAnotacao.setId(TipoAnotacaoAutomaticaEnum.CADASTRO_DIGITAL.getCodTipoAnotacao());
		filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
		filtro.setTipoAnotacao(tipoAnotacao);
		ConsultaAnotacaoDTO criterios = new ConsultaAnotacaoDTO();
		criterios.setAnotacoesBaixadas(Boolean.FALSE);
		criterios.setFiltro(filtro);
		
		AnotacaoDelegate anotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate();
		ConsultaDto<Anotacao> retorno = anotacaoDelegate.pesquisar(criterios);
		
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		// Se a pessoa foi incluída por um cadastro DIGITAL, as unidades serão carregadas com o PA DIGITAL
		if(retorno != null && retorno.getResultado() != null && !retorno.getResultado().isEmpty()) {
			unidadesInstituicao = sciIntegracaoDelegate.listarUnidadesInstituicao(idInstituicao, true);
		// Caso contrário, a lista virá sem o PA Digital
		} else {
			unidadesInstituicao = sciIntegracaoDelegate.listarUnidadesInstituicao(idInstituicao, Boolean.TRUE);
		}
		
		return unidadesInstituicao;
	}
	
	/**
	 * Faz a renovação automática do cadastro.
	 * 
	 * @param idPessoa
	 * @param idUsuario
	 * @throws BancoobException
	 */
	private void renovarCadastroAutomatico(Integer idPessoa, String idUsuario) throws BancoobException {
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, idPessoa, idUsuario);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarInformacoesLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, TransfInformacoesDTO dto) throws BancoobException {
			getDAO().gerarHistoricoLote(pesquisaEntidades, dto.getIdUsuarioLogado());
			getDAO().alterarLote(pesquisaEntidades, dto);
			getDAO().atualizarTransicaoLote();
			replicarClienteLegadoLote(pesquisaEntidades, false);
	}

	/**
	 * O método Replicar cliente legado em lote.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param atualizarRisco o valor de atualizar risco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void replicarClienteLegadoLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, boolean atualizarRisco) throws BancoobException {
		AtualizacaoClienteFachada fachada = new AtualizacaoClienteFachada();
		fachada.replicarClienteLegadoLote(pesquisaEntidades, atualizarRisco);
	}
	
}
