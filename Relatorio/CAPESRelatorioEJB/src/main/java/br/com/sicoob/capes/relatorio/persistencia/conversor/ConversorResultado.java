package br.com.sicoob.capes.relatorio.persistencia.conversor;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Interface ConversorResultado.
 *
 * @param <C> o tipo generico
 */
public interface ConversorResultado<C> {

	/**
	 * @param tupla
	 * @return
	 * @throws BancoobException
	 */
	C converterTupla(Object[] tupla) throws BancoobException;

    /**
     * @param lista
     * @return
     * @throws BancoobException
     */
    List<C> tratarLista(List<C> lista) throws BancoobException;

}