/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EmpreendimentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ProdutividadeDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum;
import br.com.sicoob.capes.cadastro.negocio.proxies.ProdutividadeProxy;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.tipos.DateTime;

/**
 * @author erico.junior
 */
@RemoteService
public class ProdutividadeFachada extends CAPESCadastroBOFachada {

	/** A constante PRODUTIVIDADE. */
	private static final String PRODUTIVIDADE = "produtividade";
	
	/** A constante PESSOA. */
	private static final String PESSOA = "pessoa";
	
	/** A constante LISTA_IMOVEIS. */
	private static final String LISTA_IMOVEIS = "imoveis";
	
	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo produtividadeDelegate. */
	private ProdutividadeDelegate produtividadeDelegate = fabrica.criarProdutividadeDelegate();
	
	/** O atributo empreendimentoDelegate. */
	private EmpreendimentoDelegate empreendimentoDelegate = fabrica.criarEmpreendimentoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = fabrica
			.criarAutorizacaoCadastroDelegate();
		
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		try {
			PessoaCompartilhamento pessoa = obterPessoaSelecionada(dto);
			validarEntidade(pessoa);
			BemDelegate bemDelegate = CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
			retorno.getDados().put(LISTA_IMOVEIS, bemDelegate.obterImoveisRurais(pessoa.getId()));
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
		
		return retorno;
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
		DadosSelGeralDTO retorno = new DadosSelGeralDTO();
		
		try {
			List<ProdutividadeProxy> listaAtuais = listarEmAberto(dto);
			List<ProdutividadeProxy> listaAnteriores = listarAnteriores(dto);
			
			Collections.sort(listaAtuais, new BeanComparator<ProdutividadeProxy>("idRegistroControlado"));
			Collections.sort(listaAnteriores, new BeanComparator<ProdutividadeProxy>("idRegistroControlado"));
			
			retorno.getDados().put("atuais", listaAtuais);
			retorno.getDados().put("anteriores", listaAnteriores);
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

		return retorno;
	}

	/**
	 * Incluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			Produtividade produtividade = (Produtividade) dto.getDados().get(PRODUTIVIDADE);
			Produtividade incluida = produtividadeDelegate.incluir(produtividade);
			
			retorno.getDados().put(PRODUTIVIDADE, incluida);
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
		return retorno;
	}

	/**
	 * Alterar dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarDados(RequisicaoReqDTO dto)
			throws BancoobException {
		try {
			Produtividade produtividade = (Produtividade) dto.getDados().get(PRODUTIVIDADE);
			produtividadeDelegate.alterar(produtividade);
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
	 * Excluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		try {
			Produtividade produtividade = (Produtividade) dto.getDados().get(PRODUTIVIDADE);
			validarEntidade(produtividade, "da Produtividade");
			produtividadeDelegate.excluir(produtividade.getId());
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
	 * Obter dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			Produtividade produtividade = (Produtividade) dto.getDados().get(PRODUTIVIDADE);
			retorno.getDados().put(PRODUTIVIDADE, produtividadeDelegate.obter(produtividade.getId()));
			
			//Verifica se o registro está bloqueado para alteração.
			retorno.getDados().put("isRegistroBloqueadoAlteracao",
					autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao((Produtividade) dto.getDados().get(PRODUTIVIDADE)));
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
		
		return retorno;
	}

	/**
	 * Finalizar exploracao.
	 *
	 * @param req o valor de req
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO finalizarExploracao(RequisicaoReqDTO req)
			throws BancoobException {
		
		try {
			Map<String, Object> mapa  = req.getDados();
			String valorPercentual = (String) mapa.get("percentualFrustracao");
			BigDecimal percentual = new BigDecimal(valorPercentual);
			ProdutividadeDTO dto = new ProdutividadeDTO();
			dto.setHouveFrustracao((Boolean) mapa.get("houveFrustracao"));
			dto.setIdProdutividade(Long.valueOf(mapa.get("idProdutividade").toString()));
			dto.setPercentualFrustracao(percentual);
			dto.setDataOcorrencia((DateTime) mapa.get("dataOcorrencia"));
			produtividadeDelegate.finalizarExploracao(dto);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, req);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, req);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, req);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, req);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, req);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, req);
		} 
		return new RetornoDTO();
	}

	/**
	 * Obter empreendimento.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterEmpreendimento(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retorno = new RetornoDTO();
		try {
			Integer codigo = (Integer) dto.getDados().get("codigoEmpreendimento");
			retorno.getDados().put("empreendimento", empreendimentoDelegate.obter(codigo));
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
		return retorno;
	}

	// Métodos auxiliares
	
	/**
	 * Listar em aberto.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ProdutividadeProxy> listarEmAberto(RequisicaoReqDTO dto) throws BancoobException {
		List<Short> situacoes = new ArrayList<Short>();
		situacoes.add(SituacaoProdutividadeEnum.EM_ABERTO.getCodigo());
		return listar(dto, situacoes);
	}
	
	/**
	 * Listar anteriores.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ProdutividadeProxy> listarAnteriores(RequisicaoReqDTO dto) throws BancoobException {
		List<Short> situacoes = new ArrayList<Short>();
		situacoes.add(SituacaoProdutividadeEnum.FINALIZADO_FRUSTRACAO.getCodigo());
		situacoes.add(SituacaoProdutividadeEnum.FINALIZADO_SUCESSO.getCodigo());
		return listar(dto, situacoes);
	}		
	
	/**
	 * Listar.
	 *
	 * @param dto o valor de dto
	 * @param situacoes o valor de situacoes
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ProdutividadeProxy> listar(RequisicaoReqDTO dto, List<Short> situacoes) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		Produtor produtor = (Produtor) dto.getDados().get("produtor");
		Produtividade produtividade = new Produtividade();
		produtividade.setPessoaCompartilhamento(pessoa);
		produtividade.setProdutor(produtor);
		
		ConsultaProdutividadeDTO criterios = new ConsultaProdutividadeDTO();
		criterios.setSituacoes(situacoes);
		criterios.setFiltro(produtividade);
		
		List<ProdutividadeProxy> lista = produtividadeDelegate.listar(criterios);
		Collections.sort(lista, new BeanComparator<ProdutividadeProxy>("idRegistroControlado"));
		
		return lista;
	}	

	/**
	 * Obter pessoa selecionada.
	 *
	 * @param dto o valor de dto
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaCompartilhamento obterPessoaSelecionada(RequisicaoReqDTO dto) throws BancoobException {
		return (PessoaCompartilhamento) dto.getDados().get(PESSOA);
	}	
}