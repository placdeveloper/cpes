/*
 * SICOOB
 * 
 * ProdutorServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.ProdutorServicoEJB)
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
import br.com.sicoob.capes.api.negocio.excecao.CAPESApiNegocioException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroProdutor;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ProdutorServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ProdutorServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.ProdutorDAO;

/**
 * @author Marcelo.Onofre
 * 
 */
@Stateless
@Local(ProdutorServicoLocal.class)
@Remote(ProdutorServicoRemote.class)
public class ProdutorServicoEJB extends CAPESApiServicoEJB implements ProdutorServicoRemote, ProdutorServicoLocal {
	
	@Inject
	@Default
	private ProdutorDAO produtorDAO;

	/** A Constante PESQUISA_POR_NOME. */
	private static final String PESQUISA_POR_NOME = "POR NOME";
	
	/** A Constante PESQUISA_POR_APELIDO. */
	private static final String PESQUISA_POR_APELIDO = "POR APELIDO";

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProdutorVO obterByIdPessoa(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		ConsultaDto<ProdutorVO> criterios = obterFiltroConsulta(idPessoa, null, null, idInstituicao, null);
		ProdutorVO produtor = produtorDAO.obterProdutor(criterios);
		if (produtor == null) {
			throw new CAPESApiNegocioException("Produtor não encontrado");
		}
		return produtor;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProdutorVO obterByIdPessoaLegado(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		if (idPessoaLegado == null) {
			throw new NegocioException("ID Pessoa legado não informado.");
		}

		validarObrigatoriedadeInstituicao(idInstituicao);
		ConsultaDto<ProdutorVO> criterios = obterFiltroConsulta(null, idPessoaLegado, null, idInstituicao, null);
		ProdutorVO produtor = produtorDAO.obterProdutor(criterios);

		if (produtor == null) {
			throw new CAPESApiNegocioException("Produtor não encontrado");
		}

		return produtor;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProdutorVO obterByCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return obterPorCpfCnpj(cpfCnpj, idInstituicao, Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProdutorVO obterProdutorCliente(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return obterPorCpfCnpj(cpfCnpj, idInstituicao, Boolean.TRUE);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private ProdutorVO obterPorCpfCnpj(String cpfCnpj, Integer idInstituicao, Boolean cliente) throws BancoobException {
		if (cpfCnpj == null) {
			throw new BancoobException("CPF/CNPJ não informado.");
		}
		
		validarObrigatoriedadeInstituicao(idInstituicao);
		ConsultaDto<ProdutorVO> criterios = obterFiltroConsulta(null, null, cpfCnpj, idInstituicao, cliente);
		ProdutorVO produtor = produtorDAO.obterProdutor(criterios);
		
		if(produtor == null){
			throw new CAPESApiNegocioException("Produtor não encontrado");
		}
		
		return produtor;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ProdutorVO> obterByNome(String nome, Integer idInstituicao) throws BancoobException {
		validarPesquisarPessoa(nome);
		validarObrigatoriedadeInstituicao(idInstituicao);

		ConsultaDto<FiltroProdutor> criterios = obterFiltroPesquisa(nome, idInstituicao, NumberUtils.INTEGER_ZERO, NumberUtils.INTEGER_ZERO);
		criterios.setTipoProcura(PESQUISA_POR_NOME);
		return produtorDAO.pesquisar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProdutorVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		return obterByNome(nome, idInstituicao, pagina, tamanhoPagina, false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ProdutorVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException {
		validarPesquisarPessoa(nomeApelido);
		validarObrigatoriedadeInstituicao(idInstituicao);

		ConsultaDto<FiltroProdutor> criterios = obterFiltroPesquisa(nomeApelido, idInstituicao, 0, 0);
		criterios.setTipoProcura(PESQUISA_POR_APELIDO);
		return produtorDAO.pesquisar(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProdutorVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		return obterByNomeApelido(nomeApelido, idInstituicao, pagina, tamanhoPagina, false);
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
	private ConsultaDto<ProdutorVO> obterFiltroConsulta(Integer idPessoa, Integer idPessoaLegado, String cpfCnpj, Integer idInstituicao, Boolean cliente) throws BancoobException {
		ProdutorVO filtro = new ProdutorVO();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setIdPessoa(idPessoa);
		filtro.setCpfCnpj(cpfCnpj);
		filtro.setIdPessoaLegado(idPessoaLegado);
		filtro.setCliente(cliente);

		ConsultaDto<ProdutorVO> criterios = new ConsultaDto<ProdutorVO>();
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
	private ConsultaDto<FiltroProdutor> obterFiltroPesquisa(String procurarPor, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		return obterFiltroPesquisa(procurarPor, idInstituicao, pagina, tamanhoPagina, false);
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
	 * @param filtrarCategoriaAtiva
	 *            the filtrar categoria ativa
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private ConsultaDto<FiltroProdutor> obterFiltroPesquisa(String procurarPor, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva) throws BancoobException {
		FiltroProdutor filtro = new FiltroProdutor();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setPesquisarPorCategoriaAtiva(filtrarCategoriaAtiva);

		ConsultaDto<FiltroProdutor> criterios = new ConsultaDto<FiltroProdutor>();
		criterios.setFiltro(filtro);
		criterios.setPagina(pagina);
		criterios.setTamanhoPagina(tamanhoPagina);
		criterios.setProcurarPor(procurarPor);

		return criterios;
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProdutorVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva) throws BancoobException {
		validarPesquisarPessoa(nome);
		validarObrigatoriedadeInstituicao(idInstituicao);
		validarPaginacao(pagina, tamanhoPagina);

		ConsultaDto<FiltroProdutor> criterios = obterFiltroPesquisa(nome, idInstituicao, pagina, tamanhoPagina, filtrarCategoriaAtiva);
		criterios.setTipoProcura(PESQUISA_POR_NOME);
		
		return produtorDAO.pesquisarPaginado(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProdutorVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva)
			throws BancoobException {
		validarPesquisarPessoa(nomeApelido);
		validarObrigatoriedadeInstituicao(idInstituicao);
		validarPaginacao(pagina, tamanhoPagina);

		ConsultaDto<FiltroProdutor> criterios = obterFiltroPesquisa(nomeApelido, idInstituicao, pagina, tamanhoPagina, filtrarCategoriaAtiva);
		criterios.setTipoProcura(PESQUISA_POR_APELIDO);
		
		return produtorDAO.pesquisarPaginado(criterios);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoBeneficiarioSicorVO> obterListaTipoBeneficiariosSicor() throws BancoobException {
		return produtorDAO.obterListaTipoBeneficiariosSicor();
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return produtorDAO;
	}

}