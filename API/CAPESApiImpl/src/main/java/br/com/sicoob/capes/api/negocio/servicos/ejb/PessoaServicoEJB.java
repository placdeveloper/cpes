/*
 * SICOOB
 * 
 * PessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.PessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
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
import br.com.sicoob.capes.api.negocio.excecao.CAPESApiNegocioException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.PessoaDAO;
import br.com.sicoob.capes.api.persistencia.dao.PessoaLegadoDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaIntegracaoServicoLocal;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;

/**
 * The Class PessoaServicoEJB.
 */
@Stateless
@Local(PessoaServicoLocal.class)
@Remote(PessoaServicoRemote.class)
public class PessoaServicoEJB extends CAPESApiServicoEJB implements PessoaServicoRemote, PessoaServicoLocal {

	/** A Constante O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES. */
	private static final String O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES = "O nome da pessoa informado deve possuir 3 ou mais caracteres.";

	/** A Constante NOME_PESSOA_NAO_INFORMADO. */
	private static final String NOME_PESSOA_NAO_INFORMADO = "Nome da pessoa não informado.";

	/** A Constante TIPO_DA_PESSOA_NAO_INFORMADO. */
	private static final String TIPO_DA_PESSOA_NAO_INFORMADO = "Tipo da pessoa não informado.";

	/** A Constante NOME_APELIDO_DA_PESSOA_INFORMADO. */
	private static final String NOME_APELIDO_DA_PESSOA_INFORMADO = "O nome apelido da pessoa informado deve possuir 3 ou mais caracteres.";

	/** A Constante NOME_APELIDO_DA_PESSOA_NAO_INFORMADO. */
	private static final String NOME_APELIDO_DA_PESSOA_NAO_INFORMADO = "Nome apelido da pessoa não informado.";

	/** A Constante TAMANHO_MINIMO_FILTRO. */
	private static final int TAMANHO_MINIMO_FILTRO = 3;

	@Inject
	@Default
	private PessoaDAO pessoaDAO;
	
	@Inject
	@Default
	private PessoaLegadoDAO pessoaLegadoDAO;
	
	@EJB
	private PessoaIntegracaoServicoLocal pessoaIntegracaoServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaVO obterByIdPessoa(Integer idPessoa, Integer idInstituicao) throws BancoobException {

		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return pessoaDAO.obterPessoa(idPessoa, null, null, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaVO obterByIdPessoaLegado(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		if (idPessoaLegado == null) {
			throw new BancoobException("ID pessoa legado não informado.");
		}
		
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoa(null, idPessoaLegado, null, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaVO obterByCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		if (cpfCnpj == null) {
			throw new BancoobException("CPF/CNPJ não informado.");
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoa(null, null, cpfCnpj, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaVO> obterByCpfCnpj(String cpfCnpj) throws BancoobException {
		if (cpfCnpj == null) {
			throw new BancoobException("CPF/CNPJ não informado.");
		}
		return pessoaDAO.obterPessoaPorCpfCnpj(cpfCnpj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaVO> obterByNome(String nome, Integer idInstituicao) throws BancoobException {
		if (nome == null) {
			throw new BancoobException(NOME_PESSOA_NAO_INFORMADO);
		} else if (nome.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES);
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNome(nome, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaVO> obterByNome(String nome) throws BancoobException {
		if (nome == null) {
			throw new BancoobException(NOME_PESSOA_NAO_INFORMADO);
		} else if (nome.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES);
		}
		return pessoaDAO.obterPessoaPorNome(nome, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException {
		if (nomeApelido == null) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_NAO_INFORMADO);
		} else if (nomeApelido.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_INFORMADO);
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNomeApelido(nomeApelido, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		if (nome == null) {
			throw new BancoobException(NOME_PESSOA_NAO_INFORMADO);
		} else if (nome.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES);
		} else if (pagina < NumberUtils.INTEGER_ZERO) {
			throw new BancoobException("O número da página deve ser maior ou igual a zero.");
		} else if (tamanhoPagina < NumberUtils.INTEGER_ONE) {
			throw new BancoobException("O tamanho da página deve ser maior que zero.");
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNome(nome, idInstituicao, pagina, tamanhoPagina);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaVO> obterByNome(String nome, int pagina, int tamanhoPagina) throws BancoobException {
		if (nome == null) {
			throw new BancoobException(NOME_PESSOA_NAO_INFORMADO);
		} else if (nome.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES);
		} else if (pagina < NumberUtils.INTEGER_ZERO) {
			throw new BancoobException("O número da página deve ser maior ou igual a zero.");
		} else if (tamanhoPagina < NumberUtils.INTEGER_ONE) {
			throw new BancoobException("O tamanho da página deve ser maior que zero.");
		}
		return pessoaDAO.obterPessoaPorNome(nome, null, pagina, tamanhoPagina);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		if (nomeApelido == null) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_NAO_INFORMADO);
		} else if (nomeApelido.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_INFORMADO);
		} else if (pagina < NumberUtils.INTEGER_ZERO) {
			throw new BancoobException("O número da página deve ser maior ou igual a zero.");
		} else if (tamanhoPagina < NumberUtils.INTEGER_ONE) {
			throw new BancoobException("O tamanho da página deve ser maior que zero.");
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNomeApelido(nomeApelido, idInstituicao, pagina, tamanhoPagina);
	}

	/**
	 * Consulta por nome, ID da intituição e tipo de pessoa.
	 * 
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parâmetro obrigatório e maior
	 *            do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parâmetro obrigatório<br>
	 *            0 : Pessoa Física<br>
	 *            1 : Pessoa Jurídica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum
	 * @throws BancoobException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaVO> obterByNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa) throws BancoobException {
		if (nome == null) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_NAO_INFORMADO);
		} else if (nome.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_INFORMADO);
		} else if (tipoPessoa == null) {
			throw new BancoobException(TIPO_DA_PESSOA_NAO_INFORMADO);
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNomeTipoPessoa(nome, idInstituicao, tipoPessoa);
	}

	/**
	 * Consulta por apelido, ID da intituição e tipo de pessoa.
	 * 
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parâmetro obrigatório e
	 *            maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parâmetro obrigatório<br>
	 *            0 : Pessoa Física<br>
	 *            1 : Pessoa Jurídica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum
	 * @throws BancoobException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaVO> obterByNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa) throws BancoobException {
		if (nomeApelido == null) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_NAO_INFORMADO);
		} else if (nomeApelido.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_INFORMADO);
		} else if (tipoPessoa == null) {
			throw new BancoobException(TIPO_DA_PESSOA_NAO_INFORMADO);
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNomeApelidoTipoPessoa(nomeApelido, idInstituicao, tipoPessoa);
	}

	/**
	 * Consulta por nome, ID da intituição e tipo de pessoa, de forma paginada.
	 * 
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parâmetro obrigatório e maior
	 *            do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parâmetro obrigatório<br>
	 *            0 : Pessoa Física<br>
	 *            1 : Pessoa Jurídica
	 * @param pagina
	 *            Número da página, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 *            Tamanho de registros por página. Deve ser maior que 1 (um)
	 * @return uma lista de pessoas de acordo com os criterios de consulta
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum
	 * @throws BancoobException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaVO> obterByNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException {
		if (nome == null) {
			throw new BancoobException(NOME_PESSOA_NAO_INFORMADO);
		} else if (nome.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(O_NOME_PESSOA_INFORMADO_DEVE_POSSUIR_3_OU_MAIS_CARACTERES);
		} else if (tipoPessoa == null) {
			throw new BancoobException(TIPO_DA_PESSOA_NAO_INFORMADO);
		}
		validarPaginacao(pagina, tamanhoPagina);
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNomeTipoPessoa(nome, idInstituicao, tipoPessoa, pagina, tamanhoPagina);
	}

	/**
	 * Consulta por apelido, ID da intituição e tipo de pessoa, de forma
	 * paginada.
	 * 
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parâmetro obrigatório e
	 *            maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parâmetro obrigatório<br>
	 *            0 : Pessoa Física<br>
	 *            1 : Pessoa Jurídica
	 * @param pagina
	 *            Número da página, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 *            Tamanho de registros por página. Deve ser maior que 1 (um
	 * @return uma lista de pessoas de acordo com os criterios de consulta
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum
	 * @throws BancoobException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaVO> obterByNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException {
		if (nomeApelido == null) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_NAO_INFORMADO);
		} else if (nomeApelido.length() < TAMANHO_MINIMO_FILTRO) {
			throw new BancoobException(NOME_APELIDO_DA_PESSOA_INFORMADO);
		} else if (tipoPessoa == null) {
			throw new BancoobException(TIPO_DA_PESSOA_NAO_INFORMADO);
		}
		validarPaginacao(pagina, tamanhoPagina);
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPessoaPorNomeApelidoTipoPessoa(nomeApelido, idInstituicao, tipoPessoa, pagina, tamanhoPagina);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(ConsultaDto<PessoaPlataformaVO> consultaDto) throws BancoobException {
		return pessoaIntegracaoServico.pesquisarPessoaPlataforma(consultaDto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(ConsultaDto<PessoaPlataformaVO> consultaDto) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate().pesquisarPessoaPlataformaResumido(consultaDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate().consultarPessoaPlataformaPorIdPessoaLegado(idPessoaLegado);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate().obterDadosPessoaLegado(cpfCnpj, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) throws BancoobException {
		validarConsultaObterPorListaPessoaInstituicao(idPessoas, idInstituicao);
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaDelegate().obterPorListaPessoaInstituicao(idPessoas, idInstituicao);
	}

	/**
	 * O método Validar consulta obter por lista pessoa instituicao.
	 *
	 * @param idPessoas o valor de id pessoas
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarConsultaObterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) throws BancoobException {
		if (idInstituicao == null) {
			throw new ParametroNaoInformadoException("Id da instituição");
		}
		if (idPessoas == null || idPessoas.isEmpty()) {
			throw new ParametroNaoInformadoException("Id pessoa");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException {
		ConsultaDto<ProcurarPessoaExternoVO> retorno = null;
		try {
			 retorno = CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate().procurarPessoaExterno(criterios);
		} catch (CAPESCadastroNegocioException e) {
			CAPESApiNegocioException.lancarExcecao(e.getMessage());
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<String> consultarPessoaPossuemAutorizacaoBacen(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException {
		if (listaCpfCnpj == null || listaCpfCnpj.isEmpty()) {
			throw new BancoobException(" Lista CPF/CNPJ não informado.");
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.consultarPessoaPossuemAutorizacaoBacen(listaCpfCnpj, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaVO obterPorCpfCnpjInstituicaoGrupoCompartilhamento(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		if (cpfCnpj == null) {
			throw new BancoobException("CPF/CNPJ não informado.");
		}
		validarObrigatoriedadeInstituicao(idInstituicao);
		return pessoaDAO.obterPorCpfCnpjInstituicaoGrupoCompartilhamento(cpfCnpj, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String obterNomePessoaCompartilhamentoSicoob(String cpfCnpj) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return pessoaDAO;
	}
	
	private PessoaLegadoDAO obterDAOLegado() {
		return pessoaLegadoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public byte[] obterAssinatura(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		PessoaVO pessoaVO = obterByIdPessoa(idPessoa, idInstituicao);

		if (pessoaVO == null) {
			return null;
		}

		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		return obterDAOLegado().obterAssinatura(pessoaVO.getIdPessoaLegado(), numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public byte[] obterImagem(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		PessoaVO pessoaVO = obterByIdPessoa(idPessoa, idInstituicao);

		if (pessoaVO == null) {
			return null;
		}
		
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		return obterDAOLegado().obterImagem(pessoaVO.getIdPessoaLegado(), numeroCooperativa);
	}
	
	/**
	 * Obtém o número da cooperativa a partir do idInstituicao.
	 * 
	 * @param idInstituicao
	 *            o idInstituicao para obter o número da cooperativa
	 * @return {@code Integer} o número da cooperativa.
	 * @throws BancoobException
	 */
	private Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		Integer numCooperativa = sciDelegate.obterNumeroCooperativa(idInstituicao);
		return numCooperativa;
	}

}