/* 
 * Sicoob
 * ConversorRelacionamentoPessoa.java 
 * Criado em: 28/10/2011
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.Iterator;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Conversor de
 * {@link br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa} em
 * mensagem da replicação.
 * 
 * 28/10/2011
 * 
 * @author Rodrigo.Chaves
 */
public class ConversorRelacionamentoPessoa
		extends
		Conversor<RelacionamentoPessoa, br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa> {

	/** O atributo pessoaDelegateDB2. */
	private br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate pessoaDelegateDB2 = CAPESCadastroFabricaDelegates
			.getInstance().criarPessoaDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelacionamentoPessoa obterEntidadeReplicacao(
			AtualizacaoCadastralDTO<br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa> dto)
			throws BancoobException {

		br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa entidade = dto
				.getEntidadeCadastro();
		RelacionamentoPessoa relacionamento = new RelacionamentoPessoa();
		relacionamento.setCodigoTipoRelacionamento(entidade.getTipoRelacionamento().getCodigo());
		relacionamento.setDataFimRelacionamento(entidade.getDataFimMandato());
		relacionamento.setDataInicioRelacionamento(entidade.getDataInicioRelacionamento());
		relacionamento.setIdInstituicao(entidade.getIdInstituicao());
		relacionamento.setIdRelacionamentoDB2(entidade.getId());
		relacionamento.setIndeterminado(entidade.getDataFimMandato() == null);
		relacionamento.setPercentualCapitalEmpresa(entidade.getPercentualCapitalEmpresa());
		relacionamento.setTipoConjuge(TipoRelacionamentoPessoaEnum.CONJUGE.getCodigo().equals(
				entidade.getTipoRelacionamento().getCodigo()));

		Pessoa pessoa = obterPessoaSQL(entidade.getPessoa().getIdPessoa(), dto.getInstituicao());
		relacionamento.setPessoa(pessoa);

		Pessoa pessoaRelacionada = obterPessoaSQL(entidade.getPessoaRelacionada().getIdPessoa(),
				dto.getInstituicao());
		relacionamento.setPessoaRelacionada(pessoaRelacionada);
		return relacionamento;
	}

	/**
	 * Obter pessoa sql.
	 *
	 * @param idDB2 o valor de id d b2
	 * @param instituicao o valor de instituicao
	 * @return Pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Pessoa obterPessoaSQL(final Integer idDB2, Instituicao instituicao)
			throws BancoobException {

		Integer idInstituicao = instituicao.getIdInstituicao();
		getLogger().debug("Obtendo pessoa na instituição ", idInstituicao.toString(),
				" com ID DB2 ", idDB2.toString());
		Pessoa pessoa = null;

		Iterator<PessoaCompartilhamento> compartilhamentos = pessoaDelegateDB2.obter(idDB2)
				.getCompartilhamentos().iterator();
		while (compartilhamentos.hasNext() && (pessoa == null)) {
			PessoaCompartilhamento compartilhamento = compartilhamentos.next();
			Iterator<TransicaoPessoa> transicoes = compartilhamento.getTransicoes().iterator();
			while (transicoes.hasNext() && (pessoa == null)) {
				TransicaoPessoa t = transicoes.next();
				if (t.getInstituicao().getIdInstituicao().equals(idInstituicao)) {
					pessoa = obterPessoa(t.getIdPessoaLegado(), instituicao);
					getLogger().info("Pessoa localizada na instituição ", idInstituicao.toString());
				}
			}
		}

		if (pessoa == null) {
			getLogger().alerta("Pessoa não localizada na instituição ", idInstituicao.toString(),
					" com ID DB2 ", idDB2.toString());
		}
		return pessoa;
	}

}
