/*
 * SICOOB
 * 
 * ValidacaoCadastralListener.java(br.com.sicoob.capes.negocio.entidades.listener.ValidacaoCadastralListener)
 */
package br.com.sicoob.capes.negocio.entidades.listener;

import org.hibernate.Session;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;

/**
 * Listener de ValidacaoCadastral para atualizacao da data da ultima atualizacao
 */
public class ValidacaoCadastralListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	private static final String ID_PESSOA_INSTITUICAO = "idPessoaInstituicao";
	private static final String ID_INSTITUICAO_ATUALIZACAO = "idInstituicaoAtualizacao";
	private static final String DATA_HORA_INICIO = "dataHoraInicio";
	private static final String ID_USUARIO_ENVIO = "idUsuarioEnvio";
	private static final String ID_USUARIO_APROV = "idUsuarioAprovacao";
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8049282423811196130L;

	/**
	 * Atualiza a data da ultima atualizacao cadastral da pessoa, quando ha uma
	 * alteracao em uma entidade que implemente {@code CadastroValidavel}
	 * 
	 * @param session
	 *            a sessao de BD atual
	 * @param entity
	 *            a entidade que esta sendo manipulada
	 * @see CadastroValidavel
	 */
	private void atualizarValidacaoCadastral(Session session, Object entity, Boolean isDelete) {
		 if (entity instanceof CadastroValidavel) {
			CadastroValidavel validavel = (CadastroValidavel) entity;
			
			 if(validavel instanceof Bem) {
				Bem bem = (Bem) validavel;
				
				for(BemPessoaCompartilhamento bpc : bem.getProprietarios()) {
					atualizarValidacaoCadastral(session, bpc.getIdPessoaCompartilhamento(), entity, isDelete);
				}
			} else {
				Long idPessoaCompartilhamento = validavel.getPessoaCompartilhamento().getId();
				atualizarValidacaoCadastral(session, idPessoaCompartilhamento, entity, isDelete);
			}
		}
	}

	private void atualizarValidacaoCadastral(Session session, Long idPessoaCompartilhamento, Object entidade, Boolean isDelete) {
		ValidacaoCadastral validacao = session.get(ValidacaoCadastral.class, idPessoaCompartilhamento);

		if (validacao == null) {
			validacao = new ValidacaoCadastral(idPessoaCompartilhamento);
		}
		
		if(isVigente(entidade)){
			if(ReflexaoUtils.objetoPossuiAtributo(entidade, ID_USUARIO_APROV)){
				if(ReflexaoUtils.getValorAtributo(entidade, ID_USUARIO_APROV) != null && !isDelete){
					setUsuariosValidacaoCadastral(entidade, validacao);
				} else if(ReflexaoUtils.getValorAtributo(entidade, DATA_HORA_INICIO) != null){
					setUsuariosValidacaoCadastral(entidade, validacao);
				}
			}
			
			validacao.setDataHoraUltimaAtualizacao(new DateTimeDB());
			session.saveOrUpdate(validacao);
			session.flush();
		}
	}

	/**
	 * Verifica se o registro e Vigente.
	 * @param entidade
	 * @return
	 */
	private boolean isVigente(Object entidade) {
		Boolean retorno;
		if(ReflexaoUtils.objetoPossuiAtributo(entidade, ID_INSTITUICAO_ATUALIZACAO)){
			retorno = ReflexaoUtils.getValorAtributo(entidade, DATA_HORA_INICIO) != null && ReflexaoUtils.getValorAtributo(entidade, ID_INSTITUICAO_ATUALIZACAO) == null;
		}else{
			if(ReflexaoUtils.objetoPossuiAtributo(entidade, DATA_HORA_INICIO)){
				retorno = ReflexaoUtils.getValorAtributo(entidade, DATA_HORA_INICIO) != null;
			}else{
				retorno = Boolean.FALSE;
			}
		}
		
		return retorno;
	}

	/**
	 * Seta o idUsuarioEnvio e o idUsuarioAprovacao.
	 * @param entidade
	 * @param validacao
	 */
	private void setUsuariosValidacaoCadastral(Object entidade, ValidacaoCadastral validacao) {
		validacao.setIdUsuarioAprov((String) ReflexaoUtils.getValorAtributo(entidade, ID_USUARIO_APROV));
		if (ReflexaoUtils.objetoPossuiAtributo(entidade, ID_USUARIO_ENVIO)){
			validacao.setIdUsuarioEnvio((String) ReflexaoUtils.getValorAtributo(entidade, ID_USUARIO_ENVIO));
		}else if(ReflexaoUtils.objetoPossuiAtributo(entidade, ID_PESSOA_INSTITUICAO)){
			validacao.setIdUsuarioEnvio((String) ReflexaoUtils.getValorAtributo(entidade, ID_USUARIO_APROV));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void onPostDelete(PostDeleteEvent event) {
		atualizarValidacaoCadastral(event.getPersister().getFactory().getCurrentSession(), event.getEntity(), Boolean.TRUE);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onPostUpdate(PostUpdateEvent event) {
		atualizarValidacaoCadastral(event.getPersister().getFactory().getCurrentSession(), event.getEntity(), Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onPostInsert(PostInsertEvent event) {
		atualizarValidacaoCadastral(event.getPersister().getFactory().getCurrentSession(), event.getEntity(), Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}
	
}