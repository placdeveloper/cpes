/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ResponsavelCadastroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Business delegate para ResponsavelCadastroServico.  
 * @author juan.damasceno
 */
public class ResponsavelCadastroDelegate extends
	EntidadeCadastroDelegate<ResponsavelCadastro, ResponsavelCadastroServico> {
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponsavelCadastroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarResponsavelCadastroServico();
	}
	
	/**
	 * Recupera o responsável pelo cadastro da pessoa informada.
	 * @param pessoa A pessoa a ser obtida.
	 * @return O responsável pelo cadastro da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public ResponsavelCadastro consultar(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().consultar(pessoa);
	}
	
	/**
	 * Altera a instituição responsável pelo cadastro.
	 * @param responsavel O responsável pelo cadastro.
	 * @param instituicao a nova instituição responsável.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public void alterar(ResponsavelCadastro responsavel, Instituicao instituicao) throws BancoobException {
		getServico().alterar(responsavel, instituicao);
	}
}
