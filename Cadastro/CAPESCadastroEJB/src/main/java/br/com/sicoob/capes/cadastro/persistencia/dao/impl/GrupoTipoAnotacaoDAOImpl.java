package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExclusaoDestinoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoTipoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Classe com as operações de grupo de tipo anotação
 * 
 * @author Bruno.Carneiro
 */
public class GrupoTipoAnotacaoDAOImpl extends CAPESCadastroCrudDao<GrupoTipoAnotacao> implements GrupoTipoAnotacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_GRUPOS_TIPO_ANOTACAO";

	/**
	 * Instancia um novo DestinoExportacaoDAOImpl.
	 */
	public GrupoTipoAnotacaoDAOImpl() {
		super(GrupoTipoAnotacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		try {
			super.excluir(chave);
			getEntityManager().flush();
		} catch (ConstraintViolationException e) {
			throw new ExclusaoDestinoException(e);
		} catch (EntityExistsException e) {
			throw new ExclusaoDestinoException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoTipoAnotacao> obterGruposPorTipoAnotacao(Integer idTipoAnotacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_GRUPOS_TIPO_ANOTACAO_POR_TIPO_ANOTACAO");
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoTipoAnotacao> obterGruposPorTiposAnotacao(List<TipoAnotacao> tiposAnotacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_GRUPOS_TIPO_ANOTACAO_POR_TIPOS_ANOTACAO");
			comando.adicionarVariavel("tiposAnotacao", tiposAnotacao);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

}