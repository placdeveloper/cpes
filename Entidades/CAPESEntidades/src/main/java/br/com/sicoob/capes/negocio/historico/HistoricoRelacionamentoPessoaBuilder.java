/*
 * SICOOB
 * 
 * HistoricoRelacionamentoPessoaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoRelacionamentoPessoaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import java.util.HashSet;

import org.apache.commons.lang.BooleanUtils;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoRegistroRelacionamento;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Gerador das entidades de histórico para {@link RelacionamentoPessoa}
 * 
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
public class HistoricoRelacionamentoPessoaBuilder extends
		HistoricoBuilder<HistoricoRelacionamentoPessoa, RelacionamentoPessoa> {

	/** O atributo entidade vigente. */
	private RelacionamentoPessoa entidadeVigente;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public HistoricoRelacionamentoPessoa criarHistorico(RelacionamentoPessoa entidadeVigente) {
		
		this.entidadeVigente = entidadeVigente;
		return preencherDadosHistorico(novaInstanciaHistorico(), entidadeVigente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoRelacionamentoPessoa novaInstanciaHistorico() {
		
		HistoricoRelacionamentoPessoa historico = new HistoricoRelacionamentoPessoa();
		if (BooleanUtils.toBoolean(this.entidadeVigente.getTipoRelacionamento()
				.getHabilitaDadosRegistro())) {
			historico = new HistoricoRegistroRelacionamento();
		}
		return historico;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoRelacionamentoPessoa preencherDadosHistorico(
			HistoricoRelacionamentoPessoa historico, RelacionamentoPessoa entidadeVigente) {
		
		ReflexaoUtils.copiarPropriedades(historico, entidadeVigente, "id", "poderes");
		historico.setPoderes(new HashSet<TipoPoderRelacionamento>(entidadeVigente.getPoderes()));
		historico.setIdRelacionamento(entidadeVigente.getId());
		return historico;
	}

}
