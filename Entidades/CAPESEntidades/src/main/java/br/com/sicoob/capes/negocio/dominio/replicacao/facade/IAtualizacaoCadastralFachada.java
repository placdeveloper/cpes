/*
 * SICOOB
 * 
 * IAtualizacaoCadastralFachada.java(br.com.sicoob.capes.negocio.dominio.replicacao.facade.IAtualizacaoCadastralFachada)
 */
package br.com.sicoob.capes.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;

/**
 * The Interface IAtualizacaoCadastralFachada.
 * 
 * @param <R>
 *            the generic type
 */
public interface IAtualizacaoCadastralFachada<R extends Replicavel> {

	/**
	 * Envia a atualização no sistema legado.
	 *
	 * @param entidade
	 *            A entidade que sofreu atualização cadastral.
	 * @param tipoAtualizacao
	 *            O tipo de atualização (Inclusão, Alteração ou Exclusão).
	 * @throws BancoobException
	 */
	void executarAtualizacao(R entidade, TipoAtualizacaoCadastralEnum tipoAtualizacao) throws BancoobException;

}