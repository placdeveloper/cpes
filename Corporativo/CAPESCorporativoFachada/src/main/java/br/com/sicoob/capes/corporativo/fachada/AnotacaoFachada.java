/*
 * SICOOB
 * 
 * AnotacaoFachada.java(br.com.sicoob.capes.corporativo.fachada.AnotacaoFachada)
 */
package br.com.sicoob.capes.corporativo.fachada;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.delegates.AnotacaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;

/**
 * @author Erico.Junior
 *
 */
@RemoteService
public class AnotacaoFachada extends CAPESCorporativoFachada {

	/** O atributo anotacao delegate. */
	private AnotacaoPessoaDelegate anotacaoDelegate = 
			CAPESApiFabricaDelegates.getInstance().criarAnotacaoPessoaDelegate();
	
	/**
	 * Obter dados selecao.
	 * 
	 * @param dto
	 *            the dto
	 * @return dados sel geral dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto)
			throws BancoobException {
		
		try {
			Integer idPessoa = (Integer) dto.getDados().get("idPessoa");
			Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
			Boolean baixadas = (Boolean) dto.getDados().get("baixadas");
				
			List<AnotacaoPessoaVO> anotacoes = anotacaoDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao, baixadas);
			
			DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
			resultadoDto.getDados().put("listaAnotacoes", anotacoes);
			return resultadoDto;
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new DadosSelGeralDTO();
	}

}
