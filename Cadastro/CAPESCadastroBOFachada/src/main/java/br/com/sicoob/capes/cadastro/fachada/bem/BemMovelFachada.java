package br.com.sicoob.capes.cadastro.fachada.bem;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
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
 * Fachada dos bem móveis.
 * 
 * @author bruno.carneiro
 */
@RemoteService
public class BemMovelFachada extends BemFachada {

	/**
	 * Método construtor
	 */
	public BemMovelFachada() {
		super("bemMovel");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDefinicoes(dto);
		try {
			retorno.getDados().put("ufs", listarUFs());
			retorno.getDados().put("listaTiposBem", obterFabricaDelegates().criarTipoBemMovelDelegate().listar());
			retorno.getDados().put("listaTiposChassi", obterFabricaDelegates().criarTipoChassiDelegate().listar());
			retorno.getDados().put("listaTiposCores", obterFabricaDelegates().criarTipoCorAutomovelDelegate().listar());
			retorno.getDados().put("listaTiposEstadoConservacao", obterFabricaDelegates().criarTipoEstadoConservacaoDelegate().listar(Boolean.FALSE));

			retorno.getDados().put("listaTiposOnus", obterFabricaDelegates().criarTipoOnusBemDelegate().listar());
			
			retorno.getDados().put("definicoesComponenteGED", obterDefinicoesGED(dto));
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}
	
	/**
	 * Obtém as ufs
	 * 
	 * @return
	 * @throws BancoobException
	 */
	private List<UF> listarUFs() throws BancoobException {
		LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		List<UF> ufs = new ArrayList<UF>();
		for (LOCIntegracaoUFVO vo : locDelegate.listarUFs()) {
			ufs.add(IntegracaoUtil.converterUF(vo));
		}
		return ufs;
	}

}