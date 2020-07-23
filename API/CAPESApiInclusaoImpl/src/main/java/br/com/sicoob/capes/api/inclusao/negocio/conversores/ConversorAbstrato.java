package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe ConversorAbstrato.
 * 
 * @param <E>
 *            a entidade usada para a conversão
 * @param <D>
 *            o DTO usado para a conversao
 * @author bruno.carneiro
 */
public abstract class ConversorAbstrato<E extends CAPESEntidade<?>, D extends RegistroInclusaoDTO<?>> implements Conversor<E, D> {
	
	private static final String CHAVE_CACHE_ID_PESSOA_COMPARTILHAMENTO = "CAPES_CACHE_ID_PESSOA_ID_INSTITUICAO_";
	private static final int CACHE_TIMEOUT = 12 * 60 * 60 * 1000; // 12 horas.

	/**
	 * {@inheritDoc}
	 */
	public E obterEntidade(D dto) throws BancoobException {
		E entidade = instanciarEntidade(dto);
		copiarPropriedades(entidade, dto);
		adicionarInformacoesPessoa(entidade, dto);
		definirInformacoesAdicionais(entidade, dto);
		anexarDocumentosComprobatorios(entidade, dto);
		return entidade;
	}

	/**
	 * {@inheritDoc}
	 */
	public D obterDTO(E entidade) throws BancoobException {
		D dto = instanciarDTO(entidade);
		copiarPropriedades(dto, entidade);
		definirInformacoesAdicionais(dto, entidade);
		return dto;
	}
	
	/**
	 * Copia as propriedades ignorando as que forem de tipos diferentes (deverão
	 * ser copiadas no conversor próprio.
	 * 
	 * @param destino
	 *            O objeto a ter as propriedades definidas.
	 * @param fonte
	 *            O objeto a ter as propriedades copiadas
	 */
	protected void copiarPropriedades(Object destino, Object fonte) {
		ReflexaoUtils.copiarPropriedadesIgnorandoDiferencas(destino, fonte);
	}

	/**
	 * O método Definir informacoes adicionais.
	 * 
	 * @param entidade
	 *            o valor de entidade
	 * @param dto
	 *            o valor de dto
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected void definirInformacoesAdicionais(E entidade, D dto) throws BancoobException {

	}

	/**
	 * O método Definir informacoes adicionais.
	 * 
	 * @param dto
	 *            o valor de dto
	 * @param entidade
	 *            o valor de entidade
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected void definirInformacoesAdicionais(D dto, E entidade) throws BancoobException {

	}

	/**
	 * Instanciar entidade.
	 * 
	 * @param dto
	 *            o valor de dto
	 * @return E
	 */
	@SuppressWarnings("unchecked")
	protected E instanciarEntidade(D dto) {
		Class<?> classe = ReflexaoUtils.obterParametroGenerico(getClass());
		return (E) ReflexaoUtils.instanciar(classe);
	}

	/**
	 * Instanciar dto.
	 * 
	 * @param entidade
	 *            o valor de entidade
	 * @return D
	 */
	@SuppressWarnings("unchecked")
	protected D instanciarDTO(E entidade) {
		Class<?> classe = ReflexaoUtils.obterParametroGenerico(getClass(), 1);
		return (D) ReflexaoUtils.instanciar(classe);
	}

	/**
	 * O método Anexar documentos comprobatorios.
	 * 
	 * @param entidade
	 *            o valor de entidade
	 * @param dto
	 *            o valor de dto
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected void anexarDocumentosComprobatorios(E entidade, D dto) throws BancoobException {
		if (entidade instanceof Comprovavel) {
			Set<DocumentoComprobatorio> documentosComprobatorios = obterDocumentosComprobatorios(dto);
			if (documentosComprobatorios != null && !documentosComprobatorios.isEmpty()) {
				((Comprovavel) entidade).setDocumentosComprobatorios(documentosComprobatorios);
			}
		}
	}
	
	/**
	 * Converte os tipos {@code Date} para {@code DateTimeDB}
	 * 
	 * @param data
	 *            a data a ser convertida
	 * @return o {@code DateTimeDB}
	 */
	protected DateTimeDB criarDateTimeDB(Date data) {
		if (data != null) {
			return new DateTimeDB(data.getTime());
		}
		return null;
	}

	/**
	 * Obter documentos comprobatorios.
	 * 
	 * @param dto
	 *            o valor de dto
	 * @return Set
	 */
	protected Set<DocumentoComprobatorio> obterDocumentosComprobatorios(D dto) {
		Set<DocumentoComprobatorio> retorno = null;
		if (dto.getIdsDocumentos() != null) {
			retorno = new HashSet<DocumentoComprobatorio>();
			for (Long idDocumento : dto.getIdsDocumentos()) {
				DocumentoComprobatorio documentoComprobatorio = new DocumentoComprobatorio();
				documentoComprobatorio.setIdDocumento(idDocumento);
				retorno.add(documentoComprobatorio);
			}
		}
		return retorno;
	}

	/**
	 * Obter fabrica cadastro.
	 * 
	 * @return CAPESCadastroFabricaDelegates
	 */
	protected CAPESCadastroFabricaDelegates obterFabricaCadastro() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}
	
	/**
	 * Gerar chave do cache por id instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @return string
	 */
	private String gerarChaveCache(Integer idPessoa, Integer idInstituicao) {
		return CHAVE_CACHE_ID_PESSOA_COMPARTILHAMENTO + idPessoa + "_" + idInstituicao;
	}

	/**
	 * Adiciona a informação da pessoa compartilhamento
	 * 
	 * @param entidade
	 * @param dto
	 * @throws BancoobException
	 */
	protected void adicionarInformacoesPessoa(E entidade, D dto) throws BancoobException {
		boolean entidadePossuiMetodo = ReflexaoUtils.possuiMetodo(entidade, "setPessoaCompartilhamento", new Class<?>[] { PessoaCompartilhamento.class });
		if (entidadePossuiMetodo) {
			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamentoCache(dto.getIdPessoa(), dto.getIdInstituicao());
			ReflexaoUtils.setPropriedade(entidade, "pessoaCompartilhamento", pessoaCompartilhamento);
		}
	}

	/**
	 * Obtém a pessoa compartilhamento do cache.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected PessoaCompartilhamento obterPessoaCompartilhamento(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterPessoaCompartilhamentoCache(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtém a pessoa compartilhamento e salva no cache.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private PessoaCompartilhamento obterPessoaCompartilhamentoCache(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		ServicoCache servicoCache = FabricaServicos.getInstance().criarServicoCache();
		String chave = gerarChaveCache(idPessoa, idInstituicao);
		PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) servicoCache.recuperar(chave);
		if (pessoaCompartilhamento == null) {
			pessoaCompartilhamento = consultarPessoaCompartilhamento(idPessoa, idInstituicao);
			if (pessoaCompartilhamento != null) {
				servicoCache.armazenar(chave, pessoaCompartilhamento, CACHE_TIMEOUT);
			}
		}
		return pessoaCompartilhamento;
	}

	/**
	 * Realiza a consulta da pessoa compartilhamento.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private PessoaCompartilhamento consultarPessoaCompartilhamento(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = obterFabricaCadastro().criarPessoaCompartilhamentoDelegate();
		return pessoaCompartilhamentoDelegate.consultarPorIdPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtém a pessoa.
	 * 
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	protected Pessoa obterPessoa(Integer idPessoa) throws BancoobException {
		PessoaDelegate pessoaDelegate = obterFabricaCadastro().criarPessoaDelegate();
		return pessoaDelegate.obter(idPessoa);
	}

	/**
	 * Recupera o logger
	 * 
	 * @return
	 */
	protected ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}

}