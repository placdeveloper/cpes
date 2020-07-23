/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

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
import br.com.sicoob.capes.cadastro.anotacao.FabricaDefinicoesDetalheAnotacao;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.DetalharAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ObservacaoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.cadastro.negocio.vo.SumarioAnotacaoVO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fachada para as anotações.
 * @author Erico.Junior
 */
@RemoteService
public class AnotacaoFachada extends CAPESCadastroBOFachada {

	/** A constante ANOTACAO. */
	private static final String ANOTACAO = "anotacao";
	
	/** A constante IMPRIMIR_RELATORIO. */
	private static final String IMPRIMIR_RELATORIO = "imprimirRelatorio";

	/** A constante TIPO_ANOTACAO. */
	private static final String TIPO_ANOTACAO = "tipoAnotacao"; 
	
	/** A constante PESSOA. */
	private static final String PESSOA = "pessoa";
	
	/** O atributo anotacaoDelegate. */
	private AnotacaoDelegate anotacaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate();
	
	/** O atributo tipoAnotacaoDelegate. */
	private TipoAnotacaoDelegate tipoAnotacaoDelegate = 
		CAPESCadastroFabricaDelegates.getInstance().criarTipoAnotacaoDelegate();

	/** O atributo pessoaDelegate. */
	private PessoaCompartilhamentoDelegate pessoaDelegate = 
		CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/** O atributo detalharAnotacaoDelegate. */
	private DetalharAnotacaoDelegate detalharAnotacaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarDetalharAnotacaoDelegate(); 
	
	/** O atributo observacaoAnotacaoDelegate. */
	private ObservacaoAnotacaoDelegate observacaoAnotacaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarObservacaoAnotacaoDelegate(); 
	
	private ParametroDelegate parametroDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarParametroDelegate();
	
	/**
	 * Baixar anotacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO baixarAnotacao(RequisicaoReqDTO dto) throws BancoobException {
		Anotacao anotacao = (Anotacao) dto.getDados().get(ANOTACAO);
		anotacaoDelegate.baixarAnotacao(anotacao);
		return new RetornoDTO();
	}

	/**
	 * Flexibilizar anotacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO flexibilizarAnotacao(RequisicaoReqDTO dto) throws BancoobException {
		Anotacao anotacao = (Anotacao) dto.getDados().get(ANOTACAO);
		anotacaoDelegate.flexibilizar(anotacao);
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
		RetornoDTO retornoDTO = new RetornoDTO();
		Anotacao anotacao = (Anotacao) dto.getDados().get(ANOTACAO);
		
		anotacaoDelegate.incluir(anotacao);
		boolean imprimirRelatorio = tipoAnotacaoDelegate.possuiSaidaTipoAnotacao(anotacao.getTipoAnotacao());
		
		retornoDTO.getDados().put(ANOTACAO, anotacao);
		retornoDTO.getDados().put(IMPRIMIR_RELATORIO, imprimirRelatorio);
		
		return retornoDTO;
	}

	/**
	 * Obter dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		Anotacao anotacao = (Anotacao) dto.getDados().get(ANOTACAO);
		
		List<DetalheAnotacaoDTO> detalhes = detalharAnotacaoDelegate.detalharAnotacao(anotacao);
		List<DefinicaoDTO> definicoes = obterDefinicoes(detalhes);
		
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("detalhes", detalhes);
		retorno.getDados().put("definicoes", definicoes);
		return  retorno;
	}

	/**
	 * Obter definicoes.
	 *
	 * @param detalhes o valor de detalhes
	 * @return List
	 */
	private List<DefinicaoDTO> obterDefinicoes(List<DetalheAnotacaoDTO> detalhes){
		
		List<DefinicaoDTO> definicoes = new ArrayList<DefinicaoDTO>();
		
		if(detalhes != null && !detalhes.isEmpty()) {
			FabricaDefinicoesDetalheAnotacao fabrica = 
					FabricaDefinicoesDetalheAnotacao.getInstance();
			definicoes = fabrica.obterDefinicoes(detalhes.get(0));
		}

		return definicoes;
	}
	
	/**
	 * Obter observacao anotacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterObservacaoAnotacao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO resultadoDto = new RetornoDTO();
		resultadoDto.getDados().put("listaObservacaoAnotacao",
				observacaoAnotacaoDelegate.consultarObservacaoAnotacao(Short.valueOf(dto.getDados().get("codigoTipoObservacaoAnotacao").toString())));
		return resultadoDto;
	}
	
	/**
	 * Obter dados anotacoes selecao.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosAnotacoesSelecao(RequisicaoReqDTO dto) throws BancoobException {

		try {
			Map<String, Object>  dados = dto.getDados();
			PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dados.get(PESSOA);
			
			Anotacao anotacao = new Anotacao();
			anotacao.setPessoaCompartilhamento(pessoa);

			ConsultaAnotacaoDTO consultaDto = new ConsultaAnotacaoDTO();
			consultaDto.setFiltro(anotacao);
			
			consultaDto.setAnotacoesBaixadas(false);
			List<Anotacao> vigentes = anotacaoDelegate.listarAnotacoesPorFiltro(consultaDto);
			
			consultaDto.setAnotacoesBaixadas(true);
			List<Anotacao> baixadas = anotacaoDelegate.listarAnotacoesPorFiltro(consultaDto);
			
			DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
			resultadoDto.getDados().put("listaVigentes", vigentes);
			resultadoDto.getDados().put("listaBaixadas", baixadas);
			return resultadoDto;
			
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new DadosSelGeralDTO();
	}

	/**
	 * Obter dados selecao.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		
		try {
			DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
			Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
			Boolean habilitado = parametroDelegate.obterParametroValorBoolean(ParametroEnum.EXIBIR_BOTAO_BAIXAR_ANOTACAO.getCodigo(), idInstituicao);
			PessoaCompartilhamento pessoa = obterPessoaSelecionada(dto);
			ConsultaDto<SumarioAnotacaoVO> consultaDto = anotacaoDelegate.pesquisarSumarioAnotacao(pessoa);
			
			resultadoDto.getDados().put("exibirBotaoBaixaAnotacao", habilitado);
			resultadoDto.getDados().put("lista", consultaDto.getResultado());
			
			return resultadoDto;
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
		return new DadosSelGeralDTO();
	}

	/**
	 * Obter pessoa selecionada.
	 *
	 * @param dto o valor de dto
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaCompartilhamento obterPessoaSelecionada(RequisicaoReqDTO dto) throws BancoobException {
		return (PessoaCompartilhamento) dto.getDados().get(PESSOA);
	}
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		PessoaCompartilhamento pessoa = obterPessoaSelecionada(dto);
		retorno.getDados().put("tiposAnotacao", tipoAnotacaoDelegate.listarTiposAnotacaoManualAtivos(pessoa.getPessoa().getTipoPessoa()));
		return retorno;
	} 

	/**
	 * Obter tipo anotacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterTipoAnotacao(RequisicaoReqDTO dto) throws BancoobException {
		TipoAnotacao tipoAnotacao = (TipoAnotacao) dto.getDados().get(TIPO_ANOTACAO);
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("tipoAnotacao", tipoAnotacaoDelegate.obter(tipoAnotacao.getCodTipoAnotacao()));
		return retorno;
	}

	/**
	 * Obter pessoa cadastro unico.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterPessoaCadastroUnico(RequisicaoReqDTO dto) throws BancoobException {
		Integer idPessoaLegado = (Integer)dto.getDados().get("numPessoa");
		PessoaCompartilhamento pessoa = pessoaDelegate.consultarPessoaPorIdPessoaLegado(idPessoaLegado);
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put(PESSOA, pessoa);
		return retorno;
	}
	
	/**
	 * Baixar anotacao sem validação.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO baixarAnotacaoSemValidacao(RequisicaoReqDTO dto) throws BancoobException {
		Anotacao anotacao = (Anotacao) dto.getDados().get(ANOTACAO);
		anotacaoDelegate.baixarAnotacaoSemValidar(anotacao);
		return new RetornoDTO();
	}

}
