package br.com.sicoob.capes.comum.negocio.delegates;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.comum.negocio.servicos.locator.CAPESComumServiceLocator;

/**
 * A Classe ClienteDelegate.
 */
public class ClienteDelegate extends CAPESComumDelegate<ClienteServico> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected ClienteServico localizarServico() {
		return CAPESComumServiceLocator.getInstance().localizarClienteServico();
	}
	
	/**
	 * Obter por id instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Map<String, Object> obterPorIdInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return localizarServico().obterPorIdInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obter por cpf cnpj instituicao.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Map<String, Object> obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return localizarServico().obterPorCpfCnpjInstituicao(cpfCnpj, idInstituicao);
	}
}