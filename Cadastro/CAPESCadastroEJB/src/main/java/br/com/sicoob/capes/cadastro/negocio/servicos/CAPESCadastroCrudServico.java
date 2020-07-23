package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o
 * sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public interface CAPESCadastroCrudServico<T extends CAPESEntidade<?>>
		extends CAPESCadastroServico, BancoobCrudServico<T> {

	/**
	 * O m�todo Excluir entidade.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void excluirEntidade(T objeto) throws BancoobException;
	
	/**
	 * O m�todo Remover objeto sessao.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void removerObjetoSessao(T objeto) throws BancoobException;
	
	 void alterarSemValidacao(T objeto) throws BancoobException;

}
