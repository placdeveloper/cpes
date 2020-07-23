package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * A Classe SalvamentoAtualizacaoContext.
 */
public class SalvamentoAtualizacaoContext {

	/** O atributo strategies. */
	private Map<TipoAutorizacaoEntidadeEnum, Class<? extends SalvamentoAtualizacaoStrategy>> strategies;
	
	/** O atributo strategy. */
	private SalvamentoAtualizacaoStrategy strategy;

	/**
	 * Instancia um novo SalvamentoAtualizacaoContext.
	 */
	private SalvamentoAtualizacaoContext() {
		strategies =
				new HashMap<TipoAutorizacaoEntidadeEnum, Class<? extends SalvamentoAtualizacaoStrategy>>();
		strategies.put(TipoAutorizacaoEntidadeEnum.PRODUTOR, SalvamentoAtualizacaoEspecifico.class);
		strategies.put(TipoAutorizacaoEntidadeEnum.PESSOA, SalvamentoAtualizacaoEspecifico.class);
		strategies.put(TipoAutorizacaoEntidadeEnum.BEM_NOVO, SalvamentoAtualizacaoBem.class);
		strategies.put(TipoAutorizacaoEntidadeEnum.BEM, SalvamentoAtualizacaoBemAntigo.class);
		strategies.put(TipoAutorizacaoEntidadeEnum.TRIBUTACAO, SalvamentoAtualizacaoEspecifico.class);
		strategies.put(TipoAutorizacaoEntidadeEnum.RESPONSAVEL, SalvamentoAtualizacaoEspecifico.class);
		strategies.put(TipoAutorizacaoEntidadeEnum.RELACIONAMENTO, SalvamentoAtualizacaoRelacionamento.class);
	}

	/**
	 * Instancia um novo SalvamentoAtualizacaoContext.
	 *
	 * @param tipo o valor de tipo
	 */
	public SalvamentoAtualizacaoContext(TipoAutorizacaoEntidadeEnum tipo) {

		this();
		Class<? extends SalvamentoAtualizacaoStrategy> tipoEstrategia = strategies.get(tipo);
		if (tipoEstrategia != null) {
			this.strategy = ReflexaoUtils.construirObjetoPorClasse(tipoEstrategia);
		} else {
			this.strategy = new SalvamentoAtualizacaoGenerico();
		}
	}

	/**
	 * Execute.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @return Autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Autorizacao execute(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao)
			throws BancoobException {
		return strategy.execute(aprovavel, tipoOperacao);
	}
}
