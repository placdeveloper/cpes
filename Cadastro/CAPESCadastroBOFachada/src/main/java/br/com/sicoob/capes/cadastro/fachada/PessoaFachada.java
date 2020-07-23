/*
 * SICOOB
 * 
 * PessoaFachada.java(br.com.sicoob.capes.cadastro.fachada.PessoaFachada)
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaFisicaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaJuridicaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;
import br.com.sicoob.capes.cadastro.helper.ParametroPilotoHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PerfilCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaIntegracaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFormaConstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFormaConstituicaoEsferaAdministrativaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralErroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.Constantes.Comum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.ITXIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.EnderecoFiscal;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.UF;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 */
@RemoteService
public class PessoaFachada extends CAPESCadastroBOFachada {

	//De acordo com o analista JorgeIn caso não retorne o número da cooperativa deve-se atribuir no número 756
	/** A constante COPERATIVA_PADRAO. */
	private static final int COPERATIVA_PADRAO = 756;
	
	/** A constante DADOS_RFB. */
	private static final String DADOS_RFB = "dadosRFB";
	
	/** A constante TIPO_PESSOA. */
	private static final String TIPO_PESSOA = "tipoPessoa";
	
	/** A constante PESSOA. */
	private static final String PESSOA = "pessoa";
	
	/** A constante LISTA_PERFIS. */
	private static final String LISTA_PERFIS = "perfis";

	/** A constante LISTA_TIPOS_PESSOA. */
	private static final String LISTA_TIPOS_PESSOA = "tiposPessoa";
	
	/** A constante NUMERO_COOPERATIVA. */
	private static final String NUMERO_COOPERATIVA = "numeroCooperativa";

	/** A constante NUMERO_UNIDADE_INSTITUICAO. */
	private static final String NUMERO_UNIDADE_INSTITUICAO = "unidadeInstituicao";

	/** O atributo fabrica. */
	private transient CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo fabricaIntegracao. */
	private transient CAPESIntegracaoFabricaDelegates fabricaIntegracao = 
			CAPESIntegracaoFabricaDelegates.getInstance();
	
	/** O atributo pessoaDelegate. */
	private PessoaCompartilhamentoDelegate pessoaDelegate = fabrica
			.criarPessoaCompartilhamentoDelegate();
	
	/** O atributo tipoPessoaDelegate. */
	private TipoPessoaDelegate tipoPessoaDelegate = fabrica.criarTipoPessoaDelegate();
	
	/** O atributo perfilDelegate. */
	private PerfilCadastroDelegate perfilDelegate = fabrica.criarPerfilCadastroDelegate();
	
	/** O atributo integracaoDelegate. */
	private PessoaIntegracaoDelegate integracaoDelegate = fabrica.criarPessoaIntegracaoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = fabrica
			.criarAutorizacaoCadastroDelegate();
	
	/** O atributo autorizacaoDelegate. */
	private AutorizacaoDelegate autorizacaoDelegate = fabrica.criarAutorizacaoDelegate();
	
	/** O atributo delegateITX. */
	private ITXIntegracaoDelegate delegateITX = fabricaIntegracao.criarITXIntegracaoDelegate();
	
	/** O atributo grupoCompartilhamentoDelegate. */
	private GrupoCompartilhamentoDelegate grupoCompartilhamentoDelegate = fabrica
			.criarGrupoCompartilhamentoDelegate();
	
	/** O atributo sciIntegracaoDelegate. */
	private SCIIntegracaoDelegate sciIntegracaoDelegate = 
			CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
	
	/** O atributo constituicaoDelegate. */
	private TipoFormaConstituicaoDelegate constituicaoDelegate = fabrica.criarTipoFormaConstituicaoDelegate();
	
	/** O atributo delegate. */
	private LOCIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
	
	private PessoaFisicaDelegate pessoaFisicaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaFisicaDelegate();
	
	private PessoaJuridicaDelegate pessoaJuridicaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaJuridicaDelegate();
	

	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {		
		RetornoDTO retorno = new RetornoDTO();
		try {
			retorno.getDados().put(LISTA_TIPOS_PESSOA, tipoPessoaDelegate.listar());
			retorno.getDados().put(LISTA_PERFIS, perfilDelegate.listar());
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
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
	 * Obter lista definicoes ged.
	 *
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @return List
	 */
	private List<DefinicoesComponenteGedVO> obterListaDefinicoesGed(Short codTipoPessoa) {
		
		//definições GED/GFT
		List<DefinicoesComponenteGedVO> listaDefinicoesGed = new ArrayList<DefinicoesComponenteGedVO>();
		
		Set<String> chavesNegocioComum = new LinkedHashSet<String>();
		chavesNegocioComum.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		
		if(TipoPessoaEnum.isPessoaFisica(codTipoPessoa)){
			obterListaDefinicoesGedPF(chavesNegocioComum, listaDefinicoesGed);
		}else{
			obterListaDefinicoesGedPJ(chavesNegocioComum, listaDefinicoesGed);
		}
		
		DefinicoesComponenteGedVO gedDeclaracaoPropositoVO = new DefinicoesComponenteGedVO();
		gedDeclaracaoPropositoVO.setIdTipoPessoa(codTipoPessoa);
		gedDeclaracaoPropositoVO.setSiglaTipoDocumento(TipoPessoaEnum.isPessoaFisica(codTipoPessoa) ?
				Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_DECLARACAO_PROPOSITO_PF : 
					Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_DECLARACAO_PROPOSITO_PJ);
		gedDeclaracaoPropositoVO.setChavesNegocio(chavesNegocioComum);
		
		DefinicoesComponenteGedVO gedFichaCadastralVO = new DefinicoesComponenteGedVO();
		gedFichaCadastralVO.setIdTipoPessoa(codTipoPessoa);
		gedFichaCadastralVO.setSiglaTipoDocumento(TipoPessoaEnum.isPessoaFisica(codTipoPessoa) ?
				Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_FICHA_CADASTRAL_PF :
					Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_FICHA_CADASTRAL_PJ);
		gedFichaCadastralVO.setChavesNegocio(chavesNegocioComum);
		
		DefinicoesComponenteGedVO gedQuestionarioRiscoSocioAmbiental = new DefinicoesComponenteGedVO();
		gedQuestionarioRiscoSocioAmbiental.setIdTipoPessoa(codTipoPessoa);
		gedQuestionarioRiscoSocioAmbiental.setSiglaTipoDocumento(TipoPessoaEnum.isPessoaFisica(codTipoPessoa) ?
				Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_QUESTIONARIO_RISCO_SOCIO_AMBIENTAL_PF :
					Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_QUESTIONARIO_RISCO_SOCIO_AMBIENTAL_PJ);
		gedQuestionarioRiscoSocioAmbiental.setChavesNegocio(chavesNegocioComum);
		
		DefinicoesComponenteGedVO gedComprovanteEstadoCivil = new DefinicoesComponenteGedVO();
		gedComprovanteEstadoCivil.setIdTipoPessoa(codTipoPessoa);
		gedComprovanteEstadoCivil.setSiglaTipoDocumento(TipoPessoaEnum.isPessoaFisica(codTipoPessoa) ? Constantes.Negocio.GED_SIGLA_COMPROVANTE_ESTADO_CIVIL : null);
		gedComprovanteEstadoCivil.setChavesNegocio(chavesNegocioComum);
		
		listaDefinicoesGed.add(gedDeclaracaoPropositoVO);
		listaDefinicoesGed.add(gedFichaCadastralVO);
		listaDefinicoesGed.add(gedQuestionarioRiscoSocioAmbiental);
		listaDefinicoesGed.add(gedComprovanteEstadoCivil);
		
		return listaDefinicoesGed;
	}

	/**
	 * O método Obter lista definicoes ged pj.
	 *
	 * @param chavesNegocioComum o valor de chaves negocio comum
	 * @param listaDefinicoesGed o valor de lista definicoes ged
	 */
	private void obterListaDefinicoesGedPJ(Set<String> chavesNegocioComum,
			List<DefinicoesComponenteGedVO> listaDefinicoesGed) {
		
		DefinicoesComponenteGedVO gedDocumentoConsituicaoVO = new DefinicoesComponenteGedVO();
		gedDocumentoConsituicaoVO.setIdTipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo());
		gedDocumentoConsituicaoVO.setSiglaTipoDocumento(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_CONSTITUICAO);
		gedDocumentoConsituicaoVO.setChavesNegocio(chavesNegocioComum);
		
		DefinicoesComponenteGedVO gedInscricaoEstadual = new DefinicoesComponenteGedVO();
		gedInscricaoEstadual.setIdTipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo());
		gedInscricaoEstadual.setSiglaTipoDocumento(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_INSCRICAO_ESTADUAL);
		gedInscricaoEstadual.setChavesNegocio(chavesNegocioComum);
		
		listaDefinicoesGed.add(gedDocumentoConsituicaoVO);
		listaDefinicoesGed.add(gedInscricaoEstadual);
		
	}

	/**
	 * O método Obter lista definicoes ged pf.
	 *
	 * @param chavesNegocioComum o valor de chaves negocio comum
	 * @param listaDefinicoesGed o valor de lista definicoes ged
	 */
	private void obterListaDefinicoesGedPF(Set<String> chavesNegocioComum, 
			List<DefinicoesComponenteGedVO> listaDefinicoesGed) {
		
		Set<String> chavesNegocioPF = new LinkedHashSet<String>();
		chavesNegocioPF.addAll(chavesNegocioComum);
		chavesNegocioPF.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_DOC_IDENTIFICACAO);
		chavesNegocioPF.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_NUMERO_DOCUMENTO);
		
		DefinicoesComponenteGedVO gedIdentificacaoVO = new DefinicoesComponenteGedVO();
		gedIdentificacaoVO.setIdTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
		gedIdentificacaoVO.setSiglaTipoDocumento(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_IDENTIFICACAO);
		gedIdentificacaoVO.setChavesNegocio(chavesNegocioPF);
		
		DefinicoesComponenteGedVO gedDeclaracaoEmancipacaoVO = new DefinicoesComponenteGedVO();
		gedDeclaracaoEmancipacaoVO.setIdTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
		gedDeclaracaoEmancipacaoVO.setSiglaTipoDocumento(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_DECLARACAO_EMANCIPACAO);
		gedDeclaracaoEmancipacaoVO.setChavesNegocio(chavesNegocioComum);
		
		listaDefinicoesGed.add(gedIdentificacaoVO);
		listaDefinicoesGed.add(gedDeclaracaoEmancipacaoVO);
		
	}

	/**
	 * Obter definicoes inclusao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoesInclusao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			List<GrauInstrucao> listaGrau = new ArrayList<GrauInstrucao>();
			List<TipoDocumentoIdentificacao> listaTipoDocumento = new ArrayList<TipoDocumentoIdentificacao>();
			List<VinculoEmpregaticio> listaVinculo = new ArrayList<VinculoEmpregaticio>();
			List<RegimeCasamento> listaRegimeCasamento = new ArrayList<RegimeCasamento>();
			List<EstadoCivil> listaEstadoCivil = new ArrayList<EstadoCivil>();
			List<TipoEmpresa> listaTipoEmpresa = new ArrayList<TipoEmpresa>();
			List<EsferaAdministrativaVO> listaEsferas = new ArrayList<EsferaAdministrativaVO>();
			List<DefinicoesComponenteGedVO> listaDefinicoesGed = null;
			List<EnderecoFiscal> listaEndFiscais = new ArrayList<EnderecoFiscal>();
			
			TipoPessoa tipoPessoa = (TipoPessoa) dto.getDados().get(TIPO_PESSOA);
			if (TipoPessoaEnum.PESSOA_FISICA.getCodigo().equals(tipoPessoa.getCodTipoPessoa())) {

				listaGrau = fabrica.criarGrauInstrucaoDelegate().listar();
				listaTipoDocumento = fabrica.criarTipoDocumentoIdentificacaoDelegate().listar();
				listaVinculo = fabrica.criarVinculoEmpregaticioDelegate().listar();
				listaRegimeCasamento = fabrica.criarRegimeCasamentoDelegate().listar();
				listaEstadoCivil = fabrica.criarEstadoCivilDelegate().listar();
				listaDefinicoesGed = obterListaDefinicoesGed(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
				
			} else {
				listaTipoEmpresa = fabrica.criarTipoEmpresaDelegate().listar();
				listaEsferas = fabricaIntegracao.criarADMIntegracaoDelegate().listarEsferasAdministrativas();
				listaDefinicoesGed = obterListaDefinicoesGed(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo());
			}

			Map<String, Object> dados = retorno.getDados();
			dados.put(LISTA_PERFIS, perfilDelegate.listar());
			dados.put("graus", listaGrau);
			dados.put("tiposDocumento", listaTipoDocumento);
			dados.put("vinculos", listaVinculo);
			dados.put("regimesCasamento", listaRegimeCasamento);
			dados.put("estadosCivis", listaEstadoCivil);
			dados.put("tiposEmpresa", listaTipoEmpresa);
			dados.put("esferasAdministrativas", listaEsferas);
			dados.put("gestorCadastro", isGestorCadastro());
			dados.put("integracaoReceita", isIntegracaoReceitaLigada());
			dados.put("definicoesComponenteGED", listaDefinicoesGed);
			dados.put("listaEndFiscais", listaEndFiscais);
			dados.put("tiposForma", constituicaoDelegate.listar());
			dados.put("ufs", listarUFs());
			dados.put("pilotoHabilitado", ParametroPilotoHelper.isPilotoHabilitado());
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);			
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
	 * Listar ufs.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<UF> listarUFs() throws BancoobException {
		List<UF> ufs = new ArrayList<UF>();
		for (LOCIntegracaoUFVO vo : delegate.listarUFs()) {
			ufs.add(IntegracaoUtil.converterUF(vo));
		}
		return ufs;
	}

	/**
	 * Verifica se eh gestor cadastro.
	 *
	 * @return {@code true}, se for gestor cadastro
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isGestorCadastro() throws BancoobException {

		AutorizacaoCadastroDelegate delegate = CAPESCadastroFabricaDelegates
				.getInstance().criarAutorizacaoCadastroDelegate();
		return delegate.isUsuarioGestorCadastro();
	}
	
	/**
	 * Validar cpf cnpj.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO validarCpfCnpj(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			String cpfCnpj = (String) dto.getDados().get("cpfCnpj");
			TipoPessoa tipo = (TipoPessoa) dto.getDados().get(TIPO_PESSOA);
			Integer numeroCooperativa = (Integer) dto.getDados().get(NUMERO_COOPERATIVA);

			boolean parametroFacaParte = pessoaDelegate.obterParametroInclusaoFacaParte(tipo.getCodTipoPessoa());
			PessoaCompartilhamento pessoa = pessoaDelegate.consultarCpfCnpjParaInclusao(cpfCnpj, tipo, numeroCooperativa);

			retorno.getDados().put("cadastroFacaParte", parametroFacaParte);
			retorno.getDados().put(PESSOA, pessoa);

			if (pessoa == null && !parametroFacaParte) {
				if (isIntegracaoReceitaLigada()) {
					TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(tipo.getCodTipoPessoa());
					retorno.getDados().put(DADOS_RFB, delegateITX.obterDadosReceita(tipoPessoa, cpfCnpj));
				}
			}
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);			
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
	 * Verifica se eh integracao receita ligada.
	 *
	 * @return {@code true}, se for integracao receita ligada
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Boolean isIntegracaoReceitaLigada() throws BancoobException {
		InformacoesUsuarioCAPES usuario = InformacoesUsuarioCAPES.getInstance();
		if (usuario == null) {
			throw new UnsupportedOperationException(
					"O InformacoesUsuarioCAPES não foi instanciado!");
		}
		SicoobLoggerPadrao.getInstance(getClass()).debug(
				getClass().getSimpleName() + ": usuario logado - ", usuario.toString());

		Integer idInstituicao = Integer.valueOf(usuario.getIdInstituicao());
		GrupoCompartilhamento grupo = grupoCompartilhamentoDelegate.obterPorInstituicao(idInstituicao);
		return grupo.getIntegracaoSrf();
	}

	/**
	 * Iniciar relacionamento.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO iniciarRelacionamento(RequisicaoReqDTO dto) throws BancoobException {

		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		Integer numeroCooperativa = (Integer) dto.getDados().get(NUMERO_COOPERATIVA);
		Integer unidadeInstituicao = (Integer) dto.getDados().get(NUMERO_UNIDADE_INSTITUICAO);
		ReplicacaoCadastroDelegate delegate = fabrica.criarReplicacaoCadastroDelegate();

		try {
			PessoaCompartilhamento pessoaIncluida = delegate.iniciarRelacionamentoInstituicao(pessoa, numeroCooperativa, unidadeInstituicao);
			return obterRetornoPessoaProxy(pessoaIncluida);
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
		
		return new RetornoDTO();
	}

	/**
	 * Incluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		
		PessoaCompartilhamento pessoaIncluida = null;
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		DadosReceitaFederalVO dadosReceita = (DadosReceitaFederalVO) dto.getDados().get(DADOS_RFB);
		Integer numeroCooperativa = (Integer) dto.getDados().get(NUMERO_COOPERATIVA);
		Integer unidadeInstituicao = (Integer) dto.getDados().get(NUMERO_UNIDADE_INSTITUICAO);
		
		try {
			if (dadosReceita != null) {
				pessoaIncluida = pessoaDelegate.incluir(pessoa, dadosReceita, numeroCooperativa, unidadeInstituicao);
			} else {
				pessoaIncluida = pessoaDelegate.incluir(pessoa, numeroCooperativa, unidadeInstituicao);
			}
			
			return obterRetornoPessoaProxy(pessoaIncluida);
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
		
		return new RetornoDTO();
	}
	
	/**
	 * Obter retorno pessoa proxy.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RetornoDTO obterRetornoPessoaProxy(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		PessoaPlataformaVO proxy = integracaoDelegate.obterPessoaPlataforma(pessoaCompartilhamento.getPessoa().getIdPessoa());
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put(PESSOA, proxy);
		return retorno; 
	}
	
	/**
	 * Alterar dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		boolean isPiloto = ParametroPilotoHelper.isPilotoHabilitado();
		
		if (isPiloto) {
			validarAlteracao(pessoa);
		}
		
		try {
			pessoaDelegate.alterar(pessoa);
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
		
		return new RetornoDTO();
	}

	/**
	 * Excluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		pessoaDelegate.excluir(pessoa.getId());
		return new RetornoDTO();
	} 

	/**
	 * Obter dados.
	 * 
	 * @param dto
	 *            the dto
	 * @return retorno dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");

			PessoaCompartilhamento pessoa = pessoaDelegate.obter(idPessoaCompartilhamento.longValue());
			
			if (pessoa.getIdInstituicaoRenovacao() != null) {
				Integer idInstituicao = pessoa.getIdInstituicaoRenovacao().intValue();
				pessoa.setIdCooperativaRenovacao(obterIdCooperativaRenovacao(idInstituicao));
			}
			
			Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade(pessoa);
			
			retorno.getDados().put(PESSOA, pessoa);
			retorno.getDados().put("isRegistroBloqueadoAlteracao", 
							autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(pessoa));
			 
			//Dados em autorização no JSON
			PessoaCompartilhamento pessoaAutorizacao = null;
			if(autorizacao != null
					&& autorizacao.getAlteracao() != null){
				
				//TODO bruno.carneiro: testar alteração
				TipoPessoa tpPessoa = pessoa.getPessoa().getTipoPessoa(); 
				Class<?> classe = TipoPessoaEnum.isPessoaFisica(tpPessoa.getCodTipoPessoa()) ? PessoaFisica.class : PessoaJuridica.class;
				pessoaAutorizacao = (PessoaCompartilhamento) SerializacaoUtils.deserializarJson(classe, autorizacao.getAlteracao());
				if(pessoaAutorizacao != null){
					pessoaAutorizacao.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(pessoaAutorizacao));
					pessoaAutorizacao.setSituacaoAprovacao(autorizacao.obterSituacao());
				}
				retorno.getDados().put("pessoaAutorizacao", pessoaAutorizacao);
			}
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
	 * Verificar se ha pendencias cadastrais de acordo com a categoria passada
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO validarCadastro(RequisicaoReqDTO dto) throws BancoobException {
		Integer idPessoa = (Integer) dto.getDados().get("idPessoa");

		//Short ordem = (Short) dto.getDados().get("ordem");
		Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
		Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
		
		RetornoDTO retorno = new RetornoDTO();

		ValidacaoCadastralRegraDelegate delegateRegra = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralRegraDelegate();
		/*
		delegateRegra.revalidarCadastroPerfilCadastro(idPessoa, ordem); 
		retirado provisioriamente, porque o sistema voltara a validar todas as regras, 
		devido conflito em alguns lugares, reunião feita 21/03/2017	
		*/
		try {
			delegateRegra.revalidarCadastro(idPessoa, idInstituicao);
			ValidacaoCadastralErroDelegate validacaoCadastralErroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralErroDelegate();
			
			retorno.getDados().put("isPossuiRegraCadastralRestritiva", 
					validacaoCadastralErroDelegate.isPossuiRegraCadastralRestritiva(idPessoaCompartilhamento.longValue(),idInstituicao));
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
	 * Obter id cooperativa renovacao.
	 * 
	 * @param idInstituicaoRenovacao
	 *            the id instituicao renovacao
	 * @return integer
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private Integer obterIdCooperativaRenovacao(Integer idInstituicaoRenovacao) throws BancoobException {

		Integer idCooperativa = COPERATIVA_PADRAO;
		InstituicaoVO obterSistemaCooperativo = sciIntegracaoDelegate.obterInstituicaoPorId(idInstituicaoRenovacao);
		if (obterSistemaCooperativo != null) {
			idCooperativa = obterSistemaCooperativo.getNumero();
		}
		return idCooperativa;
	}
	
	/**
	 * Renovar cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO renovarCadastro(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		PessoaCompartilhamento pessoaTela = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		PessoaCompartilhamento pessoa = pessoaDelegate.obter(pessoaTela.getId());
		
		try {
			this.pessoaDelegate.renovarCadastro(pessoa);
			retorno.getDados().put(PESSOA, pessoa);
			retorno.getDados().put("isRegistroBloqueadoAlteracao", autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(pessoa));
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
	 * Obter esferas administrativas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterEsferasAdministrativas(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Short codigoFormaConstituicao = Short.valueOf(dto.getDados().get("codigoFormaConstituicao").toString());
		if (codigoFormaConstituicao != null) {
			try {
				TipoFormaConstituicaoEsferaAdministrativaDelegate tipoFormaConstituicaoEsferaAdministrativaDelegate = CAPESCadastroFabricaDelegates.getInstance()
						.criarTipoFormaConstituicaoEsferaAdministrativaDelegate();

				retorno.getDados().put("esferasAdministrativas",
						tipoFormaConstituicaoEsferaAdministrativaDelegate.obterEsferasAdministrativasPorIdFormaConstituicao(codigoFormaConstituicao));
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

		return retorno;
	}
	
	/**
	 * Realiza a alteração do perfil de cadastro.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO alterarPerfilCadastro(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		PessoaCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
		delegate.alterarPerfilCadastro(pessoa);

		return retorno;
	}
	
	public RetornoDTO validarTransicaoConjuge(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			PessoaCompartilhamento pessoaTela = (PessoaCompartilhamento) dto.getDados().get("pessoaTela");
			Boolean produtosBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);

			validarEntidade(pessoaTela);

			PessoaFisica pessoaFisica = (PessoaFisica) pessoaDelegate.obter(pessoaTela.getId());
			Boolean existeTransicao = Boolean.TRUE;
			if(pessoaFisica.getConjuge() != null){
				RelacionamentoPessoa filtro = new RelacionamentoPessoa();
				filtro.setPessoaRelacionada(pessoaFisica.getConjuge().getPessoa());
				filtro.setIdInstituicao(obterIdInstituicao(produtosBancoob));
				
				existeTransicao = fabrica.criarRelacionamentoPessoaDelegate()
						.validarTransicaoPessoaRelacionada(filtro);
			}		
			

			retorno.getDados().put("existeTransicao", existeTransicao);

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
	
	public RetornoDTO compartilharConjuge(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Boolean isCompSucesso = false;

		try {
			PessoaCompartilhamento pessoaTela = (PessoaCompartilhamento) dto.getDados().get("pessoaTela");
			Boolean produtosBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);

			validarEntidade(pessoaTela);

			PessoaFisica pessoaFisica = (PessoaFisica) pessoaDelegate.obter(pessoaTela.getId());
			
			ReplicacaoCadastroDelegate delegate = fabrica.criarReplicacaoCadastroDelegate();
			PessoaCompartilhamento pessoaCompartilhada = null;
			if(produtosBancoob){
				pessoaCompartilhada = delegate.iniciarRelacionamentoBancoob(pessoaFisica.getConjuge().getPessoaCompartilhamento());
			}else{
				pessoaCompartilhada = delegate.iniciarRelacionamentoInstituicao(pessoaFisica.getConjuge().getPessoaCompartilhamento());
			}
			
			if(pessoaCompartilhada != null){
				isCompSucesso = true;
			}
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}
		
		retorno.getDados().put("isCompSucesso", isCompSucesso);
		return retorno;
	}
	
	private Integer obterIdInstituicao(Boolean produtosBancoob) {
		
		Integer idInstituicao = Comum.ID_INSTITUICAO_BANCOOB;
		if (!produtosBancoob) {
			idInstituicao = Integer.valueOf(obterUsuarioLogado().getIdInstituicao()); 
		}
		return idInstituicao;
	}
	
	private void validarAlteracao(PessoaCompartilhamento pessoa) throws BancoobException {
		boolean vigente = isVigente(pessoa);
		
		if (vigente) {
			validarPessoaPorTipo(pessoa);
		}
	}
	
	private void validarPessoaPorTipo(PessoaCompartilhamento pessoa) throws BancoobException {
		Short tipoPessoa = pessoa.getPessoa().getTipoPessoa().getCodTipoPessoa();
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		
		if (TipoPessoaEnum.PESSOA_JURIDICA.getCodigo() == tipoPessoa) {
			PessoaCompartilhamento pessoaCompartilhamento = pessoaDelegate.obter(pessoa.getId());
			validarPessoaJuridica((PessoaJuridica) pessoa, pessoaCompartilhamento, idInstituicao);
		} else {
			validarPessoaFisica((PessoaFisica) pessoa, idInstituicao);
		}
	}

	private void validarPessoaFisica(PessoaFisica pessoaFisica, Integer idInstituicao) throws BancoobException {
		
		PessoaFisicaVO pessoaVo = 
				pessoaFisicaDelegate.obterPorPessoaInstituicao(pessoaFisica.getPessoa().getIdPessoa(), idInstituicao);
		
		if (isCampoPreechido(pessoaVo.getNomePessoa()) && isCampoInvalido(pessoaFisica.getNomePessoa())) {
			throw new NegocioException("O campo Nome é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getNomeCompleto()) && isCampoInvalido(pessoaFisica.getNomeCompleto())) {
			throw new NegocioException("O campo Nome Completo é obrigatório");
		}
		if (pessoaVo.getDescricaoTipoDocumento() != null && pessoaFisica.getTipoDocumento() == null) {
			throw new NegocioException("O campo Tipo de Documento é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getNumeroDocumento()) && isCampoInvalido(pessoaFisica.getNumeroDocumento())) {
			throw new NegocioException("O campo Nº do Documento é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getOrgaoExpedidorDocumento()) && isCampoInvalido(pessoaFisica.getOrgaoExpedidorDocumento())) {
			throw new NegocioException("O campo Órgão Expedidor é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getUfOrgaoExpedidorDocumento()) && isCampoInvalido(pessoaFisica.getUfOrgaoExpedidorDocumento())) {
			throw new NegocioException("O campo UF do Órgão Expedidor é obrigatório");
		}
		if (pessoaVo.getDataEmissaoDocumento() != null && pessoaFisica.getDataEmissaoDocumento() == null) {
			throw new NegocioException("O campo Data de Emissão é obrigatório");
		}
		if (pessoaVo.getDataNascimento() != null && pessoaFisica.getDataNascimento() == null) {
			throw new NegocioException("O campo Data de Nascimento é obrigatório");
		}
		if (pessoaVo.getCodigoNacionalidade() != null && pessoaFisica.getNacionalidade() == null) {
			throw new NegocioException("O campo País de Nascimento é obrigatório");
		}
		if (pessoaVo.getIdNaturalidade() != null && pessoaFisica.getIdNaturalidade() == null) {
			throw new NegocioException("O campo Naturalidade é obrigatório");
		}
		if (pessoaVo.getDescricaoGrauInstrucao() != null && pessoaFisica.getGrauInstrucao() == null) {
			throw new NegocioException("O campo Escolaridade é obrigatório");
		}
		if (pessoaVo.getDescricaoOcupacaoProfissional() != null && pessoaFisica.getOcupacaoProfissional() == null) {
			throw new NegocioException("O campo Profissão é obrigatório");
		}
		if (pessoaVo.getIdOcupacaoProfissional() != null && pessoaFisica.getVinculoEmpregaticio() == null) {
			throw new NegocioException("O campo Natureza da Ocupação é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getNomeMae()) && isCampoInvalido(pessoaFisica.getNomeMae())) {
			throw new NegocioException("O campo Mãe é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getNomePai()) && isCampoInvalido(pessoaFisica.getNomePai())) {
			throw new NegocioException("O campo Pai é obrigatório");
		}
		if (pessoaVo.getDescricaoEstadoCivil() != null && pessoaFisica.getEstadoCivil() == null) {
			throw new NegocioException("O campo Estado Civil é obrigatório");
		}
	}

	private void validarPessoaJuridica(PessoaJuridica pessoaJuridica, PessoaCompartilhamento pessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		
		PessoaJuridicaVO pessoaVo = 
				pessoaJuridicaDelegate.obterPorPessoaInstituicao(pessoaJuridica.getPessoa().getIdPessoa(), idInstituicao);
		
		if (isCampoPreechido(pessoaVo.getNomePessoa()) && isCampoInvalido(pessoaJuridica.getNomePessoa())) {
			throw new NegocioException("O campo Razão Social é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getNomeCompleto()) && isCampoInvalido(pessoaJuridica.getNomeCompleto())) {
			throw new NegocioException("O campo Razão Social Completo é obrigatório");
		}
		if (pessoaCompartilhamento.getAtividadeEconomica() != null && pessoaJuridica.getAtividadeEconomica() == null) {
			throw new NegocioException("O campo Atividade Econômica é obrigatório");
		}
		if (pessoaCompartilhamento.getCnae() != null && pessoaJuridica.getCnae() == null) {
			throw new NegocioException("O campo Código CNAE é obrigatório");
		}
		if (pessoaVo.getCodigoTipoFormaConstituicao() != null && pessoaJuridica.getFormaConstituicao() == null) {
			throw new NegocioException("O campo Natureza Jurídica é obrigatório");
		}
		if (pessoaVo.getCodigoEsferaAdministrativa() != null && pessoaJuridica.getCodigoEsferaAdministrativa() == null) {
			throw new NegocioException("O campo Esfera Administrativa é obrigatório");
		}
		if (isCampoPreechido(pessoaVo.getNumeroRegistroJuntaComercial()) && isCampoInvalido(pessoaJuridica.getNumeroRegistroJuntaComercial())) {
			throw new NegocioException("O campo N° do Registro no Órgão é obrigatório");
		}
		if (pessoaVo.getDataRegistroJuntaComercial() != null && pessoaJuridica.getDataRegistroJuntaComercial() == null) {
			throw new NegocioException("O campo Data do Registro no Órgão é obrigatório");
		}
		
	}

	private boolean isCampoInvalido(String alterado) {
		return alterado == null || alterado.isEmpty();
	}

	private boolean isCampoPreechido(String salvo) {
		return salvo != null && !salvo.isEmpty();
	}

	private boolean isVigente(PessoaCompartilhamento pessoa) {
		return pessoa.getDataHoraInicio() != null && 
				pessoa.getIdInstituicaoAtualizacao() == null;
	}
}