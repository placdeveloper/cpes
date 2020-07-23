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
 * Servi�o utilizado para o relacionamento das pessoas com as institui��es.
 * 
 * @author Erico.Junior
 */
public interface RelacionamentoInstituicaoServico extends CAPESCadastroServico {

	/**
	 * Lista as transi��es da pessoa e a institui��o respons�vel.
	 * @param pessoa A pessoa da qual se deseja as transi��es.
	 * @return A lista das transi��es.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */	
	public List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa)
			throws BancoobException;
	
	/**
	 * Altera o gestor do cadastro.
	 * @param novoResponsavel O novo respons�vel pela gest�o do cadastro. 
	 */
	public void alterarGestorCadastro(ResponsavelCadastro novoResponsavel) throws BancoobException;

	/**
	 * M�todo para atualizar a foto e assinatura da pessoa informada na base 0001(bancoob)
	 * @param pessoa
	 * @throws BancoobException
	 */
	public void atualizarAssinaturaFotoBancoob(PessoaCompartilhamento pessoa) throws BancoobException;
}
