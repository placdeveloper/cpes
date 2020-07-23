package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;

/**
 * A Interface EntidadeCadastroDaoIF.
 *
 * @param <T> o tipo generico
 */
public interface EntidadeCadastroDaoIF<T extends EntidadeCadastroBase> extends CAPESCadastroCrudDaoIF<T> {

	/**
	 * Altera o atributo {@link br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel#setIdInstituicaoAtualizacao(Integer)}, usando SQL,
	 * para o valor contido no atributo {@link br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel#getIdInstituicaoAtualizacao()}
	 * 
	 * 
	 * @param objeto
	 *            o objeto a ser marcado
	 * @param idInstituicaoAtualizacao TODO
	 */
	void marcarEmAlteracao(T objeto, Integer idInstituicaoAtualizacao) throws BancoobException;

}