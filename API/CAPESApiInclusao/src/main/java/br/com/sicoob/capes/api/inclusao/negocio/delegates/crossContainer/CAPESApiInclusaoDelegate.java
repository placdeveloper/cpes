package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.CrossContainerDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.ICAPESApiInclusaoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ExecutarProcedimentoAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.CAPESApiInclusaoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

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
public abstract class CAPESApiInclusaoDelegate<D extends RegistroInclusaoDTO<?>, S extends CAPESApiInclusaoServico<D>> extends CrossContainerDelegate<S> implements ICAPESApiInclusaoDelegate<D> {
	
	@SuppressWarnings("unchecked")
	@Override
	public CAPESApiInclusaoServiceLocator getLocator() {
		return CAPESApiInclusaoServiceLocator.getInstance();
	}

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
	public D incluir(D dto) throws BancoobException {
		return getServico().incluir(dto);
	}

	/**
	 * Método de alteração.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem alteradas.
	 * 
	 * @throws BancoobException
	 */
	public void alterar(D dto) throws BancoobException {
		getServico().alterar(dto);
	}
	
	/**
	 * Método de exclusão
	 * 
	 * @param dto
	 *            O DTO com as informações para exclusão do registro.
	 * @throws BancoobException
	 */
	public void excluir(D dto) throws BancoobException {
		getServico().excluir(dto);
	}

	/**
	 * Encaminha a autorização de acordo com a pessoa e a instituição.
	 * 
	 * @param dto
	 *            O DTO com as informações para encaminhar uma autorização.
	 * 
	 * @return {@code String} o idRegistroControlado da autorização.
	 * @throws BancoobException
	 */
	public String encaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException {
		return getServico().encaminharAutorizacao(dto);
	}
	
	/**
	 * Executa o procedimento da autorização informada com o usuário informado.
	 * 
	 * @param dto
	 *            O DTO com as informações do registro a ser aprovado.
	 * @throws BancoobException
	 */
	public void executarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException {
		getServico().executarProcedimentoAutorizacao(dto);
	}
	
	/**
	 * Cancela a autorização que está pendente, caso exista.
	 * 
	 * @param dto
	 * @param justificativa
	 * @throws BancoobException
	 */
	public void cancelarFluxoAutorizacao(D dto, String justificativa) throws BancoobException {
		getServico().cancelarFluxoAutorizacao(dto, justificativa);
	}
	
}