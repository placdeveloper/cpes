/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.proxies.EnderecoProxy;
import br.com.sicoob.capes.cadastro.negocio.servicos.EnderecoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * Delegate para os endere�os.
 * 
 * @author Erico.Junior
 */
public class EnderecoDelegate extends EntidadeCadastroDelegate<Endereco, EnderecoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarEnderecoServico();
	}

	/**
	 * Listar enderecos.
	 *
	 * @param criterios o valor de criterios
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios)
			throws BancoobException {
		return getServico().listarEnderecos(criterios);
	}

	/**
	 * Listar enderecos.
	 *
	 * @param criterios o valor de criterios
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios, Instituicao instituicao)
			throws BancoobException {
		return getServico().listarEnderecos(criterios, instituicao);
	}
	
	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param endereco
	 *            O endere�o que ser� padr�o para correspond�ncias.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void tornarPadraoCorrespondencia(Endereco endereco) throws BancoobException {
		getServico().tornarPadraoCorrespondencia(endereco);
	}
	
	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param endereco
	 *            O endere�o que ser� padr�o para correspond�ncias.
	 * @param instituicao
	 * 			  A institui��o para a qual o endere�o ser� correpond�ncia.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void tornarPadraoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException {
		getServico().tornarPadraoCorrespondencia(endereco, instituicao);
	}
	
	/**
	 * Inclui o endere�o informado e se n�o existir endere�o de correspond�ncia na institui��o, 
	 * define o mesmo como endere�o de correspond�ncia.
	 * @param endereco O endere�o que ser� inclu�do. 
	 * @param instituicao A institui��o para o endere�o.
	 * @return O endere�o inclu�do.
	 * @throws BancoobException
	 */
	public Endereco incluir(Endereco endereco, Instituicao instituicao) throws BancoobException {
		return getServico().incluir(endereco, instituicao);
	}
	
	/**
	 * O m�todo Verificar endereco correspondencia.
	 *
	 * @param endereco o valor de endereco
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void verificarEnderecoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException{
		getServico().verificarEnderecoCorrespondencia(endereco, instituicao);
	}
}
