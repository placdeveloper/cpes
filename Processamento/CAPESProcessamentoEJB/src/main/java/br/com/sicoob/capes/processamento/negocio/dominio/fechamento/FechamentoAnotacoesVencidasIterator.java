package br.com.sicoob.capes.processamento.negocio.dominio.fechamento;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

public class FechamentoAnotacoesVencidasIterator implements Iterator<Anotacao> {
	
	/** A constante ZERO. */
	private static final Number ZERO = 0;
	
	/** O atributo propriedades. */
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("capes.processamento.propriedades");
	
	/** O atributo tamanhoPagina. */
	private final Integer tamanhoPagina = Integer.valueOf(propriedades.getProperty("tamanho.pagina.fechamentoAnotacoesVencidas"));
	
	/** O atributo registroAtual. */
	private Anotacao registroAtual;	
	
	/** O atributo iterator. */
	private Iterator<Anotacao> iterator;

	/** O atributo totalRegistros. */
	private Integer totalRegistros;
	
	/** O atributo paginaAtual. */
	private Integer paginaAtual = ZERO.intValue();
	
	/** O atributo contadorRegistros. */
	private Integer contadorRegistros = ZERO.intValue();
	
	private Date dataLimite;
	private Short codigoGrupoTipoAnotacao;

	/**
	 * Instancia um novo FechamentoAnotacoesVencidasIterator.
	 *
	 * @param dataLimite o valor de data limite
	 */
	public FechamentoAnotacoesVencidasIterator(Date dataLimite, Short codigoGrupoTipoAnotacao) {
		this.dataLimite = dataLimite;
		this.codigoGrupoTipoAnotacao = codigoGrupoTipoAnotacao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasNext() {
		if (totalRegistros == null) {
			carregarPagina(paginaAtual);
		}
		return contadorRegistros < totalRegistros;
	}

	/**
	 * {@inheritDoc}
	 */
	public Anotacao next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		if (!iterator.hasNext()) {
			carregarPagina(paginaAtual);
		}

		contadorRegistros++;
		registroAtual = iterator.next();
		
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
	protected ConsultaDto<Anotacao> pesquisar(Date dataLimite, Short codigoGrupoTipoAnotacao, int paginaAtual, int tamanhoPagina) throws BancoobException {
		AnotacaoDelegate anotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate();
		return anotacaoDelegate.obterAnotacoesVencidasPorGrupoTipoAnotacao(dataLimite, codigoGrupoTipoAnotacao, paginaAtual, tamanhoPagina);
	}
	
	/**
	 * O método Carregar pagina.
	 *
	 * @param pagina o valor de pagina
	 */
	private void carregarPagina(final Integer pagina) {
		try {
			ConsultaDto<Anotacao> dto = pesquisar(dataLimite, codigoGrupoTipoAnotacao, pagina, tamanhoPagina);
			if(totalRegistros == null){
				totalRegistros = dto.getTotalRegistros();
			}
			iterator = dto.getResultado().iterator();
			
			SicoobLoggerPadrao.getInstance(getClass()).info("Total de anotacoes vencidas: ", String.valueOf(totalRegistros));
			
		} catch (BancoobException e) {
			throw new BancoobRuntimeException(e);
		}
	}

}