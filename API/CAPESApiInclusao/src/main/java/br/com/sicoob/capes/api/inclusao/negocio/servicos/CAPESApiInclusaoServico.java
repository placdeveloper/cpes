package br.com.sicoob.capes.api.inclusao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.BancoobServico;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ExecutarProcedimentoAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;

/**
 * Interface dos serviços.
 * 
 * @param <D>
 *            O DTO base dos serviços.
 * 
 * @author bruno.carneiro
 */
public interface CAPESApiInclusaoServico<D extends RegistroInclusaoDTO<?>> extends BancoobServico {

	/**
	 * Método de inclusão.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem incluídas.
	 * 
	 * @return O dto preenchido.
	 */
	D incluir(D dto) throws BancoobException;

	/**
	 * Método de alteração.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem alteradas.
	 * 
	 */
	void alterar(D dto) throws BancoobException;
	
	/**
	 * Método de exclusão.
	 * 
	 * @param dto
	 *            O DTO com as informação do registro a ser excluído.
	 * @throws BancoobException
	 */
	void excluir(D dto) throws BancoobException;
	
	/**
	 * Encaminha a autorização de acordo com a pessoa e a instituição.
	 * 
	 * @param dto
	 *            O DTO com as informações para encaminhar uma autorização.
	 *            
	 * @return O idRegistroControlado da autorização.
	 * 
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