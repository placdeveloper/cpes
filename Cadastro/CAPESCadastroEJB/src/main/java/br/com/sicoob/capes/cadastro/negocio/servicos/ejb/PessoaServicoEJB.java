/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoa;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExistePessoaComMesmoCpfCnpjException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.PessoaDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Implementa as operações do serviço de pessoas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { PessoaServicoLocal.class })
@Remote( { PessoaServicoRemote.class })
public class PessoaServicoEJB extends CAPESCadastroCrudServicoEJB<Pessoa> implements
		PessoaServicoRemote, PessoaServicoLocal {

	@Inject
	@Default
	protected PessoaDAO pessoaDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaDAO getDAO() {
		return pessoaDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pessoa incluir(Pessoa pessoa) throws BancoobException {
		String cpfCnpj = pessoa.getCpfCnpj();
		validarExistePessoa(cpfCnpj, pessoa.getTipoPessoa());
		return super.incluir(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarCpfCnpj(Pessoa pessoa, String cpfCnpjNovo) throws BancoobException {

		Pessoa pessoaAlteracao = getDAO().obter(pessoa.getId());
		validarExistePessoa(cpfCnpjNovo, pessoaAlteracao.getTipoPessoa());

		// Garantindo que apenas o cpfCnpj será alterado.
		pessoaAlteracao.setCpfCnpj(cpfCnpjNovo);
		getDAO().alterar(pessoaAlteracao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pessoa consultarPessoaPorDocumento(String cpfCnpj) throws BancoobException {
		
		getLogger().debug("Consultando pessoa por documento: " + cpfCnpj);
		Pessoa pessoa = null;

		try {

			if (cpfCnpj != null && !cpfCnpj.trim().equals("")) {
				pessoa = getDAO().consultarPessoaPorDocumento(cpfCnpj);
			}

		} catch (ClienteNaoEncontradoException ex) {
			getLogger().info(cpfCnpj + " não cadastrado.");
			throw new PessoaNaoEncontradaException(ex);
		}

		return pessoa;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPessoaFisica(Pessoa pessoa) throws BancoobException {
		Pessoa pessoaConsultada = obter(pessoa.getIdPessoa());
		return TipoPessoaEnum.isPessoaFisica(pessoaConsultada.getTipoPessoa().getCodTipoPessoa());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPessoaJuridica(Pessoa pessoa) throws BancoobException {
		Pessoa pessoaConsultada = obter(pessoa.getIdPessoa());
		return TipoPessoaEnum.isPessoaJuridica(pessoaConsultada.getTipoPessoa().getCodTipoPessoa());
	}

	/**
	 * Valida o documento com base no tipo de pessoa informado e se o mesmo existe ou não na base de dados.
	 * @param cpfCnpj O cpfCnpj que será validado.
	 * @param tipoPessoa O tipo da pessoa a ser validada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private void validarExistePessoa(String cpfCnpj, TipoPessoa tipoPessoa) 
			throws BancoobException {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpfCnpj(cpfCnpj);
		pessoa.setTipoPessoa(tipoPessoa);
		
		ValidacaoPessoa validacao = new ValidacaoPessoa();
		validacao.validar(pessoa);

		if(recuperarPessoa(cpfCnpj) != null) {
			throw new ExistePessoaComMesmoCpfCnpjException();
		}		
	}
	
	/**
	 * Recuperar pessoa.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return Pessoa
	 * @throws FormatoInvalidoException lança a exceção FormatoInvalidoException
	 */
	private Pessoa recuperarPessoa(String cpfCnpj) throws FormatoInvalidoException {
		
		Pessoa pessoa = null;
		try {	
			if (cpfCnpj != null) {
				pessoa = getDAO().consultarPessoaPorDocumento(cpfCnpj);
			}
		} catch (ClienteNaoEncontradoException ex) {
			getLogger().debug(cpfCnpj + " não existe na base.");
		}
		
		return pessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) {
		return getDAO().obterPorListaPessoaInstituicao(idPessoas, idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long consultarPessoaPorCpfCnpjUR(String cpfCnpj) throws BancoobException {
		return getDAO().consultarPessoaPorCpfCnpjUR(cpfCnpj);
	}

}