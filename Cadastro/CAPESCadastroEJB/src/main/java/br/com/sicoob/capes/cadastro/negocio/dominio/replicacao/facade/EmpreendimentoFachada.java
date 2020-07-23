package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.legado.Empreendimento;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.EmpreendimentoDelegate;

/**
 * A Classe EmpreendimentoFachada.
 */
public class EmpreendimentoFachada {

	private transient EmpreendimentoDelegate empreendimentoDelegate = CAPESReplicacaoComumFabricaDelegates
			.getInstance().criarEmpreendimentoDelegate();

	/**
	 * 
	 * @param empreendimento
	 * @throws BancoobException
	 */
	public void replicarEmpreendimento(
			br.com.sicoob.capes.negocio.entidades.Empreendimento empreendimento)
			throws BancoobException {
		Empreendimento empreendimentoAlteracao = empreendimentoDelegate
				.obterProdLab(empreendimento.getCodigo(), Constantes.Comum.ID_COOPERATIVA_PRODLAB);
		Empreendimento empreendimentoReplicacao = criarEmpreendimentoReplicacao(
				empreendimento, empreendimentoAlteracao);

		if (empreendimentoAlteracao == null) {
			empreendimentoDelegate.incluirProdLab(empreendimentoReplicacao, Constantes.Comum.ID_COOPERATIVA_PRODLAB);
		} else { 
			empreendimentoDelegate.alterarProdLab(empreendimentoReplicacao, Constantes.Comum.ID_COOPERATIVA_PRODLAB); 
		}
	} 

	/**
	 * 
	 * @param empreendimento
	 * @param empreendimentoAlteracao
	 * @return
	 */
	private Empreendimento criarEmpreendimentoReplicacao(
			br.com.sicoob.capes.negocio.entidades.Empreendimento empreendimento,
			Empreendimento empreendimentoAlteracao) {
		Empreendimento empreendimentoReplicacao = empreendimentoAlteracao;
		if (empreendimentoAlteracao == null) {
			empreendimentoReplicacao = new Empreendimento();
		} 
		empreendimentoReplicacao
				.setIdEmpreendimento(empreendimento.getCodigo());
		empreendimentoReplicacao.setDescricao(empreendimento.getDescricao());
		empreendimentoReplicacao.setExigeArea(empreendimento.getExigeArea());
		empreendimentoReplicacao
				.setExigeImovel(empreendimento.getExigeImovel());
		empreendimentoReplicacao.setUnidadeArea(empreendimento.getUnidadeArea()
				.getCodigo());
		empreendimentoReplicacao.setUnidadePrevisao(empreendimento
				.getUnidadePrevisao().getCodigo());
		return empreendimentoReplicacao;
	}
}
