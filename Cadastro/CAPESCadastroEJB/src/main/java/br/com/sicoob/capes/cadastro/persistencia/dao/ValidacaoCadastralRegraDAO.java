/*
 * SICOOB
 * 
 * ValidacaoCadastralRegraDAO.java(br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralRegraDAO)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;

/**
 * A interface ValidacaoCadastralRegraDAO.
 */
public interface ValidacaoCadastralRegraDAO extends
        CAPESCadastroCrudDominioDaoIF<ValidacaoCadastralRegra> {

	/**
	 * Realiza a validação da regra identificada pelo {@code codigoRegra},
	 * gerando registros de {@code ValidacaoCadastralErro}.
	 * 
	 * @param codigoRegra
	 *            o codigo regra a ser validada
	 * @param idPessoaCompartilhamento
	 *            o ID da {@code pessoaCompartilhamento} caso a validação deva
	 *            ser feita apenas para uma pessoa ou {@code null} caso a
	 *            validação deva ser feita para todo mundo.
	 * @param dataValidacao
	 *            <b>Opcional</b>. A data de validacao. Soh eh utilizada se a
	 *            validacao de datas for solicitada
	 * @throws BancoobException
	 *             the bancoob exception
	 * @see br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral
	 * @see br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro
	 * @see br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento
	 */
	void executarRegra(ValidacaoCadastralRegra regra, Long idPessoaCompartilhamento, DateTimeDB dataValidacao)
	        throws BancoobException;

	/**
	 * Metodo de conveniencia. Tem o mesmo efeito que a chamada
	 * <i>executarRegra(&lt;codigoRegra&gt;, <b>null</b>, <b>null</b>)</i>
	 * 
	 * @param codigoRegra
	 *            o código da regra que deve ser executada
	 * @throws BancoobException
	 * @see {@link #executarRegra(Short, Long, DateTimeDB)}
	 */
	void executarRegra(ValidacaoCadastralRegra regra) throws BancoobException;
	
	
	/**
	 * Realiza a validação da catedoria do cadastro da pessoa.
	 * 
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void executarRegraCategoriaCadastro()
	        throws BancoobException;


	/**
	 * Lista as regras executaveis (ativas e com query).
	 * 
	 * @return lista com os codigos
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralRegra> listarRegrasExecutaveis() throws BancoobException;
	
	
	/**
	 * Lista as regras executaveis (ativas e com query).
	 * 
	 * @param codPerfilCadastro valor do perfil do cadastro
	 * @param ordem ordem do perfil cadastro
	 * @return lista com os codigos
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralRegra> listarRegrasExecutaveis(Short ordem) throws BancoobException;
	
	/**
	 * Lista as regras executaveis para a revalidação do cadastro do cliente.
	 * Após apagar os erros da pessoa, o serviço retorna apenas as regras ativas
	 * para serem executadas. (Apenas componente de revalidação).
	 * 
	 * @return lista com as regras
	 * @throws BancoobException
	 */
	List<ValidacaoCadastralRegra> listarRegrasExecutaveisRevalidacao() throws BancoobException;

}