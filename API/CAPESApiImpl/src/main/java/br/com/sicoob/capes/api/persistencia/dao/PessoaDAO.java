package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;

/**
 * A Interface PessoaDAO.
 */
public interface PessoaDAO extends CAPESApiDaoIF<PessoaVO> {

	/**
	 * Obter pessoa.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	PessoaVO obterPessoa(Integer idPessoa, Integer idPessoaLegado, String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter pessoa por cpf cnpj.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaVO> obterPessoaPorCpfCnpj(String cpfCnpj) throws BancoobException;

	/**
	 * Obter pessoa por nome.
	 *
	 * @param nome o valor de nome
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaVO> obterPessoaPorNome(String nome, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter pessoa por nome apelido.
	 *
	 * @param nomeApelido o valor de nome apelido
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaVO> obterPessoaPorNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter pessoa por nome.
	 *
	 * @param nome o valor de nome
	 * @param idInstituicao o valor de id instituicao
	 * @param pagina o valor de pagina
	 * @param tamanhoPagina o valor de tamanho pagina
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<PessoaVO> obterPessoaPorNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Obter pessoa por nome apelido.
	 *
	 * @param nomeApelido o valor de nome apelido
	 * @param idInstituicao o valor de id instituicao
	 * @param pagina o valor de pagina
	 * @param tamanhoPagina o valor de tamanho pagina
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<PessoaVO> obterPessoaPorNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Obter pessoa por nome tipo pessoa.
	 *
	 * @param nome o valor de nome
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoPessoa o valor de tipo pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaVO> obterPessoaPorNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa) throws BancoobException;

	/**
	 * Obter pessoa por nome apelido tipo pessoa.
	 *
	 * @param nomeApelido o valor de nome apelido
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoPessoa o valor de tipo pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaVO> obterPessoaPorNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa) throws BancoobException;

	/**
	 * Obter pessoa por nome tipo pessoa.
	 *
	 * @param nome o valor de nome
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoPessoa o valor de tipo pessoa
	 * @param pagina o valor de pagina
	 * @param tamanhoPagina o valor de tamanho pagina
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<PessoaVO> obterPessoaPorNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Obter pessoa por nome apelido tipo pessoa.
	 *
	 * @param nomeApelido o valor de nome apelido
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoPessoa o valor de tipo pessoa
	 * @param pagina o valor de pagina
	 * @param tamanhoPagina o valor de tamanho pagina
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<PessoaVO> obterPessoaPorNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException;
	
	/**
	 * Consultar pessoa possuem autorizacao bacen.
	 *
	 * @param listaCpfCnpj o valor de lista cpf cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<String> consultarPessoaPossuemAutorizacaoBacen(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter por cpf cnpj instituicao grupo compartilhamento.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	PessoaVO obterPorCpfCnpjInstituicaoGrupoCompartilhamento(String cpfCnpj, Integer idInstituicao) throws BancoobException;

}