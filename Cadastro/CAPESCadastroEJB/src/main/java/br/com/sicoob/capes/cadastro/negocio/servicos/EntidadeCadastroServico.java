package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * 
 * Interface que define os servi�os das entidades que possuem uma data de vig�ncia. 
 * 
 * @author Juan.Damasceno
 *
 * @param <T> entidade que herda de {@link EntidadeCadastroBase} e implenta {@link Vigente}
 */
public interface EntidadeCadastroServico<T extends EntidadeCadastroBase & Vigente> extends CAPESCadastroCrudServico<T>{

	/**
	 * Altera o estado da entidade colocando e/ou tirando de "em altera��o".
	 * 
	 * @param objeto
	 *            o objeto a alterado.
	 * @param idInstituicaoAtualizacao TODO
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void marcarEmAlteracao(T objeto, Integer idInstituicaoAtualizacao) throws BancoobException;
	
	/**
	 * {@inheritDoc}
	 */
	void excluirEntidade(T objeto) throws BancoobException;
	
}