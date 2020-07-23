/*
 * SICOOB
 * 
 * ValidacaoCadastralErroDAO.java(br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralErroDAO)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A interface ValidacaoCadastralErroDAO.
 */
public interface ValidacaoCadastralErroDAO extends CAPESCadastroCrudDaoIF<ValidacaoCadastralErro> {

	/**
	 * Deleta os erros de uma pessoa.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o identificador da pessoa
	 * @throws BancoobException
	 */
	void deletarErrosPessoa(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Deletar os erros de pessoas "validaveis". Ou seja, aquelas pessoas que
	 * nunca foram validadas ou que tenham sido atualizadas depois da ultima
	 * validacao.
	 * @param codigoRegra o codigo da regra
	 * 
	 * @param dataValidacao
	 *            a data da validacao
	 * @throws BancoobException
	 */
	void deletarErros(Short codigoRegra, DateTimeDB dataValidacao) throws BancoobException;

	/**
	 * Lista as regras que falharam na validacao cadastral da pessoa
	 * identificada pelo {@code idPessoa}
	 * 
	 * @param idPessoa
	 *            O ID da pessoa
	 * @param tipoRegra R, I ou null, caso null ambos os tipos são consultados
	 * @return a lista de falhas
	 * @throws BancoobException
	 *             the bancoob exception
	 * @see br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro
	 * @see br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento
	 */
	List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra)
			throws BancoobException;
	
	/**
	 * Lista as regras que falharam na validacao cadastral da pessoa
	 * identificada pelo {@code idPessoa}
	 * 
	 * @param idPessoa
	 *            O ID da pessoa
	 * @param tipoRegra R, I ou null, caso null ambos os tipos são consultados
	 * @param codPerfilCadastro necessario o perfil para buscar as regras adequadas
	 * @return a lista de falhas
	 * @throws BancoobException
	 *             the bancoob exception
	 * @see br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro
	 * @see br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento
	 */
	List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra, Short codPerfilCadastro)
			throws BancoobException;

	/**
	 * Verifica se a pessoa compartilhamento possui alguma regra cadastral restritiva.
	 * 
	 * @param idPessoaCompartilhamento
	 * @param idInstituicao 
	 * @return
	 */
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
	 * Verifica se existem erros cadastrados para a regra recebida como parametro
	 * 
	 * @param codigoRegra
	 *            o codigo da regra que se deseja verificar a exsistencia de erros
	 * @return {@code true} se existerem erros
	 */
	boolean isExisteErro(Short codigoRegra) throws BancoobException;
	
	/**
	 * @param pessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<String> listarPendenciasPessoaRelacionada(PessoaCompartilhamento pessoa, Integer idInstituicao) throws BancoobException;
}