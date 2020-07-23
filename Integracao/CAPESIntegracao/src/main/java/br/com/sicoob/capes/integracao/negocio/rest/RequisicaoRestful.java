package br.com.sicoob.capes.integracao.negocio.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * Classe que faz a requisição para os serviços Restful.
 *
 * @author Bruno.Carneiro
 */
public class RequisicaoRestful {
	
	public static final String METODO_HTTP_GET = "GET";
	public static final String METODO_HTTP_POST = "POST";
	public static final String METODO_HTTP_HEAD = "HEAD";
	public static final String METODO_HTTP_DELETE = "DELETE";
	public static final String METODO_HTTP_TRACE = "TRACE";
	public static final String METODO_HTTP_OPTIONS = "OPTIONS";
	public static final String METODO_HTTP_PUT = "PUT";
	
	public static final String ENCODE_ASCII = "US-ASCII";
	public static final String ENCODE_CP1252 = "Cp1252";
	public static final String ENCODE_ISO_8859_1 = "ISO-8859-1";
	public static final String ENCODE_UTF_8 = "UTF-8";

	private String metodoHttp = METODO_HTTP_GET;
	private int timeout = 5000;
	private Object requisicao;
	private String endereco;
	private Map<String, String> cabecalhos = new HashMap<String, String>();
	private Map<String, String> parametros = new HashMap<String, String>();
	private String encode;
	private Class<?> classeRespostaErro;
	
	private static Gson gson;

	/**
	 * Define qual é o conteúdo enviado na requisição
	 * 
	 * @param requisicao
	 * @return
	 */
	public RequisicaoRestful comBodyEmJson(Object requisicao) {
		this.comCabecalho("Content-Type", "application/json");
		this.requisicao = requisicao;
		return this;
	}

	/**
	 * Define que o erro deve ser transformado em um objeto
	 * 
	 * @param classeRespostaErro
	 * @return
	 */
	public RequisicaoRestful comErroEmJson(Class<?> classeRespostaErro) {
		this.comCabecalho("Content-Type", "application/json");
		this.classeRespostaErro = classeRespostaErro;
		return this;
	}

	/**
	 * Define os parâmetros que serão utilizados na requisição
	 * 
	 * @param chave
	 * @param valor
	 * @return
	 */
	public RequisicaoRestful comParametro(String chave, String valor) {
		parametros.put(chave, valor);
		return this;
	}

	/**
	 * Define os parâmetros que serão utilizados na requisição
	 * 
	 * @param parametros
	 *            Um mapa com todos os parametros requeridos
	 * @return
	 */
	public RequisicaoRestful comParametros(Map<String, String> parametros) {
		this.parametros.putAll(parametros);
		return this;
	}

	/**
	 * Define o endereço completo do serviço, pode conter variáveis que serão
	 * substituidas pelas propriedades de sistema, por exemplo setando a
	 * variavel abc como abaixo:
	 * 
	 * <pre>
	 * System.setProperty(&quot;abc&quot;, &quot;http://teste.com&quot;)
	 * </pre>
	 * 
	 * e chamando o método com o valor "${abc}/teste", o valor final da
	 * requisição será "http://teste.com/teste".
	 * 
	 * Este método também normaliza a URL, isto é, remove duplicatas de /. Por
	 * exemplo a url http://localhost//teste/a//b será utilizada como
	 * http://localhost/teste/a/b.
	 * 
	 * @param endereco
	 * @return
	 */
	public RequisicaoRestful comEndereco(String endereco) {
		this.endereco = substituirVariaveisAmbiente(endereco).replaceAll("(?<!:)//", "/");
		return this;
	}

	/**
	 * Define a forma de requisição com o método GET
	 * @return
	 */
	public RequisicaoRestful comMetodoHttpGET() {
		return comMetodoHttp(METODO_HTTP_GET);
	}
	
	/**
	 * Define a forma de requisição com o método POST
	 * @return
	 */
	public RequisicaoRestful comMetodoHttpPOST() {
		return comMetodoHttp(METODO_HTTP_POST);
	}
	
	/**
	 * Define qual será a forma de comunicação. Pode-se usar um dos abaixo:
	 * POST, GET, HEAD e DELETE
	 * 
	 * @param metodoHttp
	 * @return
	 */
	public RequisicaoRestful comMetodoHttp(String metodoHttp) {
		this.metodoHttp = metodoHttp;
		return this;
	}

	/**
	 * Define qual é o tempo máximo de espera para conexão em milisegundos. Por
	 * padrão 5s
	 * 
	 * @param timeout
	 * @return
	 */
	public RequisicaoRestful comTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	/**
	 * Define um cabeçalho para a requisição.
	 * 
	 * @param chave
	 * @param valor
	 * @return
	 */
	public RequisicaoRestful comCabecalho(String chave, String valor) {
		this.cabecalhos.put(chave, valor);
		return this;
	}

	/**
	 * Define os cabeçalhos que serão usados na requisição.
	 * 
	 * @param cabecalhos
	 * @return
	 */
	public RequisicaoRestful comCabecalhos(Map<String, String> cabecalhos) {
		this.cabecalhos.putAll(cabecalhos);
		return this;
	}

	/**
	 * Passa para o cabeçalho a autorização solicitada.
	 * 
	 * @param tipo
	 *            Define o tipo. Por exemplo Basic, Bearer, etc.
	 * @param valor
	 *            Define o valor. Por exemplo um token.
	 * @return
	 */
	public RequisicaoRestful comAutorizacao(String tipo, String valor) {
		comCabecalho("Authorization", tipo + " " + valor);
		return this;
	}

	/**
	 * Transforma os encodes.
	 * 
	 * @param encodeOriginal
	 * @param encodeDestino
	 * @return
	 */
	public RequisicaoRestful comEncode(String encode) {
		this.encode = encode;
		return this;
	}

	/**
	 * Efetua a requisição com os parametros estabelecidos.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	public Resposta<Void> requisitar() throws BancoobException {
		return requisitar((Class<Void>) null);
	}

	/**
	 * Requisita a consulta do serviço solicitado.
	 * 
	 * @param tipoRetorno
	 * @return
	 * @throws BancoobException
	 */
	public <T> Resposta<T> requisitar(Class<T> tipoRetorno) throws BancoobException {
		getLogger().info("Requisitando a consulta ao serviço rest");
		HttpURLConnection connection = null;

		try {
			connection = criaConexaoHttp();
			escreverCorpoRequisicao(connection);
			Resposta<T> resposta = dispararRequisicao(connection, tipoRetorno);

			// Verifica se o código de retorno foi de "Não encontrado".
			if(resposta.getCodigoResposta() == HttpURLConnection.HTTP_NOT_FOUND) {
	        	return resposta;
	        }
			
			// Caso contrário, se o retorno não foi de sucesso, retorna uma exceção
			if (!resposta.isSucesso()) {
				throw new RespostaException("A requisição retornou o código de erro: " + resposta.getCodigoResposta(), resposta);
			}

			getLogger().info("Finalizando a consulta ao serviço rest");

			return resposta;

		} catch (IOException e) {
			RespostaException.lancarExcecao(e.getMessage());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}

	/**
	 * Faz a leitura do InputStream para gerar o texto de resposta.
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
//	private String lerInputStream(InputStream is) throws IOException {
//		getLogger().info("Lendo o inputStream");
//		InputStreamReader reader = null;
//		try {
//			reader = new InputStreamReader(is, encode != null ? encode : Charset.defaultCharset().name());
//			Scanner s = new Scanner(reader).useDelimiter("\\A");
//			return s.hasNext() ? s.next() : null;
//		} finally {
//			if (reader != null) {
//				reader.close();
//			}
//		}
//	}
	
	private String lerInputStream(InputStream is) throws IOException {
		getLogger().info("Lendo o inputStream");
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			String linha;
			br = new BufferedReader(new InputStreamReader(is, encode != null ? encode : Charset.defaultCharset().name()));
			while ((linha = br.readLine()) != null) {
				sb.append(linha);
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return sb.toString();
	}

	/**
	 * Transforma o JSON em um objeto informado.
	 * 
	 * @param json
	 * @param tipoRetorno
	 * @return
	 */
	protected <T> T transformaJsonEmObjeto(String json, Class<T> tipoRetorno) {
		if (tipoRetorno != null) {
			try {
				if (tipoRetorno.equals(Map.class)) {
					getLogger().info("Convertendo o JSON para um mapa de strings");
					Type type = new TypeToken<Map<String, String>>(){}.getType();
					return getGson().fromJson(json, type);
				} else {
					getLogger().info("Convertendo o JSON para o tipo " + tipoRetorno);
					return getGson().fromJson(json, tipoRetorno);
				}
			} catch (JsonParseException e) {
				getLogger().erro(e, "Erro ao converter JSON de resposta para o tipo: " + tipoRetorno);
			}
		}
		return null;
	}

	/**
	 * Dispara a requisição para o serviço solicitado.
	 * 
	 * @param connection
	 * @param tipoRetorno
	 * @return
	 * @throws IOException
	 */
	protected <T> Resposta<T> dispararRequisicao(HttpURLConnection connection, Class<T> tipoRetorno) throws IOException {
		getLogger().info("Disparando a requisicao");

		int codigoResposta = connection.getResponseCode();
		Resposta<T> resposta = new Resposta<T>();
		resposta.setCodigoResposta(codigoResposta);
		resposta.setSucesso(isCodigoRespostaSucesso(codigoResposta));

		InputStream inputStream = resposta.isSucesso() ? connection.getInputStream() : connection.getErrorStream();
		String respostaEmTexto = lerInputStream(inputStream);
		resposta.setTextoResposta(respostaEmTexto);

		if (resposta.isSucesso()) {
			resposta.setConteudo(transformaJsonEmObjeto(respostaEmTexto, tipoRetorno));
		} else {
			resposta.setErro(transformaJsonEmObjeto(respostaEmTexto, classeRespostaErro));
		}

		return resposta;
	}

	/**
	 * Escreve o corpo da requisição
	 * 
	 * @param connection
	 * @throws IOException
	 */
	protected void escreverCorpoRequisicao(HttpURLConnection connection) throws IOException {
		if (requisicao != null) {
			getLogger().info("Escrevendo os dados da requisicao em JSON");
			OutputStreamWriter writer = null;
			try {
				writer = new OutputStreamWriter(connection.getOutputStream(), encode != null ? encode : Charset.defaultCharset().name());
				// Se for string, já é o JSON
				String json = requisicao instanceof String ? requisicao.toString() : getGson().toJson(requisicao);
				writer.write(json);
				writer.flush();
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		}
	}

	/**
	 * Cria a conexão
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	protected HttpURLConnection criaConexaoHttp() throws MalformedURLException, IOException, ProtocolException {
		getLogger().info("Criando conexao com o endereco: " + endereco);

		StringBuilder enderecoFinal = new StringBuilder(endereco);

		String prefixo = "?";
		for (Entry<String, String> entry : parametros.entrySet()) {
			enderecoFinal.append(prefixo);
			enderecoFinal.append(entry.getKey());
			enderecoFinal.append("=");
			enderecoFinal.append(entry.getValue());
			prefixo = "&";
		}

		URL url = new URL(enderecoFinal.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod(metodoHttp);
		connection.setDoInput(true);
		connection.setReadTimeout(timeout);
		connection.setDoOutput(requisicao != null);
		for (Entry<String, String> entry : cabecalhos.entrySet()) {
			connection.addRequestProperty(entry.getKey(), entry.getValue());
		}

		connection.connect();

		getLogger().info("Conexao criada com sucesso");
		return connection;
	}

	/**
	 * Verifica se o código de resposta é de sucesso.
	 * 
	 * @param codigoResposta
	 * @return {@code boolean} se é um código de sucesso ou não
	 */
	protected boolean isCodigoRespostaSucesso(int codigoResposta) {
		return codigoResposta >= HttpURLConnection.HTTP_OK && codigoResposta < HttpURLConnection.HTTP_MULT_CHOICE;
	}

	/**
	 * Cria o Gson
	 * 
	 * @return
	 */
	protected static Gson getGson() {
		if (gson == null) {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
				public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
					return new Date(json.getAsJsonPrimitive().getAsLong());
				}
			});

			gson = builder.create();
		}
		return gson;
	}

	/**
	 * Substitui os valores das variáveis de ambiente.
	 * 
	 * @param valor
	 * @return
	 */
	protected String substituirVariaveisAmbiente(String valor) {
		Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher m = p.matcher(valor);

		StringBuffer sb = new StringBuffer();
		if (m.find()) {
			String property = System.getProperty(m.group(1));
			if (property != null) {
				m.appendReplacement(sb, property);
			}
		}
		m.appendTail(sb);

		return sb.toString();
	}

	/**
	 * Obtém o logger
	 * 
	 * @return
	 */
	protected ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}

}