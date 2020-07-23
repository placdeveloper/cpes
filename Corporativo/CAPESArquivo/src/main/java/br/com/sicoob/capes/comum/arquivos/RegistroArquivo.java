package br.com.sicoob.capes.comum.arquivos;

/**
 * Interface usada para indicar que o objeto representa uma linha qualquer (cabe�alho, detalhe ou rodap�) de um arquivo
 * texto usado para troca de dados.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public interface RegistroArquivo {

	/**
	 * Obt�m o valor do atributo <code>numeroLinha</code>.
	 * 
	 * @return int - O atributo numeroLinha
	 */
	public int getNumeroLinha();

	/**
	 * Atribui o valor do atributo <code>numeroLinha</code>.
	 * 
	 * @param numeroLinha
	 *            - O valor a ser atribu�do.
	 */
	public void setNumeroLinha(int numeroLinha);

}