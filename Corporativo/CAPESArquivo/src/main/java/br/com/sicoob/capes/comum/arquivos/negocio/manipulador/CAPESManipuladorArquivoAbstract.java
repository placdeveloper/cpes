/*
 * SICOOB
 * 
 * CAPESManipuladorArquivoAbstract.java(br.com.sicoob.capes.comum.arquivos.negocio.manipulador.impl.CAPESGerenciadorArquivoAbstract)
 */
package br.com.sicoob.capes.comum.arquivos.negocio.manipulador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.excecao.ArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.excecao.FalhaInesperadaException;
import br.com.sicoob.capes.comum.arquivos.excecao.TamanhoLinhaInvalidoException;
import br.com.sicoob.capes.comum.arquivos.excecao.UsoIncorretoApiException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo.TipoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.IdentificadorLinha;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.MapeamentoLinhas;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.ResumoImportacaoVo;
import br.com.sicoob.capes.comum.arquivos.util.CacheMetodoTratadorRegistro;
import br.com.sicoob.capes.comum.arquivos.util.StringUtils;
import br.com.sicoob.capes.comum.arquivos.util.conversao.Conversor;
import br.com.sicoob.capes.comum.arquivos.util.conversao.FabricaConversor;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Implementação padrão do gerenciador de arquivo para importação e exportação
 * de arquivos texto para troca de dados entre sistemas e/ou funcionalidades.
 * 
 * Criado em 22/7/2014.
 * 
 * @author rodrigo.chaves
 */
public abstract class CAPESManipuladorArquivoAbstract implements CAPESManipuladorArquivo {

	/**
	 * Extensão usada para salvar arquivos temporários usados na exportação.
	 */
	private static final String EXTENSAO_ARQUIVO_TEMPORARIO = ".txt.tmp";

	/** O atributo cacheMetodoTratador. */
	private CacheMetodoTratadorRegistro cacheMetodoTratador;

	/**
	 * Construtor padrão. Inicializa o cache de métodos para tratar registros de
	 * arquivo.
	 */
	public CAPESManipuladorArquivoAbstract() {

		cacheMetodoTratador = new CacheMetodoTratadorRegistro(getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	public <D extends DadosArquivoVO> InputStream exportar(D dadosArquivo) throws ArquivoInvalidoException {
		// Arquivo temporário.
		File arquivoTemporario = criarArquivoTemporario();
		InputStream arquivo = null;
		OutputStream out = null;
		String linha = null;
		try {

			// Objetos para escrita no arquivo.
			out = new FileOutputStream(arquivoTemporario);

			// Gerando registros do arquivo.
			Arquivo metadadosArquivo = dadosArquivo.getMetadados();
			List<RegistroArquivo> registros = dadosArquivo.getRegistros();
			if (registros != null && !registros.isEmpty()) {
				final String codificacao = metadadosArquivo.codificacao().getCodigo();
				for (int i = 0; i < registros.size(); i++) {
					RegistroArquivo registro = registros.get(i);
					linha = gerarLinha(registro, metadadosArquivo);
					IOUtils.write(linha, out, codificacao);

					// Se não for a última linha, coloca o separador.
					if (i < (registros.size() - 1)) {
						IOUtils.write(IOUtils.LINE_SEPARATOR, out, codificacao);
					}
				}
			}
			arquivo = new FileInputStream(arquivoTemporario);
		} catch (IOException e) {
			throw new FalhaInesperadaException("exception.io.arqtemporario.ler", e, arquivoTemporario.toString());
		} finally {
			IOUtils.closeQuietly(out);
			out = null; // Garbage Collector.
			linha = null; // Garbage Collector.
		}

		return arquivo;
	}

	/**
	 * Cria um novo arquivo temporário em disco.
	 * 
	 * @return File - arquivo temporário criado.
	 */
	private File criarArquivoTemporario() {
		String prefixo = getClass().getSimpleName();
		String extensao = EXTENSAO_ARQUIVO_TEMPORARIO;
		try {
			return File.createTempFile(prefixo, extensao);

		} catch (IOException e) {
			throw new FalhaInesperadaException("exception.io.arqtemporario.criar", e);
		}

	}

	/**
	 * Gera uma linha do arquivo baseado nos metadados (<code>Annotation</code>
	 * de {@link CampoArquivo}) para obter os valores, posições, formatos, etc,
	 * de cada campo a ser escrito na linha do arquivo.
	 * 
	 * @param dados
	 *            - Instância da classe que represeta uma linha do arquivo que
	 *            será gerado.
	 * @param metadadosArquivo
	 *            - Propriedades do arquivo que está sendo gerado.
	 * 
	 * @return String - uma linha do arquivo.
	 * 
	 * @see #exportar(DadosArquivoVO).
	 * @see TipoArquivo.
	 */
	public static String gerarLinha(RegistroArquivo dados, Arquivo metadadosArquivo) {
		String linhaGerada = null;
		if (metadadosArquivo.tipoArquivo().equals(TipoArquivo.CAMPOS_DELIMITADOS_POR_TAMANHO)) {
			linhaGerada = gerarLinhaCamposTamanho(dados);
		} else if (metadadosArquivo.tipoArquivo().equals(TipoArquivo.CAMPOS_DELIMITADOS_POR_SEPARADOR)) {
			linhaGerada = gerarLinhaCamposComSeparador(dados, metadadosArquivo.separadorCampos());
		}
		
		if(metadadosArquivo.removerAcentuacao()){
			StringUtils.removerAcentos(linhaGerada);
		}
		
		return linhaGerada;
	}

	/**
	 * Gera a linha que será inserida no arquivo.
	 * 
	 * @param dados
	 *            - Instância da classe que represeta uma linha do arquivo que
	 *            será gerado.
	 * @param separadorCampos
	 *            - String com o separador de campos da linha.
	 * @return String - a linha gerada.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String gerarLinhaCamposComSeparador(RegistroArquivo dados, String separadorCampos) {

		Field[] propriedades = dados.getClass().getDeclaredFields();
		SortedMap<Integer, Field> propriedadesOrdenadas = new TreeMap<Integer, Field>();
		for (int i = 0; i < propriedades.length; i++) {
			Field propriedade = propriedades[i];

			// Verifica se a propriedade é um campo do arquivo.
			CampoArquivo metadados = propriedade.getAnnotation(CampoArquivo.class);
			if (metadados != null) {
				propriedadesOrdenadas.put(metadados.ordemCampo(), propriedade);
			}
		}
		FabricaConversor fabrica = FabricaConversor.getInstancia();
		StringBuilder linha = new StringBuilder();
		List<Field> camposArquivo = new ArrayList<Field>();
		camposArquivo.addAll(propriedadesOrdenadas.values());
		for (int i = 0; i < camposArquivo.size(); i++) {
			Field campo = camposArquivo.get(i);

			// Convertendo o valor.
			Object valor = ReflexaoUtils.getValorAtributo(dados, campo, false);
			Conversor conversor = fabrica.getConversor(campo.getType());
			String valorConvertido = conversor.converterParaString(valor,
			        campo.getAnnotation(CampoArquivo.class));

			// Adicionando na linha.
			linha.append(valorConvertido);
			if (i < (camposArquivo.size() - 1)) {
				linha.append(separadorCampos);
			}
		}
		return linha.toString();
	}

	/**
	 * Gera a linha que será inserida no arquivo.
	 * 
	 * @param dados
	 *            - Instância da classe que represeta uma linha do arquivo que
	 *            será gerado.
	 * @return String - a linha gerada.
	 */
	public static String gerarLinhaCamposTamanho(RegistroArquivo dados) {
		StringBuilder linha = new StringBuilder();
		List<Field> propriedades = ReflexaoUtils.getFields(dados.getClass(), false);
		for (Field propriedade : propriedades) {

			// Verifica se a propriedade é um campo do arquivo.
			CampoArquivo metadados = propriedade.getAnnotation(CampoArquivo.class);
			if (metadados != null) {

				// Posições.
				int inicio = metadados.inicio();
				int tamanho = metadados.tamanho();
				int fim = inicio + tamanho;

				String valorConvertido = converterValor(dados, propriedade);
				// Inserindo na linha.
				if (fim > linha.length()) {
					linha.setLength(fim);
				}
				linha.replace(inicio, fim, valorConvertido);
			}
		}
		return linha.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public <D extends DadosArquivoVO> D importar(InputStream conteudoArquivo,
	        Class<D> classeDadosArquivo) throws ArquivoInvalidoException {

		LineIterator iter = null;
		String codificacao = null;
		try {
			Arquivo metadados = classeDadosArquivo.getAnnotation(Arquivo.class);
			try {
				codificacao = metadados.codificacao().getCodigo();
				iter = IOUtils.lineIterator(conteudoArquivo, codificacao);
			} catch (IOException e) {
				throw new ArquivoInvalidoException("exception.arquivo.io", e);
			}

			// Objeto que conterá os dados do arquivo após processamento.
			D dadosArquivo = criarInstanciaDadosArquivo(classeDadosArquivo);
			validarImportacao(metadados);

			// Estatísticas da importação.
			int totalRegistros = 0;
			int totalRejeitados = 0;

			// Lendo as linhas.
			List<RegistroArquivo> registros = new ArrayList<RegistroArquivo>();
			int i = 0; // contador linhas.
			while (iter.hasNext()) {
				String linha = iter.nextLine(); // linha do arquivo.

				SicoobLoggerPadrao.getInstance(getClass()).debug(linha);

				boolean temProximo = iter.hasNext();
				try {
					totalRejeitados += lerLinha(dadosArquivo, metadados, registros, i, linha,
					        temProximo);
				} catch (ArquivoInvalidoException e) {
					if (metadados.ignoraRegistroInvalido() && !e.isForcaExcecao()) {

						tratarRegistroInvalido(linha, i);
						totalRejeitados += 1;
					} else {
						throw e;
					}
				} finally {
					i++; // incrementa contador linhas.
				}
			}
			if (!registros.isEmpty()) {
				dadosArquivo.setRegistros(registros);
			} else {
				registros = null; // Garbage Collector.
			}

			// Objeto com o resumo da importação.
			totalRegistros = i;
			ResumoImportacaoVo resumo = new ResumoImportacaoVo(totalRegistros, totalRejeitados);
			dadosArquivo.setResumoImportacao(resumo);
			return dadosArquivo;
		} finally {
			LineIterator.closeQuietly(iter);
			IOUtils.closeQuietly(conteudoArquivo);
		}
	}

	/**
	 * Processa uma linha do arquivo e o transforma no tipo correto de objeto.
	 * 
	 * @param dadosArquivo
	 *            - Onde os dados de arquivo são armazenados.
	 * @param metadados
	 *            - Configurações da importação
	 * @param registros
	 *            - Registros do arquivo.
	 * @param indice
	 *            - Número da linha (começa em 0).
	 * @param linha
	 *            - Linha do arquivo.
	 * @param temProximo
	 *            - Indicador se ainda existem linhas (não é a última).
	 * @param <D>
	 *            - Tipo genérico dos dados do arquivo.
	 * @return int - <code>1</code> se o registro foi rejeitado ou
	 *         <code>0</code> caso tenha sido aceito.
	 * 
	 * @throws TamanhoLinhaInvalidoException.
	 * @throws ArquivoInvalidoException.
	 */
	private <D extends DadosArquivoVO> int lerLinha(D dadosArquivo, Arquivo metadados,
	        List<RegistroArquivo> registros, int indice, String linha, boolean temProximo)
	        throws ArquivoInvalidoException {

		int rejeitado = 0;
		validarCaracteresPorLinha(metadados, linha.length(), indice);

		// Verificando tipo do registro
		Class<? extends RegistroArquivo> tipoRegistro = getTipoRegistro(dadosArquivo, indice,
		        linha, temProximo);
		if (tipoRegistro != null) {
			RegistroArquivo registro = interpretarLinhaArquivo(linha, indice, tipoRegistro);
			if (metadados.utilizaEventos()) {
				if (!tratarRegistro(registro)) {
					rejeitado = 1;
				}
			}
			if (metadados.mantemReferencias()) {
				registros.add(registro);
			}
		} else {
			if (metadados.ignoraRegistroInvalido()) {
				rejeitado = 1;
			} else {
				throw new ArquivoInvalidoException("exception.arquivo.layout.registroinvalido");
			}
		}
		return rejeitado;
	}

	/**
	 * Obtem o tipo de registro da linha
	 * 
	 * @param dadosArquivo
	 *            Onde os dados de arquivo são armazenados
	 * @param indice
	 *            Número da linha (começa em 0).
	 * @param linha
	 *            Linha do arquivo.
	 * @param temProximo
	 *            Indicador se ainda existem linhas (não é a última).
	 * @return o tipo de registro
	 */
	protected <D extends DadosArquivoVO> Class<? extends RegistroArquivo> getTipoRegistro(
	        D dadosArquivo, int indice, String linha, boolean temProximo) {

		MapeamentoLinhas mapeamento = dadosArquivo.getClass().getAnnotation(MapeamentoLinhas.class);
		if (mapeamento == null) {
			throw new UsoIncorretoApiException("exception.dadosarquivo.annotation",
			        MapeamentoLinhas.class.getName(), dadosArquivo.getClass().getName());
		} else {
			Class<? extends RegistroArquivo> tipoRegistro = mapeamento.tipoPadrao();
			int inicio = mapeamento.inicio();
			int tamanho = mapeamento.tamanho();
			if ((inicio >= 0) && (tamanho > 0)) {
				String tipoLinha = linha.substring(inicio, inicio + tamanho);
				for (IdentificadorLinha identificador : mapeamento.identificadores()) {
					if (identificador.value().equals(tipoLinha)) {
						tipoRegistro = identificador.tipoRegistro();
						break;
					}
				}
			} else if (tipoRegistro.equals(RegistroArquivo.class)) {
				throw new UsoIncorretoApiException("exception.dadosarquivo.identificador");
			}
			return tipoRegistro;
		}
	}

	/**
	 * Responsável por invocar o método correto para tratamento de eventos, ou
	 * seja, o método que será chamado quando encontrar um registro (linha)
	 * válida no arquivo. Esse método só será invocado quando o valor de
	 * {@link Arquivo#utilizaEventos()} for <code>true</code>.
	 * 
	 * @param registro
	 *            - O objeto que representa a linha do arquivo.
	 * @return boolean - <code>true</code> caso o registro tenha sido aceito ou
	 *         <code>false</code> caso tenha sido rejeitado.
	 * @throws ArquivoInvalidoException.
	 */
	protected boolean tratarRegistro(RegistroArquivo registro) throws ArquivoInvalidoException {

		try {
			boolean registroAceito = false;
			Method metodoTratador = cacheMetodoTratador.get(registro.getClass());
			if (metodoTratador != null) {
				registroAceito = (Boolean) ReflexaoUtils.invocarMetodo(this, metodoTratador,
				        registro.getClass().cast(registro));
			}
			return registroAceito;
		} catch (BancoobException e) {
			throw new ArquivoInvalidoException(e);
		}
	}

	/**
	 * Valida se os objetos configurados para importação de arquivo texto
	 * satisfazem as necessidades da API, ou seja, se a classe de dados possui a
	 * <code>Annotation</code> {@link Arquivo} e se pelo menos uma das
	 * propriedades <code>mantemReferencias</code> ou
	 * <code>utilizaEventos</code> seja <code>true</code>.
	 * 
	 * @param <D>
	 *            - tipo genérico da classe de dados do arquivo.
	 * @param metadados
	 *            - O tipo implementado da classe que armazena os dados do
	 *            arquivo e da importação.
	 * 
	 * @see #importar(InputStream, Class).
	 */
	private <D> void validarImportacao(Arquivo metadados) {

		if (!metadados.mantemReferencias() && !metadados.utilizaEventos()) {
			throw new UsoIncorretoApiException("exception.arquivo.config.importacao");
		}
	}

	/**
	 * Cria dinamicamente uma instância da classe responsável pela importação do
	 * arquivo.
	 * 
	 * @param classeDadosArquivo
	 *            - Tipo da classe dos dados do arquivo.
	 * @param <D>
	 *            - tipo genérico da classe de dados do arquivo.
	 * @return D - A instância da classe de dados do arquivo.
	 * 
	 * @see #importar(InputStream, Class)
	 */
	private <D> D criarInstanciaDadosArquivo(Class<D> classeDadosArquivo) {
		try {
			return classeDadosArquivo.newInstance();
			
		} catch (InstantiationException e) {
			throw new UsoIncorretoApiException("exception.dadosarquivo.instancia", e,
			        classeDadosArquivo.getName());
		} catch (IllegalAccessException e) {
			throw new UsoIncorretoApiException("exception.dadosarquivo.instancia", e,
			        classeDadosArquivo.getName());
		}
	}

	/**
	 * Valida a quantidade de caracteres em uma linha do arquivo caso a
	 * quantidade seja fixa e especificada na <code>Annotation</code>
	 * {@link Arquivo} de nome <code>caracteresPorLinha</code> seja maior que 0
	 * (zero).
	 * 
	 * @param metadados
	 *            - O tipo implementado da classe que armazena os dados do
	 *            arquivo e da importação.
	 * @param tamanhoLinha
	 *            - Tamanho da linha lida do arquivo.
	 * @param indiceAtual
	 *            - Indice (número da linha começando em 0) da linha do arquivo.
	 * 
	 * @throws TamanhoLinhaInvalidoException
	 *             - Caso a validação de caracteres por linha esteja habilitada
	 *             ({@link Arquivo#caracteresPorLinha()} maior que zero) e o
	 *             tamanho da linha esteja incorreto.
	 * 
	 * @see #importar(InputStream, Class).
	 */
	private void validarCaracteresPorLinha(Arquivo metadados, int tamanhoLinha, int indiceAtual)
	        throws TamanhoLinhaInvalidoException {

		int caracteresPorLinha = metadados.caracteresPorLinha();
		boolean utilizaMinimo = metadados.caracteresMinimosPorLinha();
		if ((caracteresPorLinha > 0)
		        && ((!utilizaMinimo && (tamanhoLinha != caracteresPorLinha)) || (utilizaMinimo && (tamanhoLinha < caracteresPorLinha)))) {

			// Lança exceção de tamanho de linha inválida.
			throw new TamanhoLinhaInvalidoException("exception.tamanho.linha.invalido",
			        caracteresPorLinha, tamanhoLinha, indiceAtual + 1);
		}
	}

	/**
	 * Interpreta uma linha do arquivo e, baseado nos metadados (
	 * <code>Annotation</code> de {@link CampoArquivo}), preenche as
	 * propriedades da classe que representa essa linha do arquivo.
	 * 
	 * @param linha
	 *            - Conteúdo da linha do arquivo.
	 * @param indice
	 *            - O número da linha.
	 * @param tipoRegistro
	 *            - O tipo da classe.
	 * @param <T>
	 *            - tipo genérico da classe que representa a linha do arquivo.
	 * @return T - A instância da classe que representa a linha do arquivo.
	 * @throws ArquivoInvalidoException
	 *             - Caso ocorra algum erro ao extrair algum dado da linha (erro
	 *             de layout, formato de campo, etc).
	 * 
	 * @see #importar(InputStream, Class)
	 */
	private <T extends RegistroArquivo> T interpretarLinhaArquivo(String linha, int indice,
	        Class<T> tipoRegistro) throws ArquivoInvalidoException {

		T registro = ReflexaoUtils.instanciar(tipoRegistro);
		registro.setNumeroLinha(indice);
		List<Field> propriedades = ReflexaoUtils.getFields(tipoRegistro, false);
		for (Field propriedade : propriedades) {
			if (propriedade.isAnnotationPresent(CampoArquivo.class)) {
				Object valorConvertido = extrairValorLinha(linha, registro, propriedade);
				ReflexaoUtils.setValorAtributo(registro, propriedade, valorConvertido, false);
			}
		}
		return registro;
	}

	/**
	 * Extrair valor linha.
	 *
	 * @param <T> o tipo generico
	 * @param linha o valor de linha
	 * @param registro o valor de registro
	 * @param propriedade o valor de propriedade
	 * @return Object
	 * @throws ArquivoInvalidoException lança a exceção ArquivoInvalidoException
	 * @throws CampoArquivoInvalidoException lança a exceção CampoArquivoInvalidoException
	 */
	@SuppressWarnings("rawtypes")
	protected <T> Object extrairValorLinha(String linha, T registro, Field propriedade) throws ArquivoInvalidoException, CampoArquivoInvalidoException {

		Object valorConvertido = null;

		// Verifica se a propriedade é um campo do arquivo.
		CampoArquivo metadados = propriedade.getAnnotation(CampoArquivo.class);
		if (metadados != null) {
			int inicio = metadados.inicio();
			int fim = inicio + metadados.tamanho();
			String valor = null;
			try {
				valor = linha.substring(inicio, fim);
			} catch (IndexOutOfBoundsException e) {
				throw new ArquivoInvalidoException("exception.arquivo.layout.indice",
				        propriedade.getName(), inicio, fim, linha.length(), e);
			}

			// Convertido o valor para o tipo correto e atribuindo no objeto
			Conversor conversor = FabricaConversor.getInstancia().getConversor(propriedade.getType());
			valorConvertido = conversor.converterDeString(valor, metadados);
		}
		return valorConvertido;
	}

	/**
	 * Método de evento de erro padrão. Esse método será invocado quando alguma
	 * inconsistência for encontrada na linha. Ele pode ser útil para tratamento
	 * específico de problemas, como por exemplo inserir alguma informação no
	 * banco de dados.
	 * 
	 * @param linha
	 *            - conteúdo da linha inválida.
	 * @param numeroLinha
	 *            - número da linha inválida.
	 * @see #tratarRegistro(RegistroArquivo)
	 */
	protected void tratarRegistroInvalido(String linha, int numeroLinha) throws ArquivoInvalidoException {
		// Sem implementação padrão.
	}
	
	/**
	 * Converter valor.
	 *
	 * @param dados o valor de dados
	 * @param propriedade o valor de propriedade
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static String converterValor(Object dados, Field propriedade) {
		FabricaConversor conversores = FabricaConversor.getInstancia();
		Class<?> tipoPropriedade = propriedade.getType();
		CampoArquivo metadados = propriedade.getAnnotation(CampoArquivo.class);
		String valorConvertido = StringUtils.EMPTY;
		int tamanho = metadados.tamanho();
		char complemento = metadados.complemento();
		Object valor = ReflexaoUtils.getValorAtributo(dados, propriedade, false);
		if (valor != null) {
			if (Collection.class.isAssignableFrom(tipoPropriedade)) {
				Collection<?> colecao = (Collection<?>) valor;
				StringBuilder temp = new StringBuilder();
				for (Object objeto : colecao) {
					List<Field> propriedades = ReflexaoUtils.getFields(objeto.getClass(), false);
					for (Field prop : propriedades) {
						temp.append(converterValor(objeto, prop));
					}
				}
				valorConvertido = temp.toString();
			} else {
				Conversor conversor = conversores.getConversor(propriedade.getType());
				valorConvertido = conversor.converterParaString(valor, metadados);
			}
			valorConvertido = StringUtils.completar(valorConvertido, complemento, tamanho, metadados.alinhamento(), true);
		} else {
			if (metadados.vazioSeNulo()) {
				complemento = ' ';
			}
			valorConvertido = StringUtils.completar(valorConvertido, complemento, tamanho, metadados.alinhamento(), true);
		}

		return valorConvertido;
	}

}