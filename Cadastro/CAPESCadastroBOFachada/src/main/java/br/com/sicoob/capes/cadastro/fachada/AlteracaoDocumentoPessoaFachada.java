/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.AlterarDocumentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.AlteracaoDocumentoPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.ConsultaAlteracaoDocumentoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * @author Erico.Junior
 */
@RemoteService
public class AlteracaoDocumentoPessoaFachada extends CAPESCadastroBOFachada {

	/** O atributo delegate. */
	private AlterarDocumentoPessoaDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarAlterarDocumentoPessoaDelegate();

	/** O atributo pessoaDelegate. */
	private PessoaDelegate pessoaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaDelegate();

	/** A constante PESSOA. */
	private static final String PESSOA = "pessoa";
	
	/** A constante CPF_CNPJ_ATUAL. */
	private static final String CPF_CNPJ_ATUAL = "cpfCnpjAtual";
	
	/** A constante CPF_CNPJ_NOVO. */
	private static final String CPF_CNPJ_NOVO = "cpfCnpjNovo";
	
	/** A constante MOTIVO. */
	private static final String MOTIVO = "motivo";
	
	/** A constante SOLICITANTE. */
	private static final String SOLICITANTE = "solicitante";

	/**
	 * Incluir dados.
	 *
	 * @param req o valor de req
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirDados(RequisicaoReqDTO req) throws BancoobException {

		Map<String, Object> dados = req.getDados();
		Pessoa pessoa = (Pessoa) dados.get(PESSOA);
		String cpfCnpj = (String) dados.get(CPF_CNPJ_NOVO);
		String motivo = (String) dados.get(MOTIVO);
		String solicitante = (String) dados.get(SOLICITANTE);

		AlteracaoDocumentoPessoaDTO dto = new AlteracaoDocumentoPessoaDTO();
		dto.setPessoa(pessoa);
		dto.setCpfCnpjNovo(cpfCnpj);
		dto.setMotivo(motivo);
		dto.setSolicitante(solicitante);
		delegate.alterarDocumentoPessoa(dto);
		return new RetornoDTO();
	}

	/**
	 * Obter dados.
	 *
	 * @param req o valor de req
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO req) throws BancoobException {

		Map<String, Object> dados = req.getDados();
		String cpfCnpj = (String) dados.get(CPF_CNPJ_ATUAL);
		
		Pessoa pessoa = null;
		List<ConsultaAlteracaoDocumentoVO> lista = delegate.consultarVinculosDocumento(cpfCnpj);
		
		if(lista != null && !lista.isEmpty()) {
			Integer idPessoa = lista.get(0).getIdPessoa();
			pessoa = pessoaDelegate.obter(idPessoa);	
		}
		
		RetornoDTO resultadoDto = new RetornoDTO();
		resultadoDto.getDados().put(PESSOA, pessoa);
		resultadoDto.getDados().put(NOME_PADRAO_LISTA, lista);
		return resultadoDto;
	}
	
	public RetornoDTO recuperarDocumentosGED(RequisicaoReqDTO dto) throws BancoobException {
		List<Number> listaDocumentosGed = new ArrayList<Number>();
		RetornoDTO resultado = new RetornoDTO();
		
		String idRegistroControlado = (String) dto.getDados().get("idRegistroControlado");
		Integer tipoPessoa = (Integer) dto.getDados().get("idTipoPessoaSelecionada");

		GEDIntegracaoDelegate delegateGED = 
				CAPESIntegracaoFabricaDelegates.getInstance().criarGEDIntegracaoDelegate();
		
		try {
			listaDocumentosGed = delegateGED.recuperarDocumentosGED(idRegistroControlado, tipoPessoa.shortValue());
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
		
		resultado.getDados().put("listaDocumentosGED", listaDocumentosGed);
		
		return resultado;
	}

}

