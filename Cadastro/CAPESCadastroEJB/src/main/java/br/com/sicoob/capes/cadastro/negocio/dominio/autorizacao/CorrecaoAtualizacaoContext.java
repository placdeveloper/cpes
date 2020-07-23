// 12/03/2013 - 08:13:09
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class CorrecaoAtualizacaoContext {

	/** A constante STRATEGIES. */
	private static final Map<TipoAutorizacaoEntidadeEnum, Class<? extends CorrecaoAtualizacaoStrategy>> STRATEGIES;

	/** O atributo strategy. */
	private CorrecaoAtualizacaoStrategy strategy;

	static {
		STRATEGIES =
				new HashMap<TipoAutorizacaoEntidadeEnum, Class<? extends CorrecaoAtualizacaoStrategy>>();
		STRATEGIES.put(TipoAutorizacaoEntidadeEnum.BEM_NOVO, CorrecaoAtualizacaoBem.class);
		STRATEGIES.put(TipoAutorizacaoEntidadeEnum.PRODUTOR, CorrecaoAtualizacaoProdutor.class);
		STRATEGIES.put(TipoAutorizacaoEntidadeEnum.PESSOA, CorrecaoAtualizacaoEspecifica.class);
		STRATEGIES.put(TipoAutorizacaoEntidadeEnum.RESPONSAVEL, CorrecaoAtualizacaoEspecifica.class);
		STRATEGIES.put(TipoAutorizacaoEntidadeEnum.TRIBUTACAO, CorrecaoAtualizacaoEspecifica.class);
		STRATEGIES.put(TipoAutorizacaoEntidadeEnum.RELACIONAMENTO, CorrecaoAtualizacaoRelacionamento.class);
	}

	/**
	 * Construtor
	 * 
	 * @param tipo
	 */
	public CorrecaoAtualizacaoContext(TipoAutorizacaoEntidadeEnum tipo) {

		this.strategy = ReflexaoUtils.construirObjetoPorClasse(getClasseStrategia(tipo));
	}

	/**
	 * O método Execute.
	 *
	 * @param <A> o tipo generico
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public <A extends CAPESEntidade<Long> & Aprovavel> void execute(
			Autorizacao autorizacao, A aprovavel, TipoOperacaoEnum tipoOperacao)
			throws BancoobException {
		this.strategy.execute(autorizacao, aprovavel, tipoOperacao);
	}

	/**
	 * Gets the classe strategia.
	 *
	 * @param tipo o valor de tipo
	 * @return the classe strategia
	 */
	private Class<? extends CorrecaoAtualizacaoStrategy> getClasseStrategia(
			TipoAutorizacaoEntidadeEnum tipo) {

		Class<? extends CorrecaoAtualizacaoStrategy> classe = STRATEGIES.get(tipo);
		if (classe == null) {
			classe = CorrecaoAtualizacaoGenerica.class;
		}
		return classe;
	}

}
