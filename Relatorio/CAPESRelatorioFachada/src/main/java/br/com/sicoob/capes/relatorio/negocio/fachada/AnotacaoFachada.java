/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.ITXIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.excecao.ITXIntegracaoNegocioException;

/**
 * Fachada para as anotações.
 * @author Erico.Junior
 */
@RemoteService
public class AnotacaoFachada extends CAPESRelatorioComumFachada {

	/** A constante ANOTACAO. */
	private static final String ANOTACAO = "anotacao";

	/**
	 * Instancia um novo AnotacaoFachada.
	 */
	public AnotacaoFachada() {
		super(ANOTACAO);
	}
	
	/** O atributo tipoAnotacaoDelegate. */
	private TipoAnotacaoDelegate tipoAnotacaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarTipoAnotacaoDelegate();
	
	/** O atributo tipoPessoaDelegate. */
	private TipoPessoaDelegate tipoPessoaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarTipoPessoaDelegate();

	/**
	 * Obter definicoes relatorio.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoesRelatorio() throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("tiposAnotacao", tipoAnotacaoDelegate.listar());
		retorno.getDados().put("tiposPessoa", tipoPessoaDelegate.listar());
		return retorno;
	}	
	
	/**
	 * Obter relatorio detalhe anotacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterRelatorioDetalheAnotacao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		try {
			Number idConsulta = (Number) dto.getDados().get("idConsulta");
			ITXIntegracaoDelegate itxIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarITXIntegracaoDelegate();
			byte[] arquivoPDF = itxIntegracaoDelegate.gerarRelatorioConsulta(idConsulta.intValue());
			
			retorno.getDados().put("arquivoPDF", arquivoPDF);
			retorno.getDados().put("nomeArquivoPDF", "Anotacao_" + idConsulta + ".pdf");
		} catch (ITXIntegracaoNegocioException e) {
			throw new CAPESCadastroNegocioException(e.getMessage());
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}
		
		return retorno;
	}

}