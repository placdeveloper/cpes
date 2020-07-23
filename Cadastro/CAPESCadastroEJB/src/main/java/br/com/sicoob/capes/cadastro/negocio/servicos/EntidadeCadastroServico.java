package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * 
 * Interface que define os serviços das entidades que possuem uma data de vigência. 
 * 
 * @author Juan.Damasceno
 *
 * @param <T> entidade que herda de {@link EntidadeCadastroBase} e implenta {@link Vigente}
 */
public interface EntidadeCadastroServico<T extends EntidadeCadastroBase & Vigente> extends CAPESCadastroCrudServico<T>{

	/**
	 * Altera o estado da entidade colocando e/ou tirando de "em alteração".
	 * 
	 * @param objeto
	 *            o objeto a alterado.
	 * @param idInstituicaoAtualizacao TODO
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void marcarEmAlteracao(T objeto, Integer idInstituicaoAtualizacao) throws BancoobException;
	
	/**
	 * {@inheritDoc}
	 */
	void excluirEntidade(T objeto) throws BancoobException;
	
}