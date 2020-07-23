/*
 * SICOOB
 * 
 * ValidacaoCadastralErroServico.java(br.com.sicoob.capes.cadastro.negocio.servicos.ValidacaoCadastralErroServico)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A interface ValidacaoCadastralErroServico.
 */
public interface ValidacaoCadastralErroServico extends CAPESCadastroCrudServico<ValidacaoCadastralErro> {

	/**
	 * O método Deletar erros pessoa.
	 *
	 * @param idPessoaCompartilhamento o valor de id pessoa compartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void deletarErrosPessoa(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param tipoRegra
	 * @return
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra)
			throws BancoobException;
	
	/**
	 * 
	 * @param idPessoaCompartilhameto
	 * @param idInstituicao
	 * @param tipoRegra
	 * @param codPerfilCadastro para receber o perfil do cadastro
	 * @return
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhameto, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra, Short codPerfilCadastro)
			throws BancoobException;

	/**
	 *  Verifica se a pessoa compartilhamento possui alguma regra cadastral restritiva.
	 *  
	 * @param idPessoaCompartilhamento
	 * @param idInstituicao 
	 * @return
	 * @throws BancoobException
	 */
	boolean isPossuiRegraCadastralRestritiva(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException;

	boolean isPossuiRegraCadastralRestritiva(Long idPessoaCompartilhamento, Integer idInstituicao, Short codigoPerfilCadastro) throws BancoobException;

	/**
	 * Deletar os erros gerados por uma determinada regra.
	 * 
	 * @param codigoRegra
	 *            o codigo da regra que gerou os erros
	 * @throws BancoobException
	 */
	void deletarErrosRegra(Short codigoRegra) throws BancoobException;

	/**
	 * Deletar os erros de pessoas "validaveis". Ou seja, aquelas pessoas que nunca foram validadas 
	 * ou que tenham sido atualizadas depois da ultima validacao.
	 * @param codigoRegra o codigo da regra
	 * @param dataValidacao a data da validacao
	 * 
	 * @throws BancoobException
	 */
	void deletarErros(Short codigoRegra, DateTimeDB dataValidacao) throws BancoobException;

	/**
	 * Verifica se existem erros cadastrados para a regra recebida como parametro
	 * 
	 * @param codigoRegra
	 *            o codigo da regra que se deseja verificar a exsistencia de erros
	 * @return {@code true} se existerem erros
	 */
	boolean isExisteErro(Short codigoRegra) throws BancoobException;
	
	/**
	 * 
	 * @param pessoa
	 * @return
	 * @throws BancoobException
	 */
	List<String> listarPendenciasPessoaRelacionada(PessoaCompartilhamento pessoa, Integer idInstituicao) throws BancoobException;

}