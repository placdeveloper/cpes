/*
 * SICOOB
 * 
 * CAPESArquivosException.java(br.com.sicoob.capes.comum.arquivos.excecao.CAPESArquivosException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.comum.arquivos.infraestrutura.mensagens.CAPESArquivosResourceBundle;

/**
 * The Class CAPESArquivosException.
 */
public class CAPESArquivosException extends BancoobException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 6155794647949283858L;

	/**
	 * Cria uma nova instância de CAPES exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CAPESArquivosException(String chave, Object... parametros) {

		super(MensagemUtil.getString(chave, CAPESArquivosResourceBundle.getInstance(), parametros));
	}

	/**
	 * Cria uma nova instância de CAPES exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public CAPESArquivosException(String chave, Object[] parametros, Throwable excecao) {

		super(MensagemUtil.getString(chave, CAPESArquivosResourceBundle.getInstance(), parametros), excecao);
	}

	/**
	 * Cria uma nova instância de CAPES exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param excecao
	 *            the excecao
	 */
	public CAPESArquivosException(String chave, Throwable excecao) {

		super(MensagemUtil.getString(chave, CAPESArquivosResourceBundle.getInstance()), excecao);
	}

	/**
	 * Cria uma nova instância de CAPES exception.
	 * 
	 * @param chave
	 *            the chave
	 */
	public CAPESArquivosException(String chave) {

		super(MensagemUtil.getString(chave, CAPESArquivosResourceBundle.getInstance()));
	}

	/**
	 * Cria uma nova instância de CAPES exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public CAPESArquivosException(Throwable excecao) {

		super(excecao);
	}

}
