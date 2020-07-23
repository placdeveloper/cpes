package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * Exceção lançada quando uma pessoa tenta cadastrar um bem possuindo um bem
 * interno.
 * 
 * @author Bruno.Carneiro
 */
public class CadastroNovoBemComBemInternoException extends CAPESCadastroNegocioException {
	private static final long serialVersionUID = 1L;
	
	private static final String CHAVE_MSG = "MN215";

	public CadastroNovoBemComBemInternoException() {
		super(CHAVE_MSG);
	}

}