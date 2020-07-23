package br.com.sicoob.capes.corporativo.fachada;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.DominioDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe ProcurarPessoaExternoFachada.
 */
@RemoteService
public class ProcurarPessoaExternoFachada extends CAPESCorporativoFachada {

	/**
	 * Método de pesquisa por CPF/CNPJ do componente
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public DadosSelGeralDTO procurarPessoaPorCpfCnpJ(RequisicaoReqDTO dto) throws BancoobException {
		try {
			ProcurarPessoaExternoVO filtro = obterFiltro(dto);
			return procurar(filtro, 1 - DESLOCAMENTO_NUMERO_PAGINA, TAMANHO_PAGINA_PADRAO);
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

	/**
	 * Método que obtém as definições do componente.
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesSelecionarPessoa() throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		DominioDelegate dominioDelegate = CAPESApiFabricaDelegates.getInstance().criarDominioDelegate();
		retorno.getDados().put("listaTipoPessoa", dominioDelegate.obterPorTipoDominio(TipoDominioEnum.TIPO_PESSOA));

		return retorno;
	}

	/**
	 * Método de procura geral do componente
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public DadosSelGeralDTO procurarPessoas(RequisicaoReqDTO dto) throws BancoobException {
		try {
			int pagina = Integer.valueOf(dto.getDados().get("pagina").toString()) - DESLOCAMENTO_NUMERO_PAGINA;
			ProcurarPessoaExternoVO filtro = obterFiltro(dto);
			return procurar(filtro, pagina, TAMANHO_PAGINA_PADRAO);
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

	/**
	 * Método que realiza a consulta para o componente
	 * @param filtro
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	private DadosSelGeralDTO procurar(ProcurarPessoaExternoVO filtro, Integer pagina, Integer tamanhoPagina) throws BancoobException {
		ConsultaDto<ProcurarPessoaExternoVO> criterios = new ConsultaDto<ProcurarPessoaExternoVO>();
		criterios.setTamanhoPagina(tamanhoPagina);
		criterios.setPagina(pagina);
		criterios.setFiltro(filtro);

		PessoaDelegate delegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		ConsultaDto<?> resultado = delegate.procurarPessoaExterno(criterios);

		return montarResultado(resultado);
	}

	/**
	 * Obtém O filtro para as consultas do componente
	 * @param dto
	 * @return
	 */
	private ProcurarPessoaExternoVO obterFiltro(RequisicaoReqDTO dto) {
		
		SicoobLoggerPadrao logger = SicoobLoggerPadrao.getInstance(getClass());
		ProcurarPessoaExternoVO retorno = null;
		if (dto != null && dto.getDados() != null) {
			
			logger.debug("[CAPES - Componente procurar pessoa externo] RequisicaoReqDTO: ", dto
			        .getDados().toString());
			
			retorno = new ProcurarPessoaExternoVO();
			retorno.setIdPessoa((Integer) dto.getDados().get("idPessoa"));
			retorno.setApelido((String) dto.getDados().get("apelido"));
			retorno.setCpfCnpj((String) dto.getDados().get("cpfCnpj"));
			retorno.setNome((String) dto.getDados().get("nome"));
			retorno.setProcurarBancoob((Boolean) dto.getDados().get("procurarBancoob"));
			retorno.setSomenteClientes((Boolean) dto.getDados().get("somenteClientes"));
			retorno.setNumeroCooperativa((Integer) dto.getDados().get("numeroCooperativa"));
			Integer idTipoPessoa = (Integer) dto.getDados().get("idTipoPessoa");
			
			if(idTipoPessoa != null){
				retorno.setIdTipoPessoa(idTipoPessoa.shortValue());
			}
			
			logger.debug(
			        "[CAPES - Componente procurar pessoa externo] ",
			        ToStringBuilder.reflectionToString(retorno));
		}

		return retorno;
	}

}