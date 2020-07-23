package br.com.sicoob.capes.cadastro.fachada;

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
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;

/**
 * A Classe PesquisaEnderecoFachada
 */
@RemoteService
public class PesquisaEnderecoFachada extends CAPESCadastroBOFachada {

	/**
	 * Método de pesquisa de endereco do componente 'Pesquisa Endereço'
	 * 
	 * @param dto
	 *            o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public RetornoDTO pesquisarEndereco(RequisicaoReqDTO dto) throws BancoobException {
		try {
			LocalidadeVO filtro = new LocalidadeVO();
			filtro.setNumCep((String) dto.getDados().get("numCep"));
			filtro.setNomeBairro((String) dto.getDados().get("nomeBairro"));
			filtro.setNomeLocalidade((String) dto.getDados().get("nomeLocalidade"));
			filtro.setNomeLogradouro((String) dto.getDados().get("nomeLogradouro"));
			filtro.setSiglaUF((String) dto.getDados().get("siglaUF"));

			LOCIntegracaoDelegate localidadeDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
			List<LocalidadeVO> lista = localidadeDelegate.pesquisarLocalidadeComplemento(filtro);

			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put(NOME_PADRAO_LISTA, lista);

			return retorno;
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
	
	/**
	 * Método que obtém o endereço a partir de um idLogradouro
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO pesquisarEnderecoPorIdLogradouro(RequisicaoReqDTO dto) throws BancoobException {
		try {
			LOCIntegracaoDelegate localidadeDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
			LocalidadeVO localidade = localidadeDelegate.pesquisarLocalidadePorIdLogradouro((Integer) dto.getDados().get("idLogradouro"));
			
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("localidade", localidade);
			
			return retorno;
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