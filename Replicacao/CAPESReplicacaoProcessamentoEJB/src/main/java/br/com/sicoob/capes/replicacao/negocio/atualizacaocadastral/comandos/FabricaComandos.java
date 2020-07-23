/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Fábrica de comandos.
 * 
 * @author Erico.Junior
 */
public class FabricaComandos<R extends EntidadeReplicavel<?>> {

	/** O atributo instance. */
	private static FabricaComandos<EntidadeReplicavel<?>> instance = new FabricaComandos<EntidadeReplicavel<?>>();

	/** O atributo log. */
	private ISicoobLogger log = getLogger();

	/**
	 * Cria a fábrica de comandos
	 * 
	 * @return a fábrica de comandos
	 */
	public static FabricaComandos<EntidadeReplicavel<?>> getInstance() {
		return instance;
	}

	/**
	 * Recupera o logger para essa instância.
	 * 
	 * @return O logger para essa instância.
	 */
	private ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}

	/**
	 * Cria uma instância de AtualizacaoCadastralCommand a partir do tipo de
	 * ação.
	 * 
	 * @param tipo
	 *            o tipo da opração que será realizada.
	 * @return uma instância de AtualizacaoCadastralCommand a partir do tipo de
	 *         operação.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AtualizacaoCadastralCommand<R> criarComando(Character tipo) {

		AtualizacaoCadastralCommand<R> comando = null;

		if (TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO.getTipoOperacao().equals(tipo)) {
			comando = new AlteracaoCadastralCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_EXCLUSAO.getTipoOperacao().equals(tipo)) {
			comando = new ExclusaoCadastralCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO.getTipoOperacao().equals(tipo)) {
			comando = new InclusaoCadastralCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_CLIENTE.getTipoOperacao().equals(tipo)) {
			comando = new AtualizacaoClienteCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO_CLIENTE.getTipoOperacao().equals(tipo)) {
			comando = new AtualizacaoClienteCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA.getTipoOperacao().equals(tipo)) {
			comando = (AtualizacaoCadastralCommand<R>) new AtualizacaoEnderecoCorrespondenciaCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_AVAL_FINANCEIRA.getTipoOperacao().equals(tipo)) {
			comando = (AtualizacaoCadastralCommand<R>) new AlteracaoAvalFinanceiraCommand();
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO_EMAIL_PESSOA.getTipoOperacao().equals(tipo)
				|| TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_EMAIL_PESSOA.getTipoOperacao().equals(tipo)) {
			comando = (AtualizacaoCadastralCommand<R>) new AtualizacaoEmailCommand();
		}

		log.info("Comando para atualizacao cadastral do tipo: " + tipo + " = " + comando);
		return comando;
	}
}