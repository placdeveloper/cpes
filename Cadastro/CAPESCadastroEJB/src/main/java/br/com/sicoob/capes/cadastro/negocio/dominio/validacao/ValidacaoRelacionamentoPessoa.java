/* 
 * Sicoob
 * ValidacaoRelacionamentoPessoa.java 
 * Criado em: 15/11/2011
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CapitalSocialInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataAtualException;
import br.com.sicoob.capes.cadastro.negocio.excecao.OperacaoNaoPermitidaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.QuantidadeRelacionamentoPorTipoInvalida;
import br.com.sicoob.capes.cadastro.negocio.excecao.RelacionamentoMesmaPessoaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RelacionamentoMesmoTipoPessoaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ValorForaLimiteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ViolacaoUKException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * 15/11/2011
 * 
 * @author Rodrigo.Chaves
 */
public class ValidacaoRelacionamentoPessoa extends ValidacaoEntidadeCadastroAbstract<RelacionamentoPessoa> {

	/** O atributo delegate. */
	private RelacionamentoPessoaDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarRelacionamentoPessoaDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(RelacionamentoPessoa entidade) throws CampoNaoInformadoException {
		TipoRelacionamentoPessoa tipoRelacionamento = entidade.getTipoRelacionamento();

		if ((entidade.getPessoaRelacionada() == null) || (entidade.getPessoaRelacionada().getIdPessoa() == null)) {
			throw new CampoNaoInformadoException("Pessoa Relacionada");
		}

		if ((tipoRelacionamento == null) || (tipoRelacionamento.getCodigo() == null)) {
			throw new CampoNaoInformadoException("Tipo de Relacionamento");
		}

		if(tipoRelacionamento.getHabilitaDadosRegistro()) {
			if (entidade.getDataInicioMandato() == null) {
				throw new CampoNaoInformadoException("Início do Mandato");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(RelacionamentoPessoa entidade) throws BancoobException {
		validarProdutoBancoob(entidade);
		validarPessoaRelacionada(entidade);
		validarTipoRelacionamento(entidade);
		validarDataInicio(entidade);
		validarDataFim(entidade);
		validarPercentualCapital(entidade);
		validarUKRelacionamento(entidade);
		validarDuplicidadeTipoRelacionamento(entidade);
		validarDuplicidadeMesmoTipoPessoa(entidade);
		getDelegate().verificaPendenciasPessoaRelacionada(entidade);
	}

	/**
	 * O método Validar duplicidade tipo relacionamento.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarDuplicidadeTipoRelacionamento(final RelacionamentoPessoa entidade) throws BancoobException {
		// Verifica se o tipo de relacionamento permite duplicidade
		if (!entidade.getTipoRelacionamento().getPermiteDuplicidade()) {
			
			// Obtém a lista dos relacionamentos da pessoa atual.
			List<RelacionamentoPessoa> relacionamentos = obterListaRelacionamentosValidacaoDuplicidade(
					entidade.getPessoa().getId(), entidade.getIdInstituicao(), entidade.getTipoRelacionamento());

			// Remove, para o caso de ser uma alteração
			relacionamentos.remove(entidade);
			
			// Se a pessoa já tiver um relacionamento desse tipo, lança a exceção.
			if (!relacionamentos.isEmpty()) {
				throw new QuantidadeRelacionamentoPorTipoInvalida();
			}
		}
	}
	
	/**
	 * O método Validar duplicidade mesmo tipo pessoa.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarDuplicidadeMesmoTipoPessoa(RelacionamentoPessoa entidade) throws BancoobException {
		// Obtém a lista dos relacionamentos da pessoa atual.
		List<RelacionamentoPessoa> relacionamentos = obterListaRelacionamentosValidacaoDuplicidade(entidade.getPessoa().getId(), entidade.getIdInstituicao(), entidade.getTipoRelacionamento());

		// Remove, para o caso de ser uma alteração
		relacionamentos.remove(entidade);
		
		// Verifica se a pessoa informada no relacionamento é a mesma para o mesmo tipo
		for(RelacionamentoPessoa relacionamento : relacionamentos){
			if(!relacionamento.getId().equals(entidade.getIdRelacionamentoAntigo()) && relacionamento.getPessoaRelacionada().getId().equals(entidade.getPessoaRelacionada().getId())){
				throw new RelacionamentoMesmoTipoPessoaException();
			}
		}
	}

	/**
	 * Obter lista relacionamentos validacao duplicidade.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoRelacionamento o valor de id tipo relacionamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<RelacionamentoPessoa> obterListaRelacionamentosValidacaoDuplicidade(Integer idPessoa, Integer idInstituicao, TipoRelacionamentoPessoa tipoRelacionamento)
			throws BancoobException {
		RelacionamentoPessoa filtro = new RelacionamentoPessoa();
		filtro.setPessoa(new Pessoa());
		filtro.getPessoa().setIdPessoa(idPessoa);
		filtro.setIdInstituicao(idInstituicao);
		filtro.setTipoRelacionamento(new TipoRelacionamentoPessoa());
		filtro.getTipoRelacionamento().setId(tipoRelacionamento.getCodigo());
		List<RelacionamentoPessoa> relacionamentos = delegate.pesquisarRelacionamentosVigentesPorFiltro(filtro);
		return relacionamentos;
	}

	/**
	 * O método Validar pessoa relacionada.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarPessoaRelacionada(RelacionamentoPessoa entidade) throws BancoobException {
		if (entidade.getPessoa().getIdPessoa().equals(entidade.getPessoaRelacionada().getIdPessoa())
				&& !TipoRelacionamentoPessoaEnum.SOCIO.getCodigo().equals(entidade.getTipoRelacionamento().getCodigo())) {
			throw new RelacionamentoMesmaPessoaException();
		}
	}

	/**
	 * Valida a UK da entidade de {@link RelacionamentoPessoa} que só permite a
	 * existência de um relacionamento para com os mesmos valores nas seguintes
	 * propriedades:
	 * 
	 * <ul>
	 * <li>{@link RelacionamentoPessoa#getPessoaCompartilhamento()}</li>
	 * <li>{@link RelacionamentoPessoa#getPessoaRelacionada()}</li>
	 * <li>{@link RelacionamentoPessoa#getTipoRelacionamento()}</li>
	 * <li>{@link RelacionamentoPessoa#getIdInstituicao()}</li>
	 * </ul>
	 * 
	 * @param relacionamento
	 * @throws ViolacaoUKException
	 */
	protected void validarUKRelacionamento(final RelacionamentoPessoa relacionamento) throws ViolacaoUKException {

		List<RelacionamentoPessoa> relacionamentos = this.delegate.pesquisarRelacionamentosSemelhantes(relacionamento.getPessoa(),
				relacionamento.getPessoaRelacionada(), relacionamento.getTipoRelacionamento(), relacionamento.getIdInstituicao());
		for (RelacionamentoPessoa semelhante : relacionamentos) {
			if (!semelhante.getId().equals(relacionamento.getId()) && isDataHoraInicioValidas(relacionamento, semelhante)) {
				throw new ViolacaoUKException();
			}
		}

	}

	/**
	 * Verifica se a data hora início são válidas de acordo com a UK aplicada na
	 * base de dados. Validação dos atributos Pessoa, Pessoa Relacioanada, Tipo
	 * Relacionamento, Instituição e Data Hora Início
	 * 
	 * @param relacionamento
	 * @param semelhante
	 * @return <code>true</code> se as datas de início do relacionamento e do
	 *         semelhante forem nulas, ou se essas datas forem iguais, caso
	 *         contrário, retorna <code>false</code>
	 */
	private boolean isDataHoraInicioValidas(RelacionamentoPessoa relacionamento, RelacionamentoPessoa semelhante) {
		return (isRegistrosEmAprovacao(relacionamento, semelhante))
				|| (relacionamento.getDataHoraInicio() != null && semelhante.getDataHoraInicio() != null && relacionamento.getDataHoraInicio().equals(
						semelhante.getDataHoraInicio()));

	}

	/**
	 * Verifica se eh registros em aprovacao.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @param semelhante o valor de semelhante
	 * @return {@code true}, se for registros em aprovacao
	 */
	private boolean isRegistrosEmAprovacao(RelacionamentoPessoa relacionamento, RelacionamentoPessoa semelhante) {
		return relacionamento.getDataHoraInicio() == null && relacionamento.getIdInstituicaoAtualizacao() != null && semelhante.getDataHoraInicio() == null
				&& semelhante.getIdInstituicaoAtualizacao() != null;
	}

	/**
	 * Realiza validação de data inicial, verificando as seguintes regras
	 * negociais:
	 * 
	 * <ol>
	 * <li>Caso o tipo de relacionamento seja para envio ao CCS a data de início
	 * deve ser a data atual do movimento do CCS, obtida de
	 * {@link ProdutoInstituicaoDelegate#pesquisarProdutoInstituicao(Integer, Integer)}
	 * </li>
	 * <li>Caso o tipo de relacionamento não seja para envio ao CCS, a data de
	 * início deve ser menor que a data atual</li>
	 * </ol>
	 * 
	 * @param entidade
	 *            o relacionamento contendo os dados para validar
	 * @throws BancoobException
	 */
	protected void validarDataInicio(final RelacionamentoPessoa entidade) throws BancoobException {
		DateTimeDB dataInicioMandato = entidade.getDataInicioMandato();
		if (dataInicioMandato != null) {
			Date dataInicio = DateUtils.truncate(dataInicioMandato, Calendar.DATE);
			if (dataInicio.after(DateUtils.truncate(new Date(), Calendar.DATE))) {
				throw new DataAtualException("Início Relacionamento", "igual ou menor");
			}
			
		}
	}

	/**
	 * Realiza validação de data fim, verificando as seguintes regras negociais:
	 * 
	 * <ol>
	 * <li>A data fim de relacionamento deve ser maior que a data Início</li>
	 * <li>A data fim de relacionamento deve ser maior que a Data Atual</li>
	 * <li>A data fim de relacionamento não deve exceder 100 anos</li>
	 * </ol>
	 * 
	 * @param entidade
	 *            o relacionamento contendo os dados para validar
	 * @throws BancoobException
	 */
	protected void validarDataFim(final RelacionamentoPessoa entidade) throws BancoobException {
		final Date dataFim = entidade.getDataFimMandato();

		if (dataFim != null) {

			if (dataFim.before(DateUtils.truncate(new Date(), Calendar.DATE))) {
				throw new DataAtualException("Fim Mandato", "maior");
			}

			Calendar limiteInferior = obterLimiteInferior(entidade);

			Calendar fim = Calendar.getInstance();
			fim.setTime(DateUtils.truncate(dataFim, Calendar.DATE));

			Calendar limiteSuperior = (Calendar) limiteInferior.clone();
			limiteSuperior.roll(Calendar.YEAR, 100);

			if (fim.before(limiteInferior) || fim.after(limiteSuperior)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				throw new ValorForaLimiteException("Fim do Mandato", sdf.format(limiteInferior.getTime()), sdf.format(limiteSuperior.getTime()));
			}
		}
	}

	/**
	 * Obtém o limite inferior para validação da data fim
	 * 
	 * @param relacionamento
	 *            o relacionamento contendo os dados para validar
	 * @return o limite inferior calculado
	 * @throws BancoobException
	 */
	protected Calendar obterLimiteInferior(final RelacionamentoPessoa relacionamento) throws BancoobException {

		final Date dataInicio = relacionamento.getDataInicioMandato();
		Calendar limite = Calendar.getInstance();
		limite.setTime(DateUtils.truncate(dataInicio, Calendar.DATE));
		return limite;
	}

	/**
	 * O método Validar percentual capital.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarPercentualCapital(final RelacionamentoPessoa entidade) throws BancoobException {
		BigDecimal percentual = entidade.getPercentualCapitalEmpresa();

		if (entidade.getTipoRelacionamento().getHabilitaCapitalSocial() && (percentual != null)) {

			if ((percentual.compareTo(BigDecimal.ZERO) < 0) || (percentual.compareTo(new BigDecimal(100)) > 0)) {
				throw new ValorForaLimiteException("Percentual do Capital Social", "0", "100");
			}

			Integer idPessoa = entidade.getPessoa().getIdPessoa();
			Integer idInstituicao = entidade.getIdInstituicao();
			PessoaCompartilhamento pessoaCompartilhamento = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().consultarPorIdPessoaInstituicao(idPessoa, idInstituicao);

			ConsultaDto<RelacionamentoPessoa> criterios = new ConsultaDto<RelacionamentoPessoa>();
			RelacionamentoPessoa filtro = new RelacionamentoPessoa();
			Pessoa pessoa = new Pessoa();
			pessoa.setIdPessoa(idPessoa);
			filtro.setPessoa(pessoa);
			filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
			filtro.setIdInstituicao(idInstituicao);
			filtro.setTipoRelacionamento(new TipoRelacionamentoPessoa());
			filtro.getTipoRelacionamento().setHabilitaCapitalSocial(true);
			criterios.setFiltro(filtro);

			BigDecimal capitalTotal = BigDecimal.ZERO;

			for (RelacionamentoPessoa relacionamento : delegate.pesquisar(criterios).getResultado()) {
				if (!relacionamento.getIdRelacionamento().equals(entidade.getIdRelacionamento()) && relacionamento.getDataHoraInicio() != null
						&& !relacionamento.equals(entidade)
						&& (!relacionamento.getIdRelacionamento().equals(entidade.getIdRelacionamentoAntigo()))) {
					capitalTotal = capitalTotal.add(relacionamento.getPercentualCapitalEmpresa());
				}
			}

			capitalTotal = capitalTotal.add(percentual);

			if (capitalTotal.compareTo(new BigDecimal(100.0)) > 0) {
				throw new CapitalSocialInvalidoException();
			}
		}
	}

	/**
	 * O método Validar tipo relacionamento.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws NegocioException lança a exceção NegocioException
	 */
	protected void validarTipoRelacionamento(RelacionamentoPessoa relacionamento) throws NegocioException {

		TipoRelacionamentoPessoa tipo = relacionamento.getTipoRelacionamento();
		Pessoa pessoa = relacionamento.getPessoa();
		Pessoa pessoaRelacionada = relacionamento.getPessoaRelacionada();
		if (!tipo.getTiposPessoaRelacionamento().contains(pessoa.getTipoPessoa())
				|| !tipo.getTiposPessoaRelacionada().contains(pessoaRelacionada.getTipoPessoa())) {
			throw new NegocioException("MN132", "tipo de relacionamento", pessoaRelacionada.getTipoPessoa().getDescTipoPessoa());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarObrigatoriedadePessoa(RelacionamentoPessoa entidade) throws CampoNaoInformadoException {
		Pessoa pessoa = entidade.getPessoa();
		if ((pessoa == null) || (pessoa.getIdPessoa() == null)) {
			throw new CampoNaoInformadoException("Pessoa");
		}
	}

	/**
	 * O método Validar produto bancoob.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarProdutoBancoob(RelacionamentoPessoa relacionamento) throws BancoobException {
		if (relacionamento != null && relacionamento.getProdutoBancoob()) {
			Pessoa pessoa = relacionamento.getPessoa();
			if (pessoa != null && pessoa.getTipoPessoa() != null) {
				boolean isPessoaJuridica = TipoPessoaEnum.isPessoaJuridica(pessoa.getTipoPessoa().getCodTipoPessoa());
				SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
				boolean isCooperativa = sciIntegracaoDelegate.isCooperativa(pessoa.getCpfCnpj());
				if (isPessoaJuridica && isCooperativa) {
					throw new OperacaoNaoPermitidaException();
				}
			}
		}
	}

	/**
	 * @return the delegate
	 */
	protected RelacionamentoPessoaDelegate getDelegate() {
		return delegate;
	}

}