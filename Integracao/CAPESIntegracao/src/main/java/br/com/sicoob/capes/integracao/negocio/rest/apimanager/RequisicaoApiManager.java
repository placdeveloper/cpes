package br.com.sicoob.capes.integracao.negocio.rest.apimanager;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.rest.RequisicaoRestful;
import br.com.sicoob.capes.integracao.negocio.rest.Resposta;

/**
 * Classe que faz a solicita��o para o API MANAGER.
 * 
 * <p>
 * NOTA: Toda requisi��o ao API MANAGER j� espera uma vari�vel de ambiente
 * <b>"api.manager.provider"</b> com o endere�o do servidor do API MANAGER.
 * Sendo assim, todo endere�o come�ado por "/" Ex: /fipe/consulta/veiculos ser�
 * substitu�do por "http://apimanager:8080/fipe/consulta/veiculos.
 * 
 * </p>
 * 
 * @author Bruno.Carneiro
 */
public class RequisicaoApiManager extends RequisicaoRestful {

	private TokenApiManager tokenApiManager;
	private RequisicaoRestful requisicaoRestful;
	private String chaveConsumidor;

	/**
	 * Construtor Padr�o
	 */
	public RequisicaoApiManager() {

	}

	/**
	 * Construtor
	 * 
	 * @param apiManagerToken
	 */
	public RequisicaoApiManager(TokenApiManager apiManagerToken) {
		this.tokenApiManager = apiManagerToken;
	}

	/**
	 * Define que deve-se fazer uma requisi��o para buscar o token, usando o
	 * client_credentials
	 * 
	 * @param consumerKey
	 *            A chave de consumo informada no api manager para a sua
	 *            aplica��o
	 * @return A instancia atual do ApiManagerRequestBuilder
	 */
	public RequisicaoApiManager(String chaveConsumidor) {
		this.chaveConsumidor = substituirVariaveisAmbiente(chaveConsumidor);
		requisicaoRestful = new RequisicaoRestful()
			.comEndereco("${api.manager.provider}token")
			.comParametro("grant_type", "client_credentials")
			.comAutorizacao("Basic", this.chaveConsumidor)
			.comMetodoHttpPOST();
	}

	/**
	 * Define que deve-se fazer uma requisi��o para buscar o token, usando o
	 * client_credentials, usu�rio e senha
	 * 
	 * @param chaveConsumidor
	 *            A chave de consumo informada no api manager para a sua
	 *            aplica��o
	 * @param usuario
	 *            O usu�rio para autentica��o
	 * @param senha
	 *            A senha para autentica��o
	 */
	public RequisicaoApiManager(String chaveConsumidor, String usuario, String senha) {
		this.chaveConsumidor = substituirVariaveisAmbiente(chaveConsumidor);
		requisicaoRestful = new RequisicaoRestful()
			.comEndereco("${api.manager.provider}token")
			.comParametro("grant_type", "password")
			.comParametro("username", usuario)
			.comParametro("password", senha)
			.comAutorizacao("Basic", this.chaveConsumidor)
			.comMetodoHttpPOST();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Se o endere�o come�a com "/" o endere�o � mudado para o endere�o do api
	 * manager.
	 * </p>
	 */
	@Override
	public RequisicaoRestful comEndereco(String endereco) {
		if (endereco.startsWith("/")) {
			return super.comEndereco(endereco.replaceFirst("/", "\\$\\{api.manager.provider\\}/"));
		} else {
			return super.comEndereco(endereco);
		}
	}
	
	/**
	 * Efetua uma requisi��o para o api manager
	 * 
	 * @return
	 * @throws BancoobException
	 */
	@Override
	public Resposta<Void> requisitar() throws BancoobException {
		adicionarAccessToken();
		return super.requisitar();
	}

	/**
	 * Efetua uma requisi��o para o api manager
	 * 
	 * @param tipoRetorno
	 * @return
	 * @throws BancoobException
	 */
	@Override
	public <T> Resposta<T> requisitar(Class<T> tipoRetorno) throws BancoobException {
		adicionarAccessToken();
		return super.requisitar(tipoRetorno);
	}

	/**
	 * Recupera um token do api manager. Se foi determinado uma forma busca um
	 * novo sempre que necess�rio.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	private TokenApiManager obterTokenApiManager() throws BancoobException {
		//TODO: bruno.carneiro Adicionar token no cache.
//		ServicoCache servicoCache = FabricaServicos.getInstance().criarServicoCache();
//		TokenApiManager token = (TokenApiManager) servicoCache.recuperar("CAPES_CACHE_TOKEN_API_MANAGER");
//		if (token == null) {
//			criarNovoToken();
//			token = obterTokenApiManager();
//			if (token != null) {
//				servicoCache.armazenar("", token, token.getExpiraEm());
//			}
//		}
//		
		
		boolean deveBuscarToken = tokenApiManager == null || tokenApiManager.isExpirado();
		if (deveBuscarToken && requisicaoRestful != null) {
			criarNovoToken();
		}
		return tokenApiManager;
	}

	/**
	 * Cria um novo token de acesso � requisi��o
	 * 
	 * @throws BancoobException
	 */
	private void criarNovoToken() throws BancoobException {
		getLogger().info("Recuperando novo token do API MANAGER");
		tokenApiManager = requisicaoRestful.requisitar(TokenApiManager.class).getConteudo();
		atualizarTokenRequester(tokenApiManager);
		getLogger().info("Novo token do API MANAGER recuperado com sucesso.");
	}

	/**
	 * Se possui um refresh token utiliza para as novas requisi��es
	 * 
	 * @param apiManagerToken
	 */
	private void atualizarTokenRequester(TokenApiManager apiManagerToken) {
		if (apiManagerToken.getRefreshToken() != null && chaveConsumidor != null) {
			requisicaoRestful = new RequisicaoRestful()
				.comEndereco("${api.manager.provider}token")
				.comParametro("grant_type", "refresh_token")
				.comParametro("refresh_token", apiManagerToken.getRefreshToken())
				.comAutorizacao("Basic", chaveConsumidor)
				.comMetodoHttpPOST();
		}
	}

	/**
	 * Adiciona a chave de acesso � requisi��o
	 * 
	 * @throws BancoobException
	 */
	private void adicionarAccessToken() throws BancoobException {
		TokenApiManager token = obterTokenApiManager();
		if (token != null) {
			comAutorizacao(token.getTipo(), token.getAccessToken());
		}
	}
	
	/**
	 * Adiciona um Token do API Manager.
	 * @param tokenAPIManager
	 */
	public void setApiManagerToken(TokenApiManager tokenApiManager) {
		this.tokenApiManager = tokenApiManager;
	}

}