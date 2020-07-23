/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ConselhoRegionalDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.InformacaoProfissionalDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaIntegracaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TelefoneDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAfastamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFuncionarioDelegate;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais.ConversorConselho;
import br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais.ConversorInformacaoProfissional;
import br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais.ConversorTipoAfastamento;
import br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais.ConversorTipoFuncionario;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.InformacaoProfissionalVO;

/**
 * @author Erico.Junior
 */
@RemoteService
public class InformacaoProfissionalFachada extends CAPESCadastroBOFachada {

	/** A constante PRODUTOS_BANCOOB. */
	protected static final String PRODUTOS_BANCOOB = "produtosBancoob";
	
	/** A constante PESSOA. */
	protected static final String PESSOA = "pessoa";
	
	/** A constante IDPESSOA. */
	protected static final String IDPESSOA = "idPessoa";
	
	/** A constante ID_INSTITUICAO. */
	protected static final String ID_INSTITUICAO = "idInstituicao";
	
	/** A constante INFORMACAO. */
	protected static final String INFORMACAO = "informacao";
	
	/** O atributo fabrica. */
	private transient CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo tipoFuncionarioDelegate. */
	private transient TipoFuncionarioDelegate tipoFuncionarioDelegate = 
			fabrica.criarTipoFuncionarioDelegate();
	
	/** O atributo tipoAfastamentoDelegate. */
	private transient TipoAfastamentoDelegate tipoAfastamentoDelegate = 
			fabrica.criarTipoAfastamentoDelegate();
	
	/** O atributo conselhoDelegate. */
	private transient ConselhoRegionalDelegate conselhoDelegate = 
			fabrica.criarConselhoRegionalDelegate();
	
	/** O atributo informacoesDelegate. */
	private transient InformacaoProfissionalDelegate informacoesDelegate = 
			fabrica.criarInformacaoProfissionalDelegate();
	
	/** O atributo integracaoDelegate. */
	private transient PessoaIntegracaoDelegate integracaoDelegate = 
			fabrica.criarPessoaIntegracaoDelegate();
	
	/** O atributo telefoneDelegate. */
	private transient TelefoneDelegate telefoneDelegate = fabrica.criarTelefoneDelegate();
	
	/**
	 * Obter dados selecao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO resultado = new RetornoDTO();

		try {
			Integer idPessoa = (Integer) dto.getDados().get(IDPESSOA);
			Pessoa pessoa = new Pessoa();
			pessoa.setIdPessoa(idPessoa);
			
			List<InformacaoProfissional> lista = null;

			if(isProdutosBancoob(dto)) {
				lista = informacoesDelegate.listar(pessoa, Constantes.Comum.ID_INSTITUICAO_BANCOOB);
			} else {
				lista = informacoesDelegate.listar(pessoa);
			}		
			
			resultado.getDados().put("lista", ConversorInformacaoProfissional.converter(lista));
			
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
		return resultado;
	}

	/**
	 * Alterar dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarDados(RequisicaoReqDTO dto)throws BancoobException {
		try {
			InformacaoProfissionalVO vo = (InformacaoProfissionalVO)dto.getDados().get(INFORMACAO);
			InformacaoProfissional informacao = ConversorInformacaoProfissional.converter(vo);
			informacoesDelegate.alterar(informacao);
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
			InformacaoProfissionalVO vo = (InformacaoProfissionalVO)dto.getDados().get(INFORMACAO);
			Integer idInformacao = ConversorInformacaoProfissional.obterIdInformacao(vo);
			
			InformacaoProfissional informacao = informacoesDelegate.obter(idInformacao);
			
			retorno.getDados().put(INFORMACAO, ConversorInformacaoProfissional.converter(informacao));
			
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
	 * Excluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirDados(RequisicaoReqDTO dto)throws BancoobException {
		try {
			InformacaoProfissionalVO vo = (InformacaoProfissionalVO)dto.getDados().get(INFORMACAO);
			Integer idInformacao = ConversorInformacaoProfissional.obterIdInformacao(vo);
			informacoesDelegate.excluir(idInformacao);
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
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			List<TipoFuncionario> tiposFuncionario = tipoFuncionarioDelegate.listar();
			List<TipoAfastamento> tiposAfastamento = tipoAfastamentoDelegate.listar();
			List<ConselhoRegional> conselhos = conselhoDelegate.listar();
			
			retorno.getDados().put("tiposFuncionario", 
					ConversorTipoFuncionario.converter(tiposFuncionario));
			retorno.getDados().put("tiposAfastamento", 
					ConversorTipoAfastamento.converter(tiposAfastamento));
			retorno.getDados().put("conselhos", ConversorConselho.converter(conselhos));
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, new RequisicaoReqDTO());
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, new RequisicaoReqDTO());
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, new RequisicaoReqDTO());
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, new RequisicaoReqDTO());
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, new RequisicaoReqDTO());	
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, new RequisicaoReqDTO());
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
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			InformacaoProfissionalVO vo = (InformacaoProfissionalVO)dto.getDados().get(INFORMACAO);
			InformacaoProfissional informacao = ConversorInformacaoProfissional.converter(vo);
			
			if(isProdutosBancoob(dto)){
				informacoesDelegate.incluirBancoob(informacao);
			} else {
				informacoesDelegate.incluir(informacao);
			}
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
	 * Obter contatos.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterContatos(RequisicaoReqDTO dto) throws BancoobException {
		
		RetornoDTO retorno = new RetornoDTO();
		try {
			Integer idPessoa = (Integer) dto.getDados().get(IDPESSOA);
			Integer idInstituicao = (Integer) dto.getDados().get(ID_INSTITUICAO);

			PessoaCompartilhamento pessoa = recuperarPessoaCompartilhamento(idPessoa, idInstituicao);
			List<Telefone> telefones = null;
			
			if(pessoa != null) {
				telefones = listarTelefones(pessoa);
			}
			
			retorno.getDados().put("telefones", telefones);
			
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
	 * Listar telefones.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Telefone> listarTelefones(PessoaCompartilhamento pessoa) throws BancoobException {

		Telefone telefone = new Telefone();
		telefone.setPessoaCompartilhamento(pessoa);

		ConsultaDto<Telefone> consulta = new ConsultaDto<Telefone>();
		consulta.setFiltro(telefone);
		return telefoneDelegate.listar(consulta);
	}
	
	/**
	 * Recuperar pessoa compartilhamento.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaCompartilhamento recuperarPessoaCompartilhamento(
			Integer idPessoa, Integer idInstituicao) throws BancoobException {
		
		PessoaCompartilhamento pessoa = null;
		
		if(idInstituicao != null && idPessoa != null) {
			pessoa = integracaoDelegate.obterPessoa(idInstituicao, idPessoa);
		}
		
		return pessoa;
	}
	
	/**
	 * Verifica se eh produtos bancoob.
	 *
	 * @param dto o valor de dto
	 * @return {@code true}, se for produtos bancoob
	 */
	private boolean isProdutosBancoob(RequisicaoReqDTO dto) {
		
		boolean produtosBancoob = false;
		Boolean produtos = (Boolean) dto.getDados().get(PRODUTOS_BANCOOB);
		
		if(produtos != null) {
			produtosBancoob = produtos.booleanValue();
		}
		return produtosBancoob;
	}		
	
}
