/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.List;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Fachada para os relacionamentos com as instituições.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class RelacionamentoInstituicaoFachada extends CAPESCadastroBOFachada {

	/** A constante RELACIONAMENTO. */
	private static final String RELACIONAMENTO = "relacionamento";
	
	/** A constante PESSOA. */
	private static final String PESSOA = "pessoa";

	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo delegate. */
	private RelacionamentoInstituicaoDelegate delegate = 
			fabrica.criarRelacionamentoInstituicaoDelegate();

	/**
	 * Alterar gestor cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarGestorCadastro(RequisicaoReqDTO dto)
			throws BancoobException {

		Map<String, Object> dados = dto.getDados();
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dados.get(PESSOA);
		RelacionamentoInstituicaoDTO vo = (RelacionamentoInstituicaoDTO) dados.get(RELACIONAMENTO);

		ResponsavelCadastro novoResponsavel = new ResponsavelCadastro();
		novoResponsavel.setPessoa(pessoa);
		novoResponsavel.setIdInstituicao(vo.getInstituicao().getIdInstituicao());

		delegate.alterarGestorCadastro(novoResponsavel);
		return new RetornoDTO();
	}

	/**
	 * Obter dados selecao.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
		try {
			PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
			List<RelacionamentoInstituicaoDTO> lista = delegate.listarRelacionamentoInstituicao(pessoa);
			resultadoDto.getDados().put(NOME_PADRAO_LISTA, lista);
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
		return resultadoDto;
	}

	/**
	 * Replicar cadastro bancoob.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO replicarCadastroBancoob(RequisicaoReqDTO dto)
			throws BancoobException {
		
		try {
			PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
			ReplicacaoCadastroDelegate replicacaoDelegate = 
					CAPESCadastroFabricaDelegates.getInstance().criarReplicacaoCadastroDelegate();
			
			replicacaoDelegate.iniciarRelacionamentoBancoob(pessoa);
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
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
	 * Atualizar assinatura foto bancoob.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO atualizarAssinaturaFotoBancoob(
			RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		delegate.atualizarAssinaturaFotoBancoob(pessoa);
		return new RetornoDTO();
	}
}
