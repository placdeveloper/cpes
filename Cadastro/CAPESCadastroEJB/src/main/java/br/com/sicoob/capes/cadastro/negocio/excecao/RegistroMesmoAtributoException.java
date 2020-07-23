/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * @author Rodrigo.Chaves
 *
 */
public class RegistroMesmoAtributoException extends
		CAPESCadastroNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8145479159706489379L;
	
	/**
	 * Instancia um novo RegistroMesmoAtributoException.
	 *
	 * @param nomeEntidade o valor de nome entidade
	 * @param nomeAtributo o valor de nome atributo
	 */
	public RegistroMesmoAtributoException(String nomeEntidade, String nomeAtributo) {
		this(TipoMensagem.MISTA, nomeEntidade, nomeAtributo);
	}

	/**
	 * Instancia um novo RegistroMesmoAtributoException.
	 *
	 * @param excecao o valor de excecao
	 * @param nomeEntidade o valor de nome entidade
	 * @param nomeAtributo o valor de nome atributo
	 */
	public RegistroMesmoAtributoException(Throwable excecao, String nomeEntidade,
			String nomeAtributo) {
		this(TipoMensagem.MISTA, excecao, nomeEntidade, nomeAtributo);
	}
	
	/**
	 * 
	 * @param tipoMensagem
	 * @param nomeEntidade
	 * @param nomeAtributo
	 */
	public RegistroMesmoAtributoException(TipoMensagem tipoMensagem, String nomeEntidade, String nomeAtributo) {
		super(tipoMensagem.getChaveMensagem(), nomeEntidade, nomeAtributo);
	}
	
	/**
	 * 
	 * @param tipoMensagem
	 * @param excecao
	 * @param nomeEntidade
	 * @param nomeAtributo
	 */
	public RegistroMesmoAtributoException(TipoMensagem tipoMensagem, Throwable excecao, String nomeEntidade,
			String nomeAtributo) {
		super(tipoMensagem.getChaveMensagem(), excecao, nomeEntidade, nomeAtributo);
	}
	
	
	/**
	 * 
	 * @author Paulo.Stoppa
	 *
	 */
	public enum TipoMensagem {
		MISTA("MN142"), MASCULINO("MN219");
		
		/**
		 * 
		 * @param chaveMensagem
		 */
		private TipoMensagem(String chaveMensagem) {
			this.chaveMensagem = chaveMensagem;
		}
		
		private final String chaveMensagem;

		public String getChaveMensagem() {
			return chaveMensagem;
		}
		
		
	}
	
}
