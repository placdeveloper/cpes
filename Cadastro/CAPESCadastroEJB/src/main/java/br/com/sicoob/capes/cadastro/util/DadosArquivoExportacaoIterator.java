package br.com.sicoob.capes.cadastro.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.DadosArquivoExportacaoDelegate;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.pk.DadosArquivoExportacaoPK;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe DadosArquivoExportacaoIterator.
 */
public class DadosArquivoExportacaoIterator implements Iterator<DadosArquivoExportacao> {

	/** A constante ZERO. */
	private static final Integer ZERO = 0;

	/** O atributo propriedades. */
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("capes.cadastro.propriedades");
	
	/** O atributo tamanhoPagina. */
	private final Integer tamanhoPagina = Integer.valueOf(propriedades.getProperty("tamanho.pagina.arquivo.exportacao"));

	/** O atributo filtro. */
	private DadosArquivoExportacao filtro;
	
	/** O atributo idExportacao. */
	private Integer idExportacao;
	
	/** O atributo iterator. */
	private Iterator<DadosArquivoExportacao> iterator;

	/** O atributo totalRegistros. */
	private Integer totalRegistros;
	
	/** O atributo paginaAtual. */
	private Integer paginaAtual = ZERO;
	
	/** O atributo contadorRegistros. */
	private Integer contadorRegistros = ZERO;

	/**
	 * Instancia um novo DadosArquivoExportacaoIterator.
	 *
	 * @param idExportacao o valor de id exportacao
	 */
	public DadosArquivoExportacaoIterator(Integer idExportacao) {
		this.idExportacao = idExportacao;
		this.filtro = new DadosArquivoExportacao();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasNext() {
		if (this.totalRegistros == null) {
			carregarPagina(this.paginaAtual);
		}
		return this.contadorRegistros < this.totalRegistros;
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosArquivoExportacao next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		if (!iterator.hasNext()) {
			carregarPagina(++this.paginaAtual);
		}

		this.contadorRegistros++;

		return this.iterator.next();
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove() {
		if (iterator != null) {
			iterator.remove();
		}
	}

	/**
	 * Pesquisar.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ConsultaDto<DadosArquivoExportacao> pesquisar(ConsultaDto<DadosArquivoExportacao> criterios) throws BancoobException {
		DadosArquivoExportacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarDadosArquivoExportacaoDelegate();
		return delegate.pesquisar(criterios);
	}

	/**
	 * O método Carregar pagina.
	 *
	 * @param pagina o valor de pagina
	 */
	private void carregarPagina(final Integer pagina) {
		try {
			ConsultaDto<DadosArquivoExportacao> criterios = new ConsultaDto<DadosArquivoExportacao>();
			criterios.setTamanhoPagina(tamanhoPagina);
			criterios.setPagina(pagina);
			
			DadosArquivoExportacaoPK pk = new DadosArquivoExportacaoPK();
			pk.setIdExportacao(idExportacao);
			filtro.setPk(pk);
			criterios.setFiltro(filtro);
			ConsultaDto<DadosArquivoExportacao> dto = pesquisar(criterios);
			
			if (totalRegistros == null) {
				this.totalRegistros = dto.getTotalRegistros();
			}
			this.iterator = dto.getResultado().iterator();

			SicoobLoggerPadrao.getInstance(getClass()).info("Total de linhas do arquivo: ", String.valueOf(this.totalRegistros));

		} catch (BancoobException e) {
			throw new BancoobRuntimeException(e);
		}
	}
	
}