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
 * Delegate para os endereços.
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
	 * @throws BancoobException lança a exceção BancoobException
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
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios, Instituicao instituicao)
			throws BancoobException {
		return getServico().listarEnderecos(criterios, instituicao);
	}
	
	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param endereco
	 *            O endereço que será padrão para correspondências.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void tornarPadraoCorrespondencia(Endereco endereco) throws BancoobException {
		getServico().tornarPadraoCorrespondencia(endereco);
	}
	
	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param endereco
	 *            O endereço que será padrão para correspondências.
	 * @param instituicao
	 * 			  A instituição para a qual o endereço será correpondência.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void tornarPadraoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException {
		getServico().tornarPadraoCorrespondencia(endereco, instituicao);
	}
	
	/**
	 * Inclui o endereço informado e se não existir endereço de correspondência na instituição, 
	 * define o mesmo como endereço de correspondência.
	 * @param endereco O endereço que será incluído. 
	 * @param instituicao A instituição para o endereço.
	 * @return O endereço incluído.
	 * @throws BancoobException
	 */
	public Endereco incluir(Endereco endereco, Instituicao instituicao) throws BancoobException {
		return getServico().incluir(endereco, instituicao);
	}
	
	/**
	 * O método Verificar endereco correspondencia.
	 *
	 * @param endereco o valor de endereco
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void verificarEnderecoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException{
		getServico().verificarEnderecoCorrespondencia(endereco, instituicao);
	}
}
