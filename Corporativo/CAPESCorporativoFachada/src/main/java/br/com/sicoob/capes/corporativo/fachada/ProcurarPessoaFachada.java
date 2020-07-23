/*
 * SICOOB
 * 
 * ProcurarPessoaFachada.java(br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaFachada)
 */
package br.com.sicoob.capes.corporativo.fachada;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.negocio.dto.SelPessoaReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.InstituicaoResponsavelDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * The Class ProcurarPessoaFachada.
 */
@RemoteService
public class ProcurarPessoaFachada extends CAPESCorporativoFachada {

	/** A Constante TAMANHO_PAGINA_PESQUISA_PLATAFORMA. */
	private static final int TAMANHO_PAGINA_PESQUISA_PLATAFORMA = 10;
	
	private final ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());

	/** O atributo delegate. */
	private PessoaDelegate delegate = CAPESApiFabricaDelegates.getInstance()
			.criarPessoaDelegate();

	/**
	 * Obter dados selecao.
	 * 
	 * @param req
	 *            the req
	 * @return dados sel geral dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public DadosSelGeralDTO obterDadosSelecao(SelPessoaReqDTO dto) throws BancoobException {
		try {
			return obterDadosSelecao(dto, TAMANHO_PAGINA_PADRAO);
			
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
	 * Obter dados selecao plataforma.
	 * 
	 * @param req
	 *            the req
	 * @return dados sel geral dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public DadosSelGeralDTO obterDadosSelecaoPlataforma(SelPessoaReqDTO dto)
			throws BancoobException {
		try {
			return obterDadosSelecaoPlataformaResumido(dto, TAMANHO_PAGINA_PESQUISA_PLATAFORMA);
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
	 * Obter dados selecao plataforma.
	 * 
	 * Retorna as informacoes minimas e com limite de registros.
	 * 
	 * @param req
	 *            the req
	 * @return dados sel geral dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private DadosSelGeralDTO obterDadosSelecaoPlataformaResumido(SelPessoaReqDTO req, int tamanhoPagina) throws BancoobException {
		
		int pagina = req.getPagina() - DESLOCAMENTO_NUMERO_PAGINA;
		String procuraPor = String.valueOf(req.getValor());
		
		PessoaPlataformaVO filtro = new PessoaPlataformaVO();
				
		ConsultaDto<PessoaPlataformaVO> consultaDto = new ConsultaDto<PessoaPlataformaVO>();
		consultaDto.setProcurarPor(procuraPor);
		consultaDto.setTipoProcura(req.getTipoProcura());
		consultaDto.setTamanhoPagina(tamanhoPagina);
		consultaDto.setPagina(pagina);
		consultaDto.setFiltro(filtro);
		
		ConsultaDto<PessoaPlataformaVO> resposta = delegate.pesquisarPessoaPlataformaResumido(consultaDto);
		return montarResultado(resposta);
	}	
	
	
	/**
	 * Obter dados selecao.
	 * 
	 * @param req
	 *            the req
	 * @param tamanhoPagina
	 *            the tamanho pagina
	 * @return dados sel geral dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private DadosSelGeralDTO obterDadosSelecao(SelPessoaReqDTO req, int tamanhoPagina) throws BancoobException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Informacoes do Usuario na facahda:");
			logger.debug(InformacoesUsuario.getInstance().toString());
			logger.debug(ToStringBuilder.reflectionToString(InformacoesUsuario.getInstance()));
		}
		
		int pagina = req.getPagina() - DESLOCAMENTO_NUMERO_PAGINA;
		String procuraPor = String.valueOf(req.getValor());
		boolean possuiRelacionamentoBancoob = (req.getPossuiRelacionamentoBancoob() != null)
				&& req.getPossuiRelacionamentoBancoob();

		PessoaPlataformaVO filtro = new PessoaPlataformaVO();
		filtro.setIsCliente(req.getListaApenasClientes());
		filtro.setPossuiRelacionamentoBancoob(possuiRelacionamentoBancoob);
		if(req.getTipoPessoa() == 0 || req.getTipoPessoa() == 1){
			filtro.setCodTipoPessoa(Integer.valueOf(req.getTipoPessoa()).shortValue());
		}
		
		if(req.getTipoProcura().equals("POR CODIGO COMPARTILHAMENTO")){
			filtro.setIdCompartilhamento(Long.valueOf(procuraPor));
		}
		
		ConsultaDto<PessoaPlataformaVO> consultaDto = new ConsultaDto<PessoaPlataformaVO>();
		consultaDto.setProcurarPor(procuraPor);
		consultaDto.setTipoProcura(req.getTipoProcura());
		consultaDto.setTamanhoPagina(tamanhoPagina);
		consultaDto.setPagina(pagina);
		consultaDto.setFiltro(filtro);
		
		ConsultaDto<PessoaPlataformaVO> resposta = delegate.pesquisarPessoaPlataforma(consultaDto);
		return montarResultado(resposta);
	}	
	
	/**
	 * Obter pessoa cadastro unico.
	 * 
	 * @param dto
	 *            the dto
	 * @return retorno dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public RetornoDTO obterPessoaCadastroUnico(RequisicaoReqDTO dto) throws BancoobException {

		try {
			Integer idPessoaLegado = (Integer) dto.getDados().get("numPessoa");
			PessoaPlataformaVO pessoa = delegate.consultarPessoaPlataformaPorIdPessoaLegado(idPessoaLegado);
			
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("pessoa", pessoa);
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
	 * {@inheritDoc}
	 */
	public RetornoDTO obterInstituicaoResponsavelCadastro(RequisicaoReqDTO dto) throws BancoobException {

		try {
			Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
			Integer codTipoPessoa = (Integer) dto.getDados().get("codTipoPessoa");
					
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("responsavel",
					isResponsavel(idPessoaCompartilhamento.longValue(), codTipoPessoa.shortValue()));
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
	 * Verifica se � responsavel.
	 * 
	 * @param idPessoaCompartilhamento
	 *            the id pessoa compartilhamento
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @return true, se for responsavel
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private boolean isResponsavel(Long idPessoaCompartilhamento, Short codTipoPessoa) throws BancoobException {

		InstituicaoResponsavelDelegate responsavelDelegate =
				CAPESApiFabricaDelegates.getInstance().criarInstituicaoResponsavelDelegate();
		InstituicaoResponsavelVO responsavel = responsavelDelegate
				.obterPorIDPessoaCompartilhamento(idPessoaCompartilhamento, codTipoPessoa);
		Integer instituicaoResponsavel = responsavel.getIdInstituicao();
		Integer instituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		return instituicao.equals(instituicaoResponsavel);
	}

	/**
	 * Obtém os dados da pessoa do legado para utilização na plataforma de
	 * atendimento
	 * 
	 * @param cpfCnpj
	 *            O CPF (ou CNPJ) da pessoa
	 * @param idInstituicao
	 *            O ID da instituição
	 * 
	 * @return um mapa com os dados
	 */
	public RetornoDTO obterDadosPessoaLegado(RequisicaoReqDTO dto) throws BancoobException {
		
		try {
			String cpfCnpj = (String) dto.getDados().get("cpfCnpj");
			Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
			
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("pessoaLegado", delegate.obterDadosPessoaLegado(cpfCnpj, idInstituicao));
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
