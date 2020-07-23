package br.com.sicoob.capes.processamento.negocio.dominio.fechamento;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe FechamentoAutorizacoesVencidasIterator.
 */
public class FechamentoAutorizacoesVencidasIterator implements Iterator<Autorizacao> {
	
	/** A constante ZERO. */
	private static final Number ZERO = 0;
	
	/** O atributo fabrica. */
	private transient CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo autorizacaoDelegate. */
	private transient AutorizacaoDelegate autorizacaoDelegate = fabrica.criarAutorizacaoDelegate();
	
	/** O atributo propriedades. */
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("capes.processamento.propriedades");
	
	/** O atributo tamanhoPagina. */
	private final Integer tamanhoPagina = Integer.valueOf(propriedades.getProperty("tamanho.pagina.fechamentoAutorizacoes"));
	
	/** O atributo filtro. */
	private Autorizacao filtro;
	
	/** O atributo registroAtual. */
	private Autorizacao registroAtual;	
	
	/** O atributo iterator. */
	private Iterator<Autorizacao> iterator;

	/** O atributo totalRegistros. */
	private Integer totalRegistros;
	
	/** O atributo paginaAtual. */
	private Integer paginaAtual = ZERO.intValue();
	
	/** O atributo contadorRegistros. */
	private Integer contadorRegistros = ZERO.intValue();

	/**
	 * Instancia um novo FechamentoAutorizacoesVencidasIterator.
	 *
	 * @param dataLimite o valor de data limite
	 */
	public FechamentoAutorizacoesVencidasIterator(Date dataLimite) {
		this.filtro = new Autorizacao();
		this.filtro.setDataHoraCadastro(new DateTimeDB(dataLimite.getTime()));
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
	public Autorizacao next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		if (!iterator.hasNext()) {
			carregarPagina(this.paginaAtual);
		}

		this.contadorRegistros++;
		registroAtual = this.iterator.next();
		
		return registroAtual;
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
	protected ConsultaDto<Autorizacao> pesquisar(ConsultaDto<Autorizacao> criterios) throws BancoobException {
		return autorizacaoDelegate.obterListaAutorizacoesVencidas(criterios);
	}
	
	/**
	 * O método Carregar pagina.
	 *
	 * @param pagina o valor de pagina
	 */
	private void carregarPagina(final Integer pagina) {
		
		try {
			ConsultaDto<Autorizacao> criterios = new ConsultaDto<Autorizacao>();
			criterios.setTamanhoPagina(tamanhoPagina);
			criterios.setPagina(pagina);
			criterios.setFiltro(filtro);
			
			if (registroAtual != null) {
				filtro.setIdAutorizacao(registroAtual.getIdAutorizacao());
			} else {
				filtro.setIdAutorizacao(ZERO.longValue());
			}

			ConsultaDto<Autorizacao> dto = pesquisar(criterios);
			if(totalRegistros == null){
				this.totalRegistros = dto.getTotalRegistros();
			}
			this.iterator = dto.getResultado().iterator();
			
			SicoobLoggerPadrao.getInstance(getClass()).info("Total de autorizacoes vencidas: ", String.valueOf(this.totalRegistros));
			
		} catch (BancoobException e) {
			throw new BancoobRuntimeException(e);
		}
	}

}