/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.InformacaoProfissionalDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataMaiorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataMenorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.EmpregadorDeveSerPessoaJuridicaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FuncionarioAssociadoEmpresaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.MatriculaUtilizadaEmpresaException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * @author Erico.Junior
 *
 */
public class ValidacaoInformacaoProfissional implements Validacao<InformacaoProfissional> {

	/** A constante DATA_NASCIMENTO. */
	private static final String DATA_NASCIMENTO = "Data de Nascimento";
	
	/** A constante DATA_DESLIGAMENTO. */
	private static final String DATA_DESLIGAMENTO = "Data de Desligamento";
	
	/** A constante SITUACAO. */
	private static final String SITUACAO = "Situação do Funcionário";
	
	/** A constante DATA_ADMISSAO. */
	private static final String DATA_ADMISSAO = "Data de Admissão";
	
	/** A constante TIPO_FUNCIONARIO. */
	private static final String TIPO_FUNCIONARIO = "Tipo Funcionário";
	
	/** A constante MATRICULA. */
	private static final String MATRICULA = "Matrícula do Funcionário";
	
	/** A constante EMPREGADOR. */
	private static final String EMPREGADOR = "Empregador";
	
	/** A constante DATA_ATUAL. */
	private static final String DATA_ATUAL = "Data Atual";

	/** O atributo fabrica. */
	private transient CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo delegate. */
	private transient InformacaoProfissionalDelegate delegate = 
			fabrica.criarInformacaoProfissionalDelegate();
	
	/** O atributo pessoaDelegate. */
	private transient PessoaDelegate pessoaDelegate = fabrica.criarPessoaDelegate();	
	
	/**
	 * {@inheritDoc}
	 */
	public void validar(InformacaoProfissional informacao)
			throws BancoobException {
		
		validarCamposObrigatorios(informacao);
		validarNegocio(informacao);
	}
	
	/**
	 * O método Validar campos obrigatorios.
	 *
	 * @param informacao o valor de informacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarCamposObrigatorios(InformacaoProfissional informacao)
			throws BancoobException {
	
		Pessoa pessoaEmpregador = informacao.getPessoaEmpregador();
		TipoFuncionario tipoFuncionario = informacao.getTipoFuncionario();
		
		if(pessoaEmpregador == null || pessoaEmpregador.getId() == null) {
			throw new CampoNaoInformadoException(EMPREGADOR);
		}

		if(StringUtils.isBlank(informacao.getNumMatricula())) {
			throw new CampoNaoInformadoException(MATRICULA);
		}

		if(tipoFuncionario == null || tipoFuncionario.getCodigo() == null) {
			throw new CampoNaoInformadoException(TIPO_FUNCIONARIO);
		}

		if(informacao.getDataAdmissao() == null) {
			throw new CampoNaoInformadoException(DATA_ADMISSAO);
		}
		
		if(informacao.getCodSituacao() == null) {
			throw new CampoNaoInformadoException(SITUACAO);
		}
		
		validarObrigatoriedadeCondicional(informacao);
	}

	
	/**
	 * O método Validar obrigatoriedade condicional.
	 *
	 * @param informacao o valor de informacao
	 * @throws CampoNaoInformadoException lança a exceção CampoNaoInformadoException
	 */
	protected void validarObrigatoriedadeCondicional(InformacaoProfissional informacao)
			throws CampoNaoInformadoException {

		boolean conselhoPreenchido = informacao.getConselhoRegional() != null;
		boolean ufConselhoPreenchido = StringUtils.isNotBlank(informacao.getUfConselho());
		boolean inscricaoConselho = StringUtils.isNotBlank(informacao.getNumeroInscricaoConselho());
		
		if (conselhoPreenchido || ufConselhoPreenchido || inscricaoConselho) {

			if (!conselhoPreenchido) {
				throw new CampoNaoInformadoException("Conselho");
			}
			if (!ufConselhoPreenchido) {
				throw new CampoNaoInformadoException("UF do Conselho");
			}
			if (!inscricaoConselho) {
				throw new CampoNaoInformadoException("Número de inscrição");
			}
		}
	}	
	
	/**
	 * O método Validar negocio.
	 *
	 * @param informacao o valor de informacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarNegocio(InformacaoProfissional informacao)
			throws BancoobException {
		
		validarDiaMes(informacao.getDiaMesFerias());
		
		Pessoa empregador = pessoaDelegate.obter(informacao.getPessoaEmpregador().getIdPessoa());
		DateTimeDB dataAdmissao = informacao.getDataAdmissao();
		DateTimeDB dataDesligamento = informacao.getDataDesligamento();
		
		if (TipoPessoaEnum.isPessoaFisica(empregador.getTipoPessoa().getCodTipoPessoa())) {
			throw new EmpregadorDeveSerPessoaJuridicaException();
		}

		Date dataAtual = new Date();
		validarDataMenor(dataAdmissao, dataAtual , DATA_ADMISSAO, DATA_ATUAL);
		
		Date dataNascimento = obterDataNascimento(informacao);
		if(dataNascimento != null) {
			validarDataMaior(dataAdmissao, dataNascimento, DATA_ADMISSAO, DATA_NASCIMENTO);
		}
		
		if(dataDesligamento != null) {
			validarDataMaior(dataDesligamento, dataAdmissao, DATA_DESLIGAMENTO, DATA_ADMISSAO);
			validarDataMenor(dataDesligamento, dataAtual, DATA_DESLIGAMENTO, DATA_ATUAL);
		}

		validarMatriculaEmpregador(informacao);
		
		validarMesmaMatriculaEmpregador(informacao);
	}
	
	/**
	 * Verifica se a matricula informada já existe para o empregador informado.
	 * @param informacao
	 * @throws BancoobException
	 */
	private void validarMesmaMatriculaEmpregador(InformacaoProfissional informacao) throws BancoobException {
		String numeroMatricula = informacao.getNumMatricula();
		Integer idEmpregador = informacao.getPessoaEmpregador().getId();
		
		for (InformacaoProfissional info : delegate.listar(informacao.getPessoa(), informacao.getIdInstituicao())){
			if(informacao.getId() == null || (informacao.getId() != null && !info.getId().equals(informacao.getId()))) {
				if(info.getNumMatricula().equals(numeroMatricula) && info.getPessoaEmpregador().getId().equals(idEmpregador)){
					throw new FuncionarioAssociadoEmpresaException();
				}
				
			}
			
		}
	}
	
	/**
	 * Obter data nascimento.
	 *
	 * @param informacao o valor de informacao
	 * @return Date
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Date obterDataNascimento(InformacaoProfissional informacao) throws BancoobException {
		
		Pessoa pessoa = pessoaDelegate.obter(informacao.getPessoa().getId());
		PessoaFisica pf = (PessoaFisica) pessoa.getPessoaCompartilhamento();
		
		return pf.getDataNascimento();
	}
	
	/**
	 * Valida se já existe 
	 * @param informacao
	 * @throws MatriculaUtilizadaEmpresaException
	 */
	private void validarMatriculaEmpregador(InformacaoProfissional informacao) 
			throws MatriculaUtilizadaEmpresaException { 
		
		List<InformacaoProfissional> lista = listarPorMatriculaEmpregador(informacao);

		if(CollectionUtils.isNotEmpty(lista)) {
			Integer idEmpregado = informacao.getPessoa().getIdPessoa();
			
			for (InformacaoProfissional informacaoProfissional : lista) {
				Pessoa empregadoExistente = informacaoProfissional.getPessoa();
				if(!empregadoExistente.getIdPessoa().equals(idEmpregado)) {
					throw new MatriculaUtilizadaEmpresaException(empregadoExistente.getCpfCnpj());
				}
			}		
		}
	}

	/**
	 * O método Validar data menor.
	 *
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @param nomeDataInicio o valor de nome data inicio
	 * @param nomeDataFim o valor de nome data fim
	 * @throws DataMaiorException lança a exceção DataMaiorException
	 */
	private void validarDataMenor(Date dataInicio, Date dataFim, String nomeDataInicio,
			String nomeDataFim) throws DataMaiorException {

		if (dataInicio != null && dataFim != null) {
			Date data1 = DataUtil.configurarPrimeiraDataIntervalo(dataInicio);
			Date data2 = DataUtil.configurarPrimeiraDataIntervalo(dataFim);

			if (data2.before(data1)) {
				 throw new DataMaiorException(nomeDataInicio, nomeDataFim);
			}
		}
	}

	/**
	 * O método Validar data maior.
	 *
	 * @param dataMaior o valor de data maior
	 * @param dataMenor o valor de data menor
	 * @param nomeDataMaior o valor de nome data maior
	 * @param nomeDataMenor o valor de nome data menor
	 * @throws DataMenorException lança a exceção DataMenorException
	 */
	private void validarDataMaior(Date dataMaior, Date dataMenor, String nomeDataMaior,
			String nomeDataMenor) throws DataMenorException {

		if (dataMaior != null && dataMenor != null) {
			Date data1 = DataUtil.configurarPrimeiraDataIntervalo(dataMaior);
			Date data2 = DataUtil.configurarPrimeiraDataIntervalo(dataMenor);

			if (data1.before(data2)) {
				 throw new DataMenorException(nomeDataMaior, nomeDataMenor);
			}
		}
	}
	
	/**
	 * Listar por matricula empregador.
	 *
	 * @param informacao o valor de informacao
	 * @return List
	 */
	private List<InformacaoProfissional> listarPorMatriculaEmpregador(
			InformacaoProfissional informacao) {
		return delegate.listarPorMatriculaEmpregador(informacao);
	}
	
	/**
	 * O método Validar dia mes.
	 *
	 * @param valor o valor de valor
	 * @throws FormatoInvalidoException lança a exceção FormatoInvalidoException
	 */
	private void validarDiaMes(String valor) throws FormatoInvalidoException {
		
		if(StringUtils.isNotBlank(valor)) {
			try {
				DateFormat formatador = new SimpleDateFormat("ddMMyyyy");
				formatador.setLenient(false);
				formatador.parse(valor + getAnoAtual());
			}catch (ParseException ex) {
				throw new FormatoInvalidoException("Dia/Mês", ex);
			}
		}
	}

	/**
	 * Recupera o valor de anoAtual.
	 *
	 * @return o valor de anoAtual
	 */
	private int getAnoAtual() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}	
}
