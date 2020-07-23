package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.Collection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.contexto.IGerenciadorTransacao;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.dominio.replicacao.facade.IAtualizacaoCadastralFachada;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import flexjson.JSONSerializer;
import flexjson.transformer.IterableTransformer;

/**
 * Fachada utilizada para o envio da atualização cadastral de forma assíncrona utilizando JMS.
 * @author Erico.Junior
 */
public class AtualizacaoCadastralFachada<R extends Replicavel> implements IAtualizacaoCadastralFachada<R> {

	/** O atributo logger. */
	private transient final ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());
	
	/** O atributo transicaoDelegate. */
	private transient final TransicaoPessoaDelegate transicaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarAtualizacao(R entidade, TipoAtualizacaoCadastralEnum tipoAtualizacao) throws BancoobException {
		Collection<TransicaoPessoa> transicoes = obterTransicoes(entidade);

		if (transicoes != null && !transicoes.isEmpty()) {
			for (TransicaoPessoa transicao : transicoes) {
				salvarAtualizacaoCadastral(entidade, transicao, tipoAtualizacao);
			}
		}
	}

	/**
	 * O método Salvar atualizacao cadastral.
	 *
	 * @param entidade o valor de entidade
	 * @param instituicao o valor de instituicao
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @param acao o valor de acao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void salvarAtualizacaoCadastral(R entidade, Instituicao instituicao, Integer idPessoaLegado, TipoAtualizacaoCadastralEnum acao) throws BancoobException {

		boolean salvar = isEntidadeReplicavel(entidade);
		String uuid = obterGerenciadorTransacao().obter();

		if (salvar) {
			MensagemReplicacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemReplicacaoDelegate();

			AtualizacaoCadastralDTO<R> dto = new AtualizacaoCadastralDTO<R>();
			dto.setEntidadeCadastro(entidade);
			dto.setInstituicao(instituicao);
			dto.setIdPessoaLegado(idPessoaLegado);
			dto.setTipoOperacao(obterTipoOperacao(entidade, acao));

			MensagemReplicacao mensagem = new MensagemReplicacao();
			mensagem.setEntidadeCadastro(entidade.getClass().getName());

			if (entidade instanceof Bem) {
				mensagem.setConteudoMensagem(criarConteudoMensagem(dto, "*.compartilhamento"));
			} else {
				mensagem.setConteudoMensagem(criarConteudoMensagem(dto));
			}

			mensagem.setDataCadastro(new DateTimeDB());
			mensagem.setIdentificadorOperacao(uuid);
			mensagem.setIdInstituicao(instituicao.getIdInstituicao());
			mensagem.setIdPessoaLegado(idPessoaLegado);
			mensagem.setTipoOperacao(TipoAtualizacaoCadastralEnum.valueOf(dto.getTipoOperacao()).getDescricao().toUpperCase());

			Object idRegistro = ReflexaoUtils.getValorAtributo(entidade, "id");
			if (idRegistro instanceof Number) {
				mensagem.setIdRegistro(((Number) idRegistro).longValue());
			} else {
				this.logger.alerta("A chave da entidade nao e numerica e nao pode ser salva na mensagem: ", entidade.getClass().getCanonicalName());
			}
			delegate.incluir(mensagem);
			this.logger.debug("Mensagem salva: ", uuid);
		} else {
			this.logger.debug("Mensagem nao salva (necessita de aprovacao): ", uuid);
		}
	}

	/**
	 * Verifica se eh entidade replicavel.
	 *
	 * @param entidade o valor de entidade
	 * @return {@code true}, se for entidade replicavel
	 */
	protected boolean isEntidadeReplicavel(R entidade) {
		if (entidade instanceof Aprovavel) {
			Aprovavel aprovavel = (Aprovavel) entidade;
			return (aprovavel.getIdInstituicaoAtualizacao() == null);
		}
		return true;
	}

	/**
	 * Obter tipo operacao.
	 *
	 * @param entidade o valor de entidade
	 * @param acao o valor de acao
	 * @return Character
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Character obterTipoOperacao(R entidade, TipoAtualizacaoCadastralEnum acao) throws BancoobException {
		Character tipoOperacao = acao.getTipoOperacao();
		if (entidade instanceof Aprovavel || entidade instanceof Ibem) {

			Aprovavel aprovavel = null;
			if (entidade instanceof Aprovavel) {
				aprovavel = (Aprovavel) entidade;

			} else if (entidade instanceof Ibem) {
				aprovavel = ((Ibem) entidade).getBem();
			}

			TipoOperacaoEnum tipoOperacaoAutorizacao = obterTipoOperacaoEnum(aprovavel);
			tipoOperacao = atualizarTipoOperacao(tipoOperacao, tipoOperacaoAutorizacao, acao);
		}

		return tipoOperacao;
	}

	/**
	 * Atualizar tipo operacao.
	 *
	 * @param tipoOperacao o valor de tipo operacao
	 * @param tipoOperacaoAutorizacao o valor de tipo operacao autorizacao
	 * @param acao o valor de acao
	 * @return Character
	 */
	private Character atualizarTipoOperacao(Character tipoOperacao, TipoOperacaoEnum tipoOperacaoAutorizacao, TipoAtualizacaoCadastralEnum acao) {
		if ((tipoOperacaoAutorizacao != null) && TipoOperacaoEnum.I.equals(tipoOperacaoAutorizacao) && acao.getTipoInclusao() != null) {
			return acao.getTipoInclusao();
		}
		return tipoOperacao;
	}

	/**
	 * Obter tipo operacao enum.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return TipoOperacaoEnum
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private TipoOperacaoEnum obterTipoOperacaoEnum(Aprovavel aprovavel) throws BancoobException {
		CAPESCadastroFabricaDelegates factory = CAPESCadastroFabricaDelegates.getInstance();
		AutorizacaoDelegate autorizacaoDelegate = factory.criarAutorizacaoDelegate();
		TipoOperacaoEnum tipoOperacaoAutorizacao = autorizacaoDelegate.obterTipoOperacaoAutorizacao(aprovavel);

		return tipoOperacaoAutorizacao;
	}

	/**
	 * O método Salvar atualizacao cadastral.
	 *
	 * @param entidade o valor de entidade
	 * @param transicao o valor de transicao
	 * @param acao o valor de acao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void salvarAtualizacaoCadastral(R entidade, TransicaoPessoa transicao, TipoAtualizacaoCadastralEnum acao) throws BancoobException {
		salvarAtualizacaoCadastral(entidade, transicao.getInstituicao(), transicao.getIdPessoaLegado(), acao);
	}

	/**
	 * Recupera as transições associadas a Pessoa da entidade informada.
	 * 
	 * @param entidade
	 *            A entidade que está sendo atualizada.
	 * @return O conjunto de transições da pessoa da entidade informada.
	 * @throws BancoobException
	 */
	protected Collection<TransicaoPessoa> obterTransicoes(R entidade) throws BancoobException {
		return this.transicaoDelegate.listarTransicoesParaReplicacao(entidade.getPessoaCompartilhamento());
	}
	
	/**
	 * Obter gerenciador transacao.
	 *
	 * @return IGerenciadorTransacao
	 */
	private IGerenciadorTransacao obterGerenciadorTransacao() {
		return ReflexaoUtils.construirSingletonPorClasse("br.com.sicoob.capes.cadastro.negocio.contexto.GerenciadorTransacaoReplicacao", "obterInstancia");
	}
	
	/**
	 * Cria o conteudo da mensagem de texto para a replicação.
	 * 
	 * @param entidadeReplicacao
	 *            a entidade que será replicada.
	 * @return Uma String com o contéudo da mensagem.
	 */
	private static String criarConteudoMensagem(AtualizacaoCadastralDTO<?> dto) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.id");
		serializer.transform(new IterableTransformer(), Collection.class);

		return serializer.deepSerialize(dto);
	}
	
	/**
	 * Cria o conteudo da mensagem de texto para a replicação.
	 * 
	 * @param entidadeReplicacao
	 *            a entidade que será replicada.
	 * @return Uma String com o contéudo da mensagem.
	 */
	private static String criarConteudoMensagem(AtualizacaoCadastralDTO<?> dto, String... propriedadesIgnoradas) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.id");

		if (propriedadesIgnoradas != null) {
			for (String propriedade : propriedadesIgnoradas) {
				serializer.exclude(propriedade);
			}
		}
		serializer.transform(new IterableTransformer(), Collection.class);

		return serializer.deepSerialize(dto);
	}
}