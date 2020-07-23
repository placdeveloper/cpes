package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.UF;

/**
 * A Classe LocalidadeFachada.
 */
@RemoteService
public class LocalidadeFachada extends CAPESCadastroBOFachada {

	/** O atributo delegate. */
	private LOCIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();

	/**
	 * Listar u fs.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<UF> listarUFs() throws BancoobException {
		List<UF> ufs = new ArrayList<UF>();
		for (LOCIntegracaoUFVO vo : delegate.listarUFs()) {
			ufs.add(IntegracaoUtil.converterUF(vo));
		}
		return ufs;
	}

	/**
	 * Obter u fs.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterUFs(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO dtoRetorno = new RetornoDTO();
			dtoRetorno.setDados(new HashMap<String, Object>());
			dtoRetorno.getDados().put("ufs", listarUFs());
			return dtoRetorno;
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
		return new RetornoDTO();
	}

}