/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.RelacionamentoInstituicaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Delegate utilizada para manter o relacionamento de pessoas com institui��es.
 * @author Erico.Junior
 */
public class RelacionamentoInstituicaoDelegate extends
		CAPESCadastroDelegate<RelacionamentoInstituicaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoInstituicaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarRelacionamentoInstituicaoServico();
	}

	/**
	 * Lista as transi��es da pessoa e a institui��o respons�vel.
	 * 
	 * @param pessoa
	 *            A pessoa da qual se deseja as transi��es.
	 * @return A lista das transi��es.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return getServico().listarRelacionamentoInstituicao(pessoa);
	}
	
	/**
	 * Altera o respons�vel pelo cadastro da pessoa.
	 * @param novoResponsavel O respons�vel pelo cadastro da pessoa.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	public void alterarGestorCadastro(ResponsavelCadastro novoResponsavel) throws BancoobException {
		getServico().alterarGestorCadastro(novoResponsavel);
	}
	
	/**
	 * M�todo para atualizar a foto e assinatura da pessoa informada na base 0001(bancoob)
	 * @param pessoa
	 * @throws BancoobException
	 */
	public void atualizarAssinaturaFotoBancoob(PessoaCompartilhamento pessoa)
			throws BancoobException{
		getServico().atualizarAssinaturaFotoBancoob(pessoa);
	}
}
