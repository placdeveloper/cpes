/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExclusaoDestinoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.MapaTipoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Dao para os mapas de tipos de anotação.
 * 
 */
public class MapaTipoAnotacaoDAOImpl extends CAPESCadastroCrudDao<MapaTipoAnotacao> implements MapaTipoAnotacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_MAPA_TIPO_ANOTACAO";
	
	/** A constante OBTER_MAPA_ANOTACAO_EXTERNA. */
	private static final String OBTER_MAPA_ANOTACAO_EXTERNA = "OBTER_MAPA_ANOTACAO_EXTERNA";
	
	/** A constante OBTER_MAPAS_ANOTACAO_POR_TIPO. */
	private static final String OBTER_MAPAS_ANOTACAO_POR_TIPO = "OBTER_MAPAS_ANOTACAO_POR_TIPO";
	
	/**
	 * Construtor do DAO.
	 */
	public MapaTipoAnotacaoDAOImpl() {
		super(MapaTipoAnotacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public MapaTipoAnotacao obterTipoAnotacaoAnotacaoExterna(MapaTipoAnotacao mapaTipoAnotacao) throws BancoobException {
		
		MapaTipoAnotacao resultado = null;
		String codigoTipoOrigemInformacao = mapaTipoAnotacao.getCodigoTipoOrigemInformacao();
		String nomeTipoConsultaOrigem = mapaTipoAnotacao.getTipoConsultaOrigem().getNomeTipoConsultaOrigem();
		
		Comando comando = getComando(OBTER_MAPA_ANOTACAO_EXTERNA);
		comando.adicionarVariavel("codigoTipoOrigemInformacao", codigoTipoOrigemInformacao);
		comando.adicionarVariavel("nomeTipoConsultaOrigem", nomeTipoConsultaOrigem);
		comando.configurar();

		try {
			Query query = getEntityManager().createQuery(comando.getProjecao() + " " + comando.getSqlEmUso());
			query.setParameter("parametro0", codigoTipoOrigemInformacao);
			query.setParameter("parametro1", nomeTipoConsultaOrigem);
			resultado = (MapaTipoAnotacao) query.getSingleResult();
		} catch (NoResultException e) {
			getLogger().erro(e, "Mapeamento inexistente: codigoTipoOrigemInformacao(" 
					+ codigoTipoOrigemInformacao + "), nomeTipoConsultaOrigem(" + nomeTipoConsultaOrigem + ")");
		} finally {
			fecharComando(comando);
		}

		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<MapaTipoAnotacao> obterMapasTipoAnotacaoPorTipoAnotacao(TipoAnotacao tipoAnotacao) throws BancoobException {
		List<MapaTipoAnotacao> resultado = null;

		Comando comando = getComando(OBTER_MAPAS_ANOTACAO_POR_TIPO);
		comando.adicionarVariavel("idTipoAnotacao", tipoAnotacao.getId());
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			resultado = query.getResultList();
		} finally {
			fecharComando(comando);
		}

		return resultado;
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

}
