package br.com.sicoob.capes.comum.arquivos;

/**
 * Interface usada para indicar que o objeto representa uma linha qualquer (cabeçalho, detalhe ou rodapé) de um arquivo
 * texto usado para troca de dados.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public interface RegistroArquivo {

	/**
	 * Obtém o valor do atributo <code>numeroLinha</code>.
	 * 
	 * @return int - O atributo numeroLinha
	 */
	public int getNumeroLinha();

	/**
	 * Atribui o valor do atributo <code>numeroLinha</code>.
	 * 
	 * @param numeroLinha
	 *            - O valor a ser atribuído.
	 */
	public void setNumeroLinha(int numeroLinha);

}