/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ProdutividadeDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.Empreendimento;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Produtividade;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * @author Erico.Junior
 * 
 */
public class ConversorProdutividade	extends
		ConversorAbstract<Produtividade, br.com.sicoob.capes.negocio.entidades.vigente.Produtividade> {
	
	/** A constante TAMANHO_DESCRICAO. */
	private static final int TAMANHO_DESCRICAO = 200;

	/**
	 * Obter bem.
	 *
	 * @param bemImovel o valor de bem imovel
	 * @param idInstituicao o valor de id instituicao
	 * @return Bem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Bem obterBem(Long idBem, Integer idInstituicao) throws BancoobException {
		Bem bem = null;
		if(idBem != null) {
			Bem criterio = new Bem();
			criterio.setIdBemDB2(idBem);
			
			BemDelegate delegate = obterFabricaDelegates().criarBemDelegate();
			bem = delegate.obterPorIdDB2(criterio, idInstituicao);
		}
		
		return bem;
	}

	/**
	 * Obter empreendimento.
	 *
	 * @param empreendimentoCapes o valor de empreendimento capes
	 * @param idInstituicao o valor de id instituicao
	 * @return Empreendimento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Empreendimento obterEmpreendimento(br.com.sicoob.capes.negocio.entidades.Empreendimento 
			empreendimentoCapes, Integer idInstituicao) throws BancoobException {

		Empreendimento empreendimento = null;
		if(empreendimentoCapes != null && empreendimentoCapes.getId() != null) {
			empreendimento = obterFabricaDelegates().criarEmpreendimentoDelegate().obter(
					empreendimentoCapes.getId(), idInstituicao);
		}
		return empreendimento;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Produtividade instanciarEntidade() {
		return new Produtividade();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Produtividade converter(
			br.com.sicoob.capes.negocio.entidades.vigente.Produtividade entidade,
			Produtividade produtividade, Instituicao instituicao,
			Integer numPessoa) throws BancoobException {

		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);
		String descricao = entidade.getDescricao();
		
		if(descricao != null && descricao.length() >= TAMANHO_DESCRICAO) {
			descricao = descricao.substring(0, TAMANHO_DESCRICAO);
		}
		
		produtividade.setAnoReferencia(entidade.getAnoInicioSafra());
		produtividade.setAreaHectare(entidade.getQuantidadeOuArea());
		produtividade.setDataCadastro(entidade.getDataHoraInicio());
		produtividade.setDescricao(descricao);
		produtividade.setIdProdutividadePessoaDB2(entidade.getIdProdutividade());
		produtividade.setPrecoMedioUnidade(obterValor(entidade.getValorPrecoMedio()));
		produtividade.setQuantidadeProducaoHectare(obterValor(entidade.getProducao()));
		produtividade.setRendaAgropecuaria12Meses(obterValor(entidade.getValorRendaBruta()));
		produtividade.setValorPercentualRebate(obterValor(entidade.getPercentualRebate()));
		produtividade.setPessoa(pessoa);

		if(isProdutividadeFinalizada(entidade)) {
			produtividade.setDataInativacao(entidade.getDataFinalizacao());
		}
		
		br.com.sicoob.capes.negocio.entidades.vigente.Produtividade produtividadeCapes = 
				obterProdutividadeCAPES(entidade.getIdProdutividade());
		
		if(produtividadeCapes != null) {
			Integer idInstituicao = instituicao.getIdInstituicao();
			produtividade.setBem(obterBem(produtividadeCapes.getIdBemAntigo(), idInstituicao));
			produtividade.setEmpreendimento(obterEmpreendimento(produtividadeCapes.getEmpreendimento(), idInstituicao));
		}
	
		return produtividade;
	}
	
	/**
	 * Verifica se eh produtividade finalizada.
	 *
	 * @param produtividade o valor de produtividade
	 * @return {@code true}, se for produtividade finalizada
	 */
	private boolean isProdutividadeFinalizada(
			br.com.sicoob.capes.negocio.entidades.vigente.Produtividade produtividade) {
		Short idSituacao = produtividade.getSituacao();
		return SituacaoProdutividadeEnum.FINALIZADO_FRUSTRACAO.getCodigo().equals(idSituacao) 
				|| SituacaoProdutividadeEnum.FINALIZADO_SUCESSO.getCodigo().equals(idSituacao);
	}

	/**
	 * Obter produtividade capes.
	 *
	 * @param idProdutividade o valor de id produtividade
	 * @return br.com.sicoob.capes.negocio.entidades.vigente.Produtividade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private br.com.sicoob.capes.negocio.entidades.vigente.Produtividade obterProdutividadeCAPES(
			Long idProdutividade) throws BancoobException {
		ProdutividadeDelegate delegate = 
				CAPESCadastroFabricaDelegates.getInstance().criarProdutividadeDelegate();
		return delegate.obter(idProdutividade);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Produtividade, ?> obterDelegate() {
		return obterFabricaDelegates().criarProdutividadeDelegate();
	}


}
