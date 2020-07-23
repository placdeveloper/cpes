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
 * Listener para replicação no SLQ Server.
 * 
 * @author Erico.Junior
 */
public class ReplicacaoListener<E extends CAPESEntidade<?> & Replicavel> {


	/**
	 * Executa após a inclusão da entidade.
	 * 
	 * @param entidade
	 *            A entidade que foi incluída.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	@PostPersist
	public void executarAposInclusao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO);
	}

	/**
	 * Executa após a exclusão da entidade.
	 * 
	 * @param entidade
	 *            A entidade que foi excluída.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	@PostRemove
	public void executarAposExclusao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_EXCLUSAO);
	}

	/**
	 * Executa após a alteração da entidade.
	 * 
	 * @param entidade
	 *            A entidade que foi alterada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	@PostUpdate
	public void executarAposAlteracao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO);
	}

	/**
	 * Envia a atualização no sistema legado.
	 * 
	 * @param entidade
	 *            A entidade que sofreu atualização cadastral.
	 * @param tipoAtualizacao
	 *            O tipo de atualização (Inclusão, Alteração ou Exclusão).
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
