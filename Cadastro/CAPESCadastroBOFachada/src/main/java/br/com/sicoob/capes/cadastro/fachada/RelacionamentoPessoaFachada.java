/* 
 * Sicoob
 * RelacionamentoPessoaFachada.java 
 * Criado em: 24/08/2011
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbr.negocio.dto.PesquisaDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.helper.ParametroPilotoHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizarDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoPoderRelacionamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoRelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.Constantes.Comum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Fachada para os serviços de {@link RelacionamentoPessoa}
 * 
 * 24/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class RelacionamentoPessoaFachada extends
		CAPESCadastroBOCrudFachada<RelacionamentoPessoa> {

	private static final Short DEVOLVIDO = 3;
	
	/** A constante CHAVE_MAPA. */
	private static final String CHAVE_MAPA = "relacionamentoPessoa";

	/** O atributo delegate. */
	private RelacionamentoPessoaDelegate delegate;
	
	/** O atributo tipoRelacionamentoDelegate. */
	private TipoRelacionamentoPessoaDelegate tipoRelacionamentoDelegate;
	
	/** O atributo tipoPoderRelacionamentoDelegate. */
	private TipoPoderRelacionamentoDelegate tipoPoderRelacionamentoDelegate;
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate;
	
	/** O atributo fabrica. */
	private transient CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	private AutorizarDelegate autorizarDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizarDelegate();

	/**
	 * Instancia um novo RelacionamentoPessoaFachada.
	 */
	public RelacionamentoPessoaFachada() {
		super(CHAVE_MAPA);
		this.tipoRelacionamentoDelegate = CAPESCadastroFabricaDelegates
				.getInstance().criarTipoRelacionamentoPessoaDelegate();
		this.tipoPoderRelacionamentoDelegate = CAPESCadastroFabricaDelegates
				.getInstance().criarTipoPoderRelacionamentoDelegate();
		this.autorizacaoCadastroDelegate =  CAPESCadastroFabricaDelegates
					.getInstance().criarAutorizacaoCadastroDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaDelegate obterDelegate() {
		if (delegate == null) {
			delegate = CAPESCadastroFabricaDelegates.getInstance()
					.criarRelacionamentoPessoaDelegate();
		}
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoa obterEntidade(RequisicaoReqDTO dto) {
		RelacionamentoPessoa relacionamentoPessoa = (RelacionamentoPessoa) dto.getDados().get(CHAVE_MAPA);
		if(relacionamentoPessoa != null){
			Boolean produtoBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			relacionamentoPessoa.setProdutoBancoob(produtoBancoob);
		}
		return relacionamentoPessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {
		
		try {
			PesquisaDTO pesquisaDTO = null;
			RelacionamentoPessoa filtro = null;
			if (dto instanceof PesquisaDTO) {
				pesquisaDTO = (PesquisaDTO) dto;
				filtro = (RelacionamentoPessoa) pesquisaDTO.getFiltro();
				Boolean produtosBancoob = (Boolean) pesquisaDTO.getValor();
				filtro.setIdInstituicao(obterIdInstituicao(produtosBancoob));
				
			}

			ConsultaDto<RelacionamentoPessoa> consultaCedidosDto = new ConsultaDto<RelacionamentoPessoa>();
			consultaCedidosDto.setFiltro(filtro);
			consultaCedidosDto = obterDelegate().pesquisar(consultaCedidosDto);

			ConsultaDto<RelacionamentoPessoa> consultaExercidosDto = new ConsultaDto<RelacionamentoPessoa>();
			consultaExercidosDto.setFiltro(filtro);
			consultaExercidosDto = obterDelegate()
					.pesquisarRelacionamentosExercidos(consultaExercidosDto);
			
			List<RelacionamentoPessoa> listaExercidos = consultaExercidosDto.getResultado();
			List<RelacionamentoPessoa> listaCedidos = consultaCedidosDto.getResultado();

			Collections.sort(listaExercidos, new BeanComparator<RelacionamentoPessoa>("idRegistroControlado"));
			Collections.sort(listaCedidos, new BeanComparator<RelacionamentoPessoa>("idRegistroControlado"));

			DadosSelGeralDTO resultado = new DadosSelGeralDTO();
			resultado.getDados().put("relacionamentosCedidos", listaCedidos);
			resultado.getDados().put("relacionamentosExercidos", listaExercidos);
			return resultado;
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}

	/**
	 * Obter id instituicao.
	 *
	 * @param produtosBancoob o valor de produtos bancoob
	 * @return Integer
	 */
	private Integer obterIdInstituicao(Boolean produtosBancoob) {
		
		Integer idInstituicao = Comum.ID_INSTITUICAO_BANCOOB;
		if (!produtosBancoob) {
			idInstituicao = Integer.valueOf(obterUsuarioLogado().getIdInstituicao()); 
		}
		return idInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDefinicoes(dto);
		try {
			Set<String> chavesNegocio = new LinkedHashSet<String>();
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_VINCULO);
			
			Pessoa pessoa = (Pessoa)dto.getDados().get("pessoa");
			DefinicoesComponenteGedVO gedVO = new DefinicoesComponenteGedVO();
			gedVO.setIdTipoPessoa(Short.valueOf(pessoa.getTipoPessoa().getCodTipoPessoa().toString()));
			gedVO.setSiglaTipoDocumento(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_RELACIONAMENTO_PODERES);
			gedVO.setChavesNegocio(chavesNegocio);
			
			Boolean produtosBancoob = (Boolean) dto.getDados().get(
					EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			retorno.getDados().putAll(obterTiposRelacionamento(dto).getDados());
			retorno.getDados().put("poderes", tipoPoderRelacionamentoDelegate.listar());
			retorno.getDados().put("dataInicioRelacionamento",
					obterDataMovimentoCCS(obterIdInstituicao(produtosBancoob)));
			retorno.getDados().put("definicoesComponenteGED", gedVO);
			retorno.getDados().put("pilotoHabilitado", ParametroPilotoHelper.isPilotoHabilitado());
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}

	/**
	 * Obter tipos relacionamento.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterTiposRelacionamento(RequisicaoReqDTO dto)throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			TipoPessoa tipoPessoa = ((Pessoa) dto.getDados().get("pessoa")).getTipoPessoa();
			Pessoa pessoaRelacionada = (Pessoa) dto.getDados().get("pessoaRelacionada");
			Boolean produtosBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			
			TipoPessoa tipoPessoaRelacionada = null;
			List<TipoRelacionamentoPessoa> tiposRelacionamento = null;
			if (pessoaRelacionada != null) {
				tipoPessoaRelacionada = pessoaRelacionada.getTipoPessoa();
			}
			if(!produtosBancoob){
				tiposRelacionamento = tipoRelacionamentoDelegate.pesquisarPorTiposPessoa(tipoPessoa, tipoPessoaRelacionada);
			}else{
				tiposRelacionamento = tipoRelacionamentoDelegate.pesquisarTiposRelacionamentosProdutosBancoob();
			}
			
			retorno.getDados().put("tiposRelacionamento", tiposRelacionamento);

		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	public RetornoDTO validarTransicaoPessoaRelacionada(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			Pessoa pessoaRelacionada = (Pessoa) dto.getDados().get("pessoaRelacionada");
			Boolean produtosBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			Boolean telaConsulta = (Boolean) dto.getDados().get("telaConsulta");
			
			RelacionamentoPessoa filtro = new RelacionamentoPessoa();
			filtro.setPessoaRelacionada(pessoaRelacionada);
			filtro.setIdInstituicao(obterIdInstituicao(produtosBancoob));
			
			Boolean existeTransicao = delegate.validarTransicaoPessoaRelacionada(filtro);
			
			retorno.getDados().put("existeTransicao", existeTransicao);
			retorno.getDados().put("telaConsulta", telaConsulta);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	public RetornoDTO compartilharPessoaRelacionada(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			Pessoa pessoaRelacionada = (Pessoa) dto.getDados().get("pessoaRelacionada");
			Boolean produtosBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			Boolean telaConsulta = (Boolean) dto.getDados().get("telaConsulta");
			ReplicacaoCadastroDelegate delegate = fabrica.criarReplicacaoCadastroDelegate();
			
			PessoaCompartilhamento pessoaCompartilhada = null;
			if(produtosBancoob){
				pessoaCompartilhada = delegate.iniciarRelacionamentoBancoob(pessoaRelacionada.getPessoaCompartilhamento());
			}else{
				pessoaCompartilhada = delegate.iniciarRelacionamentoInstituicao(pessoaRelacionada.getPessoaCompartilhamento());
			}
			
			Boolean isCompSucesso = false;
			if(pessoaCompartilhada != null){
				isCompSucesso = true;
			}
			
			retorno.getDados().put("isCompSucesso", isCompSucesso);
			retorno.getDados().put("telaConsulta", telaConsulta);
			
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	public void validarPessoaRelacionada(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Pessoa pessoaRelacionada = (Pessoa) dto.getDados().get("pessoaRelacionada");
			TipoRelacionamentoPessoa tipoRel = (TipoRelacionamentoPessoa) dto.getDados().get("tipoRelacionamentoPessoa");
			RelacionamentoPessoa filtro = new RelacionamentoPessoa();
			filtro.setPessoaRelacionada(pessoaRelacionada);
			filtro.setTipoRelacionamento(tipoRel);
			if (pessoaRelacionada != null) {
				delegate.verificaPendenciasPessoaRelacionada(filtro);
			}
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
	}

	/**
	 * Obtém a data atual do movimento do produto CADASTRO DE CLIENTES DO SFN -
	 * CCS (ID 41)
	 * @param idInstituicao 
	 * 
	 * @return {@link RetornoDTO} com a data atual do movimento
	 */
	private Date obterDataMovimentoCCS(Integer idInstituicao) throws BancoobException {

		return obterDelegate().obterDataMovimentoCCS(idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto)throws BancoobException {
		
		try {
			RelacionamentoPessoa relacionamento = (RelacionamentoPessoa) dto
					.getDados().get(CHAVE_MAPA);
			
			Boolean produtosBancoob = (Boolean) dto.getDados().get(
					EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			relacionamento.setIdInstituicao(obterIdInstituicao(produtosBancoob));
			
			RelacionamentoPessoa entidade = obterEntidade(dto);
			obterDelegate().incluir(entidade, Boolean.TRUE);
			return obterMapRetorno(this.chaveMapa, entidade);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RelacionamentoPessoa entidade = obterEntidade(dto);
			//setIdRelacionamentoAntigo(entidade);
			obterDelegate().alterar(entidade);
			return obterMapRetorno(this.chaveMapa, entidade);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RelacionamentoPessoa entidade = obterEntidade(dto);
			obterDelegate().excluirEntidade(entidade);
			return obterMapRetorno(this.chaveMapa, entidade);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDados(dto);
		
		try {
			//Obtém os documentos comprobatórios da certidão
			RelacionamentoPessoa relacionamentoPessoa = (RelacionamentoPessoa) retorno.getDados().get("relacionamentoPessoa");
			
			if(relacionamentoPessoa != null) {
				//Verifica se o registro está bloqueado para alteração.
				retorno.getDados().put("isRegistroBloqueadoAlteracao", autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(relacionamentoPessoa));
			}
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		
		return retorno;
	}
	
	private void setIdRelacionamentoAntigo(RelacionamentoPessoa relacionamentoPessoa) throws BancoobException {
		boolean delvovido = true;
		if (DEVOLVIDO.equals(relacionamentoPessoa.getCodigoSituacaoAprovacao())) {
			Long idRegistroNovo = relacionamentoPessoa.getIdRelacionamento();
			Autorizacao autorizacao = autorizarDelegate.obterAutorizacaoPorIdRegistroNovo(idRegistroNovo, delvovido);
			relacionamentoPessoa.setIdRelacionamentoAntigo(autorizacao != null ? autorizacao.getIdRegistroAntigo() : null);
		}
	}
	
}
