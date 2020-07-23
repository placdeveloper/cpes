/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CategoriaProdutorDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ProdutorDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 *
 */
@RemoteService
public class ProdutorFachada extends CAPESCadastroBOFachada {

	/** A constante PRODUTOR. */
	private static final String PRODUTOR = "produtor";
	
	/** A constante PRODUTOR_VIGENTE. */
	private static final String PRODUTOR_VIGENTE = "produtorVigente";
	
	/** A constante PRODUTOR_ALTERACAO. */
	private static final String PRODUTOR_ALTERACAO = "produtorAlteracao";
	
	/** A constante PESSOA. */
	private static final String PESSOA = "pessoa";
	
	/** A constante LISTA_CATEGORIAS. */
	private static final String LISTA_CATEGORIAS = "categorias";
	
	/** A constante DEFINICOES_COMP_GED. */
	private static final String DEFINICOES_COMP_GED = "definicoesComponenteGED";
	
	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo produtorDelegate. */
	private ProdutorDelegate produtorDelegate = fabrica.criarProdutorDelegate();
	
	/** O atributo categoriaDelegate. */
	private CategoriaProdutorDelegate categoriaDelegate = fabrica.criarCategoriaProdutorDelegate();
	
	/** O atributo autorizacaoDelegate. */
	private AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizacaoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = fabrica
			.criarAutorizacaoCadastroDelegate();
	
	/**
	 * Incluir produtor.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirProdutor(RequisicaoReqDTO dto)throws BancoobException {	
		try {
			Produtor produtor = (Produtor) dto.getDados().get(PRODUTOR);
			produtorDelegate.validarIncluir(produtor);
			produtorDelegate.incluir(produtor);
			
			return obterProdutor(dto);
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
	 * Excluir produtor.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirProdutor(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Produtor produtor = (Produtor) dto.getDados().get(PRODUTOR);
			produtorDelegate.excluirEntidade(produtor);
			return obterProdutor(dto);
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
	 * Obter produtor.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterProdutor(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			PessoaCompartilhamento pessoa = obterPessoaSelecionada(dto);
			CategoriaProdutor categoria = new CategoriaProdutor();
			categoria.setAtivo(true);
			retorno.getDados().put(LISTA_CATEGORIAS, categoriaDelegate.obterCategoriasPorStatus(categoria));

			Produtor produtorPesquisa = new Produtor();
			produtorPesquisa.setIdPessoaCompartilhamento(pessoa.getIdPessoaCompartilhamento());
			
			Produtor produtorVigente = produtorDelegate.obter(pessoa.getIdPessoaCompartilhamento());
			if(produtorVigente != null){
				produtorVigente.setCodigoSituacaoAprovacao((short) SituacaoRegistroEnum.VIGENTE.ordinal());
			}
			retorno.getDados().put(PRODUTOR_VIGENTE, produtorVigente);

			Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade(produtorPesquisa);
			if(autorizacao != null){
				Produtor produtorAlteracao = SerializacaoUtils.deserializarJson(Produtor.class, autorizacao.getAlteracao());
				produtorAlteracao.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(produtorAlteracao));
				produtorAlteracao.setSituacaoAprovacao(autorizacao.obterSituacao());
				retorno.getDados().put(PRODUTOR_ALTERACAO, produtorAlteracao);
			}

			retorno.getDados().put(DEFINICOES_COMP_GED, montarObjetoDefinicoesGED(Short.parseShort(((Integer) dto.getDados().get("idTipoPessoa")).toString())));
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
	 * Alterar produtor.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarProdutor(RequisicaoReqDTO dto)throws BancoobException {
		try {
			Produtor produtor = (Produtor) dto.getDados().get(PRODUTOR);
			produtorDelegate.alterar(produtor);
			return obterProdutor(dto);
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
	 * Validar inclusao produtor.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO validarInclusaoProdutor(RequisicaoReqDTO dto)
			throws BancoobException {
		PessoaCompartilhamento pessoa = obterPessoaSelecionada(dto);
		produtorDelegate.validarInclusaoProdutor(pessoa);
		return new RetornoDTO();
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
	
	/**
	 * Montar objeto definicoes ged.
	 *
	 * @param idTipoPessoa o valor de id tipo pessoa
	 * @return List
	 */
	private List<DefinicoesComponenteGedVO> montarObjetoDefinicoesGED(Short idTipoPessoa){
		List<DefinicoesComponenteGedVO> retorno = new ArrayList<DefinicoesComponenteGedVO>();
		DefinicoesComponenteGedVO vo = new DefinicoesComponenteGedVO();
		Set<String> chavesNegocio = new LinkedHashSet<String>();
		
		vo.setIdTipoPessoa(idTipoPessoa);
		vo.setSiglaTipoDocumento(Negocio.GED_SIGLA_TIPO_DOCUMENTO_DECLARACAO_APTIDAO_PRONAF);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		vo.setChavesNegocio(chavesNegocio);
		retorno.add(vo);
		
		vo = new DefinicoesComponenteGedVO();
		chavesNegocio = new LinkedHashSet<String>();
		vo.setIdTipoPessoa(idTipoPessoa);
		vo.setSiglaTipoDocumento(Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVACAO_EXERCICIO_ATIVIDADE_RURAL);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		vo.setChavesNegocio(chavesNegocio);
		retorno.add(vo);
		
		return retorno;
	}

}