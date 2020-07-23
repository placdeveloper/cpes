/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Serviço utilizado para o relacionamento das pessoas com as instituições.
 * 
 * @author Erico.Junior
 */
public interface RelacionamentoInstituicaoServico extends CAPESCadastroServico {

	/**
	 * Lista as transições da pessoa e a instituição responsável.
	 * @param pessoa A pessoa da qual se deseja as transições.
	 * @return A lista das transições.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */	
	public List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa)
			throws BancoobException;
	
	/**
	 * Altera o gestor do cadastro.
	 * @param novoResponsavel O novo responsável pela gestão do cadastro. 
	 */
	public void alterarGestorCadastro(ResponsavelCadastro novoResponsavel) throws BancoobException;

	/**
	 * Método para atualizar a foto e assinatura da pessoa informada na base 0001(bancoob)
	 * @param pessoa
	 * @throws BancoobException
	 */
	public void atualizarAssinaturaFotoBancoob(PessoaCompartilhamento pessoa) throws BancoobException;
}
