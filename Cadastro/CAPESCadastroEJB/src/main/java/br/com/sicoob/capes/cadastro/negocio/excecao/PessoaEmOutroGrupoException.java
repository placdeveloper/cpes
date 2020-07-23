package br.com.sicoob.capes.cadastro.negocio.excecao;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico;

/**
 * A Classe PessoaEmOutroGrupoException.
 */
public class PessoaEmOutroGrupoException extends CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -9042868333027355442L;

	/**
	 * A pessoa selecionada j� est� associada a outro grupo econ�mico: {1}.
	 */
	public static final String MENSAGEM_INDIVIDUAL = "MN143";

	/**
	 * Existe(m) pessoa(s) neste grupo que j� est�(�o) associada(s) a outro grupo econ�mico.
	 */
	public static final String MENSAGEM_GRUPO = "MN144";
	
	private final TipoGrupoEconomico origemGrupo;

	private final GrupoEconomicoNovo grupoAutomatico;

	/**
	 * @see #MENSAGEM_GRUPO
	 * @see #MENSAGEM_INDIVIDUAL
	 * @param codigoMensagem
	 *            o c�digo da mensagem
	 */
	public PessoaEmOutroGrupoException(String codigoMensagem) {
		super(codigoMensagem);
		this.grupoAutomatico = null;
		this.origemGrupo = null;
	}

	/**
	 * @see #MENSAGEM_GRUPO
	 * @see #MENSAGEM_INDIVIDUAL
	 * @param nomeGrupo
	 *            O nome do grupo do qual a pessoa j� faz parte
	 */
	public PessoaEmOutroGrupoException(String codigoMensagem, String nomeGrupo) {
		this(codigoMensagem, nomeGrupo, null, null);
	}

	/**
	 * @see #MENSAGEM_GRUPO
	 * @see #MENSAGEM_INDIVIDUAL
	 * @param nomeGrupo
	 *            O nome do grupo do qual a pessoa j� faz parte
	 */
	public PessoaEmOutroGrupoException(String codigoMensagem, String nomeGrupo, GrupoEconomicoNovo grupoAutomatico, TipoGrupoEconomico origemGrupo) {
		super(codigoMensagem, nomeGrupo);
		this.grupoAutomatico = grupoAutomatico;
		this.origemGrupo = origemGrupo;
	}

	public GrupoEconomicoNovo getGrupoAutomatico() {
		return grupoAutomatico;
	}
	
	public TipoGrupoEconomico getOrigemGrupo() {
		return origemGrupo;
	}

}
