package br.com.sicoob.capes.cadastro.negocio.estrategias;

import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.EstrategiaNaoLocalizadaException;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * 15/02/2011
 * 
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoContext {
	
	/** A constante ESTRATEGIAS_APROVACAO. */
	private static final Map<TipoAutorizacaoEntidadeEnum, Class<? extends EstrategiaAutorizacao>> ESTRATEGIAS_APROVACAO;
	
	/** A constante ESTRATEGIAS_REJEICAO. */
	private static final Map<TipoAutorizacaoEntidadeEnum, Class<? extends EstrategiaAutorizacao>> ESTRATEGIAS_REJEICAO;
	
	/** O atributo strategy. */
	private EstrategiaAutorizacao strategy;
	
	/** O atributo controle. */
	private ControleVO controle;
	

	static {

		// APROVACOES
		ESTRATEGIAS_APROVACAO = new HashMap<TipoAutorizacaoEntidadeEnum, Class<? extends EstrategiaAutorizacao>>();
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.PESSOA, EstrategiaAutorizacaoAprovarEspecifico.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.PRODUTOR, EstrategiaAutorizacaoAprovarEspecifico.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.RESPONSAVEL, EstrategiaAutorizacaoAprovarEspecifico.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.TRIBUTACAO, EstrategiaAutorizacaoAprovarEspecifico.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.ENDERECO, EstrategiaAutorizacaoAprovarEndereco.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.RELACIONAMENTO, EstrategiaAutorizacaoAprovarRelacionamento.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.BEM_NOVO, EstrategiaAutorizacaoAprovarBem.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.BEM, EstrategiaAutorizacaoAprovarBemAntigo.class);
		ESTRATEGIAS_APROVACAO.put(TipoAutorizacaoEntidadeEnum.FONTE_RENDA, EstrategiaAutorizacaoAprovarFonteRenda.class);

		// REJEICOES
		ESTRATEGIAS_REJEICAO = new HashMap<TipoAutorizacaoEntidadeEnum, Class<? extends EstrategiaAutorizacao>>();
		ESTRATEGIAS_REJEICAO.put(TipoAutorizacaoEntidadeEnum.PESSOA, EstrategiaAutorizacaoRejeitarEspecifico.class);
		ESTRATEGIAS_REJEICAO.put(TipoAutorizacaoEntidadeEnum.PRODUTOR, EstrategiaAutorizacaoRejeitarEspecifico.class);
		ESTRATEGIAS_REJEICAO.put(TipoAutorizacaoEntidadeEnum.RESPONSAVEL, EstrategiaAutorizacaoRejeitarEspecifico.class);
		ESTRATEGIAS_REJEICAO.put(TipoAutorizacaoEntidadeEnum.TRIBUTACAO, EstrategiaAutorizacaoRejeitarEspecifico.class);
		ESTRATEGIAS_REJEICAO.put(TipoAutorizacaoEntidadeEnum.RELACIONAMENTO, EstrategiaAutorizacaoRejeitarRelacionamento.class);
		ESTRATEGIAS_REJEICAO.put(TipoAutorizacaoEntidadeEnum.BEM_NOVO, EstrategiaAutorizacaoRejeitarBem.class);
	}

	/**
	 * Instancia um novo EstrategiaAutorizacaoContext.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @param controle o valor de controle
	 * @throws NegocioException lança a exceção NegocioException
	 */
	public EstrategiaAutorizacaoContext(TipoAutorizacaoEntidadeEnum tipoAutorizacao, ControleVO controle)
			throws NegocioException {

		SicoobLoggerPadrao.getInstance(EstrategiaAutorizacaoContext.class).debug(
				"selecionano estrategia: ", tipoAutorizacao.toString(), ", ", controle.toString());
		Class<? extends EstrategiaAutorizacao> classe = null;
		Integer codigoControle = controle.getCodigo();
		if (Negocio.GFT_CODIGO_CONTROLE_APROVAR_ALTERACOES.equals(codigoControle )) {
			classe = getClasseEstrategiaAprovacao(tipoAutorizacao);
		} else if (Negocio.GFT_CODIGO_CONTROLE_REJEITAR_ALTERACOES.equals(codigoControle)) {
			classe = getClasseEstrategiaRejeicao(tipoAutorizacao);
		} else if (Negocio.GFT_CODIGO_CONTROLE_DEVOLVER_ALTERACOES.equals(codigoControle)) {
			classe = getClasseEstrategiaDevolucao(tipoAutorizacao);
		} else if (Negocio.GFT_CODIGO_CONTROLE_CORRIGIR_ALTERACOES.equals(codigoControle)) {
			classe = getClasseEstrategiaCorrecao(tipoAutorizacao);
		} else {
			throw new EstrategiaNaoLocalizadaException(EstrategiaAutorizacao.class,
					controle.toString());
		}
		SicoobLoggerPadrao.getInstance(EstrategiaAutorizacaoContext.class).debug(
				"Estrategia de autorizacao selecionada: ", classe.getSimpleName());
		this.strategy = ReflexaoUtils.construirObjetoPorClasse(classe);
		this.controle = controle;
	}

	/**
	 * O método Executar.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void executar(Autorizacao autorizacao) throws BancoobException {
		SicoobLoggerPadrao.getInstance(EstrategiaAutorizacaoContext.class).debug(
				"executando controle: ", controle.toString());
		this.strategy.executar(autorizacao);
	}

	/**
	 * Gets the classe estrategia aprovacao.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return the classe estrategia aprovacao
	 */
	private Class<? extends EstrategiaAutorizacao> getClasseEstrategiaAprovacao(
			TipoAutorizacaoEntidadeEnum tipoAutorizacao) {

		Class<? extends EstrategiaAutorizacao> classe = ESTRATEGIAS_APROVACAO.get(tipoAutorizacao);
		if (classe == null) {
			classe = EstrategiaAutorizacaoAprovar.class;
		}
		return classe;
	}

	/**
	 * Gets the classe estrategia rejeicao.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return the classe estrategia rejeicao
	 */
	private Class<? extends EstrategiaAutorizacao> getClasseEstrategiaRejeicao(
			TipoAutorizacaoEntidadeEnum tipoAutorizacao) {
		Class<? extends EstrategiaAutorizacao> classe = ESTRATEGIAS_REJEICAO.get(tipoAutorizacao);
		if (classe == null) {
			classe = EstrategiaAutorizacaoRejeitar.class;
		}
		return classe;
	}

	/**
	 * Gets the classe estrategia devolucao.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return the classe estrategia devolucao
	 */
	private Class<? extends EstrategiaAutorizacao> getClasseEstrategiaDevolucao(
			TipoAutorizacaoEntidadeEnum tipoAutorizacao) {

		return EstrategiaAutorizacaoDevolver.class;
	}

	/**
	 * Gets the classe estrategia correcao.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return the classe estrategia correcao
	 */
	private Class<? extends EstrategiaAutorizacao> getClasseEstrategiaCorrecao(
			TipoAutorizacaoEntidadeEnum tipoAutorizacao) {

		return EstrategiaAutorizacaoCorrigir.class;
	}
}
