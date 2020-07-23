package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exce��o para a valida��o do cadastro da Receita Federal (CPF/CNPJ).
 * 
 * @author bruno.carneiro
 */
public class CadastroIrregularReceitaException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2323611997803947627L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN164";

	/**
	 * Construtor da exce��o.
	 */
	public CadastroIrregularReceitaException() {
		super(CHAVE_MSG);
	}
}