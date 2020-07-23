package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ExecutarProcedimentoAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;

/**
 * Delegate base para os demais delegates do projeto.
 * 
 * @param <D>
 *            O DTO base do serviço.
 * @param <E>
 *            A Entidade base do serviço.
 * 
 * @author bruno.carneiro
 */
public abstract interface ICAPESApiInclusaoDelegate<D extends RegistroInclusaoDTO<?>> {

	/**
	 * Método de inclusão.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem incluídas.
	 * 
	 * @return O dto preenchido.
	 * 
	 * @throws BancoobException
	 */
	D incluir(D dto) throws BancoobException;

	/**
	 * Método de alteração.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem alteradas.
	 * 
	 * @throws BancoobException
	 */
	void alterar(D dto) throws BancoobException;

	/**
	 * Método de exclusão
	 * 
	 * @param dto
	 *            O DTO com as informações para exclusão do registro.
	 * @throws BancoobException
	 */
	void excluir(D dto) throws BancoobException;

	/**
	 * Encaminha a autorização de acordo com a pessoa e a instituição.
	 * 
	 * @param dto
	 *            O DTO com as informações para encaminhar uma autorização.
	 * 
	 * @return {@code String} o idRegistroControlado da autorização.
	 * @throws BancoobException
	 */
	String encaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException;

	/**
	 * Executa o procedimento da autorização informada com o usuário informado.
	 * 
	 * @param dto
	 *            O DTO com as informações do registro a ser aprovado.
	 * @throws BancoobException
	 */
	void executarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException;

	/**
	 * Cancela a autorização que está pendente, caso exista.
	 * 
	 * @param dto
	 * @param justificativa
	 * @throws BancoobException
	 */
	void cancelarFluxoAutorizacao(D dto, String justificativa) throws BancoobException;

}