/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.bancoob.validacao.Validacao;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.OcupacaoProfissionalDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.EstadoCivilEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.AtividadeEconomicaTipoPessoaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.MenorEmancipadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.NomePaiMaeIguaisException;
import br.com.sicoob.capes.cadastro.negocio.excecao.OcupacaoProfissionalException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaFisicaPossuiConjugeException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RelacionamentoMesmaPessoaException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * Classe utilizada na validação dos dados de entrada de pessoa física.
 * 
 * @author erico.junior
 */
public class ValidacaoPessoaFisica extends ValidacaoPessoaCompartilhamento<PessoaFisica> {

	/** A constante DATA_EMISSAO. */
	private static final String DATA_EMISSAO = "Data de Emissão";
	
	/** A constante DATA_NASCIMENTO. */
	private static final String DATA_NASCIMENTO = "Data de Nascimento";

	/** A constante DIAS_NO_ANO. */
	private static final BigDecimal DIAS_NO_ANO = new BigDecimal("365.25");
	
	private PessoaCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoDocumento() {
		return "CPF";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNome() {
		return "Nome";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNomeCompleto() {
		return "Nome Completo";
	}

	/**
	 * Valida a obrigatoriedade condicional para os dados de pessoa física. Ex:
	 * Se a pessoa for casada, é necessário informar o regime de casamento.
	 * 
	 * @param pessoa
	 *            A pessoa física a ser validada.
	 * @throws CampoNaoInformadoException
	 *             Caso algum campo de preenchimento obrigatório não tenha sido
	 *             informado.
	 */
	protected void validarObrigatoriedadeCondicionalEspecifica(PessoaFisica pessoa)
			throws CampoNaoInformadoException {

		boolean tipoPreenchido = pessoa.getTipoDocumento() != null;
		boolean numeroPreenchido = StringUtils.isNotBlank(pessoa.getNumeroDocumento());
		boolean orgaoPreenchido = StringUtils.isNotBlank(pessoa.getOrgaoExpedidorDocumento());
		boolean ufPreenchida = StringUtils.isNotBlank(pessoa.getUfOrgaoExpedidorDocumento());
		boolean dataPreenchida = pessoa.getDataEmissaoDocumento() != null;

		if (tipoPreenchido || numeroPreenchido || orgaoPreenchido || ufPreenchida || dataPreenchida) {

			if (!tipoPreenchido) {
				throw new CampoNaoInformadoException("Tipo de Documento");
			}
			if (!numeroPreenchido) {
				throw new CampoNaoInformadoException("Nº do Documento");
			}
			if (!orgaoPreenchido) {
				throw new CampoNaoInformadoException("Órgão Expedidor");
			}
			if (!ufPreenchida) {
				throw new CampoNaoInformadoException("UF");
			}
			if (!dataPreenchida) {
				throw new CampoNaoInformadoException(DATA_EMISSAO);
			}
		}

		if (Boolean.TRUE.equals(pessoa.getMenorEmancipado()) && pessoa.getDataNascimento() == null) {
			throw new CampoNaoInformadoException(DATA_NASCIMENTO);
		}
		
		if (isPessoaCasada(pessoa)) {

			RegimeCasamento regime = pessoa.getRegimeCasamento();
			if (regime == null || regime.getCodigo() == null) {
				throw new CampoNaoInformadoException("Regime de Casamento");
			}
		}

	}

	/**
	 * Verifica se o estado civil informado é "Casado" ou não.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @return se o estado civil informado é "Casado" ou não.
	 */
	private boolean isPessoaCasada(PessoaFisica pessoa) {

		EstadoCivil estadoCivil = pessoa.getEstadoCivil();
		boolean isCasado = false;
		if (estadoCivil != null) {
			isCasado = EstadoCivilEnum.CASADO.getCodigo().equals(estadoCivil.getCodigo());
		}
		return isCasado;
	}

	/**
	 * A data de nascimento não pode ser maior que a data atual nem maior que a data de inclusão 
	 * no sistema.
	 * A data de emissão do documento não pode ser maior que a data atual e nem menor que a data de
	 * nascimento.
	 * @see br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoa#validarNegocioEspecifico(br.com.sicoob.capes.negocio.entidades.Pessoa)
	 */
	@Override
	protected void validarNegocioEspecifico(PessoaFisica pessoa) throws BancoobException {

		Date dataAtual = DataUtil.obterDataAtual();
		DateTimeDB dataNascimento = pessoa.getDataNascimento();

		if (dataNascimento != null) {
			validarDataInicioMenor(dataNascimento, dataAtual, DATA_NASCIMENTO, DATA_ATUAL);
			validarDataInicioMenor(dataNascimento, pessoa.getDataInclusaoSistema(),
					DATA_NASCIMENTO, DATA_INCLUSAO);
		}

		DateTimeDB dataDocumento = pessoa.getDataEmissaoDocumento();
		if (dataDocumento != null) {
			validarDataInicioMenor(dataDocumento, dataAtual, DATA_EMISSAO, DATA_ATUAL);
			validarDataMaior(dataDocumento, dataNascimento, DATA_EMISSAO, DATA_NASCIMENTO);
		}

		validarMenorEmancipado(pessoa);
		validarOcupacaoProfissional(pessoa);
		
		validarConjuge(pessoa);
		
		String nomePai = pessoa.getNomePai();
		String nomeMae = pessoa.getNomeMae();
		if (StringUtils.isNotBlank(nomePai) && StringUtils.isNotBlank(nomeMae)
				&& nomePai.equalsIgnoreCase(nomeMae)) {
			throw new NomePaiMaeIguaisException();
		}
	}
	
	private void validarConjuge(PessoaFisica pessoa) throws BancoobException {
		if (pessoa.getId() != null && pessoa.getPessoa() != null && pessoa.getPessoa().getId() != null && pessoa.getConjuge() != null) {
			if (pessoa.getPessoa().getIdPessoa().equals(pessoa.getConjuge().getPessoa().getIdPessoa())) {
				throw new RelacionamentoMesmaPessoaException();
			}
			
			PessoaFisica conjugeParceiro = (PessoaFisica) delegate.obter(pessoa.getConjuge().getIdPessoaCompartilhamento());
			if (conjugeParceiro.getConjuge() != null) {
				if (!conjugeParceiro.getConjuge().getPessoa().getIdPessoa().equals(pessoa.getPessoa().getIdPessoa())) {
					throw new PessoaFisicaPossuiConjugeException();
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarAtividadeEconomica(AtividadeEconomica atividade)
			throws AtividadeEconomicaTipoPessoaException {

		if (atividade != null && !isAtividadeEconomicaPessoaFisica(atividade)) {
			throw new AtividadeEconomicaTipoPessoaException(TipoPessoaEnum.PESSOA_FISICA
					.getDescricao());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Validacao obterValidacaoDocumento(String cpfCnpj) {
		return new ValidacaoCpf(cpfCnpj, "", getNomeAtributoDocumento());
	}

	/**
	 * Validar se a pessoa está selecionada como menor emancipado, mas é maior
	 * de 18 anos.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @throws MenorEmancipadoException
	 *             Caso a pessoa seja maior de 18 anos e esteja marcada como
	 *             menor emancipado.
	 */
	private void validarMenorEmancipado(PessoaFisica pessoa) throws MenorEmancipadoException {

		if (Boolean.TRUE.equals(pessoa.getMenorEmancipado())) {
			Date dataNascimento = pessoa.getDataNascimento();
			Date hoje = DataUtil.obterDataAtual();

			BigDecimal diasVida = new BigDecimal(DataUtil.calcularDiasEntreDatas(dataNascimento,
					hoje));
			BigDecimal idade = diasVida.divide(DIAS_NO_ANO, RoundingMode.FLOOR);

			if (idade.intValue() > 18) {
				throw new MenorEmancipadoException();
			}
		}
	}

	/**
	 * Valida se a ocupação profissional está ativa ou não. 
	 * @param pessoa A pessoa a ser verificada
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private void validarOcupacaoProfissional(PessoaFisica pessoa) throws BancoobException {

		OcupacaoProfissional ocupacao = pessoa.getOcupacaoProfissional();
		
		if (ocupacao != null && ocupacao.getIdOcupacaoProfissional() != null) {
			Integer idOcupacao = ocupacao.getIdOcupacaoProfissional();
			OcupacaoProfissional consultada = obterOcupacaoProfissional(idOcupacao);
			if (consultada != null && Boolean.FALSE.equals(consultada.getAtivo())) {
				throw new OcupacaoProfissionalException();
			}
		}
	}

	/**
	 * Recupera a ocupação profissional a partir do id.
	 * @param idOcupacao O identificador da ocupação.
	 * @return A ocupação profissional encontrada.
	 * @throws BancoobException Caso ocorra alguma exceção
	 */
	private OcupacaoProfissional obterOcupacaoProfissional(Integer idOcupacao) 
		throws BancoobException{
		OcupacaoProfissionalDelegate delegate = CAPESCadastroFabricaDelegates
				.getInstance().criarOcupacaoProfissionalDelegate();
		return delegate.obter(idOcupacao);
	}
}
