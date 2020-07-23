/*
 * SICOOB
 * 
 * ClienteServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.ClienteServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.api.negocio.excecao.InstituicaoNaoInformadaException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.excecao.PessoaNaoInformadaException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroCliente;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ClienteServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ClienteServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.ClienteDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;
import br.com.sicoob.capes.comum.util.DataUtils;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local(ClienteServicoLocal.class)
@Remote(ClienteServicoRemote.class)
public class ClienteServicoEJB extends CAPESApiServicoEJB implements ClienteServicoRemote, ClienteServicoLocal {

	/** A Constante PESQUISA_POR_NOME. */
	private static final String PESQUISA_POR_NOME = "POR NOME";
	
	/** A Constante PESQUISA_POR_APELIDO. */
	private static final String PESQUISA_POR_APELIDO = "POR APELIDO";
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();

	@Inject
	@Default
	private ClienteDAO clienteDAO;
	

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteVO obterByIdPessoa(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		ConsultaDto<ClienteVO> criterios = obterFiltroConsulta(idPessoa, null, null, idInstituicao);
		return clienteDAO.obterCliente(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteVO obterByIdPessoaLegado(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		if (idPessoaLegado == null) {
			throw new NegocioException("ID Pessoa legado não informado.");
		}

		validarObrigatoriedadeInstituicao(idInstituicao);
		ConsultaDto<ClienteVO> criterios = obterFiltroConsulta(null, idPessoaLegado, null, idInstituicao);
		return clienteDAO.obterCliente(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteVO obterByCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		logger.debug("ClienteServicoEJB obterByCpfCnpj - cpfCnpj:" + cpfCnpj + " idInstituicao:" + idInstituicao);
		if (cpfCnpj == null) {
			throw new BancoobException("CPF/CNPJ não informado.");
		}

		validarObrigatoriedadeInstituicao(idInstituicao);
		ConsultaDto<ClienteVO> criterios = obterFiltroConsulta(null, null, cpfCnpj, idInstituicao);
		return clienteDAO.obterCliente(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteVO> obterByNome(String nome, Integer idInstituicao) throws BancoobException {
		validarPesquisarPessoa(nome);
		validarObrigatoriedadeInstituicao(idInstituicao);

		ConsultaDto<FiltroCliente> criterios = obterFiltroPesquisa(nome, idInstituicao, NumberUtils.INTEGER_ZERO, NumberUtils.INTEGER_ZERO);
		criterios.setTipoProcura(PESQUISA_POR_NOME);
		return clienteDAO.pesquisar(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ClienteVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		validarPesquisarPessoa(nome);
		validarObrigatoriedadeInstituicao(idInstituicao);
		validarPaginacao(pagina, tamanhoPagina);

		ConsultaDto<FiltroCliente> criterios = obterFiltroPesquisa(nome, idInstituicao, pagina, tamanhoPagina);
		criterios.setTipoProcura(PESQUISA_POR_NOME);
		return clienteDAO.pesquisarPaginado(criterios);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException {
		validarPesquisarPessoa(nomeApelido);
		validarObrigatoriedadeInstituicao(idInstituicao);

		ConsultaDto<FiltroCliente> criterios = obterFiltroPesquisa(nomeApelido, idInstituicao, NumberUtils.INTEGER_ZERO, NumberUtils.INTEGER_ZERO);
		criterios.setTipoProcura(PESQUISA_POR_APELIDO);
		return clienteDAO.pesquisar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ClienteVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		validarPesquisarPessoa(nomeApelido);
		validarObrigatoriedadeInstituicao(idInstituicao);
		validarPaginacao(pagina, tamanhoPagina);

		ConsultaDto<FiltroCliente> criterios = obterFiltroPesquisa(nomeApelido, idInstituicao, pagina, tamanhoPagina);
		criterios.setTipoProcura(PESQUISA_POR_APELIDO);
		
		return clienteDAO.pesquisarPaginado(criterios);
	}

	/**
	 * Obter filtro consulta.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private ConsultaDto<ClienteVO> obterFiltroConsulta(Integer idPessoa, Integer idPessoaLegado, String cpfCnpj, Integer idInstituicao) throws BancoobException {
		ClienteVO filtro = new ClienteVO();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setIdPessoa(idPessoa);
		filtro.setCpfCnpj(cpfCnpj);
		filtro.setIdPessoaLegado(idPessoaLegado);

		ConsultaDto<ClienteVO> criterios = new ConsultaDto<ClienteVO>();
		criterios.setFiltro(filtro);
		return criterios;
	}

	/**
	 * Obter filtro pesquisa.
	 * 
	 * @param procurarPor
	 *            the procurar por
	 * @param idInstituicao
	 *            the id instituicao
	 * @param pagina
	 *            the pagina
	 * @param tamanhoPagina
	 *            the tamanho pagina
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private ConsultaDto<FiltroCliente> obterFiltroPesquisa(String procurarPor, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		FiltroCliente filtro = new FiltroCliente();
		filtro.setIdInstituicao(idInstituicao);

		ConsultaDto<FiltroCliente> criterios = new ConsultaDto<FiltroCliente>();
		criterios.setFiltro(filtro);
		criterios.setPagina(pagina);
		criterios.setTamanhoPagina(tamanhoPagina);
		criterios.setProcurarPor(procurarPor);

		return criterios;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void atualizarDataSFN(ClienteVO dto) throws BancoobException {
		if (dto.getIdPessoa() == null) {
			throw new PessoaNaoInformadaException();
		}
		if (dto.getIdInstituicao() == null) {
			throw new InstituicaoNaoInformadaException();
		}
		if (dto.getDataInclusaoSFN() == null) {
			throw new ParametroNaoInformadoException("Data do SFN");
		}

		ClienteVO cliente = obterByIdPessoa(dto.getIdPessoa(), dto.getIdInstituicao());

		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		PessoaCompartilhamentoDelegate delegate = fabrica.criarPessoaCompartilhamentoDelegate();

		if (cliente.getDataInclusaoSFN() == null || cliente.getDataInclusaoSFN().after(dto.getDataInclusaoSFN())) {
			delegate.alterarDataCadastramentoSFN(cliente.getIdPessoaLegado(), new Instituicao(cliente.getIdInstituicao(), 
					NumberUtils.INTEGER_ZERO), DataUtils.instanciarDateTimeDB(dto.getDataInclusaoSFN()));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {
		PessoaInstituicaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaInstituicaoDelegate();
		delegate.atualizarRiscoCliente(dto);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return clienteDAO;
	}

}