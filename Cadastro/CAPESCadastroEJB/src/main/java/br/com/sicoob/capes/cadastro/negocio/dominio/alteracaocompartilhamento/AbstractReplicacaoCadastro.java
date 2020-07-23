/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.alteracaocompartilhamento;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Superclasse para replicação do cadastro.
 * @author erico.junior
 */
public abstract class AbstractReplicacaoCadastro<T extends EntidadeCadastroBase & Vigente> {

	/**
	 * O método Executar.
	 *
	 * @param origem o valor de origem
	 * @param destino o valor de destino
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void executar(PessoaCompartilhamento origem, PessoaCompartilhamento destino) 
			throws BancoobException{
		
		List<T> lista = listar(origem);
		
		if(lista != null && !lista.isEmpty()) {
			CAPESCadastroCrudDelegate<T, ?> delegate = obterDelegate();
			
			for (T entidade : lista) {
				T novaEntidade = obterNovaEntidade(entidade, destino);
				delegate.incluir(novaEntidade);				
			}
		}
	}
	
	/**
	 * Lista as entidades relacionadas a pessoa.
	 * @param pessoa A pessoa.
	 * @return a lista as entidades relacionadas a pessoa.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	protected abstract List<T> listar(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Obter delegate.
	 *
	 * @return CAPESCadastroCrudDelegate
	 */
	protected abstract CAPESCadastroCrudDelegate<T, ? > obterDelegate();
	
	/**
	 * Obter nova entidade.
	 *
	 * @param entidade o valor de entidade
	 * @param pessoa o valor de pessoa
	 * @return T
	 */
	protected abstract T obterNovaEntidade(T entidade, PessoaCompartilhamento pessoa);

	
}
