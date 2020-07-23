/*
 * SICOOB
 * 
 * InstituicaoResponsavelDelegate.java(br.com.sicoob.capes.api.negocio.delegates.InstituicaoResponsavelDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IInstituicaoResponsavelDelegate;
import br.com.sicoob.capes.api.negocio.servicos.InstituicaoResponsavelServico;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;


/**
 * Delegate responsavel por retornar o Responsavel
 * 
 * @author Marcelo.Onofre
 *
 */
public class InstituicaoResponsavelDelegate extends
		CAPESApiDelegate<InstituicaoResponsavelServico> implements IInstituicaoResponsavelDelegate {
	
	/**
	 * 
	 */
	protected InstituicaoResponsavelDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static InstituicaoResponsavelDelegate getInstance() {
		return valueOf(InstituicaoResponsavelDelegate.class);
	}

	/**
	 * Localiza o servico <code>locator.capesintegracao.InstituicaoResponsavelServico</code>
	 */
	@Override
	protected InstituicaoResponsavelServico localizarServico() {
		return getLocator().localizarInstituicaoResponsavelServico();
	}

	/**
	 * Metodo que consulta um Responsavel pelo CPF/CNPJ juntamente com o codigo
	 * de compartilhamento do cadastro
	 * 
	 * @param cpfCnpj
	 * 		CPF/CNPJ da pessoa
	 * @param codCompartilhamentoCadastro
	 * 		Codigo de compartilhamento de cadastro
	 * @return
	 * 		Retorna um {@link InstituicaoResponsavelVO}
	 * @throws BancoobException 
	 */
	public InstituicaoResponsavelVO obterPorCpfCnpj(
			String cpfCnpj, Short codCompartilhamentoCadastro) throws BancoobException {
		return getServico().obterByCpfCnpj(cpfCnpj, codCompartilhamentoCadastro);
	}

	/**
	 * Obter por id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            the id pessoa compartilhamento
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @return instituicao responsavel vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public InstituicaoResponsavelVO obterPorIDPessoaCompartilhamento(Long idPessoaCompartilhamento, Short codTipoPessoa) throws BancoobException {
		return getServico().obterPorIDPessoaCompartilhamento(idPessoaCompartilhamento, codTipoPessoa);
	}
	
	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return instituicao responsavel vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public InstituicaoResponsavelVO obterPorCpfCnpj(String cpfCnpj) throws BancoobException {
		return getServico().obterByCpfCnpj(cpfCnpj);
	}
}
