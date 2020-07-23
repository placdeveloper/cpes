/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Business delegate para as pessoas.
 * 
 * @author Erico.Junior
 */
public class PessoaDelegate extends CAPESCadastroCrudDelegate<Pessoa, PessoaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarPessoaServico();
	}

	/**
	 * Método para alterar o cpf ou cnpj da pessoa informada.
	 * 
	 * @param pessoa
	 *            a Pessoa a ser alterada.
	 * @param cpfCnpjNovo
	 *            O novo cpf ou cnpj. 
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	public void alterarCpfCnpj(Pessoa pessoa, String cpfCnpjNovo) throws BancoobException {
		getServico().alterarCpfCnpj(pessoa, cpfCnpjNovo);
	}

	/**
	 * Consulta uma pessoa através do documento informado, seja ele um CPF ou
	 * CNPJ. Obs: Serviço disponibilizado para outos sistemas.
	 * 
	 * @param cpfCnpj
	 *            O cpf da pessoa física ou cnpj da pessoa jurídica a ser
	 *            consultada.
	 * @return A pessoa encontrada a partir do documento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public Pessoa consultarPessoaPorDocumento(String cpfCnpj) throws BancoobException {
		return getServico().consultarPessoaPorDocumento(cpfCnpj);
	}

	/**
	 * Obter por lista pessoa instituicao.
	 *
	 * @param idPessoas o valor de id pessoas
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 */
	public List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) {
		return getServico().obterPorListaPessoaInstituicao(idPessoas, idInstituicao);
	}	
	
}