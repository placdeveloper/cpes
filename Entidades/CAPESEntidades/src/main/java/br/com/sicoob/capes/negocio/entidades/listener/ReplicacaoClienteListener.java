/*
 * SICOOB
 * 
 * ReplicacaoClienteListener.java(br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoClienteListener)
 */
package br.com.sicoob.capes.negocio.entidades.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Listener para replicação de Tributacao e PessoaInstituicao no SQLServer
 *
 * @author juan.damasceno
 */
public class ReplicacaoClienteListener<E extends CAPESEntidade<?> & Replicavel>
		extends ReplicacaoListener<E> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarAposExclusao(E entidade) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Método vazio devido ao fato de que a {@link Tributacao} só é incluída no momento da
	 * inclusão de um {@link Cliente} que, por sua vez, realiza a replicação sincronamente. Ou
	 * seja, a funcionalidade que inclui o {@code Cliente} já insere as informações de {@code
	 * Tributacao}, não havendo a necessidade de utilização do mecanismo de mensagem.
	 */
	@Override
	@PostPersist
	public void executarAposInclusao(E entidade) throws BancoobException {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostUpdate
	public void executarAposAlteracao(E entidade) throws BancoobException {
		executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_CLIENTE);
	}

}