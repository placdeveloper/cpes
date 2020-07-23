/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Define as opera��es do servi�o de manipula��o de perfil tarifario.
 * 
 * @author Juan.Damasceno
 */
public interface PerfilTarifarioServico extends
		CAPESCadastroCrudServico<PerfilTarifario> {

	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 */
	List<Funcionario> listarPorPessoa(PessoaCompartilhamento pessoa);
}