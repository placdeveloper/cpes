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
 *            O DTO base do servi�o.
 * @param <E>
 *            A Entidade base do servi�o.
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
	 * M�todo de inclus�o.
	 * 
	 * @param dto
	 *            O DTO com as informa��es para serem inclu�das.
	 * 
	 * @return O dto preenchido.
	 * 
	 * @throws BancoobException
	 */
	public D incluir(D dto) throws BancoobException {
		return getServico().incluir(dto);
	}

	/**
	 * M�todo de altera��o.
	 * 
	 * @param dto
	 *            O DTO com as informa��es para serem alteradas.
	 * 
	 * @throws BancoobException
	 */
	public void alterar(D dto) throws BancoobException {
		getServico().alterar(dto);
	}
	
	/**
	 * M�todo de exclus�o
	 * 
	 * @param dto
	 *            O DTO com as informa��es para exclus�o do registro.
	 * @throws BancoobException
	 */
	public void excluir(D dto) throws BancoobException {
		getServico().excluir(dto);
	}

	/**
	 * Encaminha a autoriza��o de acordo com a pessoa e a institui��o.
	 * 
	 * @param dto
	 *            O DTO com as informa��es para encaminhar uma autoriza��o.
	 * 
	 * @return {@code String} o idRegistroControlado da autoriza��o.
	 * @throws BancoobException
	 */
	public String encaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException {
		return getServico().encaminharAutorizacao(dto);
	}
	
	/**
	 * Executa o procedimento da autoriza��o informada com o usu�rio informado.
	 * 
	 * @param dto
	 *            O DTO com as informa��es do registro a ser aprovado.
	 * @throws BancoobException
	 */
	public void executarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException {
		getServico().executarProcedimentoAutorizacao(dto);
	}
	
	/**
	 * Cancela a autoriza��o que est� pendente, caso exista.
	 * 
	 * @param dto
	 * @param justificativa
	 * @throws BancoobException
	 */
	public void cancelarFluxoAutorizacao(D dto, String justificativa) throws BancoobException {
		getServico().cancelarFluxoAutorizacao(dto, justificativa);
	}
	
}