/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.TipoBem;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.TipoBemDelegate;

/**
 * Conversor do Bem.
 * 
 * @author erico.junior
 */
public class ConversorBem
		extends ConversorAbstract<Bem, br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> {

	/** O atributo tipoBemDelegate. */
	private transient TipoBemDelegate tipoBemDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarTipoBemDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem instanciarEntidade() {
		return new Bem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem converter(
			br.com.sicoob.capes.negocio.entidades.bemantigo.Bem entidade,
			Bem bem, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		// O subtipo do bem no CAPES é representado pelo tipo no legado.
		Short codigo = entidade.getSubTipo().getCodigo();
		TipoBem tipo = tipoBemDelegate.obter(codigo, instituicao.getIdInstituicao());

		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);

		bem.setPessoa(pessoa);
		bem.setDataCadastro(entidade.getDataHoraInicio());
		bem.setDataVencimentoSeguro(entidade.getDataVencimentoSeguro());
		bem.setDescricao(entidade.getDescricao());
		bem.setNomeSeguradora(entidade.getNomeSeguradora());
		bem.setSituacao(entidade.getSituacao().getCodigo());
		bem.setTipoBem(tipo);
		bem.setValorAtualMercado(obterValor(entidade.getValorAtualMercado()));
		BigDecimal valorCalculoLimite = obterValorCalculoLimite(entidade.getValorAtualMercado(),
				entidade.getPercentual());
		bem.setValorUsadoCalculoLimite(valorCalculoLimite);
		bem.setValorSeguro(obterValor(entidade.getValorSeguro()));
		bem.setIdBemDB2(entidade.getId());
		return bem;
	}

	/**
	 * Obter valor calculo limite.
	 *
	 * @param valorAtualMercado o valor de valor atual mercado
	 * @param percentual o valor de percentual
	 * @return BigDecimal
	 */
	private BigDecimal obterValorCalculoLimite(BigDecimal valorAtualMercado,
			BigDecimal percentual) {
		BigDecimal retorno = valorAtualMercado;
		if(valorAtualMercado != null
				&& percentual != null){
			retorno = valorAtualMercado.multiply(percentual).divide(BigDecimal.valueOf(100));
		}
		
		return obterValor(retorno);
	}
	
	/**
	 * Obter entidade para alteracao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Bem obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.Bem entidadeCapes) 
			throws BancoobException {

		Bem entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		List<Bem> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		
		Bem entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = listEntidade.get(0);
		}
		return converter(entidadeCapes, entidade , instituicao, numPessoa);
	}
	
	/**
	 * Obter entidade para exclusao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Bem obterEntidadeParaExclusao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.Bem entidadeCapes) throws BancoobException {
		
		Bem entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		
		List<Bem> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		
		Bem entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = listEntidade.get(0);
		}
		
		return entidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemDelegate obterDelegate() {
		return obterFabricaDelegates().criarBemDelegate();
	}
}
