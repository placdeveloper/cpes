/*
 * SICOOB
 * 
 * HistoricoListener.java(br.com.sicoob.capes.negocio.entidades.listener.HistoricoListener)
 */
package br.com.sicoob.capes.negocio.entidades.listener;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.MotivoExclusao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.historico.FabricaHistoricoBuilder;
import br.com.sicoob.capes.negocio.historico.HistoricoBuilder;

/**
 * Listener para gravar os históricos das entidades.
 *
 * @author Erico.Junior
 */
public class HistoricoListener<H extends Historico, V extends Vigente>
		implements PreUpdateEventListener, PreDeleteEventListener {

	/** Serial UID. */
	private static final long serialVersionUID = -1487316566658352033L;

	/** 
	 * {@inheritDoc}
	 */
	public boolean onPreUpdate(PreUpdateEvent evento) {
		gravarHistorico(evento.getPersister(), evento.getEntity(), evento.getId(), false);
		return false;
	}

	/** 
	 * {@inheritDoc}
	 */
	public boolean onPreDelete(PreDeleteEvent evento) {
		gravarHistorico(evento.getPersister(), evento.getEntity(), evento.getId(), true);
		return false;
	}

	/**
	 * Grava o histórico para a versão anterior do registro se a entidade for
	 * uma EntidadeVigente.
	 *
	 * @param persister
	 *            O EntityPersister utilizado na sessão.
	 * @param entidade
	 *            A entidade atual.
	 */
	@SuppressWarnings("unchecked")
	private void gravarHistorico(EntityPersister persister, Object entidade, Serializable id, boolean isExclusao) {

		if (entidade instanceof Vigente) {

			Session session = persister.getFactory().getCurrentSession();
			Vigente entidadeNova = (Vigente) entidade;
			boolean gravarHistorico = entidadeNova.getGravarHistorico();

			if (entidadeNova instanceof Aprovavel) {
				Aprovavel aprovavel = (Aprovavel) entidadeNova;
				gravarHistorico = gravarHistorico && (aprovavel.getIdInstituicaoAtualizacao() == null);
			}
			
			if(entidadeNova instanceof Ibem){
				Ibem iBem = (Ibem)entidade;
				br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemPai = iBem.getBem();
				gravarHistorico = bemPai.getGravarHistorico() && (bemPai.getIdInstituicaoAtualizacao() == null);
			}
			
			if(entidadeNova instanceof BemPessoaCompartilhamento) {
				BemPessoaCompartilhamento bpc = (BemPessoaCompartilhamento) entidade;
				Bem bem = session.get(Bem.class, bpc.getIdBem());
				gravarHistorico = bem.getGravarHistorico() && (bem.getIdInstituicaoAtualizacao() == null);
			}

			if (gravarHistorico) {
				V entidadeAnterior = (V) session.get(entidadeNova.getClass(), id);
				
				final Historico historico = obterHistorico(entidadeAnterior);
				if(entidadeNova instanceof MotivoExclusao) {
					((MotivoExclusao) historico).setMotivoExclusao(((MotivoExclusao) entidadeNova).getMotivoExclusao());
				}

				if (isExclusao) {
					if (!(entidadeNova instanceof GrupoEconomicoNovo || entidadeNova instanceof GrupoEconomicoAutomaticoPessoa || entidadeNova instanceof GrupoEconomicoManualPessoa) 
							&& InformacoesUsuarioCAPES.getInstance() != null) {
						historico.setIdUsuarioExclusao(InformacoesUsuarioCAPES.getInstance().getLogin());
					} else if (ReflexaoUtils.objetoPossuiAtributo(entidadeNova, "idUsuarioExclusao")) {
						historico.setIdUsuarioExclusao((String) ReflexaoUtils.getValorAtributo(entidadeNova, "idUsuarioExclusao"));
					}
				}

				if (historico != null) {
					session.persist(historico);
					session.flush();
				}
			}
		}
	}

	/**
	 * Recupera o histórico que será gravado para a entidade vigente informada.
	 *
	 * @param entidade
	 *            A entidade vigente para histórico.
	 * @return o histórico que será gravado para a entidade vigente informada.
	 */
	@SuppressWarnings("unchecked")
	private Historico obterHistorico(V entidade) {

		Historico historico = null;
		FabricaHistoricoBuilder<Historico, Vigente> fabrica = FabricaHistoricoBuilder.getInstance();
		HistoricoBuilder<H, V> builder = (HistoricoBuilder<H, V>) fabrica.obterBuilder(entidade);

		if (builder != null) {
			historico = builder.criarHistorico(entidade);
		}

		return historico;
	}
}
