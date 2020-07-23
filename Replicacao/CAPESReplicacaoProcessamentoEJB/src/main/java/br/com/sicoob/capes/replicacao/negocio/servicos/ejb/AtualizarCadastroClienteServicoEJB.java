/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import static br.com.sicoob.capes.replicacao.negocio.excecao.MensagemReplicacaoInacessivelException.ERRO_MARCAR_MENSAGEM_REPLICACAO;
import static br.com.sicoob.capes.replicacao.negocio.excecao.MensagemReplicacaoInacessivelException.ERRO_OBTER_MENSAGENS_REPLICACAO;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ExcecaoUtils;
import br.com.sicoob.capes.comum.util.json.FabricaDatas;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos.AtualizacaoCadastralCommand;
import br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos.FabricaComandos;
import br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao.Conversor;
import br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao.FabricaConversoresReplicacao;
import br.com.sicoob.capes.replicacao.negocio.excecao.MensagemReplicacaoInacessivelException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AtualizarCadastroClienteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AtualizarCadastroClienteServicoRemote;
import flexjson.JSONDeserializer;
import flexjson.JSONException;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ AtualizarCadastroClienteServicoLocal.class })
@Remote({ AtualizarCadastroClienteServicoRemote.class })
public class AtualizarCadastroClienteServicoEJB<ER extends EntidadeReplicavel<?>, R extends Replicavel>
		extends CAPESReplicacaoProcessamentoServicoEJB implements
		AtualizarCadastroClienteServicoRemote<ER, R>, AtualizarCadastroClienteServicoLocal<ER, R> {

	/** A constante MENSAGEM_ERRO. */
	private static final String MENSAGEM_ERRO =
			"Erro ao processar a mensagem de atualizacao do cadastro.";

	/** O atributo fabrica. */
	private transient CAPESCadastroFabricaDelegates fabrica =
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo delegate. */
	private transient MensagemReplicacaoDelegate delegate = fabrica
			.criarMensagemReplicacaoDelegate();
	
	@EJB(mappedName = "capes/replicacao/AtualizarCadastroClienteServico")
	private AtualizarCadastroClienteServicoLocal<ER, R> servico;

	/**
	 * {@inheritDoc}
	 */
	public List<MensagemReplicacao> obterMensagensReplicacao(final String idOperacao,
			final Integer idInstituicao) {

		try {
			MensagemReplicacao filtro = new MensagemReplicacao();
			filtro.setIdentificadorOperacao(idOperacao);
			filtro.setIdInstituicao(idInstituicao);
			return delegate.listarMensagensNaoProcessadasPorFiltro(filtro, null);
		} catch (BancoobException e) {
			throw new MensagemReplicacaoInacessivelException(e, ERRO_OBTER_MENSAGENS_REPLICACAO,
					idOperacao);
		}
	}

	/**
	 * Executa a atualização cadastral.
	 * 
	 * @param mensagem
	 *            a mensagem que com os dados para a atualização cadastral
	 * 
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na atualização cadastral.
	 */
	public void atualizarCadastro(MensagemReplicacao mensagem) throws BancoobException {

		String mensagemErro = null;
		AtualizacaoCadastralDTO<R> dto = null;
		AtualizacaoCadastralCommand<EntidadeReplicavel<?>> comando = null;
		JSONDeserializer<AtualizacaoCadastralDTO<R>> deserializer;
		deserializer = new JSONDeserializer<AtualizacaoCadastralDTO<R>>();
		deserializer.use(Date.class, new FabricaDatas());

		try {
			dto = deserializer.deserialize(mensagem.getConteudoMensagem());
			mensagemErro = obterMensagemErro(dto);
			if (verificarDtoNula(dto)) {
				R entidadeCadastro = dto.getEntidadeCadastro();
				Character tipoOperacao = dto.getTipoOperacao();
				if ((entidadeCadastro != null) && deveProcessar(tipoOperacao, entidadeCadastro)) {
					
					if (entidadeCadastro instanceof RelacionamentoPessoa) {
						servico.verificarRelacionamentos(entidadeCadastro, dto.getInstituicao());
					}

					comando = FabricaComandos.getInstance().criarComando(tipoOperacao);
					if (comando != null) {
						ER entidadeReplicacao = converter(dto);
						comando.executar(entidadeReplicacao, dto.getInstituicao());
					}
					getLogger().info("Atualizacao efetuada com sucesso");
				}

				// limpa o erro (caso exista) e atualiza a data de processamento
				atualizarDataProcessamento(mensagem, null);
			}
		} catch (JSONException e) {
			getLogger().alerta(e, "Erro ao deserializar a replicação. Mensagem descartada: ",
					mensagem.getId().toString());
		} catch (BancoobException excecao) {
			throw new BancoobRuntimeException(mensagemErro, excecao);
		}
	}

	/**
	 * Verificar dto nula.
	 *
	 * @param dto o valor de dto
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean verificarDtoNula(AtualizacaoCadastralDTO<R> dto) {
		return dto != null;
	}

	/**
	 * Verifica se a operação deve ser processado sem outras verificações
	 * 
	 * @param tipoOperacao
	 *            o tipo da operacao
	 * @return {@code true} caso seja de processamento direto
	 */
	private boolean isProcessamentoDireto(Character tipoOperacao) throws BancoobException {

		TipoOperacaoEnum operacao =
				TipoAtualizacaoCadastralEnum.valueOf(tipoOperacao).getOperacaoBase();
		boolean processamentoDireto =
				TipoOperacaoEnum.I.equals(operacao) || TipoOperacaoEnum.E.equals(operacao);
		getLogger().debug("Processamento Direto: ", String.valueOf(processamentoDireto), "(",
				tipoOperacao.toString(), ")");
		return processamentoDireto;
	}

	/**
	 * Uma atualização deve ser reenviada quando: 1) Não for uma entidade
	 * vigente do cadastro único. 2) Sendo uma entidade do cadastro único: 2.1)
	 * Se a data do registro no CAPES for menor que a data do registro no
	 * cadastro.
	 * 
	 * @param tipoOperacao
	 *            O tipo da operação
	 * @param replicavel
	 *            A replicação do cadastro.
	 * @return boolean indicando se a replicação deve ou não ser reenviada.
	 */
	private boolean deveProcessar(Character tipoOperacao, R replicavel) throws BancoobException {

		boolean deveProcessar = true;
		String classe = replicavel.getClass().getName();

		getLogger().debug("Deve processar ", classe, ": INICIO");

		if (!isProcessamentoDireto(tipoOperacao)) {
			deveProcessar = deveProcessarEntidade(replicavel);
		}

		getLogger().debug("Deve processar ", classe, ": ", String.valueOf(deveProcessar));

		// FIXME rodrigo.chaves: paliativo para resolução de problema em
		// produção
		return true;
	}

	/**
	 * Verifica se o objeto {@code replicavel} é uma entidade (
	 * {@code extends CAPESEntidade}) e define se deve
	 * processá-lo ou não
	 * 
	 * @param replicavel
	 *            o objeto a ser verificado
	 * @return <code>true</code> se o objeto deve ser processado
	 * @see CAPESEntidade
	 */
	private boolean deveProcessarEntidade(final R replicavel) throws BancoobException {

		boolean deveProcessar;
		CAPESEntidade<?> entidade;
		if (replicavel instanceof CAPESEntidade<?>) {
			entidade = (CAPESEntidade<?>) replicavel;
			deveProcessar = deveProcessarVigente(entidade);
		} else {
			getLogger().alerta("Objeto replicavel nao e uma entidade: ",
					replicavel.getClass().getName());
			deveProcessar = true;
		}
		return deveProcessar;
	}

	/**
	 * Verifica se o objeto {@code replicavel} possui histórico (
	 * {@code  implements Vigente}) e define se deve processá-lo ou não.
	 * 
	 * @param entidade
	 *            o objeto a ser verificado
	 * @return <code>true</code> se o objeto deve ser processado
	 * @see Vigente
	 */
	private <E extends CAPESEntidade<?>> boolean deveProcessarVigente(
			final E entidade) throws BancoobException {

		boolean deveProcessar;
		final String classe = entidade.getClass().getName();
		if (entidade instanceof Vigente) {
			E entidadePersistente = consultar(entidade);

			if (entidadePersistente != null) {
				Date dataAtualizacao = ((Vigente) entidade).getDataHoraInicio();
				Date dataPersistente = ((Vigente) entidadePersistente).getDataHoraInicio();

				// Só processa mensagens que a data da entidade seja
				// anterior à data de atualização
				deveProcessar = !dataAtualizacao.before(dataPersistente);

				getLogger().debug("Comparacao de datas [", classe, "(",
						String.valueOf(entidade.getId()), ")]: ", String.valueOf(dataPersistente),
						" <= ", String.valueOf(dataAtualizacao), " = ",
						String.valueOf(deveProcessar));
			} else {
				getLogger().alerta(
						"Entidade persistente nao localizada para verificacao de datas: ", classe,
						"(", String.valueOf(entidade.getId()), ") ");
				deveProcessar = true;
			}
		} else {
			getLogger().alerta(
					"Objeto replicavel nao implementa \"Vigente\", "
							+ "para verificacao de datas: ", classe);
			deveProcessar = true;
		}
		return deveProcessar;
	}

	/**
	 * Consultar.
	 *
	 * @param <E> o tipo generico
	 * @param entidade o valor de entidade
	 * @return E
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <E extends CAPESEntidade<?>> E consultar(final E entidade)
			throws BancoobException {

		CAPESCadastroCrudDelegate delegate = null;
		E retorno = null;

		if (entidade instanceof BemOnus || entidade instanceof BemPosse
				|| entidade instanceof BemRegistro) {
			retorno = (E) consultarBemOnusPosseRegistro(entidade);
		} else {
			if (entidade instanceof PessoaFisica || entidade instanceof PessoaJuridica) {
				delegate = fabrica.criarPessoaCompartilhamentoDelegate();
			} else if (entidade instanceof Referencia || entidade instanceof ReferenciaBancaria) {
				delegate = fabrica.criarReferenciaDelegate();
			} else {
				delegate = fabrica.criarDelegate(entidade.getClass());
			}
			retorno = (E) delegate.obter(entidade.getId());
		}

		return retorno;
	}

	/**
	 * Consultar bem onus posse registro.
	 *
	 * @param <E> o tipo generico
	 * @param entidade o valor de entidade
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	private <E extends CAPESEntidade<?>> R consultarBemOnusPosseRegistro(
			E entidade) throws BancoobException {

		R retorno = null;
		Ibem onusPosseRegistro = (Ibem) entidade;
		Bem bem = consultarBem(onusPosseRegistro.getBem());
		if (bem != null) {
			if (entidade instanceof BemOnus) {
				retorno = (R) recuperarBemOnus(bem, ((BemOnus) entidade).getIdBemOnus());
			} else if (entidade instanceof BemRegistro) {
				retorno =
						(R) recuperarBemRegistro(bem, ((BemRegistro) entidade).getIdBemRegistro());
			} else if (entidade instanceof BemPosse) {
				retorno = (R) recuperarBemPosse(bem, ((BemPosse) entidade).getIdBemPosse());
			}
		}

		return retorno;
	}

	/**
	 * Recuperar bem onus.
	 *
	 * @param bem o valor de bem
	 * @param idBemOnus o valor de id bem onus
	 * @return BemOnus
	 */
	private BemOnus recuperarBemOnus(Bem bem, Integer idBemOnus) {

		BemOnus retorno = null;
		List<BemOnus> onus = bem.getBensOnus();

		if (onus != null) {
			for (BemOnus bemOnus : onus) {
				if (bemOnus.getId().equals(idBemOnus)) {
					retorno = bemOnus;
					break;
				}
			}
		}

		return retorno;
	}

	/**
	 * Recuperar bem registro.
	 *
	 * @param bem o valor de bem
	 * @param idBemRegistro o valor de id bem registro
	 * @return BemRegistro
	 */
	private BemRegistro recuperarBemRegistro(Bem bem, Integer idBemRegistro) {

		BemRegistro retorno = null;
		List<BemRegistro> registros = bem.getBensRegistro();

		if (registros != null) {
			for (BemRegistro registro : registros) {
				if (registro.getId().equals(idBemRegistro)) {
					retorno = registro;
					break;
				}
			}
		}
		return retorno;
	}

	/**
	 * Recuperar bem posse.
	 *
	 * @param bem o valor de bem
	 * @param idBemPosse o valor de id bem posse
	 * @return BemPosse
	 */
	private BemPosse recuperarBemPosse(Bem bem, Integer idBemPosse) {

		BemPosse retorno = null;
		List<BemPosse> posses = bem.getBensPosse();

		if (posses != null) {
			for (BemPosse posse : posses) {
				if (posse.getId().equals(idBemPosse)) {
					retorno = posse;
					break;
				}
			}
		}
		return retorno;
	}

	/**
	 * Consultar bem.
	 *
	 * @param bem o valor de bem
	 * @return Bem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Bem consultarBem(Bem bem) throws BancoobException {
		Long idBem = bem.getIdBem();
		Bem retorno = null;
		if (idBem != null) {
			retorno = fabrica.criarBemAntigoDelegate().obter(idBem);
		}
		return retorno;
	}

	/**
	 * Converter.
	 *
	 * @param dto o valor de dto
	 * @return ER
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ER converter(AtualizacaoCadastralDTO<R> dto) throws BancoobException {

		Conversor<ER, R> conversor = obterConversor(dto);
		return conversor.obterEntidadeReplicacao(dto);
	}

	/**
	 * Obter conversor.
	 *
	 * @param dto o valor de dto
	 * @return Conversor
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Conversor<ER, R> obterConversor(AtualizacaoCadastralDTO<R> dto) {
		FabricaConversoresReplicacao fabrica = FabricaConversoresReplicacao.getInstance();
		Conversor<ER, R> conversor = fabrica.obterConversor(dto.getEntidadeCadastro());
		return conversor;
	}

	/**
	 * Obter mensagem erro.
	 *
	 * @param dto o valor de dto
	 * @return String
	 */
	private String obterMensagemErro(AtualizacaoCadastralDTO<R> dto) {

		String nomeClasse = "";
		Object idCapes = null;

		R entidadeCadastro = dto.getEntidadeCadastro();
		if (entidadeCadastro != null) {
			nomeClasse = entidadeCadastro.getClass().getSimpleName();
			if (entidadeCadastro instanceof CAPESEntidade) {
				idCapes = ((CAPESEntidade<?>) entidadeCadastro).getId();
			}
		}

		StringBuilder sb = new StringBuilder(MENSAGEM_ERRO);
		sb.append(" idInstituicao: ");
		sb.append(dto.getInstituicao().getIdInstituicao());
		sb.append(", idPessoaLegado: ");
		sb.append(dto.getIdPessoaLegado());
		sb.append(", tipoOperacao: ");
		sb.append(dto.getTipoOperacao());
		sb.append(", entidade: ");
		sb.append(nomeClasse);
		sb.append(", idCapes: ");
		sb.append(idCapes);
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarDataProcessamento(final MensagemReplicacao mensagem,
			final Exception excecao) {
		if (mensagem != null) {
			Integer idMensagem = mensagem.getId();
			try {
				delegate.atualizarDataProcessamento(idMensagem, new Date(),
						ExcecaoUtils.extrairStackTrace(excecao));
				getLogger().debug("Data de processamento atualizada: ", idMensagem.toString());
			} catch (BancoobException e) {
				throw new MensagemReplicacaoInacessivelException(e,
						ERRO_MARCAR_MENSAGEM_REPLICACAO, idMensagem);
			}
		}
	}
	
	/**
	 * Verifica a necessidade de replicar um cadastro.
	 * 
	 * @param entidadeCadastro
	 * @throws BancoobException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void verificarRelacionamentos(R entidadeCadastro, Instituicao instituicao) throws BancoobException {
		if (entidadeCadastro instanceof RelacionamentoPessoa) {
			RelacionamentoPessoa relacionamento = (RelacionamentoPessoa) entidadeCadastro;
			
			if(relacionamento.getPessoaRelacionada() != null) {
				getLogger().debug("Verificando o cadastro da pessoa relacionada: " + relacionamento.getPessoaRelacionada().getId() + " na instituicao: " + instituicao.toString());
				boolean possuiTransicao = possuiTransicao(relacionamento.getPessoaRelacionada(), instituicao.getIdInstituicao());
				getLogger().debug("Pessoa relacionada possui transicao: " + possuiTransicao);
				if (!possuiTransicao) {
					getLogger().debug("Compartilhando o cadastro da pessoa relacionada na instituicao" + instituicao.toString());
					PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
					PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate.consultarPorIdPessoaInstituicao(relacionamento.getPessoaRelacionada().getId(), instituicao.getIdInstituicao());
					
					ReplicacaoCadastroDelegate delegate = fabrica.criarReplicacaoCadastroDelegate();
					delegate.iniciarRelacionamentoInstituicao(pessoaCompartilhamento, instituicao);
				}
			}
		}
	}
	
	/**
	 * Verifica se a pessoa existe na base da cooperativa.
	 * 
	 * @param pessoa
	 * @param idInsituicao
	 * @return
	 * @throws BancoobException
	 */
	private boolean possuiTransicao(Pessoa pessoa, Integer idInsituicao) throws BancoobException {
		TransicaoPessoaDelegate transicaoPessoaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
		TransicaoPessoa transicao = transicaoPessoaDelegate.obterTransicaoPorPessoaInstituicao(pessoa, idInsituicao);
		return transicao != null;
	}
}
