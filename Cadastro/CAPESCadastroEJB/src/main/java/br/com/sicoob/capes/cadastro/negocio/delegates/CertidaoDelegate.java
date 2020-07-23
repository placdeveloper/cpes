/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.CertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Business delegate para certidao.  
 * @author juan.damasceno
 */
public class CertidaoDelegate extends
	EntidadeCadastroDelegate<Certidao, CertidaoServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarCertidaoServico();
	}
	
	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<Certidao> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().listarPorPessoa(pessoa);
	}
	
	/**
	 * Exclui as certidoes informadas.
	 * @throws BancoobException 
	 */
	public void excluir(List<Certidao> certidoes) throws BancoobException {
		getServico().excluirCertidoes(certidoes);
	}

	/**
	 * Lista as certidões vencidas somente quando o tipo de prazo de vencimento for 0,1 e 2.
	 * @param criterios DTO com o número de registros por pagina e o número da página.
	 * @throws BancoobException 
	 */
	public List<Certidao> listarVencidas(ConsultaDto<Certidao> criterios) throws BancoobException {
		return getServico().listarVencidas(criterios);
	}		
}
