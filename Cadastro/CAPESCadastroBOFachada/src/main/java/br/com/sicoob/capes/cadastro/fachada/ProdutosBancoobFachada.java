package br.com.sicoob.capes.cadastro.fachada;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe ProdutosBancoobFachada.
 */
@RemoteService
public class ProdutosBancoobFachada extends CAPESCadastroBOFachada  {

	/** A constante CHAVE_PESSOA. */
	private static final String CHAVE_PESSOA = "pessoa";
	
	/** O atributo transicaoDelegate. */
	private TransicaoPessoaDelegate transicaoDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTransicaoPessoaDelegate();

	/**
	 * Validar abertura produtos bancoob.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO validarAberturaProdutosBancoob(RequisicaoReqDTO dto) throws BancoobException {
		try {
			PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(CHAVE_PESSOA);
			transicaoDelegate.validarPessoaPossuiRelacionamentoBancoob(pessoa);
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