package br.com.sicoob.capes.cadastro.negocio.delegates;


import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.ValidacaoCadastralRegraServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;

/**
 * A Classe ValidacaoCadastralRegraDelegate.
 */
public class ValidacaoCadastralRegraDelegate extends
        CAPESCadastroCrudDominioDelegate<ValidacaoCadastralRegra, ValidacaoCadastralRegraServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralRegraServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarValidacaoCadastralRegraServico();
	}

	/**
	 * O método Revalidar cadastro.
	 *
	 * @param idPessoaCompartilhamento
	 *            o valor de idPessoaCompartilhamento
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public void revalidarCadastro(Long idPessoaCompartilhamento) throws BancoobException {
		getServico().revalidarCadastro(idPessoaCompartilhamento);
	}
	
	/**
	 * O método Revalidar cadastro.
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public void revalidarCadastro(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		getServico().revalidarCadastro(idPessoa, idInstituicao);
	}

	/**
	 * O método Revalidar cadastro baseado no perfil.
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public void revalidarCadastroPerfilCadastro(Long idPessoaCompartilhamento) throws BancoobException {
		getServico().revalidarCadastroPerfilCadastro(idPessoaCompartilhamento);
	}

	/**
	 * O método Revalidar cadastro baseado no perfil, levando em consideracao a ordem
	 * 
	 * @param idPessoa
	 * @param ordem
	 * @throws BancoobException
	 */
	public void revalidarCadastroPerfilCadastro(Long idPessoaCompartilhamento, Short ordem) throws BancoobException {
		getServico().revalidarCadastroPerfilCadastro(idPessoaCompartilhamento, ordem);
	}
	
	/**
	 * Executa a regra recebida como parametro, para todas as pessoas,
	 * independente das datas da ultima validacao/atualizacao
	 * 
	 * @param regra
	 *            a regra a ser executada
	 * @throws BancoobException
	 */
	public void validar(ValidacaoCadastralRegra regra) throws BancoobException {
		getServico().validar(regra);
	}
	
	/**
	 * Lista os codigos das regras de validacao cadastral executaveis
	 * 
	 * @return a lista com os codigos
	 * @throws BancoobException
	 */
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveis() throws BancoobException {
		return getServico().listarRegrasExecutaveis();
	}
	
	/**
	 * Lista os codigos das regras de validacao cadastral executaveis
	 * @param codPerfilCadastro valor do perfil cadastral 
	 * @return a lista com os codigos
	 * @throws BancoobException
	 */
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveis(Short ordem) throws BancoobException {
		return getServico().listarRegrasExecutaveis(ordem);
	}

	/**
	 * Executa a regra recebida como parametro, para todas as pessoas que nunca
	 * tenham sido validadas ou que tenham sido atualizadas depois da ultima
	 * validacao.
	 * 
	 * @param codigoRegra
	 *            o codigo da regra a ser executada
	 * @param dataValidacao
	 *            a data de validacao
	 * @throws BancoobException
	 */
	public void validar(ValidacaoCadastralRegra regra, DateTimeDB dataValidacao) throws BancoobException {
		getServico().validar(regra, dataValidacao);
    }
	
	/**
	 * Executa as validacoes necessarias em relacao a categoria do cadastro
	 * para ver a possibilidade de atualizacao de categoria de cadastro
	 * efetuando se possivel a mudando de categoria da pessoa
	 * 
	 * @throws BancoobException
	 */
	public void validarCategoriaCadastro() throws BancoobException {
		getServico().validarCategoriaCadastro();
    }

}