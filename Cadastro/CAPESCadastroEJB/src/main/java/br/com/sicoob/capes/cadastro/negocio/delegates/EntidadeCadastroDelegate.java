package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.EntidadeCadastroServico;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * Business delegate padrao para operacoes de CRUD do sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class EntidadeCadastroDelegate<T extends EntidadeCadastroBase & Vigente, S extends EntidadeCadastroServico<T>>
		extends CAPESCadastroCrudDelegate<T, S> {
	
	/**
	 * Altera o estado da entidade para em alteração.
	 * @param objeto o objeto a alterado.
	 * @param idInstituicaoAtualizacao TODO
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public void marcarEmAlteracao(T objeto, Integer idInstituicaoAtualizacao) throws BancoobException{
		getServico().marcarEmAlteracao(objeto, idInstituicaoAtualizacao);
	}
	

}