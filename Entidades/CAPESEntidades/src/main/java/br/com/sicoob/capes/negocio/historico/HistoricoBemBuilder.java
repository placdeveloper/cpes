package br.com.sicoob.capes.negocio.historico;

import java.util.HashSet;
import java.util.Set;

import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBem;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemMovelAvaliacao;

/**
 * Builder para o histórico de bens
 * 
 * @author bruno.carneiro
 */
public class HistoricoBemBuilder extends HistoricoBuilder<HistoricoBem, Bem> {

	private Bem entidadeVigente;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HistoricoBem criarHistorico(Bem entidadeVigente) {
		this.entidadeVigente = entidadeVigente;
		return super.criarHistorico(entidadeVigente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBem novaInstanciaHistorico() {
		return instanciarHistorico();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBem preencherDadosHistorico(HistoricoBem historico, Bem entidadeVigente) {
		historico.setId(null);

		if (entidadeVigente instanceof BemImovel) {
			BemImovel bemImovel = (BemImovel) entidadeVigente;

			Set<HistoricoBemImovelTipoRelacionamentoPessoa> lista = preencherDadosRelacionamentoPessoasBemImovel((HistoricoBemImovel) historico, bemImovel);
			((HistoricoBemImovel) historico).setRelacionamentoPessoas(lista);
		}

		if (historico instanceof HistoricoBemMovelAvaliacao) {
			((HistoricoBemMovelAvaliacao) historico).setTiposOnus(null);
			Set<TipoOnusBem> lista = ReflexaoUtils.clonarLista(((BemMovelAvaliacao) entidadeVigente).getTiposOnus(), HashSet.class);
			((HistoricoBemMovelAvaliacao) historico).setTiposOnus(lista);
		} else if (historico instanceof HistoricoBemImovelAvaliacao) {
			((HistoricoBemImovelAvaliacao) historico).setTiposOnus(null);
			Set<TipoOnusBem> lista = ReflexaoUtils.clonarLista(((BemImovelAvaliacao) entidadeVigente).getTiposOnus(), HashSet.class);
			((HistoricoBemImovelAvaliacao) historico).setTiposOnus(lista);
		}

		return historico;
	}

	/**
	 * Cria a instância correta do histórico.
	 * 
	 * @return {@link HistoricoBem} historicoBem
	 */
	private HistoricoBem instanciarHistorico() {
		HistoricoBem historico;
		if (entidadeVigente.getTipoClassificacaoBem() != null) {
			if (TipoClassificacaoBemEnum.isImovel(entidadeVigente.getTipoClassificacaoBem().getCodigo())) {
				if (entidadeVigente instanceof BemImovelAvaliacaoUrbano) {
					historico = new HistoricoBemImovelAvaliacaoUrbano();
				} else if (entidadeVigente instanceof BemImovelAvaliacaoRural) {
					historico = new HistoricoBemImovelAvaliacaoRural();
				} else if (entidadeVigente instanceof BemImovelAvaliacao) {
					historico = new HistoricoBemImovelAvaliacao();
				} else {
					historico = new HistoricoBemImovel();
				}
			} else {
				if (entidadeVigente instanceof BemMovelAvaliacao) {
					historico = new HistoricoBemMovelAvaliacao();
				} else {
					historico = new HistoricoBemMovel();
				}
			}
		} else {
			historico = new HistoricoBem();
		}
		return historico;
	}

	/**
	 * Cria a lista de {@code HistoricoBemImovelTipoRelacionamentoPessoa} a
	 * partir do bemImovel.
	 * 
	 * @param historico
	 * @param bemImovel
	 * @return
	 */
	private Set<HistoricoBemImovelTipoRelacionamentoPessoa> preencherDadosRelacionamentoPessoasBemImovel(HistoricoBemImovel historico, BemImovel bemImovel) {
		Set<HistoricoBemImovelTipoRelacionamentoPessoa> lista = null;
		if (bemImovel.getRelacionamentoPessoas() != null && bemImovel.getRelacionamentoPessoas().size() > 0) {
			lista = new HashSet<HistoricoBemImovelTipoRelacionamentoPessoa>();
			for (BemImovelTipoRelacionamentoPessoa relacionamento : bemImovel.getRelacionamentoPessoas()) {
				lista.add(preencherDadosHistorico(relacionamento, historico));
			}
		}
		return lista;
	}
	
	/**
	 * Cria um novo {@linkplain HistoricoBemImovelTipoRelacionamentoPessoa}
	 * 
	 * @param relacionamento
	 * @param historico
	 * @return
	 */
	private HistoricoBemImovelTipoRelacionamentoPessoa preencherDadosHistorico(BemImovelTipoRelacionamentoPessoa relacionamento, HistoricoBemImovel historico) {
		HistoricoBemImovelTipoRelacionamentoPessoa historicoBemImovelTipoRelacionamentoPessoa = new HistoricoBemImovelTipoRelacionamentoPessoa();

		historicoBemImovelTipoRelacionamentoPessoa.setDataInicioRelacionamento(relacionamento.getDataInicioRelacionamento());
		historicoBemImovelTipoRelacionamentoPessoa.setDataFimRelacionamento(relacionamento.getDataFimRelacionamento());
		historicoBemImovelTipoRelacionamentoPessoa.setHistoricoBemImovel(historico);
		historicoBemImovelTipoRelacionamentoPessoa.setIdBemImovelRelacionamento(relacionamento.getIdBemImovelRelacionamento());
		historicoBemImovelTipoRelacionamentoPessoa.setAreaPosse(relacionamento.getAreaPosse());
		historicoBemImovelTipoRelacionamentoPessoa.setPessoaCompartilhamento(relacionamento.getPessoaCompartilhamento());
		historicoBemImovelTipoRelacionamentoPessoa.setTipoRelacionamento(relacionamento.getTipoRelacionamento());

		return historicoBemImovelTipoRelacionamentoPessoa;
	}
}