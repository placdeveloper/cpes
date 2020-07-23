/*
 * SICOOB
 * 
 * ValidacaoCadastralRegraServico.java(br.com.sicoob.capes.cadastro.negocio.servicos.ValidacaoCadastralRegraServico)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A interface ValidacaoCadastralRegraServico.
 */
public interface ValidacaoCadastralRegraServico extends
        CAPESCadastroCrudDominioServico<ValidacaoCadastralRegra> {

	/**
	 * Faz a revalidacao do cadastro do cliente
	 * 
	 * @param idPessoaCompartilhamento
	 * @throws BancoobException
	 */
	void revalidarCadastro(Long idPessoaCompartilhamento) throws BancoobException;
	
	void revalidarCadastro(Integer idPessoa, Integer idInstiticao) throws BancoobException;

	/**
	 * Faz a revalidacao do cadastro do cliente baseado na categoria do cadastro
	 * 
	 * @param idPessoaCompartilhamento
	 * @throws BancoobException
	 */
	void revalidarCadastroPerfilCadastro(Long idPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Realiza a validacao cadastral baseado no idcompartilhamento de {@link PessoaCompartilhamento} e ordem da {@link PerfilCadastro}
	 * @param idPessoaCompartilhamento
	 * @param ordem
	 * @throws BancoobException
	 */
	
	void revalidarCadastroPerfilCadastro(Long idPessoaCompartilhamento, Short ordem) throws BancoobException;

	/**
	 * Executa a regra recebida como parametro, para todas as pessoas,
	 * independente das datas da ultima validacao/atualizacao
	 * 
	 * @param regra
	 *            a regra a ser executada
	 * @throws BancoobException
	 */
	void validar(ValidacaoCadastralRegra regra) throws BancoobException;

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
	void validar(ValidacaoCadastralRegra regra, DateTimeDB dataValidacao) throws BancoobException;

	
	/**
	 * Executa as validacoes necessarias em relacao a categoria do cadastro
	 * para ver a possibilidade de atualizacao de categoria de cadastro
	 * efetuando se possivel a mudando de categoria da pessoa
	 * 
	 * @throws BancoobException
	 */
	void validarCategoriaCadastro() throws BancoobException;

	/**
	 * Lista os codigos das regras de validacao cadastral executaveis
	 * 
	 * @return a lista com os codigos
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralRegra> listarRegrasExecutaveis() throws BancoobException;
	
	/**
	 * Lista os codigos das regras de validacao cadastral executaveis
	 * @param codPerfilCadastro valor do perfil do cadastro
	 * @return a lista com os codigos
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralRegra> listarRegrasExecutaveis(Short ordem) throws BancoobException;

}
