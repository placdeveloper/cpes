package br.com.sicoob.capes.cadastro.fachada;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.comum.util.DataUtils;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;

/**
 * A Classe MonitoracaoAtualizacaoCadastralFachada.
 */
@RemoteService
public class MonitoracaoAtualizacaoCadastralFachada extends
		CAPESCadastroBOCrudFachada<MensagemReplicacao> {
	
	/** A constante SITUACAO_PROCESSADAS_COM_ERRO. */
	private static final String SITUACAO_PROCESSADAS_COM_ERRO = "processadasComErro";
	
	/** A constante SITUACAO_NAO_PROCESSADAS. */
	private static final String SITUACAO_NAO_PROCESSADAS = "naoProcessadas";
	
	/** A constante SITUACAO_NAO_ENVIADAS. */
	private static final String SITUACAO_NAO_ENVIADAS = "naoEnviadas";
	
	/** A constante TAMANHO_PAGINA. */
	private static final Integer TAMANHO_PAGINA = 1000;
	
	/** O atributo delegate. */
	private transient MensagemReplicacaoDelegate delegate;

	/**
	 * Instancia um novo MonitoracaoAtualizacaoCadastralFachada.
	 */
	public MonitoracaoAtualizacaoCadastralFachada() {
		super("mensagem");
	}

	/**
	 * Deve pesquisar.
	 *
	 * @param situacaoPesquisa o valor de situacao pesquisa
	 * @param situacaoSolicitada o valor de situacao solicitada
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean devePesquisar(final String situacaoPesquisa, final String situacaoSolicitada) {
		
		return StringUtils.isEmpty(situacaoSolicitada)
				|| situacaoPesquisa.equals(situacaoSolicitada);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		
		final String situacao = (String) dto.getDados().get("situacao");
		RetornoDTO retorno = new RetornoDTO();
		
		// obtendo as mensagens não enviadas
		retorno.getDados().put(SITUACAO_NAO_ENVIADAS, obterMensagensNaoEnviadas(situacao));

		// obtendo as mensagens não processadas
		retorno.getDados().put(SITUACAO_NAO_PROCESSADAS, obterMensagensNaoProcessadas(situacao));

		// obtendo as mensagens processadas com erro
		retorno.getDados().put(SITUACAO_PROCESSADAS_COM_ERRO,
				obterMensagensProcessadasComErro(situacao));

		return retorno;
	}
	
	
	/**
	 * Criar mapa retorno dados.
	 *
	 * @param dto o valor de dto
	 * @return Map
	 */
	private Map<String, Object> criarMapaRetornoDados(ConsultaDto<MensagemReplicacao> dto) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("resultado", dto.getResultado());
		mapa.put("totalRegistros", dto.getTotalRegistros());
		return mapa;
	}
	
	/**
	 * Obter mensagens nao enviadas.
	 *
	 * @param situacao o valor de situacao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Map<String, Object> obterMensagensNaoEnviadas(final String situacao)
			throws BancoobException {
		
		Map<String, Object> resultado = null;
		if (devePesquisar(SITUACAO_NAO_ENVIADAS, situacao)) {

			MensagemReplicacao filtro = new MensagemReplicacao();
			filtro.setDataCadastro(new DateTimeDB(DataUtils.percorrer(new Date(), Calendar.MINUTE,
					MensagemReplicacao.DELAY).getTime()));
			
			ConsultaDto<MensagemReplicacao> criterios = new ConsultaDto<MensagemReplicacao>();
			criterios.setPagina(0);
			criterios.setTamanhoPagina(TAMANHO_PAGINA);
			criterios.setFiltro(filtro);
			resultado = criarMapaRetornoDados(obterDelegate()
					.pesquisarMensagensNaoEnviadasPorFiltro(criterios));
		}
		return resultado;
	}

	/**
	 * Obter mensagens nao processadas.
	 *
	 * @param situacao o valor de situacao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Map<String, Object> obterMensagensNaoProcessadas(final String situacao)
			throws BancoobException {
		
		Map<String, Object> resultado = null;
		if (devePesquisar(SITUACAO_NAO_PROCESSADAS, situacao)) {

			// um minuto atrás
			MensagemReplicacao filtro = new MensagemReplicacao();
			filtro.setDataEnvio(new DateTimeDB(DataUtils.percorrer(null, Calendar.MINUTE,
					MensagemReplicacao.DELAY).getTime()));

			ConsultaDto<MensagemReplicacao> criterios = new ConsultaDto<MensagemReplicacao>();
			criterios.setPagina(0);
			criterios.setTamanhoPagina(TAMANHO_PAGINA);
			criterios.setTipoProcura("naoProcessadas");
			criterios.setFiltro(filtro);
			resultado = criarMapaRetornoDados(obterDelegate().obterMensagensNaoProcessadas(criterios));
		}
		return resultado;
	}
	
	/**
	 * Obter mensagens processadas com erro.
	 *
	 * @param situacao o valor de situacao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Map<String, Object> obterMensagensProcessadasComErro(final String situacao)
			throws BancoobException {

		Map<String, Object> resultado = null;
		if (devePesquisar(SITUACAO_PROCESSADAS_COM_ERRO, situacao)) {	
			ConsultaDto<MensagemReplicacao> criterios = new ConsultaDto<MensagemReplicacao>();
			criterios.setPagina(0);
			criterios.setTamanhoPagina(TAMANHO_PAGINA);
			criterios.setTipoProcura(SITUACAO_PROCESSADAS_COM_ERRO);
			resultado = criarMapaRetornoDados(obterDelegate().pesquisar(criterios));
		}
		return resultado;
	}
	
	/**
	 * Enviar mensagens.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO enviarMensagens(RequisicaoReqDTO dto) throws BancoobException {
		
		List<MensagemReplicacao> mensagens = (List<MensagemReplicacao>) dto.getDados().get("mensagens");
		validarEntidade(mensagens, "no envio de mensagens");
		Set<String> operacoes = new HashSet<String>();
		for (MensagemReplicacao mensagem : mensagens) {
			operacoes.add(mensagem.getIdentificadorOperacao());
		}
		obterDelegate().enviarMensagens(operacoes.toArray(new String[operacoes.size()]));
		return new RetornoDTO(); 
	}

	/**
	 * Reenviar mensagens.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO reenviarMensagens(RequisicaoReqDTO dto) throws BancoobException {
		
		List<MensagemReplicacao> mensagens = (List<MensagemReplicacao>) dto.getDados().get(
				"mensagens");
		obterDelegate().reenviarMensagens(mensagens);
		return new RetornoDTO();
	}
	
	/**
	 * Reprocessar mensagem.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO reprocessarMensagem(RequisicaoReqDTO dto) throws BancoobException {
		MensagemReplicacao mensagem = (MensagemReplicacao) dto.getDados().get("mensagem");
		obterDelegate().reprocessarMensagem(mensagem);
		return new RetornoDTO();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemReplicacao obterEntidade(RequisicaoReqDTO dto) {
		return (MensagemReplicacao) dto.getDados().get(this.chaveMapa);
	}
	
	/**
	 * Excluir mensagem.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirMensagem(RequisicaoReqDTO dto) throws BancoobException {
		Integer id = (Integer) dto.getDados().get("idMensagem");
		obterDelegate().excluir(id);
		return new RetornoDTO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemReplicacaoDelegate obterDelegate() {
		if (delegate == null) {
			delegate = CAPESCadastroFabricaDelegates.getInstance()
					.criarMensagemReplicacaoDelegate();
		}
		return delegate;
	}

}
