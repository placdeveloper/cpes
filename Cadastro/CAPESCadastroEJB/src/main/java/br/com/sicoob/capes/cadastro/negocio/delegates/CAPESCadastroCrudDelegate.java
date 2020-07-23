package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Business delegate padrao para operacoes de CRUD do sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESCadastroCrudDelegate<T extends CAPESEntidade<?>, S extends CAPESCadastroCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

	/**
	 * O m�todo Excluir entidade.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void excluirEntidade(T objeto) throws BancoobException {
		getServico().excluirEntidade(objeto);
	}
	
	/**
	 * O m�todo Remover objeto sessao.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void removerObjetoSessao(T objeto) throws BancoobException {
		getServico().removerObjetoSessao(objeto);
	}

	public void alterarSemValidacao(T objeto) throws BancoobException {
		getServico().alterarSemValidacao(objeto);
	}
}