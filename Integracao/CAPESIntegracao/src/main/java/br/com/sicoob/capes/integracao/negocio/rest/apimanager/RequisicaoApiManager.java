package br.com.sicoob.capes.integracao.negocio.rest.apimanager;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.rest.RequisicaoRestful;
import br.com.sicoob.capes.integracao.negocio.rest.Resposta;

/**
 * Classe que faz a solicitação para o API MANAGER.
 * 
 * <p>
 * NOTA: Toda requisição ao API MANAGER já espera uma variável de ambiente
 * <b>"api.manager.provider"</b> com o endereço do servidor do API MANAGER.
 * Sendo assim, todo endereço começado por "/" Ex: /fipe/consulta/veiculos será
 * substituído por "http://apimanager:8080/fipe/consulta/veiculos.
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
	 * Construtor Padrão
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
	 * Define que deve-se fazer uma requisição para buscar o token, usando o
	 * client_credentials
	 * 
	 * @param consumerKey
	 *            A chave de consumo informada no api manager para a sua
	 *            aplicação
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
	 * Define que deve-se fazer uma requisição para buscar o token, usando o
	 * client_credentials, usuário e senha
	 * 
	 * @param chaveConsumidor
	 *            A chave de consumo informada no api manager para a sua
	 *            aplicação
	 * @param usuario
	 *            O usuário para autenticação
	 * @param senha
	 *            A senha para autenticação
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
	 * Se o endereço começa com "/" o endereço é mudado para o endereço do api
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
	 * Efetua uma requisição para o api manager
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
	 * Efetua uma requisição para o api manager
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
	 * novo sempre que necessário.
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
	 * Cria um novo token de acesso à requisição
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
	 * Se possui um refresh token utiliza para as novas requisições
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
	 * Adiciona a chave de acesso à requisição
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