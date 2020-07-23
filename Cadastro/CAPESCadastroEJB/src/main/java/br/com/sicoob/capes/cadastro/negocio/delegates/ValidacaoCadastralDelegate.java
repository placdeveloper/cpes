package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.ValidacaoCadastralServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;

/**
 * A Classe ValidacaoCadastralDelegate.
 */
public class ValidacaoCadastralDelegate extends CAPESCadastroCrudDominioDelegate<ValidacaoCadastral, ValidacaoCadastralServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarValidacaoCadastralServico();
	}

	/**
	 * O método Atualizar data ultima validacao.
	 *
	 * @param dataUltimaValidacao o valor de data ultima validacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void atualizarDataUltimaValidacao(DateTimeDB dataUltimaValidacao) throws BancoobException {
		getServico().atualizarDataUltimaValidacao(dataUltimaValidacao);
	}

	/**
	 * O método Atualizar data ultima validacao.
	 *
	 * @param idPessoaCompartilhamento o valor de id pessoa compartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void atualizarDataUltimaValidacao(Long idPessoaCompartilhamento) throws BancoobException {
		getServico().atualizarDataUltimaValidacao(idPessoaCompartilhamento);
    }

}