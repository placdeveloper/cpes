/*
 * SICOOB
 * 
 * FabricaAtualizacaoCadastralFachada.java(br.com.sicoob.capes.negocio.dominio.replicacao.facade.FabricaAtualizacaoCadastralFachada)
 */
package br.com.sicoob.capes.negocio.dominio.replicacao.facade;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * @author Erico.Junior
 * 
 */
public final class FabricaAtualizacaoCadastralFachada<R extends Replicavel> {

	/** Instancia do Fabrica. */
	private static FabricaAtualizacaoCadastralFachada<Replicavel> fabrica;

	/**
	 * Construtor privado para garantir a instância única.
	 */
	private FabricaAtualizacaoCadastralFachada() {
	}

	/**
	 * Retorna a fabrica a ser utilizada.
	 * 
	 * @return a fabrica a ser utilizada.
	 */
	public static FabricaAtualizacaoCadastralFachada<Replicavel> getInstance() {
		if (fabrica == null) {
			synchronized (FabricaAtualizacaoCadastralFachada.class) {
				if (fabrica == null) {
					fabrica = new FabricaAtualizacaoCadastralFachada<Replicavel>();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Recupera o Builder para o histórico da entidade vigente.
	 * @param vigente A entidade vigente.
	 * @return o Builder para o histórico da entidade vigente.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IAtualizacaoCadastralFachada obterFachada(R replicavel) {
		IAtualizacaoCadastralFachada<? extends Replicavel> fachada = null;
		if (replicavel instanceof Anotacao) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoAnotacaoFachada");
		} else if (replicavel instanceof EnderecoCorrespondencia) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoEnderecoCorrespondenciaFachada");
		} else if (replicavel instanceof HistoricoAlteracaoCpfCnpj) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoCpfCnpjFachada");
		} else if (replicavel instanceof GrupoEconomico) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.GrupoEconomicoFachada");
		} else if (replicavel instanceof GrupoEconomicoPessoaBase) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.GrupoEconomicoPessoaFachada");
		} else if (replicavel instanceof Mensagem) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoMensagemFachada");
		} else if (replicavel instanceof RelacionamentoPessoa) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.RelacionamentoPessoaFachada");
		} else if (replicavel instanceof Email) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoEmailFachada");
		} else if (replicavel instanceof PessoaCompartilhamento) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoPessoaCompartilhamentoFachada");
		} else if (isInstanciaBemListas(replicavel)) {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoBemAntigoFachada");
		} else {
			fachada = ReflexaoUtils.construirObjetoPorClasse(IAtualizacaoCadastralFachada.class,
					"br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoCadastralFachada");
		}
		return fachada;
	}

	/**
	 * Verifica se é instancia bem listas.
	 * 
	 * @param replicavel
	 *            the replicavel
	 * @return true, se for instancia bem listas
	 */
	private boolean isInstanciaBemListas(R replicavel) {
		return (replicavel instanceof Bem
				|| replicavel instanceof BemImovel
				|| replicavel instanceof BemOnus
				|| replicavel instanceof BemRegistro
				|| replicavel instanceof BemPosse);
	}
}