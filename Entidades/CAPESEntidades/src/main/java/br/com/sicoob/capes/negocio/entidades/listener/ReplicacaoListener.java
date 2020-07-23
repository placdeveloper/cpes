/*
 * SICOOB
 * 
 * ReplicacaoListener.java(br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener)
 */
package br.com.sicoob.capes.negocio.entidades.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.dominio.replicacao.facade.FabricaAtualizacaoCadastralFachada;
import br.com.sicoob.capes.negocio.dominio.replicacao.facade.IAtualizacaoCadastralFachada;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;

/**
 * Listener para replica��o no SLQ Server.
 * 
 * @author Erico.Junior
 */
public class ReplicacaoListener<E extends CAPESEntidade<?> & Replicavel> {


	/**
	 * Executa ap�s a inclus�o da entidade.
	 * 
	 * @param entidade
	 *            A entidade que foi inclu�da.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	@PostPersist
	public void executarAposInclusao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO);
	}

	/**
	 * Executa ap�s a exclus�o da entidade.
	 * 
	 * @param entidade
	 *            A entidade que foi exclu�da.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	@PostRemove
	public void executarAposExclusao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_EXCLUSAO);
	}

	/**
	 * Executa ap�s a altera��o da entidade.
	 * 
	 * @param entidade
	 *            A entidade que foi alterada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	@PostUpdate
	public void executarAposAlteracao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO);
	}

	/**
	 * Envia a atualiza��o no sistema legado.
	 * 
	 * @param entidade
	 *            A entidade que sofreu atualiza��o cadastral.
	 * @param tipoAtualizacao
	 *            O tipo de atualiza��o (Inclus�o, Altera��o ou Exclus�o).
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	protected void executarAtualizacao(E entidade, TipoAtualizacaoCadastralEnum tipoAtualizacao)
			throws BancoobException {
		
		IAtualizacaoCadastralFachada<Replicavel> fachada = 
				FabricaAtualizacaoCadastralFachada.getInstance().obterFachada(entidade);
		fachada.executarAtualizacao(entidade, tipoAtualizacao);
	}

}
