/*
 * SICOOB
 * 
 * CAPESArquivosRuntimeException.java(br.com.sicoob.capes.comum.arquivos.excecao.CAPESArquivosRuntimeException)
 */
package br.com.sicoob.capes.comum.arquivos.excecao;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.comum.arquivos.infraestrutura.mensagens.CAPESArquivosResourceBundle;

/**
 * The Class CAPESArquivosRuntimeException.
 */
public class CAPESArquivosRuntimeException extends BancoobRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -3484523941378993959L;

	/**
	 * Cria uma nova instância de CAPES arquivos runtime exception.
	 * 
	 * @param excecao
	 *            the excecao
	 */
	public CAPESArquivosRuntimeException(Exception excecao) {

		super(excecao);
	}

	/**
	 * Cria uma nova instância de CAPES arquivos runtime exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 * @param excecao
	 *            the excecao
	 */
	public CAPESArquivosRuntimeException(String mensagem, Exception excecao) {

		super(MensagemUtil.getString(mensagem, CAPESArquivosResourceBundle.getInstance()), excecao);
	}

	/**
	 * Cria uma nova instância de CAPES arquivos runtime exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 * @param excecao
	 *            the excecao
	 */
	public CAPESArquivosRuntimeException(String chave, Object[] parametros, Exception excecao) {

		super(MensagemUtil.getString(chave, CAPESArquivosResourceBundle.getInstance(), parametros), excecao);
	}

	/**
	 * Cria uma nova instância de CAPES arquivos runtime exception.
	 * 
	 * @param chave
	 *            the chave
	 * @param parametros
	 *            the parametros
	 */
	public CAPESArquivosRuntimeException(String chave, Object[] parametros) {

		super(MensagemUtil.getString(chave, CAPESArquivosResourceBundle.getInstance(), parametros));
	}

	/**
	 * Cria uma nova instância de CAPES arquivos runtime exception.
	 * 
	 * @param mensagem
	 *            the mensagem
	 */
	public CAPESArquivosRuntimeException(String mensagem) {

		super(MensagemUtil.getString(mensagem, CAPESArquivosResourceBundle.getInstance()));
	}

}
