/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Superclasse para convers�o das entidades do Cadastro �nico para as entidades
 * do sistema de replica��o.
 * 
 * @author Erico.Junior
 */
public abstract class Conversor<R extends EntidadeReplicavel<?>, E extends Replicavel> {

	/** O atributo log. */
	private ISicoobLogger log = getLogger();

	/** O atributo pessoaDelegate. */
	private PessoaDelegate pessoaDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate();

	/**
	 * Recupera o logger para essa inst�ncia.
	 * 
	 * @return O logger para essa inst�ncia.
	 */
	protected ISicoobLogger getLogger() {

		return SicoobLoggerPadrao.getInstance(getClass());
	}

	/**
	 * Monta a entidade no modelo do legado.
	 * 
	 * @param dto
	 *            O DTO com os dados da atualiza��o cadastral.
	 * @return A entidade no legado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public abstract R obterEntidadeReplicacao(AtualizacaoCadastralDTO<E> dto) throws BancoobException;

	/**
	 * Recupera a pessoa no sistema legado.
	 * 
	 * @param idPessoa
	 *            O identificador da pessoa na institui��o informada.
	 * @param instituicao
	 *            A institui��o na qual se deseja a pessoa no legado.
	 * @return A pessoa no sistema legado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected Pessoa obterPessoa(Integer idPessoa, Instituicao instituicao) throws BancoobException {

		Integer idInstituicao = instituicao.getIdInstituicao();
		log.debug("Recuperando a pessoa na institui��o: " + idInstituicao);
		return pessoaDelegate.obter(idPessoa, idInstituicao);
	}

	/**
	 * Recupera a pessoa no sistema legado.
	 * 
	 * @param dto
	 *            O DTO com os dados da atualiza��o cadastral.
	 * @return A pessoa no sistema legado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected Pessoa obterPessoa(AtualizacaoCadastralDTO<E> dto) throws BancoobException {

		Integer idPessoa = dto.getIdPessoaLegado();
		return obterPessoa(idPessoa, dto.getInstituicao());
	}

	/**
	 * Recupera a Pessoa no cadastro �nico.
	 * 
	 * @param pessoa
	 *            A pessoa no cadastro �ncio
	 * @return A pessoa no cadastro �ncio
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected PessoaCompartilhamento obterPessoaCompartilhamento(PessoaCompartilhamento pessoa) throws BancoobException {

		PessoaCompartilhamento pessoaCUC = null;

		if (pessoa != null) {
			PessoaCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
					.criarPessoaCompartilhamentoDelegate();
			pessoaCUC = delegate.obter(pessoa.getIdPessoaCompartilhamento());
		}

		return pessoaCUC;
	}

	/**
	 * Recupera a pessoa no legado a partir da pessoa no cadastro �nico e com transi��o para a
	 * institui��o informada.
	 * 
	 * @param pessoa
	 *            A pessoa no cadastro �nico.
	 * @param instituicao
	 *            A institui��o.
	 * @return A pessoa para replica��o na institui��o informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected Pessoa obterPessoaReplicacao(Instituicao instituicao, PessoaCompartilhamento pessoa)
			throws BancoobException {

		Pessoa pessoaReplicacao = null;
		Set<TransicaoPessoa> transicoes = pessoa.getTransicoes();

		if (transicoes != null) {

			Instituicao instituicaoTransicao = null;
			for (TransicaoPessoa transicao : transicoes) {
				instituicaoTransicao = transicao.getInstituicao();

				if (isMesmaInstituicao(instituicao, instituicaoTransicao)) {
					pessoaReplicacao = obterPessoa(transicao.getIdPessoaLegado(), instituicaoTransicao);
					break;
				}
			}
		}

		return pessoaReplicacao;
	}

	/**
	 * Verifica se eh mesma instituicao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param outraInstituicao o valor de outra instituicao
	 * @return {@code true}, se for mesma instituicao
	 */
	private boolean isMesmaInstituicao(Instituicao instituicao, Instituicao outraInstituicao) {

		return instituicao.getIdInstituicao().equals(outraInstituicao.getIdInstituicao());
	}

	/**
	 * Monta a descri��o a partir do nome da pessoa e da descri��o.
	 * 
	 * @param pessoa
	 *            A pessoa no cuc.
	 * @param descricao
	 *            A descri��o complementar.
	 * @return a descri��o a partir do nome e da descri��o.
	 */
	protected String obterDescricao(PessoaCompartilhamento pessoa, String descricao) {

		String nome = pessoa.getNomePessoa();
		StringBuilder sb = new StringBuilder();
		sb.append(nome);
		if (descricao != null && !descricao.trim().equals("")) {
			sb.append(" - ");
			sb.append(descricao);
		}
		return sb.toString();
	}

	/**
	 * Recupera a localidade a partir do idLocalidade informado.
	 * 
	 * @param localidade
	 *            A localidade com o idLocalidade utilizado na consulta.
	 * @return a localidade a partir do idLocalidade informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected Localidade obterLocalidade(Localidade localidade) throws BancoobException {

		return obterLocalidade(localidade.getIdLocalidade());
	}

	/**
	 * Recupera a localidade a partir do idLocalidade informado.
	 * 
	 * @param idLocalidade
	 *            O id da localidade utilizado na consulta.
	 * @return a localidade a partir do idLocalidade informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected Localidade obterLocalidade(Integer idLocalidade) throws BancoobException {

		Localidade localidade = null;
		if (idLocalidade != null) {
			LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance()
					.criarLOCIntegracaoDelegate();
			localidade = IntegracaoUtil.converterLocalidade(locDelegate.obterLocalidade(idLocalidade));
		}
		return localidade;
	}

	/**
	 * Obter valor.
	 *
	 * @param valor o valor de valor
	 * @return BigDecimal
	 */
	protected BigDecimal obterValor(BigDecimal valor) {

		BigDecimal novoValor = null;
		if (valor != null) {
			novoValor = valor.setScale(2, RoundingMode.HALF_EVEN);
		}
		return novoValor;
	}

}
