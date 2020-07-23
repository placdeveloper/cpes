/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.helper.ParametroPilotoHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FonteRendaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmpresaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFonteRendaDelegate;
import br.com.sicoob.capes.cadastro.negocio.proxies.FonteRendaProxy;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fachada para as fontes de rendas.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class FonteRendaFachada extends EntidadeCadastroFachada<FonteRenda> {

	/** A constante LISTA_TIPOS_FONTE_RENDA. */
	private static final String LISTA_TIPOS_FONTE_RENDA = "listaTiposFonteRenda";
	
	/** A constante LISTA_TIPO_EMPRESA. */
	private static final String LISTA_TIPO_EMPRESA = "listaTipoEmpresa";
	
	/** A constante FONTE_RENDA. */
	private static final String FONTE_RENDA = "fonteRenda";

	/** O atributo delegate. */
	private FonteRendaDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarFonteRendaDelegate();
	
	/** O atributo tipoFonteRendaDelegate. */
	private TipoFonteRendaDelegate tipoFonteRendaDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoFonteRendaDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate =  CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizacaoCadastroDelegate();
	
	/** O atributo tipoEmpresaDelegate. */
	private TipoEmpresaDelegate tipoEmpresaDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoEmpresaDelegate();

	/**
	 * Instancia um novo FonteRendaFachada.
	 */
	public FonteRendaFachada() {
		super(FONTE_RENDA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {

		try {
			Integer codTipoPessoa = (Integer) dto.getDados().get("codTipoPessoa");
			TipoPessoa tipoPessoa = new TipoPessoa();
			tipoPessoa.setCodTipoPessoa(codTipoPessoa.shortValue());
			
			List<TipoFonteRenda> lista = tipoFonteRendaDelegate.listarPorTipoPessoa(tipoPessoa);
			List<TipoEmpresa> listaTipoEmpresa = listarTipoEmpresaAtivos(dto);
			
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put(LISTA_TIPOS_FONTE_RENDA, lista);
			retorno.getDados().put(LISTA_TIPO_EMPRESA, listaTipoEmpresa);
			retorno.getDados().put("definicoesComponenteGED", obterDefinicoesGED(dto));
			retorno.getDados().put("pilotoHabilitado", ParametroPilotoHelper.isPilotoHabilitado());
			
			return retorno;
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
	
	private List<DefinicoesComponenteGedVO> obterDefinicoesGED(RequisicaoReqDTO dto) {
		Integer codTipoPessoa = (Integer) dto.getDados().get("codTipoPessoa");
		List<DefinicoesComponenteGedVO> listaDefinicoesGed = new ArrayList<DefinicoesComponenteGedVO>();

		if (codTipoPessoa != null) {
			Set<String> chavesNegocio = new LinkedHashSet<String>();
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_RENDIMENTO);

			DefinicoesComponenteGedVO gedVO = new DefinicoesComponenteGedVO();
			gedVO.setIdTipoPessoa(Short.valueOf(codTipoPessoa.toString()));
			gedVO.setSiglaTipoDocumento(TipoPessoaEnum.isPessoaFisica(codTipoPessoa.shortValue()) ? Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVANTE_RENDA_PF
					: Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVANTE_RENDA_PJ);
			gedVO.setChavesNegocio(chavesNegocio);

			DefinicoesComponenteGedVO gedSimplesNacional = new DefinicoesComponenteGedVO();
			gedSimplesNacional.setIdTipoPessoa(Short.valueOf(codTipoPessoa.toString()));
			gedSimplesNacional.setSiglaTipoDocumento(TipoPessoaEnum.isPessoaJuridica(codTipoPessoa.shortValue()) ? Constantes.Negocio.GED_SIGLA_COMPROVANTE_OPCAO_SIMPLES_NACIONAL : null);
			gedSimplesNacional.setChavesNegocio(chavesNegocio);

			listaDefinicoesGed.add(gedVO);
			listaDefinicoesGed.add(gedSimplesNacional);
		}
		return listaDefinicoesGed;
	}
	
	private List<TipoEmpresa> listarTipoEmpresaAtivos(RequisicaoReqDTO dto) throws BancoobException{
		ConsultaDto<TipoEmpresa> criterios = new ConsultaDto<TipoEmpresa>();
		TipoEmpresa filtro = new TipoEmpresa();
		filtro.setAtivo(Boolean.TRUE);
		criterios.setFiltro(filtro);
		return tipoEmpresaDelegate.listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRenda obterEntidade(RequisicaoReqDTO dto) {
		return (FonteRenda) dto.getDados().get(FONTE_RENDA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaDelegate obterDelegate() {
		return this.delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		
		DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
		try {
			resultadoDto.getDados().put(NOME_PADRAO_LISTA, obterDelegate().listarRendas(pessoa));
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
		return resultadoDto;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDados(dto);
		
		try {
			//Obtém os documentos comprobatórios da renda
			FonteRenda fonteRenda = (FonteRenda) retorno.getDados().get("fonteRenda");
			validarEntidade(fonteRenda, "na Fonte de Renda");
			fonteRenda.setDocumentosComprobatorios(autorizacaoCadastroDelegate
					.obterDocumentosComprobatorios(fonteRenda));
			retorno.getDados().put("fonteRenda", fonteRenda);
			
			//Verifica se o registro está bloqueado para alteração.
			retorno.getDados().put("isRegistroBloqueadoAlteracao", autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(fonteRenda));
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
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = super.incluirDados(dto);
			obterMensagensValidacao(retorno);
			return retorno;
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
			RetornoDTO retorno = super.alterarDados(dto);
			obterMensagensValidacao(retorno);
			return retorno;
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
	 * O método Obter mensagens validacao.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void obterMensagensValidacao(RetornoDTO dto) throws BancoobException {
		FonteRenda fonteRenda = (FonteRenda) dto.getDados().get(this.chaveMapa);
		String mensagem = obterDelegate().obterMensagensValidacao(fonteRenda);
		dto.getDados().put("mensagemValidacao", mensagem);
	}
	
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			FonteRenda entidade = obterEntidade(dto);
			valdarExclusao(entidade);
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

	private void valdarExclusao(FonteRenda entidade) throws BancoobException {

		boolean isPiloto = ParametroPilotoHelper.isPilotoHabilitado();

		if (!isPiloto) {
			return;
		}

		List<FonteRendaProxy> fontesRendas = obterDelegate().listarRendas(entidade.getPessoaCompartilhamento());
		int i = 0;

		for (FonteRendaProxy renda : fontesRendas) {
			boolean vigente = isVigente(renda);
			if (vigente) {
				i++;
			}
		}
		if (i == 1) {
			throw new NegocioException(Constantes.Negocio.MENSAGEM_ERRO_NEGOCIAL);
		}
	}
	
	private boolean isVigente(FonteRendaProxy renda) {
		return renda.getDataHoraInicio() != null && renda.getIdInstituicaoAtualizacao() == null;
	}
}