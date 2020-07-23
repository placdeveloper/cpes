/*
 * SICOOB
 * 
 * Formatador.java(br.com.sicoob.capes.comum.util.formatacao.Formatador)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import br.com.bancoob.excecao.BancoobException;



/**
 * Interface comum para os Formatadores de dados.
 * 
 * Criado em 28/10/2010
 * 
 * @author rodrigo.chaves
 * @param <T>
 *            Classe do objeto que ser� formatado
 */
public interface Formatador<T> {

	/**
	 * Formata objeto do tipo espec�fico informado (gen�rico) para uma
	 * {@code String}
	 * 
	 * @param valor
	 *            O objeto a ser formatado
	 * @param mascara
	 *            Par�etro opicional que define o formato que ser� usado pelo
	 *            formatador, caso necess�rio
	 * @return String - o valor formatado
	 * @throws BancoobException TODO
	 */
	String formatar(T valor, String mascara) throws BancoobException;

}
