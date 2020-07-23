package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoMensagemDAO;
import br.com.sicoob.capes.negocio.entidades.TipoMensagem;

/**
 * A Classe TipoMensagemDAOImpl.
 */
public class TipoMensagemDAOImpl extends CAPESCadastroCrudDao<TipoMensagem> implements TipoMensagemDAO {

	/** A constante CONSULTA_TIPO_DE_MENSAGEM_POR_DESTINO_EXIBICAO. */
	private static final String CONSULTA_TIPO_DE_MENSAGEM_POR_DESTINO_EXIBICAO = "CONSULTA_TIPO_DE_MENSAGEM_POR_DESTINO_EXIBICAO";

	/**
	 * Instancia um novo TipoMensagemDAOImpl.
	 */
	public TipoMensagemDAOImpl() {
		super(TipoMensagem.class, "CONSULTA_TIPO_MENSAGEM");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoMensagem> listaDeTipoMensagensDoTipoDestinoExibicao(Short codTipoDestinoExibicao) {
		Comando comando = getComando(CONSULTA_TIPO_DE_MENSAGEM_POR_DESTINO_EXIBICAO);
		comando.adicionarVariavel("codTipoDestinoExibicao", codTipoDestinoExibicao);
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			return query.getResultList();
		} catch (NoResultException e) {
			throw new ClienteNaoEncontradoException(e);
		} finally {
			fecharComando(comando);
		}
	}

}