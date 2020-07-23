/*
 * SICOOB
 * 
 * AbstractCAPESApiServicoPessoaIncluirEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.AbstractCAPESApiServicoPessoaIncluirEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.CAPESApiServicoPessoa;
import br.com.sicoob.capes.api.negocio.vo.AbstractPessoaVO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Marcelo.Onofre
 * 
 * @param <K>
 */
public abstract class AbstractCAPESApiServicoPessoaIncluirEJB<K extends AbstractPessoaVO> extends AbstractCAPESApiServicoPessoaEJB<K> implements
		CAPESApiServicoPessoa<K> {

	/**
	 * Obter pessoa compartilhamento.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return pessoa compartilhamento
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	protected PessoaCompartilhamento obterPessoaCompartilhamento(Integer idInstituicao, String cpfCnpj) throws BancoobException {
		validarObrigatoriedadePesquisa(idInstituicao, cpfCnpj);
		PessoaCompartilhamento pc = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate()
				.consultarPessoaPorDocumento(idInstituicao, cpfCnpj);
		if (pc == null || pc.getPessoa() == null) {
			throw new BancoobException("Pessoa não encontrada.");
		}

		return pc;
	}

	/**
	 * Validar obrigatoriedade pesquisa.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @throws ParametroNaoInformadoException
	 *             the parametro nao informado exception
	 */
	private void validarObrigatoriedadePesquisa(Integer idInstituicao, String cpfCnpj) throws ParametroNaoInformadoException {

		if (cpfCnpj == null || cpfCnpj.equals("")) {
			throw new ParametroNaoInformadoException("CPF/CNPJ");
		}
		if (idInstituicao == null) {
			throw new ParametroNaoInformadoException("ID Instituição");
		}

	}

}
